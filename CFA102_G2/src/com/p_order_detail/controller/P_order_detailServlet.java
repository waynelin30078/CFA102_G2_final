package com.p_order_detail.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.p_order.model.P_orderService;
import com.p_order.model.P_orderVO;
import com.p_order_detail.model.P_order_detailService;
import com.p_order_detail.model.P_order_detailVO;
import com.promotion_detail.model.Promotion_detailService;
import com.promotion_detail.model.Promotion_detailVO;


public class P_order_detailServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer porderNo = new Integer(req.getParameter("porderNo"));
//				Integer pno = new Integer(req.getParameter("pno"));
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				P_order_detailService p_order_detailSvc = new P_order_detailService();
//				P_order_detailVO p_order_detailVO = p_order_detailSvc.getOneP_order_detail(porderNo, pno);
//				if (p_order_detailVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("p_order_detailVO", p_order_detailVO); // 資料庫取出的empVO物件,存入req
//				String url = "/back_end/p_order_detail/listOneP_order_detail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer porderNo = new Integer(req.getParameter("porderNo"));
				Integer pno = new Integer(req.getParameter("pno"));
				Integer pratings = new Integer(req.getParameter("pratings"));
				
				/***************************2.開始查詢資料****************************************/
				P_order_detailService p_order_detailSvc = new P_order_detailService();
				P_order_detailVO p_order_detailVO = p_order_detailSvc.updateRatings(porderNo, pno, pratings);
						
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("p_order_detailVO", p_order_detailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/protected/p_order/listAllP_orderByMno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);				
				failureView.forward(req, res);
			}
		}		
		
		if ("getAll_For_Display_ByPorderNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer porderNo = new Integer(req.getParameter("porderNo"));
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				P_order_detailService p_order_detailSvc = new P_order_detailService();
				List<P_order_detailVO> list = p_order_detailSvc.getAll_ByOrderNo(porderNo);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list);
				//				req.getSession().setAttribute("list", list);
				String url = "/back_end/p_order_detail/listAllP_order_detail_byPorderNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_order_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}
