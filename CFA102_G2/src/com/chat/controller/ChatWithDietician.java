package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import oracle.net.aso.s;

import com.chat.jedis.JedisHandleMessage;
import com.chat.model.ChatMessage;
import com.chat.model.State;
import com.dietician.model.DieticianService;

@ServerEndpoint("/ChatWithDietician/{userName}")
public class ChatWithDietician {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();// java5的時候加入, concurrent並行

	// hashtable跟HashMap結構差不多, hashtable有實作synchronized, 但使用的是老方法, 同步較大的範圍
	// 現在使用比較新的做法ConcurrentHashMap,同步較小的範圍，執行效率提高
	// 訊息用session存
	// 用map存 使用者-訊息, 只有特定的人可以取, 記得使用這不能重複

	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		
		sessionsMap.put(userName, userSession);
		Set<String> userNames = sessionsMap.keySet();
		
		//System.out.println("有");
		
		MemberService memberSvc = new MemberService();
		DieticianService dieticianSvc = new DieticianService();
		
		if( userSession.getQueryString().equals("dieticianSide")) {
			
			//營養師的客戶們
			Set<MemberVO> memberSet = (Set<MemberVO>) memberSvc.getDnoNotNull().stream()
									 	.filter(m -> dieticianSvc.findByPrimaryKey(m.getDno()).getDaccount().equals(userName))
									 	.collect(Collectors.toSet());
			//所有客戶名字
			Set<String> memberNames = new HashSet<String>();
			
			for(MemberVO member : memberSet) {
				memberNames.add(member.getMid());
			}

			State stateMessage = new State("open", userName, memberNames);
	
			String stateMessageJson = gson.toJson(stateMessage);
		
			Collection<Session> memberSessions = new HashSet(); 
					
			for(String key : memberNames) {
				memberSessions.add(sessionsMap.get(key));
			}		
					
			for (Session session : memberSessions) {
				if (session != null) {
					session.getAsyncRemote().sendText(stateMessageJson);
					
				}
			}
			
			sessionsMap.get(userName).getAsyncRemote().sendText(stateMessageJson);
			
		} else {
			
			if((memberSvc.getOneMemberByMid(userName).getDno()) != 0) {
			
				String daccount = dieticianSvc.findByPrimaryKey(memberSvc.getOneMemberByMid(userName).getDno()).getDaccount();
				State stateMessage = new State("open", userName, daccount);
				String stateMessageJson = gson.toJson(stateMessage);
				Session receiverSession = sessionsMap.get(daccount);
				
				if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(stateMessageJson);
				sessionsMap.get(userName).getAsyncRemote().sendText(stateMessageJson);
				}
			}
		}
			

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) { // message是前端sendMessage()丟過來的json字串
		
	
		
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class); // 給你一個字串, 你自己去看那個說明書class做處理
		String sender = chatMessage.getSender(); // 有註明上面的class就可以直接用getter
		String receiver = chatMessage.getReceiver();
		String time = chatMessage.getTime();

		if ("history".equals(chatMessage.getType())) { // 去對應chatMessage.java的變數名稱, 對面的addListener送過來的
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			// 存在redis的訊息, 每一個都是json
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, time); // ChatMessage的constructor
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory)); // 這行會觸發js的onMessage
//				System.out.println("history = " + gson.toJson(cmHistory));

				return; // 取得歷史訊息後跳出
			}
		}

		if ("chat".equals(chatMessage.getType())) {
			Session receiverSession = sessionsMap.get(receiver); // 一般聊天, 取得對方的session, 同時丟給對方

			if (receiverSession != null && receiverSession.isOpen()) {

				receiverSession.getAsyncRemote().sendText(message); // 對雙方都發出訊息
			}

			userSession.getAsyncRemote().sendText(message); // 對雙方都發出訊息

			JedisHandleMessage.saveChatMessage(sender, receiver, message); // 存入redis

			return;
		}

		if ("check".equals(chatMessage.getType())) { // 去對應chatMessage.java的變數名稱, 對面的addListener送過來的
			JedisHandleMessage.checkMessage(sender, receiver);
			
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			// 存在redis的訊息, 每一個都是json
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, time); // ChatMessage的constructor
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory)); // 這行會觸發js的onMessage
//				System.out.println("history = " + gson.toJson(cmHistory));

			}
			
			
			
			return;
		}

		if ("showUnreadCount".equals(chatMessage.getType())) { // 去對應chatMessage.java的變數名稱, 對面的addListener送過來的

			String unreadCount = JedisHandleMessage.checkUnreadNumber(receiver, sender);

			ChatMessage unreadCountMessage = new ChatMessage("showUnreadCount", sender, receiver, unreadCount); // ChatMessage的constructor
			 userSession.getAsyncRemote().sendText(gson.toJson(unreadCountMessage));
			
			//System.out.println(unreadCount);
			
			return;
		}

		if ("clientListInfo".equals(chatMessage.getType())) { // 去對應chatMessage.java的變數名稱, 對面的addListener送過來的

			MemberService memberSvc = new MemberService();
			DieticianService dieticianSvc = new DieticianService();

			Set<MemberVO> memberSet = (Set<MemberVO>) memberSvc.getDnoNotNull().stream()
									 	.filter(m -> dieticianSvc.findByPrimaryKey(m.getDno()).getDaccount().equals(sender))
									 	.collect(Collectors.toSet());

			Set<String> memberNames = new HashSet<String>();
			
			for(MemberVO member : memberSet) {
				memberNames.add(member.getMid());
			}
					
			
			List<String> infoList= new ArrayList<String>();
		
			for (String member : memberNames) {
				Session clientSession = sessionsMap.get(member);
				ChatMessage clientState = gson.fromJson(JedisHandleMessage.getClientListInfo(sender, member), ChatMessage.class);
				
				if(clientSession!= null && clientSession.isOpen()) {
					clientState.setIsOnline("1");
				} else {
					clientState.setIsOnline("0");
				}
				
				
				infoList.add(gson.toJson(clientState));
			
			}
			
			
			
			
			
			String jsonInfoList = gson.toJson(infoList);		
			
			ChatMessage clientListInfo = new ChatMessage(); // ChatMessage的constructor
			
			clientListInfo.setType("clientListInfo");
			clientListInfo.setSender(sender);
			clientListInfo.setMessage(jsonInfoList);		
			
			userSession.getAsyncRemote().sendText(gson.toJson(clientListInfo));
			 
			return;
		}
	
	}

	@OnError
	public void onError(Session userSession, Throwable e) { // 直接貼
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(@PathParam("userName") String userName, Session userSession, CloseReason reason) {
		
		
		MemberService memberSvc = new MemberService();
		DieticianService dieticianSvc = new DieticianService();
		
		if( userSession.getQueryString().equals("dieticianSide")) {
			
			//營養師的客戶們
			Set<MemberVO> memberSet = (Set<MemberVO>) memberSvc.getDnoNotNull().stream()
									 	.filter(m -> dieticianSvc.findByPrimaryKey(m.getDno()).getDaccount().equals(userName))
									 	.collect(Collectors.toSet());
			//所有客戶名字
			Set<String> memberNames = new HashSet<String>();
			
			for(MemberVO member : memberSet) {
				memberNames.add(member.getMid());
			}

			State stateMessage = new State("close", userName, memberNames); // State裡面放open跟close兩種type
			String stateMessageJson = gson.toJson(stateMessage);
			
			Collection<Session> memberSessions = new HashSet(); 
					
			for(String key : memberNames) {
				memberSessions.add(sessionsMap.get(key));
			}		
					
			for (Session session : memberSessions) {
				if (session != null && session.isOpen()) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}
		} else {
			
			
			
			if((memberSvc.getOneMemberByMid(userName).getDno()) != 0) {
				String daccount = dieticianSvc.findByPrimaryKey(memberSvc.getOneMemberByMid(userName).getDno()).getDaccount();
				State stateMessage = new State("close", userName, daccount);
				String stateMessageJson = gson.toJson(stateMessage);
				Session receiverSession = sessionsMap.get(daccount);
				
				if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(stateMessageJson);
				}
			}
		
		
		}
		
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		
		for (String user : userNames) { // 迴圈從所有session找出自己, 然後移除
			if (sessionsMap.get(user).equals(userSession)) {
				userNameClose = user;
				sessionsMap.remove(user);
				break; // 移除掉自己的就可以中斷了
			}
		}

		
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		//System.out.println(text);
	}
}
