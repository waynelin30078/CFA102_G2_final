package com.p_order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.p_order.model.P_orderService;
import com.p_order.model.P_orderVO;
import com.p_order_detail.model.P_order_detailVO;

public class P_orderServlet extends HttpServlet {

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
				Integer porderNo = new Integer(req.getParameter("porderNo"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				P_orderService p_orderSvc = new P_orderService();
				P_orderVO p_orderVO = p_orderSvc.getOneP_order(porderNo);
				if (p_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("p_orderVO", p_orderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/p_order/listOneP_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_order/select_page.jsp");
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
				Integer porderNo = new Integer(req.getParameter("porderNo"));
				
				/***************************2.開始查詢資料****************************************/
				P_orderService p_orderSvc = new P_orderService();
				P_orderVO p_orderVO = p_orderSvc.getOneP_order(porderNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("p_orderVO", p_orderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/p_order/update_p_order_input.jsp";
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
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer porderNo = new Integer(req.getParameter("porderNo").trim());					
				Integer mno = new Integer(req.getParameter("mno").trim());
				java.sql.Timestamp porderDate = java.sql.Timestamp.valueOf(req.getParameter("porderDate").trim());
				Integer porderTotal = new Integer(req.getParameter("porderTotal").trim());
				
				String porderName = req.getParameter("porderName");
				String porderNameReg = "^[(\u4e00-\u9fa5))]{2,10}$";
				if (porderName == null || porderName.trim().length() == 0) {
					errorMsgs.add("收件人姓名: 請勿空白");
				} else if(!porderName.trim().matches(porderNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人姓名: 只能是中文字母, 且長度必需在2到10之間");
	            }
				
				String porderPhone = req.getParameter("porderPhone");
				String porderPhoneReg = "^[(0-9)]{10}$";
				if (porderPhone == null || porderPhone.trim().length() == 0) {
					errorMsgs.add("收件人電話: 請勿空白");
				} else if(!porderPhone.trim().matches(porderPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人電話: 只能是數字 , 且長度必需為10碼");
	            }
				
				String porderAddress = req.getParameter("porderAddress");			
				String porderAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";				
				if (porderAddress == null || porderAddress.trim().length() == 0) {
					errorMsgs.add("收件人地址: 請勿空白");
				} else if(!porderAddress.trim().matches(porderAddressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人地址: 只能是中英文字母、數字");
	            }
				
				Integer ppayment = new Integer(req.getParameter("ppayment").trim());				
				String pcreditCard = req.getParameter("pcreditCard");
				String pcreditCardCVV = req.getParameter("pcreditCardCVV");
				Integer pshipping = new Integer(req.getParameter("pshipping").trim());
				Integer porderState = new Integer(req.getParameter("porderState").trim());
				

				P_orderVO p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(porderNo);				
				p_orderVO.setMno(mno);			
				p_orderVO.setPorderDate(porderDate);
				p_orderVO.setPorderTotal(porderTotal);
				p_orderVO.setPorderName(porderName);
				p_orderVO.setPorderPhone(porderPhone);
				p_orderVO.setPorderAddress(porderAddress);
				p_orderVO.setPpayment(ppayment);
				p_orderVO.setPcreditCard(pcreditCard);
				p_orderVO.setPcreditCardCVV(pcreditCardCVV);
				p_orderVO.setPshipping(pshipping);
				p_orderVO.setPorderState(porderState);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_orderVO", p_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order/update_p_order_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				P_orderService p_orderSvc = new P_orderService();
				p_orderVO = p_orderSvc.updateP_order(porderNo, mno, porderDate, porderTotal, porderName,
							porderPhone, porderAddress, ppayment, pcreditCard, pcreditCardCVV, pshipping, porderState);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				if(requestURL.equals("/back_end/p_order/listP_order_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<P_orderVO> list  = p_orderSvc.getAll(map);
					req.setAttribute("listP_order_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request				
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_order/update_p_order_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer porderNo = new Integer(req.getParameter("porderNo").trim());
				Integer mno = new Integer(req.getParameter("mno").trim());
				java.sql.Timestamp porderDate = java.sql.Timestamp.valueOf(req.getParameter("porderDate").trim());
				Integer porderTotal = new Integer(req.getParameter("porderTotal").trim());
				
				String porderName = req.getParameter("porderName");
				String porderNameReg = "^[(\u4e00-\u9fa5))]{2,10}$";
				if (porderName == null || porderName.trim().length() == 0) {
					errorMsgs.add("收件人姓名: 請勿空白");
				} else if(!porderName.trim().matches(porderNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人姓名: 只能是中文字母, 且長度必需在2到10之間");
	            }
				
				String porderPhone = req.getParameter("porderPhone");
				String porderPhoneReg = "^[(0-9)]{10}$";
				if (porderPhone == null || porderPhone.trim().length() == 0) {
					errorMsgs.add("收件人電話: 請勿空白");
				} else if(!porderPhone.trim().matches(porderPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人電話: 只能是數字 , 且長度必需為10碼");
	            }
				
				String porderAddress = req.getParameter("porderAddress");
				String porderAddressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{255}$";
				if (porderAddress == null || porderAddress.trim().length() == 0) {
					errorMsgs.add("收件人地址: 請勿空白");
				} else if(!porderAddress.trim().matches(porderAddressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收件人地址: 只能是中文字母、數字和- , 且長度不能超過255");
	            }				
				
				Integer ppayment = new Integer(req.getParameter("ppayment").trim());
				String pcreditCard = req.getParameter("pcreditCard");
				String pcreditCardCVV = req.getParameter("pcreditCardCVV");
				Integer pshipping = new Integer(req.getParameter("pshipping").trim());
				Integer porderState = new Integer(req.getParameter("porderState").trim());				

				P_orderVO p_orderVO = new P_orderVO();
				p_orderVO.setPorderNo(porderNo);
				p_orderVO.setMno(mno);
				p_orderVO.setPorderDate(porderDate);
				p_orderVO.setPorderTotal(porderTotal);
				p_orderVO.setPorderName(porderName);
				p_orderVO.setPorderPhone(porderPhone);
				p_orderVO.setPorderAddress(porderAddress);
				p_orderVO.setPpayment(ppayment);
				p_orderVO.setPcreditCard(pcreditCard);
				p_orderVO.setPcreditCardCVV(pcreditCardCVV);
				p_orderVO.setPshipping(pshipping);
				p_orderVO.setPorderState(porderState);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("p_orderVO", p_orderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/p_order/addP_order.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				P_orderService p_orderSvc = new P_orderService();
				p_orderVO = p_orderSvc.addP_order( porderNo, mno, porderDate, porderTotal, porderName,  
							porderPhone,  porderAddress,  ppayment,  pcreditCard, pcreditCardCVV, pshipping, porderState);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/p_order/listAllP_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_order/addP_order.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("listPorder_detail_ByPorderNo".equals(action)) {

        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);
        	

        	try {
        		/*************************** 1.接收請求參數 ****************************************/
        		Integer porderNo = new Integer(req.getParameter("porderNo"));
//        		Integer mno = new Integer(req.getParameter("mno"));

        		/*************************** 2.開始查詢資料 ****************************************/
        		P_orderService p_orderSvc = new P_orderService();
        		Set<P_order_detailVO> set = p_orderSvc.getPorderDetailByPorderNo(porderNo);     		

        		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
        		req.setAttribute("listPorder_detail_ByPorderNo", set);    // 資料庫取出的list物件,存入request

        		String url = null;
        		url = "/front_end/protected/p_order/listAllP_orderByMno.jsp";

        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);

        		/*************************** 其他可能的錯誤處理 ***********************************/
        	} catch (Exception e) {
        		throw new ServletException(e);
        	}
        }        
        
        if ("getOne_For_Display_Front_End".equals(action)) {

        	List<String> errorMsgs = new LinkedList<String>();
        	req.setAttribute("errorMsgs", errorMsgs);

        	try {
        		/*************************** 1.接收請求參數 ****************************************/
        		Integer porderNo = new Integer(req.getParameter("porderNo"));

        		P_orderVO p_orderVO = new P_orderVO();
        		p_orderVO.setPorderNo(porderNo);
        		
        		/*************************** 2.開始查詢資料 ****************************************/
        		P_orderService p_orderSvc = new P_orderService();
        		p_orderVO = p_orderSvc.getOneP_order(porderNo);     		

        		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
        		req.setAttribute("p_orderVO", p_orderVO);

        		String url = null;
        		url = "/front_end/protected/p_order/listOneP_orderByPorderNo.jsp";

        		RequestDispatcher successView = req.getRequestDispatcher(url);
        		successView.forward(req, res);

        		/*************************** 其他可能的錯誤處理 ***********************************/
        	} catch (Exception e) {
        		throw new ServletException(e);
        	}
        }         
        
        
		
		if ("listP_order_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				P_orderService p_orderSvc = new P_orderService();
				List<P_orderVO> list  = p_orderSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listP_order_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/p_order/listP_order_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/p_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}        
	}
}
