<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                <h3 class="title">營養師會員登入</h3>

                                <div class="form-wrapper">
                                    <form action="<%=request.getContextPath()%>/DieticianLoginHandler.do" METHOD="post">
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="text"  name="daccount" value="" placeholder="帳號" >
                                        </div>
                                        <!-- Single Form End -->
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                            <input type="password" name="dpassword" value="" placeholder="密碼" >
                                        </div>
<%--                                         <font style="color: red">${errorMsgs.daccount}${errorMsgs.dpassword}</font> --%>


                                        <!-- Single Form End -->
                                        <!-- Single Form Start -->
                                        <div class="single-form">
                                        	<input type="hidden" name="action" value="signIn">
                                            <button class="btn btn-primary btn-hover-dark w-100">登入</button>
                                        </div>
                                        
                                        <div>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul style="list-sytle: none; list-style-type: none">
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red; list-style-type: none">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
                                        
                                        
                                        
                                        <!-- Single Form End -->
                                        <p><a href="<%=request.getContextPath()%>/front_end/free/dietician/lostPassWord.jsp">忘記密碼</a></p>
                                        <p><a href="<%=request.getContextPath()%>/front_end/free/dietician/add_dietician_page01.jsp">註冊新會員 </a></p>
                                    </form>
                                </div>
                            </div>
                            <!-- Register & Login Form End -->

                        </div>
                    </div>
                </div>
                <!-- Register & Login Wrapper End -->

</div>
</div>
  <!-- Login & Register Section End -->












<!-- 服務很好先做一個置中的div(結束) -->
</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%-- <%@ include file="/front_end_example/footer_link.jsp" %> --%>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<script>
if(<%=request.getAttribute("seccessGetPassWord") != null%>){
	alert("密碼已寄出，請至您的個人信箱查看");
}
</script>
</body>
</html>