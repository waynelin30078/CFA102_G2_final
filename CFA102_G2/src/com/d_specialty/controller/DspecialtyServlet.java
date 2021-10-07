package com.d_specialty.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.d_license.model.DlicenseService;
import com.d_specialty.model.DspecialtyService;
import com.d_specialty.model.DspecialtyVO;
import com.dietician.model.DieticianService;
import com.dietician.model.DieticianVO;
import com.specialty.model.SpecialtyService;
import com.specialty.model.SpecialtyVO;

public class DspecialtyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action=req.getParameter("action");	
		List<String> errorMsgs = new ArrayList<String>();
		
		req.setCharacterEncoding("UTF-8");
		if("findBySpecialty".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			String str[]=req.getParameterValues("vehicle1");
			if(str==null) {
				errorMsgs.add("請勾選專長");
				String url = "/front_end/free/dietician/select_dietician01.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return ;
			}

			Set<DieticianVO> set = new HashSet<DieticianVO>();
			if(str!=null) {
				int num[]=new int[str.length];
				for(int i=0;i<str.length;i++) {
					num[i]=Integer.parseInt(str[i]);
				}
				DspecialtyService dspecialtySvc=new DspecialtyService();
				DieticianService dieticianSvc=new DieticianService();
				for(int specialtyNo :num) {
					List<DspecialtyVO> dno= dspecialtySvc.findByDno1(specialtyNo);
					for(DspecialtyVO d:dno) {
					DieticianVO dieticianVO =new DieticianVO();
					dieticianVO=dieticianSvc.findByPrimaryKey(d.getDno());
					
						set.add(dieticianVO);
					}
					
				}
			}
			req.setAttribute("set", set);

			String url = "/front_end/free/dietician/select_dietician01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

	
 
}
