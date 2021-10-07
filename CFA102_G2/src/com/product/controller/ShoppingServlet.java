package com.product.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.p_order.model.P_orderService;
import com.p_order.model.P_orderVO;
import com.p_order_detail.model.P_order_detailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;


public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		
		@SuppressWarnings("unchecked")
		List<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		String requestURL = req.getParameter("requestURL");
		
		if (!action.equals("CHECKOUT")&&!action.equals("CHECKOUT_BillInfo")) {
			// 刪除購物車中的商品
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增商品至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的商品
				Integer pno = new Integer(req.getParameter("pno"));
				Integer quantity = new Integer(req.getParameter("quantity"));
				
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
				productVO.setQuantity(quantity);
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(productVO);
				} else {
					if (buylist.contains(productVO)) {
						ProductVO innerProduct = buylist.get(buylist.indexOf(productVO));
						innerProduct.setQuantity(innerProduct.getQuantity() + productVO.getQuantity());
					} else {
						buylist.add(productVO);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
				String url = requestURL;
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
		
		}

		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				Integer price = order.getPprice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.getSession().setAttribute("amount", amount);
			String url = "/front_end/protected/product/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}else 
		
		if ("CHECKOUT_BillInfo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);	
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				
				String price = req.getParameter("discount_price");
				String[] result = price.split(" ");
				System.out.println(Arrays.toString(result));
				
				
				
				Integer mno = new Integer ((int)session.getAttribute("mno"));
				java.sql.Timestamp porderDate = null;
				porderDate=new java.sql.Timestamp(System.currentTimeMillis());
				Integer porderTotal = new Integer(req.getParameter("amount").trim());
			
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
				String pcreditCardReg = "^[(0-9)]{16}$";
				String pcreditCardCVV = req.getParameter("pcreditCardCVV");
				String pcreditCardCVVReg = "^[(0-9)]{3}$";
				if (ppayment == 1) {
					if (pcreditCard != "" || pcreditCardCVV != "") {
						errorMsgs.add("付款方式為「貨到付款」，不需填寫信用卡資訊");
					}
				}
				
				if (ppayment == 2) {
					if (pcreditCard == null || pcreditCard.trim().length() == 0) {
						errorMsgs.add("信用卡號: 請勿空白");
					}else if (!pcreditCard.trim().matches(pcreditCardReg)){
						errorMsgs.add("信用卡號: 只能是數字, 且長度必需為16碼");
					}
					
					if (pcreditCardCVV == null || pcreditCardCVV.trim().length() == 0) {
						errorMsgs.add("信用卡檢查碼: 請勿空白");
					}else if (!pcreditCardCVV.trim().matches(pcreditCardCVVReg)){
						errorMsgs.add("信用卡檢查碼: 只能是數字, 且長度必需為3碼");
					}	
				}
				Integer pshipping = new Integer(req.getParameter("pshipping").trim());	
//				Integer porderState = new Integer(req.getParameter("porderState").trim());			

				P_orderVO p_orderVO = new P_orderVO();		
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
//				p_orderVO.setPorderState(porderState);
				
				List<P_order_detailVO> p_order_detailList = new LinkedList<P_order_detailVO>();
				
				
				for(int i=0; i<buylist.size(); i++) {
					P_order_detailVO p_order_detailVO = new P_order_detailVO();
					p_order_detailVO.setPno(buylist.get(i).getPno());
					p_order_detailVO.setPorderQuantity(buylist.get(i).getQuantity());
					p_order_detailVO.setPprice(new Integer(result[i]));
					p_order_detailVO.setPratings(0);
					p_order_detailList.add(p_order_detailVO);
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
				
					req.setAttribute("p_orderVO", p_orderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/product/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}	
				/***************************2.開始新增資料***************************************/
				P_orderService p_orderSvc = new P_orderService();
				p_orderSvc.insertWithPorderDetail(p_orderVO, p_order_detailList);
				for (int i = 0; i < buylist.size(); i++) {
					buylist.remove(i);
				}
				session.setAttribute("shoppingcart", buylist);
				/***************************3.新增完成,準備轉交(Send the Success view)************/
				RequestDispatcher successView = req.getRequestDispatcher("/front_end/protected/product/CheckOrder.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/protected/product/Checkout.jsp");
				failureView.forward(req, res);
			}		
		}
	}
}