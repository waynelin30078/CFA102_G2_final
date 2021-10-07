package com.c_favorite.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.c_favorite.model.C_FavoriteService;
import com.course.model.CourseService;
import com.course.model.CourseVO;

public class C_FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charser=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		/*********************** 1.接收請求參數*************************/

		if("add".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
		try {
			CourseService courseSvs = new CourseService();
			Integer cno = new Integer(req.getParameter("cno"));
			Integer mno = new Integer((int)session.getAttribute("mno"));
			CourseVO courseVO = courseSvs.getOneCourse(cno);

		/*************************** 2.開始新增資料 ***************************************/
			C_FavoriteService c_FavoriteSvc = new C_FavoriteService();
			c_FavoriteSvc.addCourseFavorite(mno, cno);
			/*************************** 3.新增完成回到原頁面(Send the Success view) ***********/
			req.setAttribute("courseVO", courseVO);
			String url = requestURL;
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			}catch(NullPointerException e) {
				String url = "/front_end/free/member/memberLogin.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {//移除收藏
			String requestURL = req.getParameter("requestURL");
			System.out.println(requestURL);
		try {
			CourseService courseSvs = new CourseService();
			Integer cno = new Integer(req.getParameter("cno"));
			Integer mno = new Integer((int)session.getAttribute("mno"));
			CourseVO courseVO = courseSvs.getOneCourse(cno);

		/*************************** 2.開始新增資料 ***************************************/
			C_FavoriteService c_FavoriteSvc = new C_FavoriteService();
			c_FavoriteSvc.deleteCourseFavorite(mno, cno);
			/*************************** 3.新增完成回到原頁面(Send the Success view) ***********/
			req.setAttribute("courseVO", courseVO);
			String url = requestURL;
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			}catch(NullPointerException e) {
				String url = "/front_end/free/member/memberLogin.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}
	}

}
