package com.admin.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.admin.model.*;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ano");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer ano = null;
				try {
					ano = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.getOneAdmin(ano);
				if (adminVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adminVO", adminVO);
				String url = "/back_end/admin/blank_listSingleAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer ano = new Integer(req.getParameter("ano"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdminService empSvc = new AdminService();
				AdminVO adminVO = empSvc.getOneAdmin(ano);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adminVO", adminVO);
				String url = "/back_end/admin/blank_update_admin_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_listAllAdmin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer ano = new Integer(req.getParameter("ano").trim());

				String aname = req.getParameter("aname");
				String anameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (aname == null || aname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!aname.trim().matches(anameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String aid = req.getParameter("aid").trim();
				if (aid == null || aid.trim().length() == 0) {
					errorMsgs.add("員工帳號請勿空白");
				}

				String apsw = req.getParameter("apsw").trim();
				if (apsw == null || apsw.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				}
				
				AdminJDBCDAO dao =new AdminJDBCDAO();
				List<AdminVO> all=dao.getAll();
				for(AdminVO admin: all) {
					String aid1=admin.getAid();
					if(aid.equals(aid1)) {
						errorMsgs.add("帳號重複");
					}
				}

				AdminVO adminVO = new AdminVO();
				adminVO.setAno(ano);
				adminVO.setAname(aname);
				adminVO.setAid(aid);
				adminVO.setApsw(apsw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/admin/blank_update_admin_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdminService adminSvc = new AdminService();
				adminVO = adminSvc.updateAdmin(ano, aname, aid, apsw);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adminVO", adminVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdmin.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/admin/blank_update_admin_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String aname = req.getParameter("aname");
				String Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";

				if (aname == null || aname.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if (!aname.trim().matches(Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String aid = req.getParameter("aid");
				if (aid == null || aid.trim().length() == 0) {
					errorMsgs.add("管理員帳號:請勿空白");
				} else if (!aid.trim().matches(Reg)) {
					errorMsgs.add("管理員帳號:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String apsw = req.getParameter("apsw");
				if (apsw == null || apsw.trim().length() == 0) {
					errorMsgs.add("管理員密碼:請勿空白");
				} else if (!aid.trim().matches(Reg)) {
					errorMsgs.add("管理員密碼:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				AdminVO adminVO = new AdminVO();
				adminVO.setAname(aname);
				adminVO.setAid(aid);
				adminVO.setApsw(apsw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_addAdmin.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdminService adminSvc = new AdminService();
				adminVO = adminSvc.addAdmin(aname, aid, apsw);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/admin/blank_listAllAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_addAdmin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer ano = new Integer(req.getParameter("ano"));

				/*************************** 2.開始刪除資料 ***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(ano);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/admin/blank_listAllAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/blank_listAllAdmin.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
