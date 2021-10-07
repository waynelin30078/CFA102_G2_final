package com.meal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diary.model.*;
import com.food.model.FoodService;
import com.food.model.FoodVO;
import com.food_record.model.*;
import com.diary.model.*;
import com.meal.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024 * 1024)

public class MealServlet extends HttpServlet {

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			
			HttpSession session = req.getSession();
			
			if("add_meal_page".equals(action)) {
				
				
				try {	
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					MealVO meal = new MealVO();
					meal.setDiaryNo(diaryNo);
					meal.setMealName("");
					meal.setMealCal(0.0);
					meal.setMealCho(0.0);
					meal.setMealPro(0.0);
					meal.setMealFat(0.0);
					
					req.setAttribute("meal", meal);
					String url ="/front_end/protected/diary/add_meal_page.jsp";
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
			
			if("check_food".equals(action)) {
				
				
				try {	
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					FoodService foodSvc = new FoodService();
					
					Integer fdNo = new Integer(req.getParameter("fdNo"));
					FoodVO food = foodSvc.findByFdNo(fdNo);
					
					req.setAttribute("food", food);
					
					String fdName = req.getParameter("fdName_maintain");
					String fdBrand = req.getParameter("fdBrand_maintain");
					Integer fdState = new Integer((String)req.getParameter("fdState"));
					
					List<FoodVO> foodList = foodSvc.getAll()
												   .stream()
												   .filter(f -> f.getFdName().contains(fdName))
												   .filter(f -> f.getFdBrand().contains(fdBrand))
												   .filter(f -> f.getFdState().equals(fdState))
											       .collect(Collectors.toList());
					
					req.setAttribute("fdName", fdName);
					req.setAttribute("fdBrand", fdBrand);
					req.setAttribute("fdState", fdState);
					req.setAttribute("foodList", foodList);
					
					String url ="/front_end/protected/diary/add_meal_page.jsp";
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
			
			if("search_food".equals(action)) {
				
				
				try {	
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					
					
					String fdName = req.getParameter("fdName");
					String fdBrand = req.getParameter("fdBrand");
					Integer fdState = new Integer((String)req.getParameter("fdState"));
					FoodService foodSvc = new FoodService();
					List<FoodVO> foodList = foodSvc.getAll()
												   .stream()
												   .filter(f -> f.getFdName().contains(fdName))
												   .filter(f -> f.getFdBrand().contains(fdBrand))
												   .filter(f -> f.getFdState().equals(fdState))
											       .collect(Collectors.toList());
					
					
					req.setAttribute("fdName", fdName);
					req.setAttribute("fdBrand", fdBrand);
					req.setAttribute("fdState", fdState);
					req.setAttribute("foodList", foodList);
					
					String url ="/front_end/protected/diary/add_meal_page.jsp";
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
			
			
				if("search_my_food".equals(action)) {
				
				
				try {	
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					
					Integer mno = diary.getMno();
					
					String fdName = req.getParameter("fdName");
					String fdBrand = req.getParameter("fdBrand");
					Integer fdState = new Integer((String)req.getParameter("fdState"));
					FoodService foodSvc = new FoodService();
					List<FoodVO> foodList = foodSvc.getAll()
												   .stream()
												   .filter(f -> f.getFdName().contains(fdName))
												   .filter(f -> f.getFdBrand().contains(fdBrand))
												   .filter(f -> f.getFdState().equals(fdState))
												   .filter(f -> f.getMno().equals(mno))
											       .collect(Collectors.toList());
					
					
					req.setAttribute("fdName", fdName);
					req.setAttribute("fdBrand", fdBrand);
					req.setAttribute("fdState", fdState);
					req.setAttribute("myFoodList", foodList);	
					
					String url ="/front_end/protected/diary/add_meal_page.jsp";
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
			
				if("check_my_food".equals(action)) {
					
					
					try {	
						Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
						DiaryService diarySvc = new DiaryService();
						DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
						req.setAttribute("diary", diary);
						
						Integer mno = diary.getMno();
						
						FoodService foodSvc = new FoodService();
						
						Integer fdNo = new Integer(req.getParameter("fdNo"));
						FoodVO food = foodSvc.findByFdNo(fdNo);
						
						req.setAttribute("myFood", food);
						
						String fdName = req.getParameter("fdName_my_maintain");
						String fdBrand = req.getParameter("fdBrand_my_maintain");
						Integer fdState = new Integer((String)req.getParameter("fdState"));
						
						List<FoodVO> foodList = foodSvc.getAll()
													   .stream()
													   .filter(f -> f.getFdName().contains(fdName))
													   .filter(f -> f.getFdBrand().contains(fdBrand))
													   .filter(f -> f.getFdState().equals(fdState))
													   .filter(f -> f.getMno().equals(mno))
												       .collect(Collectors.toList());
						
						req.setAttribute("fdName", fdName);
						req.setAttribute("fdBrand", fdBrand);
						req.setAttribute("fdState", fdState);
						req.setAttribute("myFoodList", foodList);
						
						String url ="/front_end/protected/diary/add_meal_page.jsp";
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

			
			
			if("add_food_record".equals(action)) {
				
				Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
				DiaryService diarySvc = new DiaryService();
				DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
				req.setAttribute("diary", diary);
				
				List<FoodRecordVO> foodRecords = (List<FoodRecordVO>)session.getAttribute("foodRecords");
				
				try {	
					
					if(foodRecords == null) {
						foodRecords = new ArrayList<FoodRecordVO>();
					}
					
					FoodRecordVO foodRecord = new FoodRecordVO();
					
					Integer fdNo = new Integer(req.getParameter("fdNo"));
					Integer wtPerPortion = new Integer(req.getParameter("wtPerPortion"));
					Double singlelCal = new Double(req.getParameter("singlelCal"));
					Double singleCho = new Double(req.getParameter("singleCho"));
					Double singlePro = new Double(req.getParameter("singlePro"));
					Double singleFat = new Double(req.getParameter("singleFat"));
					
					
					foodRecord.setFdNo(fdNo);
					
					if(!req.getParameter("fdPortion").isEmpty()) {
						Integer fdPortion = new Integer(req.getParameter("fdPortion"));
						
						foodRecord.setFdPortion(fdPortion);
						foodRecord.setFdWt(0);
						foodRecord.setSinglelCal(singlelCal * fdPortion);
						foodRecord.setSingleCho(singleCho * fdPortion);
						foodRecord.setSinglePro(singlePro * fdPortion);
						foodRecord.setSingleFat(singleFat * fdPortion);
						
					} else {
						Integer fdWt = new Integer(req.getParameter("fdWt"));
						foodRecord.setFdPortion(0);
						foodRecord.setFdWt(fdWt);
						foodRecord.setSinglelCal(singlelCal/wtPerPortion * fdWt);
						foodRecord.setSingleCho(singleCho/wtPerPortion * fdWt);
						foodRecord.setSinglePro(singlePro/wtPerPortion * fdWt);
						foodRecord.setSingleFat(singleFat/wtPerPortion * fdWt);
					}
					
					foodRecords.add(foodRecord);
					session.setAttribute("foodRecords", foodRecords);
					
					String url ="/front_end/protected/diary/add_meal_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}catch(NumberFormatException nfe) {
					nfe.printStackTrace();
					String url ="/meal/meal.do?action=add_meal_page&diaryNo=" + diary.getDiaryNo();
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
			
				if("delete_food_record".equals(action)) {
				
				
				
				
				List<FoodRecordVO> foodRecords = new ArrayList<FoodRecordVO>();
				
				foodRecords = (ArrayList<FoodRecordVO>)session.getAttribute("foodRecords");
				
				
				try {	
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					
					int fdIndex = new Integer((String)req.getParameter("fdIndex"));
					
					foodRecords.remove(fdIndex);
					session.setAttribute("foodRecords", foodRecords);
					
					String url ="/front_end/protected/diary/add_meal_page.jsp";
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
				
				if("add_new_meal".equals(action)) {
					
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					List<FoodRecordVO> foodRecords = new ArrayList<FoodRecordVO>();
					
					foodRecords = (ArrayList<FoodRecordVO>)session.getAttribute("foodRecords");
					
					
					try {	

						
						
						String mealName = req.getParameter("mealName");
						
						MealVO meal = new MealVO();
						meal.setDiaryNo(diaryNo);
						meal.setMealName(mealName);
						meal.setMealCal(0.0);
						meal.setMealCho(0.0);
						meal.setMealPro(0.0);
						meal.setMealFat(0.0);
						
						
						MealService mealSvc = new MealService();
						mealSvc.insertMeal(meal, foodRecords);
						
								


						req.setAttribute("meal", meal);
						
						session.removeAttribute("foodRecords");
						
						
						
						//String url ="/front_end/protected/diary/diary_page.jsp?action=show_diary_page&date=" + diary.getDiaryDate();
						String url ="/diary/diary.do?action=show_diary_page&date=" + diary.getDiaryDate();
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}catch(NullPointerException npe) {
						npe.printStackTrace();
						String url ="/meal/meal.do?action=add_meal_page&diaryNo=" + diary.getDiaryNo();
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
			
				if("add_my_food".equals(action)) {
					Integer diaryNo = new Integer((String)req.getParameter("diaryNo"));		
					DiaryService diarySvc = new DiaryService();
					DiaryVO diary = diarySvc.findByDiaryNo(diaryNo);
					req.setAttribute("diary", diary);
					
					
					List<FoodRecordVO> foodRecords = (List<FoodRecordVO>)session.getAttribute("foodRecords");
					
					
					try {	

						
						if(foodRecords == null) {
							foodRecords = new ArrayList<FoodRecordVO>();
						}
						
						FoodService foodSvc = new FoodService();
						
						Integer mno = new Integer(req.getParameter("mno"));
						String fdName = req.getParameter("fdName");
						String fdBrand = req.getParameter("fdBrand");
						Integer wtPerPortion = new Integer(req.getParameter("wtPerPortion"));
						Double calPerWt = new Double(req.getParameter("calPerWt"));
						Double choPerWt = new Double(req.getParameter("choPerWt"));
						Double proPerWt = new Double(req.getParameter("proPerWt"));
						Double fatPerWt = new Double(req.getParameter("fatPerWt"));
						Integer fdState = new Integer(req.getParameter("fdState"));
						
						
						foodSvc.addFood(mno, fdName, wtPerPortion, calPerWt, choPerWt, proPerWt, fatPerWt, fdBrand, fdState);
						

						
						
						session.setAttribute("foodRecords", foodRecords);
						
						String url ="/front_end/protected/diary/add_meal_page.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}catch(NumberFormatException nfe) {
						nfe.printStackTrace();
						String url ="/meal/meal.do?action=add_meal_page&diaryNo=" + diary.getDiaryNo();
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
				
				
				DiaryVO diary = (DiaryVO) session.getAttribute("diary");
				String redirectPath = req.getContextPath() +"/meal/meal.do?action=add_meal_page&diaryNo=" + diary.getDiaryNo();
				res.sendRedirect(redirectPath);
		}

		
		
		
		
}