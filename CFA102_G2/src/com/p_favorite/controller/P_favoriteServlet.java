package com.p_favorite.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.p_favorite.model.P_favoriteService;
import com.p_favorite.model.P_favoriteVO;
import com.p_order.model.P_orderVO;


public class P_favoriteServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				Integer mno = new Integer((int) session.getAttribute("mno"));
				Integer pno = new Integer(req.getParameter("pno"));
				
				/***************************2.開始刪除資料***************************************/
				P_favoriteService p_favoriteSvc = new P_favoriteService();
				p_favoriteSvc.deleteP_favorite(mno,pno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/protected/p_favorite/listAllP_favoriteByMno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/protected/p_favorite/listAllP_favoriteByMno.jsp");
				failureView.forward(req, res);
			}
		}		

		if ("addFavorite".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			
			HttpSession session = req.getSession();
			Integer mno = new Integer((int) session.getAttribute("mno"));
			Integer pno = new Integer(req.getParameter("pno"));
				
			P_favoriteVO p_favoriteVO = new P_favoriteVO();		
			p_favoriteVO.setMno(mno);
			p_favoriteVO.setPno(pno);				
				
			P_favoriteService p_favoriteSvc = new P_favoriteService();
			p_favoriteVO = p_favoriteSvc.addP_favorite(mno,pno);				
				
			String url = requestURL;
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}		
		
		if ("getAll_For_Display_ByPorderNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer mno = new Integer(req.getParameter("mno"));
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_favorite/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				P_favoriteService p_favoriteSvc = new P_favoriteService();
				List<P_favoriteVO> list = p_favoriteSvc.getAll_ByMno(mno);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_favorite/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				//				req.getSession().setAttribute("list", list);
				String url = "/back_end/p_favorite/listAllP_favorite_byMno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_favorite/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}	
}
