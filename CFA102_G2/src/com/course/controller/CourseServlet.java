package com.course.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;

import com.c_order.model.C_OrderService;
import com.c_order.model.C_OrderVO;
import com.c_order_detail.model.C_Order_DetailService;
import com.c_order_detail.model.C_Order_DetailVO;
import com.course.model.*;

@MultipartConfig
public class CourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset = UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("cno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入課程編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/course/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer cno = null;
				try {
					cno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("課程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/course/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CourseService courseSvc = new CourseService();
				CourseVO courseVO = courseSvc.getOneCourse(cno);
				if (courseVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/course/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** .查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
				String url = "/back_end/course/listOneCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/course/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCourse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer cno = new Integer(req.getParameter("cno"));

				/*************************** 2.開始查詢資料 ****************************************/
				CourseService courseSvc = new CourseService();
				CourseVO courseVO = courseSvc.getOneCourse(cno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
				String url = "/front_end/protected/course/update_course_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_course_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		// 修改課程
		if ("update".equals(action)) { // 來自update_course_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			System.out.println(requestURL);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer cno = new Integer(req.getParameter("cno").trim());

				String cname = req.getParameter("cname");

				String cnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (cname == null || cname.trim().length() == 0) {
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!cname.trim().matches(cnameReg)) {
					errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在3到50之間");
				}
				Integer cprice = null;
				if (req.getParameter("cprice").trim().length() == 0) {
					cprice = 0;
				} else {
					try {
						cprice = new Integer(req.getParameter("cprice").trim());

					} catch (NumberFormatException e) {
						errorMsgs.add("課程價格請輸入數字");
					}
				}
				if (cprice < 1) {
					errorMsgs.add("請輸入大於0的數字");
				}
				String cintroduction = req.getParameter("cintroduction");
				if (cintroduction == null || cintroduction.trim().length() == 0) {
					errorMsgs.add("課程介紹請勿空白");
				}
				String cdescription = req.getParameter("cdescription");
				if (cdescription == null || cdescription.trim().length() == 0) {
					errorMsgs.add("課程預覽說明請勿空白");
				}

				Part pic = req.getPart("cpic");// 新圖片

				InputStream in = pic.getInputStream();
				byte[] cpic = null;// new byte[in.available()];
				if (in.available() == 0) {
					CourseService courseSvc = new CourseService();
					CourseVO courseVO = courseSvc.getOneCourse(cno);
					cpic = courseVO.getCpic();
				} else {
					cpic = new byte[in.available()];
					in.read(cpic);
					in.close();
				}

				Integer dno = new Integer(req.getParameter("dno").trim());
				Integer cstate = new Integer(req.getParameter("cstate"));
				CourseVO courseVO = new CourseVO();
				courseVO.setCno(cno);
				courseVO.setCname(cname);
				courseVO.setCprice(cprice);
				courseVO.setCintroduction(cintroduction);
				courseVO.setCdescription(cdescription);
				courseVO.setDno(dno);
				courseVO.setCstate(cstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的courseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/course/update_course_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CourseService courseSvc = new CourseService();
				courseVO = courseSvc.updateCourse(cno, cname, cprice, cintroduction, cpic, cdescription, cstate);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

				req.setAttribute("courseVO", courseVO); // 資料庫update成功後,正確的的courseVO物件,存入req

//			if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//				req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(deptno)); // 資料庫取出的list物件,存入request
				String url = requestURL;

				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCourse.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/protected/course/update_course_input.jsp");
				failureView.forward(req, res);
			}
		}
		// 新增課程
		if ("insert".equals(action)) { // 來自add.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				CourseVO courseVO = new CourseVO();

				Integer dno = new Integer(req.getParameter("dno").trim());

				String cname = req.getParameter("cname");
				String cnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (cname == null || cname.trim().length() == 0) {// 前段防呆用 打錯字時會一直顯示請輸入
					errorMsgs.add("課程名稱: 請勿空白");
				} else if (!cname.trim().matches(cnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程名稱: 只能是中、英文字母、數字和_ , 且長度必需在3到50之間");
				}

				Integer cprice = 0;
				if (req.getParameter("cprice").trim().length() == 0) {
					cprice = 0;

				} else {
					try {
						cprice = new Integer(req.getParameter("cprice").trim());

					} catch (NumberFormatException e) {
						errorMsgs.add("課程價格請輸入數字");
					}
				}
				if (cprice < 1) {
					errorMsgs.add("請輸入大於0的數字");
				}

				String cintroduction = req.getParameter("cintroduction");
				if (cintroduction == null || cintroduction.trim().length() == 0) {
					errorMsgs.add("課程介紹請勿空白");
				}
				String cdescription = req.getParameter("cdescription");
				if (cdescription == null || cdescription.trim().length() == 0) {
					errorMsgs.add("課程預覽說明請勿空白");
				}
				Part pic = req.getPart("cpic");// 圖片處理
				InputStream in = pic.getInputStream();
				byte[] cpic = new byte[in.available()];
				in.read(cpic);
				in.close();

				Integer ctype = new Integer(req.getParameter("ctype"));

				courseVO.setCname(cname);
				courseVO.setCprice(cprice);
				courseVO.setCintroduction(cintroduction);
				courseVO.setCtype(ctype);
				courseVO.setCdescription(cdescription);
				courseVO.setDno(dno);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("courseVO", courseVO); // 含有輸入格式錯誤的courseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/protected/course/addCourse.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CourseService courseSvc = new CourseService();
				courseVO = courseSvc.addCourse(dno, cname, cprice, cintroduction, ctype, cpic, cdescription);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/protected/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCourse.jsp
				successView.forward(req, res);

				/************** 其他可能的錯誤處理 **************/

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/course/update_course_input.jsp");
				failureView.forward(req, res);
			}

		}
		// 課程詳情頁面
		if ("get_course_detail".equals(action)) {

			// 取得參數
			Integer cno = new Integer(req.getParameter("cno"));
			/*************************** 2.開始查詢資料 *****************************************/
			CourseService courseSvc = new CourseService();
			CourseVO courseVO = courseSvc.getOneCourse(cno);
			/*************************** .查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
			String url = "/front_end/free/course/course_detail_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse.jsp
			successView.forward(req, res);
		}

		// 我的課程
		if ("mycourse".equals(action)) {
			Integer mno = new Integer((int) session.getAttribute("mno"));// 取得登陸的員工編號

			System.out.println(mno);
			CourseService courseSvc = new CourseService();
			C_OrderService c_orderSvc = new C_OrderService();
			C_Order_DetailService c_order_detailSvc = new C_Order_DetailService();
			List<Integer> coursenoList = new ArrayList<Integer>();
			List<CourseVO> courseList = new ArrayList<CourseVO>();
			List<C_OrderVO> myCourseOrderList = c_orderSvc.getALLCourseOrderByMno(mno);// 取得該會員的訂單
			System.out.println(myCourseOrderList);
			for (int i = 0; i < myCourseOrderList.size(); i++) {
				C_OrderVO corderVO = myCourseOrderList.get(i);
				Integer corderno = corderVO.getCorderno();// 取得該會員所有訂單編號

				List<C_Order_DetailVO> myCourseOrderDeatilList = c_order_detailSvc
						.getALLCourseOrderDetailByCorderno(corderno);// 取得該會員所有的明細
				for (int j = 0; j < myCourseOrderDeatilList.size(); j++) {
					C_Order_DetailVO corderDetailVO = myCourseOrderDeatilList.get(j);
					coursenoList.add(corderDetailVO.getCno());// 取得所有購買的課程編號
				}
			}
			for (int cno = 0; cno < coursenoList.size(); cno++) {
				CourseVO courseVO = courseSvc.getOneCourse(coursenoList.get(cno));
				courseList.add(courseVO);
			}

			req.setAttribute("courseList", courseList); // 資料庫取出的courseVO物件
			String url = "/front_end/protected/course/mycourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse.jsp
			successView.forward(req, res);
		}

		// 觀看課程
		if ("learnCourse".equals(action)) {

			// 取得參數
			Integer cno = new Integer(req.getParameter("cno"));
			/*************************** 2.開始查詢資料 *****************************************/
			CourseService courseSvc = new CourseService();
			CourseVO courseVO = courseSvc.getOneCourse(cno);
			/*************************** .查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
			String url = "/front_end/protected/course/lectures.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCourse.jsp
			successView.forward(req, res);
		}
		if ("search".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String str = req.getParameter("searchString");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入搜尋內容");
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/free/course/course_front_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/

				CourseService courseSvc = new CourseService();
				List<CourseVO> courseList = (List<CourseVO>) courseSvc.getAll().stream()
						.filter(c -> c.getCname().contains(str)).filter(c -> c.getCstate() == 1)
						.collect(Collectors.toList());
//				System.out.println(courseList);
				/*************************** .查詢完成,準備轉交(Send the Success view) *************/
				// 查無資料未處理
				req.setAttribute("courseList", courseList);
				String url = "/front_end/free/course/course_front_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/free/course/course_front_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("searchctype".equals(action)) {
			Integer ctype = new Integer(req.getParameter("ctype"));

			CourseService courseSvc = new CourseService();
			List<CourseVO> courseList = new ArrayList<CourseVO>();
			if (ctype == 0) {
				courseList = (List<CourseVO>) courseSvc.getAll();
			} else {
				courseList = (List<CourseVO>) courseSvc.getAll().stream().filter(c -> c.getCstate() == 1)
						.filter(c -> c.getCtype().equals(ctype)).collect(Collectors.toList());
			}

			req.setAttribute("courseList", courseList);
			String url = "/front_end/free/course/course_front_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("audit".equals(action)) { // 來自listAllCourse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer cno = new Integer(req.getParameter("cno"));

				/*************************** 2.開始查詢資料 ****************************************/
				CourseService courseSvc = new CourseService();
				CourseVO courseVO = courseSvc.getOneCourse(cno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("courseVO", courseVO); // 資料庫取出的courseVO物件,存入req
				String url = "/back_end/course/audit_course.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_course_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String url = "/back_end/course/listAllCourse.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}

		if ("showpic".contentEquals(action)) {
//			ImageUtil img = new ImageUtil();  
			CourseJDBCDAO dao = new CourseJDBCDAO();
			byte[] cimg = dao.getImg(Integer.parseInt(req.getParameter("cpic")));
			res.setContentType("image/jpeg");
			ServletOutputStream out = res.getOutputStream();
			out.write(cimg);
			out.close();
		}
	}
}