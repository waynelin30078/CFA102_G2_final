package com.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;

public class AdminLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected boolean allowUser(String aid, String apsw) {

		AdminService aser = new AdminService();
		AdminVO adminVO1 = aser.findByAid(aid);
		if (adminVO1 == null)
			return false;
		else if (aid.equals(adminVO1.getAid()) && apsw.equals(adminVO1.getApsw()))
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
		String login = req.getParameter("action");
		String logout = req.getParameter("action");
		if ("login".equals(login)) {

			String aid = req.getParameter("aid");
			String apsw = req.getParameter("apsw");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if (aid == null || (aid.trim().length() == 0))
				errorMsgs.add("帳號不能為空");
			if (apsw == null || (apsw.trim().length() == 0))
				errorMsgs.add("密碼不能為空");

			if (!errorMsgs.isEmpty()) {
				try {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end_home/index.jsp");

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

			if (!allowUser(aid, apsw)) {
				errorMsgs.add("帳號密碼有誤");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end_home/index.jsp");
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
				session.setAttribute("aid", aid);
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/back_end/admin/blank_space.jsp");
			}
		}
		if ("logout".equals(logout)) {
			try {
				HttpSession session = req.getSession();
				session.removeAttribute("aid");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end_home/index.jsp");
				failureView.forward(req, res);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			}

		}
	}
}
