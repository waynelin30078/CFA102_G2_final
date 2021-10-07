package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import mail.*;
public class MemberLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");

		if ("signIn".equals(action)) {
			try {

				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

				req.setAttribute("errorMsgs", errorMsgs);

				String mid = req.getParameter("mem_id");
				String mpsw = req.getParameter("mem_pwd");

				if (mid == null || (mid.trim().length() == 0)) {

					errorMsgs.put("mem_id", "請輸入帳號");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				if (mpsw == null || (mpsw.trim().length() == 0)) {
				
					errorMsgs.put("mem_pwd", "請輸入密碼");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = new MemberVO();

				memberVO = memberSvc.getOneMemberByMid(mid);  //用帳號去找

//System.out.println(memberVO.getMno());
//System.out.println(memberVO.getMname());
			
			
				if (memberVO == null && mid.trim().length() != 0) {

					errorMsgs.put("mem_id", "此帳號尚未註冊");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				}

//				memberVO.setMid(mid);

				MemberVO memberVO1 = memberSvc.isUser(mid, mpsw);

//				System.out.println(memberVO1.getMno());
//				System.out.println(memberVO1.getMname());
				if (memberVO1 == null && memberVO != null) {

					errorMsgs.put("mem_pwd", "密碼錯誤");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				if(memberVO1 != null && memberVO1.getMstate() == 0) {
					errorMsgs.put("mem_pwd", "此帳號已被停權");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/memberLogin.jsp");
					failureView.forward(req, res);
					return;
				} else if (memberVO1 != null) {
					String mname =memberVO1.getMname();
					Integer mno= memberVO1.getMno();
					HttpSession session = req.getSession();
					session.setAttribute("account", mid); //*工作1: 才在session內做已經登入過的標識
					session.setAttribute("accountname", mname);   //設定姓名顯示置網頁
					session.setAttribute("mno", mno);   //設定員工PK給後面抓
					session.setAttribute("memberVO1", memberVO1);
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {

					}
				}
			} catch (Exception e) {

			}
		res.sendRedirect(req.getContextPath()+"/front_end/free/home.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}
		if ("signOut".equals(action)) {
			HttpSession session1 = req.getSession();
			if (!session1.isNew()) {
				session1.removeAttribute("account");
				String url = "/front_end/free/home.jsp";
				res.sendRedirect(req.getContextPath()+url);
				return;
			}
		}
		if ("forgetpassword".equals(action)) {
			  String mem_mail=req.getParameter("mem_mail");   //取得輸入的email
				List<String> resendMail = new LinkedList<String>();
				req.setAttribute("resendMail", resendMail);
				MemberService memberSvc = new MemberService();
				MemberVO memberVO2 = new MemberVO();
				Optional<MemberVO> result;
				result = memberSvc.getAll()
						.stream()
						.filter(f -> f.getMmail().equals(mem_mail))
						.findFirst();
				if (result.isPresent()) {
					String to = mem_mail;   //收件人
					String subject = "密碼通知";   //主旨
					MemberVO memberVO=result.get();
					String mname =memberVO.getMname();//取得會員名子
					Integer mno =memberVO.getMno();  //取得會員編號
					String mpsw = memberVO.getMpsw();
					String mid = memberVO.getMid();  //取得帳號
					final Base64.Encoder encoder = Base64.getEncoder();
					final byte[] mpswByte = mpsw.getBytes("UTF-8");
					final String mpswUrl = encoder.encodeToString(mpswByte); //轉成阿公拿掃把
					final Base64.Decoder decoder = Base64.getDecoder();
					String mpsw1=new String(decoder.decode(mpswUrl), "UTF-8");
					System.out.println(mpsw1);
					String ch_name = "mname";   //會員名稱
					String url = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/front_end/free/member/resetpassword.jsp?member_mid="+mid+"&"+"member_password="+mpswUrl;  //連結
					System.out.println(url);
					String messageText = "Hello! " + mname + " 請點擊此連結: "  +url+"這個連結"+ "\n" +" (更改密碼)";  //內容 
					MailService mailService = new MailService();
					mailService.sendMail(to, subject, messageText,url);  //(收件人，主旨，內容)
					resendMail.add("請至信箱收信件，用新的密碼登入");
					String url1 = "/front_end/free/member/memberLogin.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url1);
					successView.forward(req, res);
				}else {
					System.out.println("沒有郵件");
					resendMail.add("此email尚未註冊");
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/free/member/forgetpassword.jsp");
					failureView.forward(req, res);
				}
		}
		if ("resetpassword".equals(action)) { //輸入email以後重新設定會員密碼
			String mpassword=req.getParameter("mem_password");    //取得新的密碼
			Integer mno=new Integer(req.getParameter("mno"));    //取得會員編號
			MemberService memberSvc =new MemberService();
			List<String> resendMail = new LinkedList<String>();
			req.setAttribute("resendMail", resendMail);
			MemberVO memberVO=memberSvc.resetPassword(mpassword, mno);
			req.setAttribute("memberVO", memberVO);
			resendMail.add("已更新好，請用新的密碼登入");
			String url = "/front_end/free/member/memberLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}


