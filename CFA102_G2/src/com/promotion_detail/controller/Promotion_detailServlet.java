package com.promotion_detail.controller;

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

import com.promotion_detail.model.Promotion_detailService;
import com.promotion_detail.model.Promotion_detailVO;


public class Promotion_detailServlet extends HttpServlet {

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
				Integer promNo = new Integer(req.getParameter("promNo"));
				Integer pno = new Integer(req.getParameter("pno"));
				
				String str = req.getParameter("pprice");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品價格");
				}
				
//				Integer pprice = null;
//				try {
//					pprice = new Integer(req.getParameter("pprice").trim());
//				} catch (NumberFormatException e) {
//					pprice = 0;
//					errorMsgs.add("商品價格請填數字.");
//				}			
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
				Promotion_detailVO promotion_detailVO = promotion_detailSvc.getOnePromotion_detail(promNo, pno);
				if (promotion_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("promotion_detailVO", promotion_detailVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/promotion_detail/listOnePromotion_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer promNo = new Integer(req.getParameter("promNo"));
				Integer pno = new Integer(req.getParameter("pno"));
				
				/***************************2.開始查詢資料****************************************/
				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
				Promotion_detailVO promotion_detailVO = promotion_detailSvc.getOnePromotion_detail(promNo, pno);
						
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("promotion_detailVO", promotion_detailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/promotion_detail/update_promotion_detail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);				
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotion_detail/listAllPromotion_detail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
					
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer promNo = new Integer(req.getParameter("promNo").trim());
				Integer pno = new Integer(req.getParameter("pno").trim());

				String str = req.getParameter("pprice");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品價格");
				}
			
				Integer pprice = null;
				try {
					pprice = new Integer(req.getParameter("pprice").trim());
				} catch (NumberFormatException e) {
					pprice = 0;
					errorMsgs.add("商品價格請填數字.");
				}				

				Promotion_detailVO promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(promNo);
				promotion_detailVO.setPno(pno);
				promotion_detailVO.setPprice(pprice);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotion_detailVO", promotion_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion_detail/update_promotion_detail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
				promotion_detailVO = promotion_detailSvc.updatePromotion_detail(promNo, pno, pprice);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				if(requestURL.equals("/back_end/promotion_detail/listPromotion_detail_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Promotion_detailVO> list  = promotion_detailSvc.getAll(map);
					req.setAttribute("listPromotion_detail_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request				
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion_detail/update_promotion_detail_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer promNo = new Integer(req.getParameter("promNo"));
				Integer pno = new Integer(req.getParameter("pno"));

				String str = req.getParameter("pprice");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品價格");
				}
				
				Integer pprice = null;
				try {
					pprice = new Integer(req.getParameter("pprice").trim());
				} catch (NumberFormatException e) {
					pprice = 0;
					errorMsgs.add("商品價格請填數字.");
				}		

				Promotion_detailVO promotion_detailVO = new Promotion_detailVO();
				promotion_detailVO.setPromNo(promNo);
				promotion_detailVO.setPno(pno);
				promotion_detailVO.setPprice(pprice);



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promotion_detailVO", promotion_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/promotion_detail/addPromotion_detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
				promotion_detailVO = promotion_detailSvc.addPromotion_detail(promNo, pno, pprice);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/promotion_detail/listAllPromotion_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion_detail/addPromotion_detail.jsp");
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
//				Integer pno = new Integer(req.getParameter("pno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
//				promotion_detailSvc.deletePromotion_detail(promNo,pno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/back_end/promotion_detail/listAllPromotion_detail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/promotion_detail/listAllPromotion_detail.jsp");
//				failureView.forward(req, res);
//			}
//		}
        
		if ("listPromotion_detail_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				Promotion_detailService promotion_detailSvc = new Promotion_detailService();
				List<Promotion_detailVO> list  = promotion_detailSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPromotion_detail_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/promotion_detail/listPromotion_detail_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/promotion_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}	        
	}
}
