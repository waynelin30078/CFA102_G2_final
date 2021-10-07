package com.chat.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;

public class NameServlet extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("memberVO1");
		
		String userName = member.getMid();
		
		
		//String userName = req.getParameter("userName");
		session.setAttribute("userName", userName);
		//req.setAttribute("userName", userName);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/front_end/free/chat/member_chat_box_icon.jsp");
		dispatcher.forward(req, res);
	}
}
