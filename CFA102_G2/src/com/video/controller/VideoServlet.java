package com.video.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.video.model.VideoService;
import com.video.model.VideoVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)

public class VideoServlet extends HttpServlet {
	String saveDirectory = "/front_end/protected/course_video/videos_uploaded";// 上傳目的地目錄

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charser=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert_video_page".equals(action)) {

//			try {
			Integer cno = new Integer(req.getParameter("cno").trim());
			VideoVO videoVO = new VideoVO();
			videoVO.setCno(cno);

			req.setAttribute("videoVO", videoVO);
			String url = "/front_end/protected/course_video/addCourseVideo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCourse.jsp
			successView.forward(req, res);
//				}	
		}
		if ("getAllVideoByCno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 *************************/
				Integer cno = new Integer(req.getParameter("cno"));
				/*************************** 2.開始查詢資料 ****************************************/
				VideoService videoSvc = new VideoService();
				List<VideoVO> videoList = videoSvc.getAll(cno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("videoList", videoList);
				String url = "/front_end/protected/course_video/listAllVideoByCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/protected/video/addCourseVideo.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			System.out.println(1);
			/*********************** 1.接收請求參數 *************************/
			Integer vno = new Integer(req.getParameter("vno"));
			System.out.println(vno);
			/*************************** 2.開始刪除影片 *********************/
			VideoService videoSvc = new VideoService();
			videoSvc.deleteVideo(vno);

			/*************************** 3.刪除完成返回原頁面 ************/
			String url = "/front_end/protected/course/listAllCourse.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert_video".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 *************************/
				VideoVO videoVO = new VideoVO();
				Integer cno = new Integer(req.getParameter("cno"));
				String vname = new String(req.getParameter("vname"));
				String vlength = new String(req.getParameter("vlength"));
				System.out.println(vlength);
				float seconds = Float.parseFloat(vlength);
				long ms = (long) ((seconds * 1000f)-28800000);//時區8hr
				Part part = req.getPart("vFile");// 取得part物件
				if (part.getSize() == 0) {
					errorMsgs.add("請加入影片");
				}
				String realPath = getServletContext().getRealPath(saveDirectory);// 取得真實路徑

				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists())
					fsaveDirectory.mkdirs();// 如果目的地目錄自動建立目的目錄

				String filename = getFileNameFromPart(part);
				String savefilepath = (saveDirectory + "/" + filename);// 存入資料庫用
				File f = new File(fsaveDirectory, filename);
				part.write(f.toString());
				videoVO.setVlength(new java.sql.Time(ms));
//			videoVO.setVfile(f.toString());
//			videoVO.setVlength(new Time(11,11,11));
//			System.out.println(f);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("VideoVO", videoVO); // 含有輸入格式錯誤的courseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/protected/course_video/addCourseVideo.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				VideoService videoSvc = new VideoService();
				videoSvc.addVideo(cno,vname, savefilepath,new java.sql.Time(ms));
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/protected/course/listAllCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCourse.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/protected/course_video/addCourseVideo.jsp");
				failureView.forward(req, res);

			}
		}

	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
