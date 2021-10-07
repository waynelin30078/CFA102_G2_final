package com.d_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.d_order.model.DorderService;
import com.d_order.model.DorderVO;
import com.dietician.model.DieticianVO;
import com.member.model.MemberService;

public class DorderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		DieticianVO dietician = (DieticianVO)session.getAttribute("dieticianVO2");
		String action=req.getParameter("action");
		if("openOrder".equals(action)) {
		Integer dno =new Integer(req.getParameter("dno"));
		Integer mthFee=new Integer(req.getParameter("mthFee"));
		Integer autoSubs=new Integer(req.getParameter("autoSubs"));
		 
			if(req.getParameter("mno")==null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer mno=new Integer(req.getParameter("mno"));
			DorderService dorderSvc=new DorderService();
			MemberService memberSvc=new MemberService();
			Long now=System.currentTimeMillis();
			Timestamp subStart = new Timestamp(now);
			Timestamp subEnd = new Timestamp(now+1000L*60*60*24*30);
			memberSvc.updateDieticianNo(dno, mno);

			dorderSvc.addDorder(dno,mno,subStart,subEnd,mthFee,autoSubs);
			req.setAttribute("orderOk", 1);
			RequestDispatcher failureView = req.getRequestDispatcher("/dietician/dietician.do?action=one_dietician_page&dno="+dno);
			failureView.forward(req, res);
			return; 
		}
		if(dietician==null) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/dietician/login_dietician.jsp");
			failureView.forward(req, res);
			return; 
		  
		}
		
		 if("show_list_Order".equals(action)){
			
			Integer dno1 = dietician.getDno();
			DorderService dorderSvc=new DorderService();
			List<DorderVO> order = dorderSvc.getActiveOrderByDNo(dno1);
			req.setAttribute("order", order);
			
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/dietician/listOrder.jsp");
			failureView.forward(req, res);
			return; 
		}
		if("closeOrder".equals(action)) {
				Integer dno =new Integer(req.getParameter("dno"));
				Integer mthFee=new Integer(req.getParameter("mthFee"));
				Integer autoSubs=new Integer(req.getParameter("autoSubs"));
				if(req.getParameter("mno")==null) {
			
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
		}
				Integer mno=new Integer(req.getParameter("mno"));
				DorderService dorderSvc=new DorderService();
				dorderSvc.update_autoSubs( dno,  mno, autoSubs);
		
				req.setAttribute("orderOk", 2);
				RequestDispatcher failureView = req.getRequestDispatcher("/dietician/dietician.do?action=one_dietician_page&dno="+dno);
				failureView.forward(req, res);
				return; 
		 }
	}
}
