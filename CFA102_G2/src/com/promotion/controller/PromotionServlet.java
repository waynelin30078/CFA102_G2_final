package com.promotion.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.promotion.model.PromotionService;
import com.promotion.model.PromotionVO;

public class PromotionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("promNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer promNo = null;
				try {
					promNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("優惠活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.getOnePromotion(promNo);
				if (promotionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotionVO", promotionVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/promotion/listOnePromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer promNo = new Integer(req.getParameter("promNo"));
				
				/***************************2.開始查詢資料****************************************/
				PromotionService promotionSvc = new PromotionService();
				PromotionVO promotionVO = promotionSvc.getOnePromotion(promNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("promotionVO", promotionVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/promotion/update_promotion_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer promNo = new Integer(req.getParameter("promNo").trim());
				
				String promName = req.getParameter("promName");
				String promNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (promName == null || promName.trim().length() == 0) {
					errorMsgs.add("優惠活動名稱: 請勿空白");
				} else if(!promName.trim().matches(promNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Date promStartDate = null;
				try {
					promStartDate = java.sql.Date.valueOf(req.getParameter("promStartDate").trim());
				} catch (IllegalArgumentException e) {
					promStartDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date promEndDate = null;
				try {
					promEndDate = java.sql.Date.valueOf(req.getParameter("promEndDate").trim());
				} catch (IllegalArgumentException e) {
					promEndDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}				

				PromotionVO promotionVO = new PromotionVO();
				promotionVO.setPromNo(promNo);
				promotionVO.setPromName(promName);
				promotionVO.setPromStartDate(promStartDate);
				promotionVO.setPromEndDate(promEndDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion/update_promotion_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionVO = promotionSvc.updatePromotion(promNo, promName, promStartDate, promEndDate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("promotionVO", promotionVO); // 資料庫update成功後,正確的的empVO物件,存入req
				if(requestURL.equals("/back_end/promotion/listPromotion_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<PromotionVO> list  = promotionSvc.getAll(map);
					req.setAttribute("listPromotion_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request				
				}
				
//				String url = "/back_end/promotion/listOnePromotion.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotion/update_promotion_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String promName = req.getParameter("promName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (promName == null || promName.trim().length() == 0) {
					errorMsgs.add("優惠活動名稱: 請勿空白");
				} else if(!promName.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Date promStartDate = null;
				try {
					promStartDate = java.sql.Date.valueOf(req.getParameter("promStartDate").trim());
				} catch (IllegalArgumentException e) {
					promStartDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date promEndDate = null;
				try {
					promEndDate = java.sql.Date.valueOf(req.getParameter("promEndDate").trim());
				} catch (IllegalArgumentException e) {
					promEndDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}				

				PromotionVO promotionVO = new PromotionVO();
				promotionVO.setPromName(promName);
				promotionVO.setPromStartDate(promStartDate);
				promotionVO.setPromEndDate(promEndDate);



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotionVO", promotionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion/addPromotion.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PromotionService promotionSvc = new PromotionService();
				promotionVO = promotionSvc.addPromotion(promName, promStartDate, promEndDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/promotion/listAllPromotion.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion/addPromotion.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer promNo = new Integer(req.getParameter("promNo"));
//				
//				/***************************2.開始刪除資料***************************************/
//				PromotionService promotionSvc = new PromotionService();
//				promotionSvc.deletePromotion(promNo);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back_end/promotion/listAllPromotion.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotion/listAllPromotion.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("listPromotion_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				PromotionService promotionSvc = new PromotionService();
				List<PromotionVO> list  = promotionSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPromotion_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/promotion/listPromotion_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion/select_page.jsp");
				failureView.forward(req, res);
			}
		}        
	}
}
