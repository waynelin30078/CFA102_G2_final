package com.appointment.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import com.apt_order.model.*;
import com.dietician.model.*;
import com.appointment.model.*;
public class AppointmentServlet extends HttpServlet{
	

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
		if ("insert".equals(action)) {
			System.out.println("進到insert預約ˋ");
			AppointmentService appointmentSvc = new AppointmentService();
			AppointmentVO appointmentVO = new AppointmentVO();
			
			Integer dno =new Integer(req.getParameter("dno"));
			
			java.sql.Date rdate = null;
			rdate = java.sql.Date.valueOf(req.getParameter("rdate").trim());
			
			String rstate=req.getParameter("rstate");
			appointmentSvc.addAppointment(dno, rdate, rstate);
			
		}
	
		
		if ("getone_appointment".equals(action)) {
			Integer dno = new Integer(req.getParameter("dno")); //那個營養師
			AppointmentService appointmentSvc = new AppointmentService();
			AppointmentVO appointmentVO = new AppointmentVO();
			DieticianService dieticianSvc = new DieticianService();
			DieticianVO dieticianVO= dieticianSvc.findByPrimaryKey(dno);
			
			LocalDate nowDate =LocalDate.now();
//			DateTimeFormatter java8Format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        Date date2 = Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			List<AppointmentVO> result;
			result = appointmentSvc.getAll()
					.stream()
					.filter(f -> f.getDno().equals(dno))
					.filter(f -> !f.getRstate().equals("222222222222222222222222"))
					.filter(f -> f.getRdate().after(date2))
					
					.collect(Collectors.toList());
			
			
			
			
			req.setAttribute("dieticianVO", dieticianVO);
			req.setAttribute("result", result);
			String url = "/front_end/protected/appointment/appointment_dietician.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}	
		if ("update_appointment".equals(action)) {
			try {
				System.out.println("進到update預約從營養師列表");
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs" , errorMsgs);
				AppointmentService appointmentSvc = new AppointmentService();
				Integer dno =new Integer(req.getParameter("dno"));//取得營養師編號
				
				
				
				java.sql.Date rdate = null;
				//日期
				rdate = java.sql.Date.valueOf(req.getParameter("serchdate").trim());//預約日期
				
				String rstate= req.getParameter("rstate");//抓到24個字
				Integer rstate_num= new Integer(req.getParameter("rstate_num")); //抓到時段13
				Integer aptno= new Integer(req.getParameter("aptno"));
				
				AppointmentVO appointmentVO=appointmentSvc.getOneAppointment(aptno);
				String rstateServlet=appointmentVO.getRstate(); //再次重資料庫抓
				System.out.println("sdfds");
				System.out.println("123"+rstateServlet.charAt(rstate_num));
				if(rstateServlet.charAt(rstate_num) == '1') {
					System.out.println("進到錯誤修改");
					errorMsgs.add("此時間段已被預約");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/appointment/appointment_dietician.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
				//假設預約13點第13個是
				
				StringBuilder sb = new StringBuilder(rstate);
				
				sb.setCharAt(rstate_num, '1'); //設定24的時段中，改為1
				String rstated=new String(sb);

				appointmentSvc.updateAppointment(dno, rdate, rstated, aptno);//更新諮詢時間ok，將0改為1
				Apt_orderService apt_orderSvc= new Apt_orderService();

				Integer mno=new Integer(req.getParameter("mno"));

				

				DieticianService dieticianSvc = new DieticianService();
				DieticianVO dieticianVO = dieticianSvc.findByPrimaryKey(dno);
				Integer clprice =dieticianVO.getClPrice();
				
				Integer clstate =new Integer(req.getParameter("clstate"));
				String aptreviews=req.getParameter("aptreviews");
				

				//rdate+rstate日期+時間段
				java.text.SimpleDateFormat simple = new java.text.SimpleDateFormat();
				simple.applyPattern("yyyy-MM-dd");
				String rdateString=simple.format(rdate);
				System.out.println(rdateString);
				
				//現在時間
				Long datetime = System.currentTimeMillis();
				Timestamp orderDate = new Timestamp(datetime);
				//現在時間結束
				Timestamp orderTime = new Timestamp(System.currentTimeMillis());
				String tsStr = rdateString+" "+rstate_num+":00:00";
				System.out.println("印出來的時間"+tsStr);
				try {
				orderTime = Timestamp.valueOf(tsStr);
				System.out.println(orderTime);
				} catch (Exception e) {
				e.printStackTrace();
				} 
				
				//新增諮詢訂單
				Apt_orderVO apt_orderVO1 = new Apt_orderVO();
				apt_orderVO1=apt_orderSvc.addApt_order(mno, dno, orderTime, orderDate, clprice, clstate, aptreviews);
				
				req.setAttribute("apt_orderVO1", apt_orderVO1);
				
				String url = "/front_end/protected/appointment/search_appointment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
	





}
