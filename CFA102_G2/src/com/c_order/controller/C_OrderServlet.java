package com.c_order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.c_order.model.*;
import com.course.model.*;
import com.c_order_detail.model.*;
public class C_OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html;charser=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Vector<CourseVO> buylist = (Vector<CourseVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		// 新增至購物車中
		if ("ADD".equals(action)) {

			// 取得後來新增的課程
			Integer cno = new Integer(req.getParameter("cno"));
			CourseService courseSvc = new CourseService();
			CourseVO courseVO = courseSvc.getOneCourse(cno);
//			String requestURL = req.getParameter("requestURL");
			// 新增第一個課程至購物車時
			if (buylist == null) {
				buylist = new Vector<CourseVO>();
				buylist.add(courseVO);
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					CourseVO course = buylist.get(i);
					if (!course.getCno().equals(courseVO.getCno()))// 同一課程不能重複加入購物車
						buylist.add(courseVO);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			req.setAttribute("courseVO", courseVO);
			String url = "/front_end/free/course/course_detail_page.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if ("CART".equals(action)) {
			if (buylist == null) 
				buylist = new Vector<CourseVO>();
			int total = buylist.stream().mapToInt(c -> c.getCprice()).sum();
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front_end/protected/course_cart/course_cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}
		if ("DELETE".equals(action)) {
			String del = req.getParameter("del");
			int index = Integer.parseInt(del);
			buylist.remove(index);
			int total = buylist.stream()
					    .mapToInt(c -> c.getCprice())
					    .sum();
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front_end/protected/course_cart/course_cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if ("CHECKOUT".equals(action)) {
			
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			C_OrderVO c_orderVO = new C_OrderVO();
			
			Integer mno = new Integer((int) session.getAttribute("mno"));
//			System.out.println(mno);
			Integer total = new Integer(req.getParameter("total"));
//			System.out.println(total);
			Integer paymentMethod = new Integer(req.getParameter("paymentMethod"));
//			Integer paymentMethod =1;
			
//			System.out.println(paymentMethod);
			String paymentInfo = req.getParameter("paymentInfo");
//			System.out.println(paymentInfo);
			c_orderVO.setMno(mno);
			c_orderVO.setTotal(total);
			c_orderVO.setPaymentMethod(paymentMethod);
			c_orderVO.setPaymentInfo(paymentInfo);
			List<C_Order_DetailVO> c_order_detail_List = new LinkedList<C_Order_DetailVO>();
			
			req.setAttribute("amount", total);
			for(int i = 0;i<buylist.size();i++) {
				C_Order_DetailVO c_order_detailVO = new C_Order_DetailVO();
				c_order_detailVO.setCno(buylist.get(i).getCno());
				c_order_detailVO.setCprice(buylist.get(i).getCprice());
				CourseService courseSvc = new CourseService();
				CourseVO course =  courseSvc.getOneCourse(buylist.get(i).getCno());
				courseSvc.addQuantity(course);
//				Integer quantity = course.getQuantity();
//				quantity++;
//				course.setQuantity(quantity);
//				System.out.println(buylist.get(i).getCno());
//				System.out.println(buylist.get(i).getCprice());
				c_order_detail_List.add(c_order_detailVO);
			}
			
			
			/*************************** 2.開始新增資料 ***************************************/
			C_OrderService c_orderSvc = new C_OrderService();
			c_orderSvc.insertWithDetail(c_orderVO,c_order_detail_List);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			
			req.setAttribute("c_orderVO", c_orderVO); // 資料庫update成功後,正確的的courseVO物件,存入req
			req.setAttribute("total", total);
			String url ="/front_end/protected/course_cart/course_checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			session.removeAttribute("shoppingcart");
		}

	}

}
