package com.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

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
				String str = req.getParameter("pno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer pno = null;
				try {
					pno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product/select_page.jsp");
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
				Integer pno = new Integer(req.getParameter("pno"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/product/listAllProduct.jsp");
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
				Integer pno = new Integer(req.getParameter("pno").trim());
				
				String categoryName = req.getParameter("categoryName");
				String categoryNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (categoryName == null || categoryName.trim().length() == 0) {
					errorMsgs.add("商品類別名稱: 請勿空白");
				} else if(!categoryName.trim().matches(categoryNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品類別名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }
			
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!pname.trim().matches(pnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }	
				Integer pprice = null;
				try {
					pprice = new Integer(req.getParameter("pprice").trim());
				} catch (NumberFormatException e) {
					pprice = 0;
					errorMsgs.add("商品單價請填數字");
				}
				
				String pinfo = req.getParameter("pinfo").trim();
				if (pinfo == null || pinfo.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				}					

				Integer pquantity = null;
				try {
					pquantity = new Integer(req.getParameter("pquantity").trim());
				} catch (NumberFormatException e) {
					pquantity = 0;
					errorMsgs.add("商品數量請填數字");
				}					

				java.sql.Date ponDate = null;
				try {
					ponDate = java.sql.Date.valueOf(req.getParameter("ponDate").trim());
				} catch (IllegalArgumentException e) {
					ponDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				java.sql.Date poffDate = null;
				try {
					poffDate = java.sql.Date.valueOf(req.getParameter("poffDate").trim());
				} catch (IllegalArgumentException e) {
					poffDate=java.sql.Date.valueOf("9999-12-31");
					errorMsgs.add("請輸入下架日期!");
				}				

				String directoryPath = getServletContext().getRealPath("/back_end/product/images");
				File fasaveDirectory = new File(directoryPath);
				if(!fasaveDirectory.exists()) {
					fasaveDirectory.mkdirs();
				}

				String pimage1 = "";
				String pimage2 = "";
				String pimage3 = "";
				
				Part part = req.getPart("pimage1");			
				String filename = getFileNameFromPart(part);
				if(filename != null && part.getContentType() != null) {
					pimage1 = "/back_end/product/images/" + filename;
					File f = new File(fasaveDirectory, filename);
					part.write(f.getPath());
				}else {
					pimage1 = req.getParameter("orgImage1").trim();
				}
	
				if (pimage1 == null || pimage1.trim().length() == 0) {
					errorMsgs.add("請上傳一張商品圖片");
				}				

				Part part2 = req.getPart("pimage2");
				String filename2 = getFileNameFromPart(part2);
				if(filename2 != null && part2.getContentType() != null) {
					pimage2 = "/back_end/product/images/" + filename2;
					File f2 = new File(fasaveDirectory, filename2);
					part2.write(f2.getPath());
				}
				
				Part part3 = req.getPart("pimage3");
				String filename3 = getFileNameFromPart(part3);			
				if(filename3 != null && part3.getContentType() != null) {
					pimage3 = "/back_end/product/images/" + filename3;
					File f3 = new File(fasaveDirectory, filename3);
					part.write(f3.getPath());
				}
				
//				Integer pratingsQuantity = new Integer(req.getParameter("pratingsQuantity").trim());			
//				Integer ptotalRatings = new Integer(req.getParameter("ptotalRatings").trim());
	
				Integer pstate = null;
				try {
					pstate = new Integer(req.getParameter("pstate").trim());
				} catch (NumberFormatException e) {
					pstate = 1;
					errorMsgs.add("商品狀態請填數字");
				}
				
				ProductVO productVO = new ProductVO();
				productVO.setPno(pno);
				productVO.setCategoryName(categoryName);
				productVO.setPname(pname);
				productVO.setPprice(pprice);
				productVO.setPinfo(pinfo);
				productVO.setPquantity(pquantity);
				productVO.setPonDate(ponDate);
				productVO.setPoffDate(poffDate);
				productVO.setPimage1(pimage1);
				productVO.setPimage2(pimage2);
				productVO.setPimage3(pimage3);
//				productVO.setPratingsQuantity(pratingsQuantity);
//				productVO.setPtotalRatings(ptotalRatings);
				productVO.setPstate(pstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(pno, categoryName, pname, pprice, pinfo, pquantity
						, ponDate, poffDate, pimage1, pimage2, pimage3, pstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				if(requestURL.equals("/back_end/product/listProduct_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<ProductVO> list  = productSvc.getAll(map);
					req.setAttribute("listProduct_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				//				String url = "/back_end/product/listOneProduct.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product/update_product_input.jsp");
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
				String categoryName = req.getParameter("categoryName");
				String categoryNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (categoryName == null || categoryName.trim().length() == 0) {
					errorMsgs.add("商品類別名稱: 請勿空白");
				} else if(!categoryName.trim().matches(categoryNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品類別名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }

				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!pname.trim().matches(pnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
	            }				
				
				Integer pprice = null;
				try {
					pprice = new Integer(req.getParameter("pprice").trim());
				} catch (NumberFormatException e) {
					pprice = 0;
					errorMsgs.add("商品單價請填數字");
				}
				
				String pinfo = req.getParameter("pinfo").trim();
				if (pinfo == null || pinfo.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				}

				Integer pquantity = null;
				try {
					pquantity = new Integer(req.getParameter("pquantity").trim());
				} catch (NumberFormatException e) {
					pquantity = 0;
					errorMsgs.add("商品數量請填數字");
				}				
				
				java.sql.Date ponDate = null;
				try {
					ponDate = java.sql.Date.valueOf(req.getParameter("ponDate").trim());
				} catch (IllegalArgumentException e) {
					ponDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}
				
				java.sql.Date poffDate = null;
				try {
					poffDate = java.sql.Date.valueOf(req.getParameter("poffDate").trim());
				} catch (IllegalArgumentException e) {
					poffDate=java.sql.Date.valueOf("9999-12-31");
					errorMsgs.add("請輸入下架日期!");
				}	
			
				String directoryPath = getServletContext().getRealPath("/back_end/product/images");
				File fasaveDirectory = new File(directoryPath);
				if(!fasaveDirectory.exists()) {
					fasaveDirectory.mkdirs();
				}
				
				String pimage1 = "";
				String pimage2 = "";
				String pimage3 = "";
				
				Part part = req.getPart("pimage1");
				String filename = getFileNameFromPart(part);
			
				if(filename != null && part.getContentType() != null) {
					pimage1 = "/back_end/product/images/" + filename;
					File f = new File(fasaveDirectory, filename);
					part.write(f.getPath());
				}
				
				if (pimage1 == null || pimage1.trim().length() == 0) {
					errorMsgs.add("請上傳一張商品圖片");
				}				
				
				Part part2 = req.getPart("pimage2");
				String filename2 = getFileNameFromPart(part2);
				
				if(filename2 != null && part2.getContentType() != null) {
					pimage2 = "/back_end/product/images/" + filename2;
					File f2 = new File(fasaveDirectory, filename2);
					part2.write(f2.getPath());
				}
				
				Part part3 = req.getPart("pimage3");
				String filename3 = getFileNameFromPart(part3);
				
				if(filename3 != null && part3.getContentType() != null) {
					pimage3 = "/back_end/product/images/" + filename3;
					File f3 = new File(fasaveDirectory, filename3);
					part.write(f3.getPath());
				}
				
				Integer pstate = null;
				try {
					pstate = new Integer(req.getParameter("pstate").trim());
				} catch (NumberFormatException e) {
					pstate = 1;
				}
				
				ProductVO productVO = new ProductVO();
				productVO.setCategoryName(categoryName);
				productVO.setPname(pname);
				productVO.setPprice(pprice);
				productVO.setPinfo(pinfo);
				productVO.setPquantity(pquantity);
				productVO.setPonDate(ponDate);
				productVO.setPoffDate(poffDate);
				productVO.setPimage1(pimage1);
				productVO.setPimage2(pimage2);
				productVO.setPimage3(pimage3);
				productVO.setPstate(pstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(categoryName, pname, pprice, pinfo, pquantity
						, ponDate, poffDate, pimage1, pimage2, pimage3, pstate);				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product/addProduct.jsp");
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
//			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
//		
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer pno = new Integer(req.getParameter("pno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				ProductService productSvc = new ProductService();
//				productSvc.deleteProduct(pno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				if(requestURL.equals("/back_end/product/listProduct_ByCompositeQuery.jsp")){
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					List<ProductVO> list  = productSvc.getAll(map);
//					req.setAttribute("listProduct_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
//
//				}				
//		
//				String url = requestURL;
//				
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);					
//				failureView.forward(req, res);
//			}
//		}
		
		if ("listProduct_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
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
				ProductService productSvc = new ProductService();
				List<ProductVO> list  = productSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listProduct_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/product/listProduct_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneProduct_ByPno".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer pno = new Integer(req.getParameter("pno"));
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/free/product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(pno);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/free/product/listAllProduct.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("productVO", productVO);
				String url = "/front_end/free/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/free/product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}

	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
