<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" %>
<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<style>
.checksex {
	display: inline-block;
}
</style>

</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->




                <!-- Register & Login Wrapper Start -->
                <div class="register-login-wrapper">
                    <div class="row justify-content-center">
                        <div class="col-lg-6 col-xl-5">
                            <!-- Register & Login Form Start -->
                            <div class="register-login-form">
                                <h3 class="title">Registration <span>Now</span></h3>

                                <div class="form-wrapper">
                                    <form action="<%=request.getContextPath()%>/member/member.do" METHOD="post">
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="text" placeholder="Name" name="mname" value="<%= (memberVO==null)? "" : memberVO.getMname()%>"> <font style="color: red"> ${errorMsgs.mname}</font> <!-- 會員姓名 -->
                                        </div>
                                        <!-- Single Form End -->
                                                                                <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="text" placeholder="Account" name="mid" value="<%= (memberVO==null)? "" : memberVO.getMid()%>"><font style="color: red"> ${errorMsgs.mid} </font><!-- 會員帳號 -->
                                        </div>
                                        <!-- Single Form End -->
                                        
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="password" placeholder="Password" name="mpsw"><font style="color: red"> ${errorMsgs.mpsw } </font>  <!-- 會員密碼 -->
                                        </div>
                                        <!-- Single Form End -->
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="email" placeholder="Email" name="mmail" value="<%= (memberVO==null)? "" : memberVO.getMmail()%>"><font style="color: red"> ${errorMsgs.mmail} </font><!-- 會員郵件 -->
                                        </div>
                                        <!-- Single Form End -->
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="TEXT" placeholder="Phone" name="mphone" value="<%= (memberVO==null)? "" : memberVO.getMphone()%>"><font style="color: red"> ${errorMsgs.mphone}</font> <!-- 會員電話 -->
                                        </div>
                                        <!-- Single Form End -->
                                        <!-- Single Form Start -->

                                        
  <div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="msex" id="inlineRadio1" value="1" checked>
  <label class="form-check-label" for="inlineRadio1">男</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="msex" id="inlineRadio2" value="2">
  <label class="form-check-label" for="inlineRadio2">女</label><font style="color: red">${errorMsgs.msex }</font>
</div>
<!-- <div> -->
<%-- <%-- 錯誤表列 --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<!-- </div> -->
                                        <!-- Single Form Start -->
                                        
                                        <input type="hidden" name="action" value="insert">
                                        <div class="single-form">
                                            <button class="btn btn-primary btn-hover-dark w-100">Create an account</button>
                                        </div>
                                        <!-- Single Form End -->
                                        <p>Already have an account? <a href="<%=request.getContextPath()%>/front_end/free/member/memberLogin.jsp">Log in instead!</a></p>
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