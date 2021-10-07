<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                                <h3 class="title">請輸入郵件</h3>

                                <div class="form-wrapper">
                                    <form action="<%=request.getContextPath()%>/memberLoginHandler.do" METHOD="post">
                                        <!-- Single Form Start -->
                                        <div class="single-form">
<!--                                         輸入會員的郵件 -->
                                            <input type="email"  name="mem_mail" placeholder="Email">  <font style="color:red">${resendMail[0]}</font> 
                                        </div>
                                        <!-- Single Form End -->
                                   
                                        <div class="single-form">
                                        	<input type="hidden" name="action" value="forgetpassword">
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

</div>
</div>
  <!-- Login & Register Section End -->













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