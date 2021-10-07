package com.artice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.artice.model.ArticeService;
import com.artice.model.ArticeVO;
@MultipartConfig
public class ArticeServlet extends HttpServlet  {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException , IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println(action);
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("articNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("欄位不得為空");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer articNo = null;
				try {
					articNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors			
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
				ArticeService artSvc = new ArticeService();
				ArticeVO articeVO = artSvc.findbyarticNo(articNo);
				if (articeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("articeVO" , articeVO);
				String url = "/back_end/artice/listOneArtice.jsp";
				RequestDispatcher succesView = req.getRequestDispatcher(url);
				succesView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display2".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("articNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("欄位不得為空");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer articNo = null;
				try {
					articNo = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors			
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				
				ArticeService artSvc = new ArticeService();
				ArticeVO articeVO = artSvc.findbyarticNo(articNo);
				if (articeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("articeVO" , articeVO);
				String url = "/front_end/free/artice/listOneArtice.jsp";
				RequestDispatcher succesView = req.getRequestDispatcher(url);
				succesView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/select_artice_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				Integer articNo = new Integer(req.getParameter("articNo").trim());
				
				ArticeService articeSvc = new ArticeService();
				ArticeVO articeVO = articeSvc.findbyarticNo(articNo);
				
				
				req.setAttribute("articeVO", articeVO);
				String url = "/back_end/artice/update_artice_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch  (Exception e) {
				errorMsgs.add("error:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/listAllArtice.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer articNo = new Integer(req.getParameter("articNo").trim());
				
				String articTitle = req.getParameter("articTitle");
				String articTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (articTitle == null || articTitle.trim().length() == 0) {
					errorMsgs.add("標題不得為空!");
				} else if(!articTitle.trim().matches(articTitleReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~50字元");
	            }
			
				String articContent = req.getParameter("articContent1");
				String articContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,1000}$";
				if (articContent == null || articContent.trim().length() == 0) {
					errorMsgs.add("內容不得為空!");
				} else if(!articContent.trim().matches(articContentReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~1000字元");
	            }
				
				byte[] articPhoto=null;
				try {
					Part part = req.getPart("articPhoto");
					InputStream in = part.getInputStream();
					articPhoto = new byte[in.available()];
					in.read(articPhoto);
					in.close();
					if(part.getSize()==0) {
						System.out.println("no part!");
						ArticeService articeSvc = new ArticeService();
						ArticeVO articeVO = articeSvc.findbyarticNo(articNo);
						articPhoto = articeVO.getArticPhoto();
					} 
					System.out.println("buffer length: " + articPhoto.length);
				} catch (Exception e) {
					errorMsgs.add("error");
				}
				System.out.println("articPhoto:"+articPhoto);
				
				Integer articState = new Integer(req.getParameter("articState").trim());
				
				Integer articType = new Integer(req.getParameter("articType").trim());
				
				ArticeVO articeVO = new ArticeVO();
				articeVO.setArticNo(articNo);
				articeVO.setArticTitle(articTitle);
				articeVO.setArticContent(articContent);
				articeVO.setArticPhoto(articPhoto);
				articeVO.setArticState(articState);
				articeVO.setArticType(articType);
				
			
				/***************************2.開始修改資料*****************************************/
				ArticeService articeSvc = new ArticeService();
				articeVO = articeSvc.update(articNo, articTitle, articContent, articPhoto, articState, articType);
				System.out.println(22);
				/***************************3.修改成功轉交(Send the Success view)*************/
			
				req.setAttribute("articeVO", articeVO);
				String url = "/back_end/artice/listAllArtice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
				/***************************其他可能錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("error:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/update_artice_input.jsp");
				failureView.forward(req, res);
			}
		} 
		
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收參數***************************************/
				Integer articNo = new Integer(req.getParameter("articNo").trim());
				/***************************2.開始刪除***************************************/
				ArticeService articSvc = new ArticeService();
				articSvc.delete(articNo);
				/***************************3.刪除成功轉交(Send the Success view)***********/								
				String url = "/back_end/artice/listAllArtice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("error:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/listAllArtice.jsp");
				failureView.forward(req, res);
		    }
		} 
			
			
			
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***********************1.接收參數*************************/
			try {
				Integer articType = new Integer(req.getParameter("articType").trim());
				
				String articTitle = req.getParameter("articTitle");
				String articTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,50}$";
				if (articTitle == null || articTitle.trim().length() == 0) {
					errorMsgs.add("標題不得為空!");
				} else if(!articTitle.trim().matches(articTitleReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~50字元");
	            }
				String articContent = req.getParameter("articContent1");
				String articContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,1000}$";
				if (articContent == null || articContent.trim().length() == 0) {
					errorMsgs.add("內容不得為空!");
				} else if(!articContent.trim().matches(articContentReg)) { //(regular-expression)
					errorMsgs.add("只能輸入中、英 長度上限3~1000字元");
	            }
				
//				Integer articNo = new Integer(req.getParameter("articNo").trim());
				
				byte[] articPhoto = null;
				try {
					Part part = req.getPart("articPhoto1");
					InputStream in = part.getInputStream();
					articPhoto = new byte[in.available()];
					in.read(articPhoto);
					in.close();
					System.out.println("buffer length: " + articPhoto.length);
				} catch (Exception e) {
					errorMsgs.add("error" + e.getMessage());
				}
				System.out.println("articPhoto1:"+articPhoto);
				
				ArticeVO articeVO = new ArticeVO();
				articeVO.setArticType(articType);
				articeVO.setArticTitle(articTitle);
				articeVO.setArticContent(articContent);
//				articeVO.setArticNo(articNo);
				articeVO.setArticPhoto(articPhoto);
				
				/***************************2.開始新增資料***************************************/
				ArticeService articeSvc = new ArticeService();
				articeVO = articeSvc.insert(articType, articTitle, articContent, articPhoto);
				/***************************3.新增成功轉交(Send the Success view)***********/
				String url = "/back_end/artice/listAllArtice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("error:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/artice/addartice.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
