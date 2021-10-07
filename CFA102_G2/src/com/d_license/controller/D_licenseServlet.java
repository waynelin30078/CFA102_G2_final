package com.d_license.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.d_license.model.DlicenseService;
import com.d_license.model.DlicenseVO;
import com.dietician.model.DieticianService;
import com.dietician.model.DieticianVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
* 1024)
public class D_licenseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res); 
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
	
			Integer dno=new Integer(req.getParameter("dno"));
			String licDesc=req.getParameter("licDesc");
			
			String dpic = "";

			String directoryPath = getServletContext().getRealPath("/testImg");
			File fsaveDirectory = new File(directoryPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			}
			Part part = req.getPart("licFile");
			String filename = dno + "_" + getFileNameFromPart(part);
			if (filename != null && part.getContentType() != null) {

				dpic = "/testImg/" + filename;
				File f = new File(fsaveDirectory, filename);
				part.write(f.getPath());
			}
			DlicenseVO dlicense =new DlicenseVO();

			dlicense.setDno(dno);
			dlicense.setLicDesc(licDesc);
			dlicense.setLicFile(dpic);
		
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dlicenseVO", dlicense);
				String url = "/front_end/free/dietician/add_dietician_page.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}

			// =====================格式驗證完成, 開始建立VO==================

			DlicenseService dlicenseSvc = new DlicenseService();
			dlicense=dlicenseSvc.addDlicense(dno,licDesc,dpic);
			String url = "/front_end/protected/dietician/one_dietician_page01.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("新增資料發生錯誤");
			String url = "/front_end/protected/dietician/one_dietician_page01.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url);
			failureView.forward(req, res);

		}
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
