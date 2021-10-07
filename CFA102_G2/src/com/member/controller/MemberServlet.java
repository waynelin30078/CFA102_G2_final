package com.member.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import com.member.model.*;
import javax.servlet.*;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
@MultipartConfig

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
			String str = req.getParameter("mno");
			if(str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/protected/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer mno = null;
			try {
				mno = new Integer(str);
			}catch (Exception e) {
				errorMsgs.add("會員編號格是不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/protected/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(mno);
			if(memberVO == null) {
				errorMsgs.add("查無此資料");
				
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/protected/member/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("memberVO", memberVO);
			String url = "/front_end/protected/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/protected/member/select_page.jsp");
			failureView.forward(req, res);
		}
	}
	
	if ("getOne_For_Update".equals(action)) {
		System.out.println("進入到修改");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs",errorMsgs);
	try {
		Integer mno = new Integer(req.getParameter("mno"));
		
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMember(mno);
		
		req.setAttribute("memberVO", memberVO);
		String url = "/front_end/protected/member/edit_Profile_Member.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	
		
	}catch (Exception e) {
		errorMsgs.add("無法取得要修改的資料" + e.getMessage());
		RequestDispatcher failureView = req.getRequestDispatcher("/back_end/protected/member/listAllMember.jsp");
		failureView.forward(req, res);
		}
	}
	
	if ("update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			

			Integer mno = new Integer(req.getParameter("mno").trim());
			String mname = req.getParameter("mname");			
			String mnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(mname==null || mname.trim().length() == 0) {
				errorMsgs.add("會員姓名:請勿空白");
				
			}else if(!mname.trim().matches(mnameReg)) {
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				
			}
			String mid = req.getParameter("mid");

			String mpsw = req.getParameter("mpsw").trim();
			if (mpsw == null || mpsw.trim().length() == 0) {
				errorMsgs.add("請輸入密碼好嗎");
			}
			String mmail = req.getParameter("mmail").trim();
			if (mmail == null || mmail.trim().length() == 0) {
				errorMsgs.add("請輸入e-mail");
			}
			String mphone = req.getParameter("mphone").trim();
			if (mphone == null || mphone.trim().length() == 0) {
				errorMsgs.add("請輸入電話");
			}
			java.sql.Date mbday = null;
			try {
				mbday = java.sql.Date.valueOf(req.getParameter("mbday").trim());
			}catch (IllegalArgumentException e) {
				mbday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期");
				
			}
			String mintro = req.getParameter("mintro");			
			
			
			Integer msex = null;
			try {
			msex = new Integer(req.getParameter("msex").trim());
			}catch (NullPointerException e){
				errorMsgs.add("請輸入性別");
			}
			
			Part is =req.getPart("mimg");

			InputStream in = is.getInputStream();

			byte[] mimg = null;

			if(in.available() == 0) {
			   
			   MemberService memberSvc = new MemberService();
			   MemberVO memberVO = memberSvc.getOneMember(mno);
			   mimg = memberVO.getMimg();

			}else {
			mimg = new byte[in.available()];

			in.read(mimg);
			in.close();

			}
	
			MemberVO memberVO = new MemberVO();
			
			memberVO.setMname(mname);			
			memberVO.setMpsw(mpsw);
			memberVO.setMmail(mmail);
			memberVO.setMphone(mphone);
			memberVO.setMimg(mimg);
			memberVO.setMbday(mbday);
			memberVO.setMsex(msex);
			memberVO.setMintro(mintro);
			memberVO.setMno(mno);
			memberVO.setMid(mid);
System.out.println("9");			
			

			if(!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/member/edit_Profile_Member.jsp");
				failureView.forward(req, res);
				return;
			}
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.updateMember( mname, mpsw, mmail, mphone, mimg, mbday, msex , mintro, mno);
			
			req.setAttribute("memberVO", memberVO);
			String url = "/front_end/protected/member/member_info.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}catch (Exception e) {
			errorMsgs.add("修改資料失敗"+ e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/member/edit_Profile_Member.jsp");
			failureView.forward(req, res);
		}
		
	}
	if ("insert".equals(action)) {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {

			String mname = req.getParameter("mname");
			String mnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(mname==null || mname.trim().length() == 0) {
				errorMsgs.put("mname","會員姓名請勿空白");
				
			}else if(!mname.trim().matches(mnameReg)) {
				errorMsgs.put("mname","會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				
			}
			String mid = req.getParameter("mid").trim();
			if (mid == null || mid.trim().length() == 0) {
				errorMsgs.put("mid","請輸入會員帳號");
			}
			String mpsw = req.getParameter("mpsw").trim();
			if (mpsw == null || mpsw.trim().length() == 0) {
				errorMsgs.put("mpsw","請輸入密碼好嗎");
			}
			String mmail = req.getParameter("mmail").trim();
			if (mmail == null || mmail.trim().length() == 0) {
				errorMsgs.put("mmail","請輸入e-mail");
			}
			String mphone = req.getParameter("mphone").trim();
			if (mphone == null || mphone.trim().length() == 0) {
				errorMsgs.put("mphone","請輸入電話");
			}
			Integer msex = null;
			try {
			msex = new Integer(req.getParameter("msex").trim());
			}catch (NullPointerException e) {
				
				errorMsgs.put("msex","請選取性別");
			}
			MemberService memberSvc = new MemberService();
			MemberVO memberVO2 = new MemberVO();
			memberVO2 = memberSvc.getOneMemberByMid(mid);  //用帳號去找
			if (memberVO2 != null && mid.trim().length() != 0) {
				errorMsgs.put("mid", "此帳號已註冊");
			}
			
			MemberVO memberVO = new MemberVO();
			memberVO.setMname(mname);
			memberVO.setMid(mid);
			memberVO.setMpsw(mpsw);
			memberVO.setMmail(mmail);
			memberVO.setMphone(mphone);
			memberVO.setMsex(msex);
			
			
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/addNewMember.jsp");
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
//			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(mname, mid, mpsw, mmail, mphone, msex);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			HttpSession session = req.getSession();
			session.setAttribute("account", mid);
			req.setAttribute("memberVO", memberVO);
			String url = "/front_end/protected/member/member_info.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/

		}catch (Exception e){
			System.out.println("insert錯誤");
			errorMsgs.put("error",e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/addNewMembera.jsp");
			failureView.forward(req, res);
		}
	}
	
	if("showPhoto".equals(action)) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		  if(req.getParameter("photo")!=null){
		byte[] mimg = dao.getImg(Integer.parseInt(req.getParameter("photo")));
		
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		   out.write(mimg);
		   out.close();
		  }else if(dao.getImgByMid(req.getParameter("mid"))!= null) {
			   byte[] mimg = dao.getImgByMid(req.getParameter("mid"));
			
			   res.setContentType("image/jpeg");
			   ServletOutputStream out = res.getOutputStream();
		out.write(mimg);
		out.close();
		  }
	}
	if("disable".equals(action)) {

		try {
			Integer mno = new Integer(req.getParameter("mno").trim());

			Integer mstate = new Integer(req.getParameter("mstate").trim());

			MemberVO memberVO = new MemberVO();
			memberVO.setMno(mno);	
			memberVO.setMstate(mstate);
			
			MemberService memberSvc = new MemberService();
			memberVO=memberSvc.disable(mno,mstate);
			
			
			req.setAttribute("memberVO", memberVO);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req (是為了給update_emp_input.jsp)
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:istAllEmp.jsp)
			
			String url = "/back_end/member/allMemberList.jsp";
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
