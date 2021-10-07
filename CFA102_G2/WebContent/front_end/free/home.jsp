<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</style>


</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->



   <!-- Slider Section Start -->
        <div class="slider-section slider-active section">
            <div class="swiper-container">
                <div class="swiper-wrapper">

                    <!-- Single Slider Start -->
                    <div class="swiper-slide animation-style-01 single-slider d-flex align-items-center"  style="background-image: url(<%= request.getContextPath() %>/front_end_example/images/slider-1.jpg);">
                        <div class="container">

                            <!-- Slider Content Start -->
                            <div class="slider-content">
                                <h6 class="sub-title">Fitness & Nutrition</h6>
                                <h1 class="main-title">This life style for your fitness, not only diet.</h1>
                                <p>It has survived not only five centuries but also</p>
                                <a href="#" class="btn btn-primary btn-hover-secondary">Start Course</a>
                            </div>
                            <!-- Slider Content End -->

                        </div>

                        <!-- Slider Social Start -->
                        <div class="slider-social">
                            <div class="container">
                                <div class="social-wrapper">
                                    <p>Connect with us:</p>
                                    <ul class="social">
                                        <li><a href="#"><i class="icofont-facebook"></i></a></li>
                                        <li><a href="#"><i class="icofont-skype"></i></a></li>
                                        <li><a href="#"><i class="icofont-twitter"></i></a></li>
                                        <li><a href="#"><i class="icofont-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- Slider Social End -->
                    </div>
                    <!-- Single Slider End -->
                </div>
            </div>
        </div>
        <!-- Slider Section End -->


<img id="chat_icon_fake" class="chat_icon_fake" src="<%=request.getContextPath()%>/front_end_example/images/msn-icon.png" alt="">








<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>