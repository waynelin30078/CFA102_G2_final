<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.member.model.*" %>
   <%@ page import ="java.util.*"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
  
   
    <%
  String mid = request.getParameter("member_mid"); //從郵件中取得會員編號
  String mpsw = request.getParameter("member_password"); //從郵件中取得會員編號
  System.out.println(mpsw);
  final Base64.Decoder decoder = Base64.getDecoder();
  
  String mpsw1=new String(decoder.decode(mpsw), "UTF-8");
  
  MemberService memberSvc = new MemberService();
  

  System.out.println(mpsw1);
  MemberVO memberVO = memberSvc.isUser(mid, mpsw1);

%>
   <jsp:useBean id="memberVO1" scope="page" class="com.member.model.MemberVO"/>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->












 <!-- Login & Register Section Start -->
        <div class="section section-padding">
            <div class="container">

                <!-- Register & Login Wrapper Start -->
                <div class="register-login-wrapper">
                    <div class="row justify-content-center">
                        <div class="col-lg-6 col-xl-5">
                            <!-- Register & Login Form Start -->
                            <div class="register-login-form">
                                <h3 class="title"> <%=memberVO.getMname()%>請重新設定密碼</h3>

                                <div class="form-wrapper">
                                    <form action="<%=request.getContextPath()%>/memberLoginHandler.do" METHOD="post">
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="password"  name="mem_password" placeholder="Password"> 
                                        </div>
                                        <!-- Single Form End -->
                                   
                                        <div class="single-form">
                                        	<input type="hidden" name="mno" value=<%=memberVO.getMno() %>>
                                        	<input type="hidden" name="action" value="resetpassword">
                                            <button class="btn btn-primary btn-hover-dark w-100">確定</button>
                                            
                                        </div>
                                        <!-- Single Form End -->
                                   
                                        
                                    </form>
                                </div>
                            </div>
                            <!-- Register & Login Form End -->

                        </div>
                    </div>
                </div>
                <!-- Register & Login Wrapper End -->












<!-- 服務很好先做一個置中的div(結束) -->
</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>