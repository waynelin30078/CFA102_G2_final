package com.apt_order.controller;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

import com.apt_order.model.Apt_orderService;
import com.apt_order.model.Apt_orderVO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.appointment.*;
import com.appointment.model.AppointmentService;
import com.appointment.model.AppointmentVO;
import com.member.model.*;

import com.google.gson.*;


public class Apt_orderServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		  req.setCharacterEncoding("UTF-8");
		  res.setContentType("text/html;charset=UTF-8");
		  String action = req.getParameter("action");
		  Integer dno=new Integer(req.getParameter("dno"));
		  if ("showOrder".equals(action)) {
		   System.out.println("進到月曆");
		   PrintWriter out=res.getWriter();
		   List<Map<String, String>> jsonList = new ArrayList<>();
		   Apt_orderService apt_orderSvc=new Apt_orderService();
		   List<Apt_orderVO> allOrderForOne = apt_orderSvc.getAll()
		            .stream()
		            .filter(f ->f.getDno().equals(dno))
		            .filter(f ->f.getClState().equals(1))
		            .collect(Collectors.toList());
		   
		   System.out.println("1");
		   System.out.println(allOrderForOne.isEmpty());
		   for(Apt_orderVO thisOrder : allOrderForOne) {
		    
		    Integer mno=thisOrder.getMno();
		    
		    String whenOrder=thisOrder.getOrderTime().toString();
		    System.out.println("mno會員編號"+mno);
		    MemberService memberSvc =  new MemberService();
		    MemberVO memberVO=memberSvc.getOneMember(mno);
		    String mname=memberVO.getMname();
		    
		    System.out.println("whenOrder訂單時間"+whenOrder);
		    StringBuffer buff= new StringBuffer(whenOrder);
		    buff.setCharAt(10,'T');
		    buff.delete(19, 21);
		    
		    String changed=buff.toString();
		    System.out.println("更改過後"+"="+changed+"=");
		    
		    Map<String, String> jsonMap=new HashMap<String, String>();
		    jsonMap.put("title",mname);
		    jsonMap.put("start",changed);
		    jsonList.add(jsonMap);
		   }
		   
		//  for(Apt_orderVO thisOrder : allOrderForOne) {
		//   String mno=thisOrder.getMno().toString();
		//   String whenOrder=thisOrder.getOrderTime().toString();
		//   String title=null;
		//   String start = null;
		//   System.out.println(whenOrder);
		//   Map<String, String> jsonMap=new HashMap();
		//   jsonMap.put("title",mno);
		//   jsonMap.put("start",whenOrder);
		//   jsonList.add(jsonMap);
		//  }
		  Gson gson = new Gson();
		  String jsonString = gson.toJson(jsonList);
		  out.write(jsonString);
		  out.close();
		  }
		  doPost(req, res);
		  
		 }
		
		
		
	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if ("listApt_orderbymno".equals(action)) {
			
			
		}
		if ("cancel_apt_order".equals(action)) {
			System.out.println("進入到取消預約");
			Integer clstate=new Integer(req.getParameter("clState")); //取得取消的值0
			String orderTime=req.getParameter("orderTime"); //取得預約的時間段 TimeStamp
			Integer mno=new Integer(req.getParameter("mno"));
			Integer dno=new Integer(req.getParameter("dno"));
			Integer aptorderno=new Integer(req.getParameter("aptOrderNo"));
			String orderDate=(req.getParameter("orderDate"));//TimeStamp
			Integer clprice=new Integer(req.getParameter("clPrice"));

			String aptreviews=req.getParameter("aptReviews");
			
			
	        String dateTime = "2020-12-12 01:24:23";

	        
	        
	        Timestamp orderTime1 = Timestamp.valueOf(orderTime);
	        Timestamp orderDate1 = Timestamp.valueOf(orderDate);
			
			
			
			
			System.out.println(orderTime);

			Apt_orderService apt_orderSvc=new Apt_orderService();
			//修改訂單狀態為0
			apt_orderSvc.updateApt_order(mno, dno, orderTime1, orderDate1, clprice, clstate, aptreviews, aptorderno);
			
			//諮詢日期的部分
			String[] dateSplit=orderTime.split(" ");
			for (String token:dateSplit) {
				System.out.println(token);
			}
			System.out.println(dateSplit[0]);//諮詢日期String
			String rdate=dateSplit[0];
			
			
			
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date rdated = df.parse(rdate);//諮詢日期util
				java.sql.Date sql_rdated = new java.sql.Date(rdated.getTime());//sql.date
				System.out.println("rdated="+rdated);
				String[] timeSplit=dateSplit[1].split(":");
				for(String text:timeSplit) {
					System.out.println(text);
				}
				String rstate_num =timeSplit[0];//取得諮詢時間段14
				
				int int_rstate_num=Integer.parseInt(rstate_num);
			
				AppointmentService appointmentSvc=new AppointmentService();
				AppointmentVO appointmentVO= new AppointmentVO();
					
				System.out.println("dno="+dno);
				System.out.println("rdated="+rdated);
				System.out.println("sqldate="+sql_rdated);
				Optional<AppointmentVO> result;
				result=appointmentSvc.getAll()
											.stream()
											.filter(f -> f.getDno().equals(dno))
											.filter(f -> f.getRdate().equals(sql_rdated))
											.findFirst();
				AppointmentVO appointmentVO1=result.get();
				Integer aptno=new Integer(appointmentVO1.getAptNo());
				String cancelTime=appointmentVO1.getRstate();
				System.out.println("要被取消的時間"+rstate_num);
				System.out.println("準備被恢復的時間字串"+cancelTime);
				StringBuffer sb = new StringBuffer(cancelTime);
				sb.setCharAt(int_rstate_num, '0');  //將特定的時間單位改為0
				System.out.println(result.isPresent());//true為有到資料
				String rstated=new String(sb); //已更改好
				AppointmentVO appointmentVO2=appointmentSvc.updateAppointment(dno, sql_rdated, rstated, aptno);
				
				req.setAttribute("appointmentVO2", appointmentVO2);
				
				String url = "/front_end/protected/appointment/search_appointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
}}
