package com.dietician.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.dietician.model.*;

public class DieticianLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected boolean allowUser(String daccount, String dpassword) {

		DieticianService dite = new DieticianService();
		DieticianVO dieticianVO1 = dite.findByAccount(daccount);
		if (dieticianVO1 == null) 
			return false;
		else if (daccount.equals(dieticianVO1.getDaccount()) && dpassword.equals(dieticianVO1.getDpassword())) 
			return true;
		else 
			return false;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String signIn = req.getParameter("action");
		String logout = req.getParameter("action");
		if ("signIn".equals(signIn)) {

			String daccount = req.getParameter("daccount");
			String dpassword = req.getParameter("dpassword");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if (daccount == null || (daccount.trim().length() == 0))
				errorMsgs.add("帳號不能為空");
			if (dpassword == null || (dpassword.trim().length() == 0))
				errorMsgs.add("密碼不能為空");

			if (!errorMsgs.isEmpty()) {
				try {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/dietician/login_dietician.jsp");

					failureView.forward(req, res);
					return;// 程式中斷
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (!allowUser(daccount, dpassword)) {
				errorMsgs.add("帳號密碼有誤");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/dietician/login_dietician.jsp");
				try {
					failureView.forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}

			else {
				HttpSession session = req.getSession();
				DieticianService dite = new DieticianService();
				DieticianVO dieticianVO1 = dite.findByAccount(daccount);
				Integer dno=dieticianVO1.getDno();
				DieticianVO dieticianVO2 = dite.findByPrimaryKey(dno);
				session.setAttribute("daccount", daccount);
				session.setAttribute("dieticianVO2", dieticianVO2);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/front_end/free/home.jsp");
			}
		}
		if ("logout".equals(logout)) {
			try {
				HttpSession session = req.getSession();
				session.removeAttribute("daccount");
				session.removeAttribute("dieticianVO2");
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/dietician/login_dietician.jsp");
				failureView.forward(req, res);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			}

		}
	}
}
