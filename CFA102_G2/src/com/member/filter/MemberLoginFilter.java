package com.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session =req.getSession();
		
		//以下三行為登出後，點擊上一頁不會看到登入中的畫面
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		res.setDateHeader("Expires", 0); // Proxies.
		
		Object account = session.getAttribute("account");
		if(account == null) {   //判斷是否登入過
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front_end/free/member/memberLogin.jsp");
			return;			
			
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
