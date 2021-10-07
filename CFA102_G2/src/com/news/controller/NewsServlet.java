package com.news.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.cj.Session;
import com.news.model.NewsService;
import com.news.model.NewsVO;



public class NewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_news_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收參數**********************/
				String str = req.getParameter("newsNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("欄位不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				Integer newsNo = null;
				try {
					newsNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.find(newsNo);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢成功轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO); // 取出的VO物件 存入req
				String url = "/back_end/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交listOneNews.jsp
				successView.forward(req, res);

				/***************************其他可能錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/select_news_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display2".equals(action)) { // 來自select_news_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收參數**********************/
				String str = req.getParameter("newsNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("欄位不得為空");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				Integer newsNo = null;
				try {
					newsNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.find(newsNo);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/select_news_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢成功轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO); // 取出的VO物件 存入req
				String url = "/front_end/free/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交listOneNews.jsp
				successView.forward(req, res);
				
				/***************************其他可能錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/select_news_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自 listAllNews.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收參數****************************************/
				Integer newsNo = new Integer(req.getParameter("newsNo"));
				
				/***************************2.查詢資料****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.find(newsNo);
								
				/***************************3.查詢完成轉交(Send the Success view)************/
				req.setAttribute("newsVO", newsVO);         // 取出的VO物件存入req
				String url = "/back_end/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 轉交 update_news_input.jsp
				successView.forward(req, res);

				/***************************其他可能錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { //來自 update_news_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收參數**********************/
				Integer newsNo = new Integer(req.getParameter("newsNo").trim());
				
				String newsTitle = req.getParameter("newsTitle");
				String newsTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (newsTitle == null || newsTitle.trim().length() == 0) {
					errorMsgs.add("標題不得為空!");
				} else if(!newsTitle.trim().matches(newsTitleReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~50字元");
	            }
				
				
				String newsContent = req.getParameter("newsContent");
				String newsContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,1000}$";
				if (newsContent == null || newsContent.trim().length() == 0) {
					errorMsgs.add("內容不得為空!");
				} else if(!newsContent.trim().matches(newsContentReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~1000字元");
	            }
			
			
				NewsVO newsVO = new NewsVO();
				newsVO.setNewsNo(newsNo);
				newsVO.setNewsTitle(newsTitle);
				newsVO.setNewsContent(newsContent);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 取出的VO物件存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/update_news_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				/***************************2.開始修改資料*****************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.update(newsTitle , newsContent, newsNo);
	
				/***************************3.修改成功轉交(Send the Success view)*************/
				req.setAttribute("newsVO", newsVO); // 成功修改後的物件存入req				
				String url = "/back_end/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交 lissAllNews.jsp
				successView.forward(req, res);

				/***************************其他可能錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("error:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/update_news_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addNews.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收參數*************************/
//				Integer newsNo = new Integer(req.getParameter("newsNo").trim());
				
				
				String newsTitle = req.getParameter("newsTitle");
				String newsTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (newsTitle == null || newsTitle.trim().length() == 0) {
					errorMsgs.add("標題不得為空!");
				} else if(!newsTitle.trim().matches(newsTitleReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 且長度為3~50字元");
	            }
				
				
				String newsContent = req.getParameter("newsContent");
				String newsContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,1000}$";
				if (newsContent == null || newsContent.trim().length() == 0) {
					errorMsgs.add("內容不得為空!");
				} else if(!newsContent.trim().matches(newsContentReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英文且長度為3~1000字元");
	            }			
				

				NewsVO newsVO = new NewsVO();
//				newsVO.setNewsNo(newsNo);
				newsVO.setNewsTitle(newsTitle);
				newsVO.setNewsContent(newsContent);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/news/addnews.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.addNews( newsTitle, newsContent);
				
				/***************************3.新增成功轉交(Send the Success view)***********/
				String url = "/back_end/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 轉交listAllNews.jsp
				successView.forward(req, res);				
				
				/***************************其他可能錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("error" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/addnews.jsp");
				failureView.forward(req, res);
			}
		} 
		
		
		if ("delete".equals(action)) { // 來自listAllNew.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收參數***************************************/
				Integer newsNo = new Integer(req.getParameter("newsNo"));
				
				/***************************2.開始刪除***************************************/
				NewsService newsSvc = new NewsService();
				newsSvc.deletenews(newsNo);
				
				/***************************3.刪除成功轉交(Send the Success view)***********/								
				String url = "/back_end/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 轉交 listAllNewes.jsp
				successView.forward(req, res);
				
				/***************************其他可能錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("error:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
