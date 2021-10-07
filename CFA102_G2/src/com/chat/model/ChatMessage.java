package com.chat.model;

public class ChatMessage {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String time;
	private String readState;
	private String unreadCount;
	private String isOnline;
	
	//可以增加時間的欄位, 時間建議private String time;
	//因為Json轉日期很麻煩, 直接用string過去就好了
	//這邊可以增加已讀未讀
	//對應訊息的VO
	
	public ChatMessage(String type, String sender, String receiver, String message, String time, String unreadCount) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
		
	}	

	
	public ChatMessage(String type, String sender, String receiver, String message, String time) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
	}	
	
	public ChatMessage(String type, String sender, String receiver) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	
	public ChatMessage(String type, String sender, String receiver, String unreadCount) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.unreadCount = unreadCount;
	}
	
	public ChatMessage() {
		super();
	}
	
	public String getSender() {
		return sender;
	}



	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public String getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(String unreadCount) {
		this.unreadCount = unreadCount;
	}


	public String getIsOnline() {
		return isOnline;
	}


	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	
	
	
	
	
	
	
}
