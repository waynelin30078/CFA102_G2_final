package com.diary.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity_record.model.ActivityRecordService;
import com.activity_record.model.ActivityRecordVO;
import com.diary.model.DiaryService;
import com.diary.model.DiaryVO;
import com.meal.model.MealService;
import com.meal.model.MealVO;
import com.member.model.MemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)

public class DiaryServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		
		
		
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("foodRecords") != null) {
			session.removeAttribute("foodRecords");
		}
		
		
		
		if("add_diary".equals(action)) {
			
			try {
				
				MemberVO member = (MemberVO)session.getAttribute("memberVO1");
				Date date = Date.valueOf(req.getParameter("date"));
				
				DiaryService diarySvc = new DiaryService();

				DiaryVO checkdiary = diarySvc.findByDate(member.getMno(), date);
				
				if(checkdiary.getDiaryDate() != null) {
					
					String url ="/diary/diary.do?action=show_diary_page&date=" + date;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}
				
				DiaryVO diary = new DiaryVO();
				
				diary.setMno(member.getMno());    //要確認
				diary.setDno(member.getDno());
				diary.setDiaryDate(date); //
				diary.setHt(0);
				diary.setWt(0);
				diary.setBodyFat(0.0);
				diary.setWc(0);
				diary.setBodyPic("");
				diary.setViewState(0);
				diary.setReply("");
				diary.setTotalCal(0.0);
				diary.setTotalCho(0.0);
				diary.setTotalPro(0.0);
				diary.setTotalFat(0.0);
				diary.setTotalCalBurn(0.0);
				
				
				diarySvc.addDiary(diary);
				
				
				
				
				
				diary = diarySvc.findByDate(member.getMno(), date);
				
				ActivityRecordService actRecordSvc = new ActivityRecordService();
				ActivityService activitySvc = new ActivityService();
				MealService mealSvc = new MealService();
				
			
				List<ActivityRecordVO> actRecords = actRecordSvc.findByDiaryNo(diary.getDiaryNo());
				List<MealVO> meals = mealSvc.findByDiaryNo(diary.getDiaryNo());
				
				
				
				req.setAttribute("diary", diary);
				req.setAttribute("actRecords", actRecords);
				req.setAttribute("meals", meals);
				req.setAttribute("activitySvc", activitySvc);
				session.setAttribute("diary", diary);
				
				
				
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				
				
				} catch(Exception e) {
					e.printStackTrace();
					String url = "/front_end/protected/diary/diary_calendar_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				return;
				}
			
			
			
		}

		if("show_diary_page".equals(action)) {
			
			try {
				
				MemberVO member = (MemberVO)session.getAttribute("memberVO1");
				Date date = Date.valueOf(req.getParameter("date"));
				DiaryService diarySvc = new DiaryService();
				Integer mno = member.getMno();
							
				ActivityRecordService actRecordSvc = new ActivityRecordService();
				ActivityService activitySvc = new ActivityService();
				MealService mealSvc = new MealService();
				
				DiaryVO diary = diarySvc.findByDate(mno, date);
				List<ActivityRecordVO> actRecords = actRecordSvc.findByDiaryNo(diary.getDiaryNo());
				List<MealVO> meals = mealSvc.findByDiaryNo(diary.getDiaryNo());
				
				req.setAttribute("diary", diary);
				req.setAttribute("actRecords", actRecords);
				req.setAttribute("meals", meals);
				req.setAttribute("activitySvc", activitySvc);
				
				
//				測試session
				session.setAttribute("diary", diary);
				
				
				
				
				
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}catch(Exception e) {
					e.printStackTrace();
					String url = "/front_end/protected/diary/diary_calendar_page.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
			
	}

		
		if("add_bodyPic".equals(action)) {
			
			try {
				DiaryService diarySvc = new DiaryService();
				Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));
				DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
				
				String bodyPic= "";
				String directoryPath = getServletContext().getRealPath("/front_end/protected/diary/images") ;
				
				File fsaveDirectory = new File(directoryPath);
				if (!fsaveDirectory.exists()) {
					 fsaveDirectory.mkdirs();
				}
				
				Part part = req.getPart("bodyPic");
			
				String filename = diary.getDiaryNo() + "_" + getFileNameFromPart(part);
						
				if(filename != null && part.getContentType()!=null ) {
					
					bodyPic="/front_end/protected/diary/images/" + filename;
					File f = new File(fsaveDirectory, filename);
					part.write(f.getPath());
				}
				
				diary.setBodyPic(bodyPic);
				diarySvc.updateDiary(diary);
				
				
				diary = diarySvc.findByDiaryNo(diaryNo);
				req.setAttribute("diary", diary);
				
				String url ="/front_end/protected/diary/diary_page.jsp?";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			}catch(Exception e) {
				e.printStackTrace();
				String url = "/front_end/protected/diary/diary_calendar_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		}
		
		if("update_bodyPic".equals(action)) {
			
			try {
				DiaryService diarySvc = new DiaryService();
				Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));
				DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
				
				String bodyPic= diary.getBodyPic();
				String directoryPath = getServletContext().getRealPath("/front_end/protected/diary/images") ;
				
				File fsaveDirectory = new File(directoryPath);
				if (!fsaveDirectory.exists()) {
					 fsaveDirectory.mkdirs();
				}
				
				Part part = req.getPart("bodyPic_changed");
			
				String filename = diary.getDiaryNo() + "_" + getFileNameFromPart(part);
						
				if(filename != null && part.getContentType()!=null ) {
					
					bodyPic="/front_end/protected/diary/images/" + filename;
					File f = new File(fsaveDirectory, filename);
					part.write(f.getPath());
					
				}
				
				diary.setBodyPic(bodyPic);
				diarySvc.updateDiary(diary);
				
				
				diary = diarySvc.findByDiaryNo(diaryNo);
				req.setAttribute("diary", diary);
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			}catch(Exception e) {
				e.printStackTrace();
				String url = "/front_end/protected/diary/diary_calendar_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		}
		
		if("update_bodyRecord".equals(action)) {
			
			try {
				DiaryService diarySvc = new DiaryService();
				Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));
				DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
				
				
				Integer ht = 0;
				if(!req.getParameter("ht").isEmpty()) {
				ht = new Integer((String)req.getParameter("ht"));
				}
				
				Integer wt = 0;
				if(!req.getParameter("wt").isEmpty()) {
				wt = new Integer((String)req.getParameter("wt"));
				}
				
				
				
				Double bodyFat = 0.0;
				if(!req.getParameter("bodyFat").isEmpty()) {
					bodyFat = new Double((String)req.getParameter("bodyFat"));
				}
				
				
				
				Integer wc = 0;
				if(!req.getParameter("wc").isEmpty()) {
				wc = new Integer((String)req.getParameter("wc"));
				}
				
				
				
				diary.setHt(ht);
				diary.setWt(wt);
				diary.setBodyFat(bodyFat);
				diary.setWc(wc);
				
				diarySvc.updateDiary(diary);
				
				
				diary = diarySvc.findByDiaryNo(diaryNo);
				req.setAttribute("diary", diary);
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

			}catch(Exception e) {
				e.printStackTrace();
				String url = "/front_end/protected/diary/diary_calendar_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		}
		
		
		if("add_activity_record".equals(action)) {
			
			DiaryService diarySvc = new DiaryService();
			ActivityService activitySvc = new ActivityService();
			ActivityRecordService actRecordSvc = new ActivityRecordService();
			ActivityRecordVO activityRecord = new  ActivityRecordVO();
			
			Integer diaryNo = new Integer(req.getParameter("diaryNo"));
			DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
			
			
			try {
				System.out.println("新增運動紀錄");
				
				Integer actNo = new Integer(req.getParameter("actNo"));
				Double actHr = new Double(req.getParameter("actHr"));
				Integer wt = new Integer(req.getParameter("wt"));
				Double calBurn = new Double(req.getParameter("calBurn"));
			
				activityRecord.setDiaryNo(diaryNo);
				activityRecord.setActNo(actNo);
				activityRecord.setActHr(actHr);
				activityRecord.setWt(wt);
				activityRecord.setCalBurn(calBurn);
				
				
				actRecordSvc.addActivity(diaryNo, activityRecord);
				
				
				
				
				List<ActivityRecordVO> actRecords = actRecordSvc.findByDiaryNo(diaryNo);
				
				req.setAttribute("activitySvc", activitySvc);
				req.setAttribute("actRecords", actRecords);
				req.setAttribute("diary", diary);
				
				String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				return;

			}catch(RuntimeException re) {
				
				String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
				
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}catch(Exception e) {
				e.printStackTrace();
				String url = "/front_end/protected/diary/diary_calendar_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		}
		
		if("delete_activity_record".equals(action)) {
			
			DiaryService diarySvc = new DiaryService();
			Integer diaryNo = new Integer(req.getParameter("diaryNo"));
			DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
			
			try {
				System.out.println("刪除運動");
				
				ActivityService activitySvc = new ActivityService();
				ActivityRecordService actRecordSvc = new ActivityRecordService();
				
				
				
				Integer actNo = new Integer(req.getParameter("actNo"));
				
				
				
				ActivityRecordVO activityRecord = actRecordSvc.findByPrimaryKey(diaryNo, actNo);
				
				actRecordSvc.deleteActivity(diary, activityRecord);
				
				activityRecord = actRecordSvc.findByPrimaryKey(diaryNo, actNo);
				List<ActivityRecordVO> actRecords = actRecordSvc.findByDiaryNo(diary.getDiaryNo());
				diary = diarySvc.findByDiaryNo(diaryNo);
				
				req.setAttribute("actRecords", actRecords);
				req.setAttribute("diary", diary);
				
				
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				return;
			
			
			}catch(RuntimeException re) {
				
				String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
				
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}catch(Exception e) {
				e.printStackTrace();
				String url = "/front_end/protected/diary/diary_calendar_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			
			
			
		}
		
			if("delete_meal".equals(action)) {
			
				Integer diaryNo = new Integer(req.getParameter("diaryNo"));
				DiaryService diarySvc = new DiaryService();
				DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
				
				
			try {
				MealService mealSvc = new MealService();
				
				Integer mealNo = new Integer(req.getParameter("mealNo"));
				
				mealSvc.deleteMeal(mealNo);
				
				List<MealVO> meals = mealSvc.findByDiaryNo(diaryNo);
				
				diary = diarySvc.findByDiaryNo(diaryNo);
				req.setAttribute("meals", meals);
				req.setAttribute("diary", diary);
				
				String url ="/front_end/protected/diary/diary_page.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				return;
			}catch(RuntimeException re) {
				
				String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
				
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}catch(Exception e) {
				e.printStackTrace();
				String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
		
			}
		
		
		//String redirectPath = req.getContextPath() + "/front_end/protected/diary/diary_calendar_page.jsp";
		
			
		DiaryVO diary = (DiaryVO) session.getAttribute("diary");	
		System.out.println(diary);
		System.out.println(diary.getDiaryDate());
		
		String redirectPath = req.getContextPath() +"/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
		res.sendRedirect(redirectPath);
		
		
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
