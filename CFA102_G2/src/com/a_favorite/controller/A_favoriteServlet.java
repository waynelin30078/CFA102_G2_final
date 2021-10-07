package com.a_favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.a_favorite.model.A_favoriteService;
import com.a_favorite.model.A_favoriteVO;

public class A_favoriteServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		
		if ("addfav".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer articNo = new Integer(req.getParameter("articNo").trim());
				Integer mNo = new Integer(req.getParameter("mNo").trim());
				
				A_favoriteVO afVO = new A_favoriteVO();
				afVO.setArticNo(articNo);
				afVO.setMno(mNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("afVO", afVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("");
					failureView.forward(req, res);
					return;
				}
				
				A_favoriteService afS = new A_favoriteService();
				afS.insert(afVO);
				
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}

		if ("delfav".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer articNo = new Integer(req.getParameter("articNo").trim());
				
				
				A_favoriteService afS = new A_favoriteService();
				afS.delete(articNo);
				
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
		
		if ("getfav".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer mNo = new Integer(req.getParameter("mNo").trim());
				
				
				A_favoriteService afS = new A_favoriteService();
				afS.findbymNO(mNo);
				
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("");
				failureView.forward(req, res);
			}
		}
	}
}
