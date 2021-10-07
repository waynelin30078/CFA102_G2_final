package com.dietician.controller;

import javax.servlet.http.*;

import com.dietician.model.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;

import java.io.*;
import java.sql.Date;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)

public class DieticianServlet extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getAll_for_display".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				DieticianService dieticianSvc = new DieticianService(); 
				List<DieticianVO> list = dieticianSvc.getAll();
				Set<DieticianVO> set = new HashSet<DieticianVO>(); 
				for(DieticianVO vo: list) {
					set.add(vo);
				}
				req.setAttribute("set", set);
				
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("資料查詢發生錯誤");
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}
		
		if ("update".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// =====================驗證資料格式=====================

			try {

				String dname = req.getParameter("dname");

				if (dname == null || dname.trim().length() == 0) {
					errorMsgs.add("請輸入姓名");
				}

				String daccount = req.getParameter("daccount");
				if (daccount == null || daccount.trim().length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				String dpasswordRegEx = "^(?=.*[0-9])(?=.*[a-z]).{6,15}$";

				String dpassword = req.getParameter("dpassword");
				if (dpassword == null || dpassword.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!dpassword.matches(dpasswordRegEx)) {
					errorMsgs.add("密碼請輸入混合英文及數字6-15碼");
				}

				java.sql.Date dbirthDay = null;
				try {
					dbirthDay = java.sql.Date.valueOf(req.getParameter("dbirthDay"));

				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日");
				}

				String dphone = req.getParameter("dphone");
				if (dphone == null || dphone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}

				String daddress = req.getParameter("daddress");
				if (dphone == null || dphone.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}

				String demail = req.getParameter("demail");

				String intro = req.getParameter("intro");
				if (intro == null || intro.trim().length() == 0) {
					intro = "";
				}

				String edu = req.getParameter("edu");
				if (edu == null || edu.trim().length() == 0) {
					edu = "";
				}
				String exp = req.getParameter("exp");
				if (exp == null || exp.trim().length() == 0) {
					exp = "";
				}
				String lic = req.getParameter("lic");
				if (lic == null || lic.trim().length() == 0) {
					lic = "";
				}
				String prof = req.getParameter("prof");
				if (prof == null || prof.trim().length() == 0) {
					prof = "";
				}

				Integer clPrice = null;
				if (req.getParameter("clPrice").trim().length() == 0) {
					clPrice = 0;
				} else {
					try {
						clPrice = new Integer(req.getParameter("clPrice").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("諮詢價格請輸入數字");
					}
				}

				if (intro == null || intro.trim().length() == 0) {
					intro = "";
				}

				Integer mprice = null;
				if (req.getParameter("mprice").trim().length() == 0) {
					mprice = 0;
				} else {
					try {
						mprice = new Integer(req.getParameter("mprice").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("營養師月費請輸入數字");
					}
				}

				String dpic = req.getParameter("dpic"); // 舊圖片
				Part part = req.getPart("dpic_changed"); // 新圖片

				if (part.getSize() != 0) { // 如果新圖片的大小不等於0, 代表有新圖片
					String directoryPath = getServletContext().getRealPath("/front_end/free/dietician/images");

					File fsaveDirectory = new File(directoryPath);
					if (!fsaveDirectory.exists()) {
						fsaveDirectory.mkdirs();
					}

					String filename = daccount + "_" + getFileNameFromPart(part);

					if (filename != null && part.getContentType() != null) {

						dpic = "/front_end/free/dietician/images/" + filename; // 改寫舊圖片的路徑

						File f = new File(fsaveDirectory, filename);
						part.write(f.getPath());
					}
				}

				Integer dno = new Integer(req.getParameter("dno"));
				Integer dstate = new Integer(req.getParameter("dstate"));
				Integer totalScore = new Integer(req.getParameter("totalScore"));
				Integer totalReviewer = new Integer(req.getParameter("totalReviewer"));
				Integer donState = new Integer(req.getParameter("donState"));
				String offDay = req.getParameter("offDay");
				String optTime = req.getParameter("optTime");

				DieticianVO dietician = new DieticianVO();

				dietician.setDno(dno);
				dietician.setDname(dname);
				dietician.setDaccount(daccount);
				dietician.setDpassword(dpassword);
				dietician.setDbirthDay(dbirthDay);
				dietician.setDpic(dpic);
				dietician.setDphone(dphone);
				dietician.setDaddress(daddress);
				dietician.setDemail(demail);
				dietician.setEdu(edu);
				dietician.setExp(exp);
				dietician.setLic(lic);
				dietician.setProf(prof);
				dietician.setClPrice(clPrice);
				dietician.setMprice(mprice);
				dietician.setIntro(intro);
				dietician.setDstate(dstate);
				dietician.setTotalScore(totalScore);
				dietician.setTotalReviewer(totalReviewer);
				dietician.setDonState(donState);
				dietician.setOffDay(offDay);
				dietician.setOptTime(optTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dieticianVO", dietician);

					String url = "/front_end/free/dietician/update_dietician_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}

				// =====================格式驗證完成, 開始建立VO==================

				DieticianService dieticianSvc = new DieticianService();
				dietician = dieticianSvc.updateDietician(dno, dname, daccount, dpassword, dbirthDay, dpic, dphone,
						daddress, demail, edu, exp, lic, prof, clPrice, mprice, intro, dstate, totalScore,
						totalReviewer, donState, offDay, optTime);

				req.setAttribute("dieticianVO", dietician);

				String url = "/front_end/free/dietician/one_dietician_page.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("新增資料發生錯誤");
				String url = "/front_end/free/dietician/update_dietician_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);

			}

		}

		
		
		if("findByScore".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Double avgScore = new Double(req.getParameter("avgScore"));
				DieticianService dieticianSvc = new DieticianService(); 
				List<DieticianVO> list = dieticianSvc.findByScore(avgScore);
				Set<DieticianVO> set =new HashSet<DieticianVO>();
				for(DieticianVO vo:list) {
					set.add(vo);
				}
				
				req.setAttribute("set", set);
				
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("資料查詢發生錯誤");
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}
		if("findByPrice".equals(action)) {
	
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			DieticianService dieticianSvc = new DieticianService();
			try {
				if(req.getParameter("minPrice").isEmpty()&&req.getParameter("maxPrice").isEmpty())
					errorMsgs.add("請輸入查詢價格");
				else if(req.getParameter("minPrice").isEmpty()) {
				int minPrice = 0;
				Integer maxPrice = new Integer(req.getParameter("maxPrice"));
		
				List<DieticianVO> list = dieticianSvc.findBySubscribeFee(minPrice, maxPrice);
				Set<DieticianVO> set =new HashSet<DieticianVO>();
				for(DieticianVO vo:list) {
					set.add(vo);
				}
				req.setAttribute("set", set);
				}else if(req.getParameter("maxPrice").isEmpty()) {
					int maxPrice = 5000;
				Integer minPrice = new Integer(req.getParameter("minPrice"));
					List<DieticianVO> list = dieticianSvc.findBySubscribeFee(minPrice, maxPrice);
					Set<DieticianVO> set =new HashSet<DieticianVO>();
					for(DieticianVO vo:list) {
						set.add(vo);
					}
					req.setAttribute("set", set);
				}else {
				Integer maxPrice = new Integer(req.getParameter("maxPrice"));
					Integer minPrice = new Integer(req.getParameter("minPrice"));
				List<DieticianVO> list = dieticianSvc.findBySubscribeFee(minPrice, maxPrice);
					if(list.size()==0) {
						errorMsgs.add("查無資料");
					}
					Set<DieticianVO> set =new HashSet<DieticianVO>();
					for(DieticianVO vo:list) {
						set.add(vo);
					}
					req.setAttribute("set", set);
				}
		
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		
		
			} catch (Exception e) {
				errorMsgs.add("資料查詢發生錯誤");
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}

		}
		
		
		if("one_dietician_page".equals(action)) {
			
			DieticianService dieticianSvc = new DieticianService(); 
			
			Integer dno = new Integer(req.getParameter("dno"));
			
			DieticianVO dietician = dieticianSvc.findByPrimaryKey(dno);
			
			req.setAttribute("dieticianVO", dietician);
			
			String url = "/front_end/free/dietician/select_one_dietician.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("update_dietician_page".equals(action)) {
			
			DieticianService dieticianSvc = new DieticianService(); 
			
			Integer dno = new Integer(req.getParameter("dno"));
			
			DieticianVO dietician = dieticianSvc.findByPrimaryKey(dno);
			
			req.setAttribute("dieticianVO", dietician);
			
			String url = "/front_end/protected/dietician/update_dietician_page01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if ("update1".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// =====================驗證資料格式=====================

			try {

				String dname = req.getParameter("dname");

				if (dname == null || dname.trim().length() == 0) {
					errorMsgs.add("請輸入姓名");
				}

				String daccount = req.getParameter("daccount");
				if (daccount == null || daccount.trim().length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				String dpasswordRegEx = "^(?=.*[0-9])(?=.*[a-z]).{6,15}$";

				String dpassword = req.getParameter("dpassword");
				if (dpassword == null || dpassword.trim().length() == 0) {
					errorMsgs.add("請輸入密碼");
				} else if (!dpassword.matches(dpasswordRegEx)) {
					errorMsgs.add("密碼請輸入混合英文及數字6-15碼");
				}

				java.sql.Date dbirthDay = null;
				try {
					dbirthDay = java.sql.Date.valueOf(req.getParameter("dbirthDay"));

				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入生日");
				}

				String dphone = req.getParameter("dphone");
				if (dphone == null || dphone.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}

				String daddress = req.getParameter("daddress");
				if (dphone == null || dphone.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}

				String demail = req.getParameter("demail");

				String intro = req.getParameter("intro");
				if (intro == null || intro.trim().length() == 0) {
					intro = "";
				}

				String edu = req.getParameter("edu");
				if (edu == null || edu.trim().length() == 0) {
					edu = "";
				}
				String exp = req.getParameter("exp");
				if (exp == null || exp.trim().length() == 0) {
					exp = "";
				}
				String lic = req.getParameter("lic");
				if (lic == null || lic.trim().length() == 0) {
					lic = "";
				}
				String prof = req.getParameter("prof");
				if (prof == null || prof.trim().length() == 0) {
					prof = "";
				}

				Integer clPrice = null;
				if (req.getParameter("clPrice").trim().length() == 0) {
					clPrice = 0;
				} else {
					try {
						clPrice = new Integer(req.getParameter("clPrice").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("諮詢價格請輸入數字");
					}
				}

				if (intro == null || intro.trim().length() == 0) {
					intro = "";
				}

				Integer mprice = null;
				if (req.getParameter("mprice").trim().length() == 0) {
					mprice = 0;
				} else {
					try {
						mprice = new Integer(req.getParameter("mprice").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("營養師月費請輸入數字");
					}
				}

				String dpic = req.getParameter("dpic"); // 舊圖片
				Part part = req.getPart("dpic_changed"); // 新圖片

				if (part.getSize() != 0) { // 如果新圖片的大小不等於0, 代表有新圖片
					String directoryPath = getServletContext().getRealPath("/front_end/free/dietician/images");

					File fsaveDirectory = new File(directoryPath);
					if (!fsaveDirectory.exists()) {
						fsaveDirectory.mkdirs();
					}

					String filename = daccount + "_" + getFileNameFromPart(part);

					if (filename != null && part.getContentType() != null) {

						dpic = "/front_end/free/dietician/images/" + filename; // 改寫舊圖片的路徑

						File f = new File(fsaveDirectory, filename);
						part.write(f.getPath());
					}
				}

				Integer dno = new Integer(req.getParameter("dno"));
//				Integer dstate = new Integer(req.getParameter("dstate"));
//				Integer totalScore = new Integer(req.getParameter("totalScore"));
//				Integer totalReviewer = new Integer(req.getParameter("totalReviewer"));
//				Integer donState = new Integer(req.getParameter("donState"));
//				String offDay = req.getParameter("offDay");
//				String optTime = req.getParameter("optTime");

				DieticianVO dietician = new DieticianVO();

				dietician.setDno(dno);
				dietician.setDname(dname);
				dietician.setDaccount(daccount);
				dietician.setDpassword(dpassword);
				dietician.setDbirthDay(dbirthDay);
				dietician.setDpic(dpic);
				dietician.setDphone(dphone);
				dietician.setDaddress(daddress);
				dietician.setDemail(demail);
				dietician.setEdu(edu);
				dietician.setExp(exp);
				dietician.setLic(lic);
				dietician.setProf(prof);
				dietician.setClPrice(clPrice);
				dietician.setMprice(mprice);
				dietician.setIntro(intro);
//				dietician.setDstate(dstate);
//				dietician.setTotalScore(totalScore);
//				dietician.setTotalReviewer(totalReviewer);
//				dietician.setDonState(donState);
//				dietician.setOffDay(offDay);
//				dietician.setOptTime(optTime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dieticianVO", dietician);

					String url = "/front_end/protected/dietician/update_dietician_page01.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}

				// =====================格式驗證完成, 開始建立VO==================

				DieticianService dieticianSvc = new DieticianService();
				dietician = dieticianSvc.updateDietician1( dname, daccount, dpassword, dbirthDay, dpic, dphone,
						daddress, demail, edu, exp, lic, prof, clPrice, mprice, intro,
						dno);

				req.setAttribute("dieticianVO", dietician);

				String url = "/front_end/protected/dietician/one_dietician_page01.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("新增資料發生錯誤");
				String url = "/front_end/protected/dietician/update_dietician_page01.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);

			}

		}
		if ("update_dstate".equals(action)) {
			try {
				if(req.getParameter("self").equals("selfAll")) {
					DieticianService dieticianSvc = new DieticianService();
					DieticianService dieticianService =new DieticianService();
					Integer dno = new Integer(req.getParameter("dno"));
					Integer dstate = new Integer(req.getParameter("dstate"));
					dieticianSvc.update_dstate(dstate, dno);
					List<DieticianVO> dstateList=dieticianService.findByDieticianState(0);
					req.getSession().setAttribute("list",dstateList);
				String url = "/back_end/dietician/blank_listAllDietician.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				}
				
				else if(req.getParameter("self").equals("selfOne")) {
					DieticianService dieticianSvc = new DieticianService();
					DieticianService dieticianService =new DieticianService();
					Integer dno = new Integer(req.getParameter("dno"));
					Integer dstate = new Integer(req.getParameter("dstate"));
					dieticianSvc.update_dstate(dstate, dno);
					if(!req.getParameter("origin_dstate").isEmpty()) {
					Integer origin_dstate = new Integer(req.getParameter("origin_dstate"));
						List<DieticianVO> dstateList=dieticianService.findByDieticianState(origin_dstate);
						req.getSession().setAttribute("list",dstateList);
					} else {
						List<DieticianVO> dstateList=dieticianService.findByDieticianState(dstate);	
						req.getSession().setAttribute("list",dstateList);
			}
					String url = "/back_end/dietician/blank_listDstateDietician.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req,res);
				}
			} catch (IOException e) {
				e.printStackTrace();
		}
		}
		
		
		if("findByDieticianState".equals(action)) {
			try {
		
				DieticianService dieticianService =new DieticianService();
				Integer dstate= new Integer(req.getParameter("dstate"));
				List<DieticianVO> dstateList=dieticianService.findByDieticianState(dstate);
				req.setAttribute("oringin_dstate", dstate);
				req.getSession().setAttribute("list",dstateList);
				String url = "/back_end/dietician/blank_listDstateDietician.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if("find_demail".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String daccount= req.getParameter("daccount");
			if(daccount==null||daccount.trim().length()==0) {
					errorMsgs.add("請輸入帳號");
				}
			String demail= req.getParameter("demail");
			if(demail==null||demail.trim().length()==0) {
				errorMsgs.add("請輸入信箱");
			}
			DieticianService dietsvc=new DieticianService();
			DieticianVO dac =dietsvc.findByAccount(daccount);
		
			if(daccount.trim().length()!=0) {
			if(dac==null)
				errorMsgs.add("無此帳號");
			}
			if(demail.trim().length()!=0) {
			if(dac!=null&&!(dac.getDemail().equals(demail))) 
				errorMsgs.add("信箱錯誤，請檢查信箱");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/free/dietician/lostPassWord.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
				String x[]=new String[8];
					int y=0;
					while(y<8){
						int z=(int)((Math.random()*75)+48);
						if((z>47&&z<58)||(z>64&&z<91)||(z>96&&z<123)) {
							x[y]=Character.toString((char)z);
						y++;
						}
					}
				String password= "親愛的 "+daccount+" 會員您好，您的新密碼為:"+x[0]+x[1]+x[2]+x[3]+x[4]+x[5]+x[6]+x[7]+"，請用此組密碼登入後，修改您的密碼。";
				String password1=x[0]+x[1]+x[2]+x[3]+x[4]+x[5]+x[6]+x[7];
				dietsvc.update_demail(daccount,password1);
				String dmail=req.getParameter("demail");
				LostPassWordMailService mailService = new LostPassWordMailService();
				mailService.sendMail(dmail, "密碼通知", password );
				req.setAttribute("seccessGetPassWord", 1);
				String url = "/front_end/free/dietician/login_dietician.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		
		if("add_Dietician".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//=====================驗證資料格式=====================
			
			try {
	
				String dname = req.getParameter("dname");
				
				if(dname ==null || dname.trim().length()==0) {
					errorMsgs.add("請輸入姓名");
				}
				
				String daccount = req.getParameter("daccount");
				if(daccount ==null || daccount.trim().length()==0) {
					errorMsgs.add("請輸入帳號");
				}
				
				String dpasswordRegEx =  "^(?=.*[0-9])(?=.*[a-z]).{6,15}$";
				
				String dpassword = req.getParameter("dpassword");
				if(dpassword ==null || dpassword.trim().length()==0) {
					errorMsgs.add("請輸入密碼");
				} else if (!dpassword.matches(dpasswordRegEx)) {
					errorMsgs.add("密碼請輸入混合英文及數字6-15碼");
				}
				
				java.sql.Date dbirthDay = null;
				try {
				dbirthDay = java.sql.Date.valueOf(req.getParameter("dbirthDay"));
					
				} catch(IllegalArgumentException e) {
					errorMsgs.add("請輸入生日");
				}
				
				String dphone = req.getParameter("dphone");
				if(dphone ==null || dphone.trim().length()==0) {
					errorMsgs.add("請輸入電話");
				}
				
				String daddress = req.getParameter("daddress");
				if(dphone ==null || dphone.trim().length()==0) {
					errorMsgs.add("請輸入地址");
				}
				
				String demail = req.getParameter("demail");
				
				
				String intro = req.getParameter("intro");
				if(intro ==null || intro.trim().length()==0) {
					intro="";
				}
				
				String edu = req.getParameter("edu");
				if(edu ==null || edu.trim().length()==0) {
					edu="";
				}
				String exp = req.getParameter("exp");
				if(exp ==null || exp.trim().length()==0) {
					exp="";
				}
				String lic = req.getParameter("lic");
				if(lic ==null || lic.trim().length()==0) {
					lic="";
				}
				String prof = req.getParameter("prof");
				if(prof ==null || prof.trim().length()==0) {
					prof="";
				}
				
				Integer clPrice = null;
				if(req.getParameter("clPrice").trim().length()==0) {
					clPrice = 0;
				} else {
					try {
						clPrice = new Integer(req.getParameter("clPrice").trim());
					}catch(NumberFormatException e) {
						errorMsgs.add("諮詢價格請輸入數字");
					}
				}
				
				
				
				if(intro ==null || intro.trim().length()==0) {
					intro="";
				}
				
				
				Integer mprice = null;
				if(req.getParameter("mprice").trim().length()==0) {
					mprice = 0;
				} else {
					try {
						mprice = new Integer(req.getParameter("mprice").trim());
					}catch(NumberFormatException e) {
						errorMsgs.add("營養師月費請輸入數字");
					}
				}
				

				String dpic= "";
				
				String directoryPath = getServletContext().getRealPath("/front_end/free/dietician/images") ;
				
				File fsaveDirectory = new File(directoryPath);
				if (!fsaveDirectory.exists()) {
					 fsaveDirectory.mkdirs();
				}
				
				Part part = req.getPart("dpic");
				String filename = daccount + "_" + getFileNameFromPart(part);
				
				
						
				if(filename != null && part.getContentType()!=null ) {
					
					dpic="/front_end/free/dietician/images/" + filename;
					File f = new File(fsaveDirectory, filename);
					part.write(f.getPath());
				}
				
				Integer dstate = 0;
				Integer totalScore = 0;
				Integer totalReviewer = 0;
				Integer donState = 0;
				String offDay = "";
				String optTime = "";
				
				DieticianVO dietician = new DieticianVO();

				dietician.setDname(dname);
				dietician.setDaccount(daccount);
				dietician.setDpassword(dpassword);
				dietician.setDbirthDay(dbirthDay);
				dietician.setDpic(dpic);
				dietician.setDphone(dphone);
				dietician.setDaddress(daddress);
				dietician.setDemail(demail);
				dietician.setEdu(edu);
				dietician.setExp(exp);
				dietician.setLic(lic);
				dietician.setProf(prof);
				dietician.setClPrice(clPrice);
				dietician.setMprice(mprice);
				dietician.setIntro(intro);
				dietician.setDstate(dstate);
				dietician.setTotalScore(totalScore);
				dietician.setTotalReviewer(totalReviewer);
				dietician.setDonState(donState);
				dietician.setOffDay(offDay);
				dietician.setOptTime(optTime);
				
				
				
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("dieticianVO", dietician);
				
				String url = "/front_end/free/dietician/add_dietician_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
	
			//=====================格式驗證完成, 開始建立VO==================
			
			DieticianService dieticianSvc = new DieticianService();
				
				dietician = dieticianSvc.addDietician(dname, daccount, dpassword, dbirthDay, dpic, dphone, daddress,
						demail, edu, exp, lic, prof, clPrice, mprice, intro, dstate, totalScore, totalReviewer,
						donState, offDay, optTime);
			
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			

			} catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("新增資料發生錯誤");
				String url = "/front_end/free/dietician/add_dietician_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				
			}
			
			

		}
		//愷增加  更新營養師的預約設定
		if("updateAppointmentInfo".equals(action)) {
			try {
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				String [] offday = req.getParameterValues("schDate");
				String optTimeFrom = req.getParameter("from");
				String optTimeTo = req.getParameter("to");
				Integer dno =new Integer(req.getParameter("dieticiandno"));
				int intFrom= Integer.parseInt(optTimeFrom);

				int intTo= Integer.parseInt(optTimeTo);
				String week=new String("0000000");
				StringBuffer weeked = new StringBuffer(week);
				
				
				if(!(offday==null)){ 
				for(String element:offday) {
					System.out.print(element+",");
					int weekint= Integer.parseInt(element);					
					weeked.setCharAt(weekint, '1');
										
				}}else {
					errorMsgs.add("請點選排休日");
				}
				
				System.out.println("更改後的int"+intFrom);
				System.out.println("更改過後"+weeked);//ok
				System.out.println();
				System.out.println("開始"+optTimeFrom);
				System.out.println("結束"+optTimeTo);


				
				String hours=new String("222222222222222222222222");
				StringBuffer hoursed = new StringBuffer(hours);
				
//			hoursed.setCharAt(intFrom, '2');
//			hoursed.setCharAt(intTo, '2');

				
				for( int i=intFrom ;i<intTo; i++) {
					hoursed.setCharAt(i, '0');
				}
				String offday1=new String(weeked);
				String opttime1=new String(hoursed);
				
				
				System.out.println("更改過後"+hoursed);//ok
				
				DieticianVO dieticainVO = new DieticianVO();
				dieticainVO.setOffDay(offday1);
				dieticainVO.setOptTime(opttime1);
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("dieticainVO", dieticainVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/protected/appointment/appointment_infoEdit.jsp");
					failureView.forward(req, res);
					return;
				}
				DieticianService dieticianSvc = new DieticianService();
				DieticianVO dieticianVO1=dieticianSvc.findByPrimaryKey(dno);
				
				String dname=dieticianVO1.getDname();
				String daccount=dieticianVO1.getDaccount();
				String dpassword=dieticianVO1.getDpassword();
				Date dbirthDay= (dieticianVO1.getDbirthDay());
				String dpic=dieticianVO1.getDpic();
				String dphone=dieticianVO1.getDphone();
				String daddress=dieticianVO1.getDaddress();
				String demail=dieticianVO1.getDemail();
				String edu=dieticianVO1.getEdu();
				String exp=dieticianVO1.getExp();
				String lic=dieticianVO1.getLic();
				String prof=dieticianVO1.getProf();
				Integer clPrice=dieticianVO1.getClPrice();
				Integer mprice=dieticianVO1.getMprice();
				String intro=dieticianVO1.getIntro();
				Integer dstate=dieticianVO1.getDstate();
				Integer totalScore=dieticianVO1.getTotalScore();
				Integer totalReviewer=dieticianVO1.getTotalReviewer();
				Integer donState=dieticianVO1.getDonState();

				
				
				
				
				DieticianVO dieticianVO=dieticianSvc.updateDietician(dno, dname, daccount, dpassword, dbirthDay, dpic, dphone, daddress, demail, edu, exp, lic, prof, clPrice, mprice, intro, dstate, totalScore, totalReviewer, donState, offday1, opttime1);
				req.setAttribute("dieticianVO", dieticianVO);
				String url = "/front_end/protected/appointment/appointment_infoEdit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	

			
			
			
			
			
		}
		
		
		if("updateTotalScore".equals(action)) {
			Integer dno=new Integer(req.getParameter("dno"));
			Integer score =new Integer(req.getParameter("updateScore"));
			DieticianService dieticianSvc=new DieticianService();
			dieticianSvc.updateTotalScore(dno,score);
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/dietician/select_dietician01.jsp");
			failureView.forward(req, res);
			return;
		}
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	
	
	
}
