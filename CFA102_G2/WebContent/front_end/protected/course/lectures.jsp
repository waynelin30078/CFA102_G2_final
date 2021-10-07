<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.video.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>

<%
CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
Integer cno = courseVO.getCno();
pageContext.setAttribute("courseVO", courseVO);
VideoService videoSvc = new VideoService();

List<VideoVO> videoList = videoSvc.getAll(cno);//課程編號取得影片list
long videoCount = videoList.stream().count();//取得影片數

long videoTime =0;//取得總時長
for(int i = 0 ;i<videoList.size();i++){
	videoTime += videoList.get(i).getVlength().getTime()/1000+28800;
}
Timestamp totalTime = new Timestamp(videoTime*1000-28800000);



pageContext.setAttribute("totalTime", totalTime);	 
pageContext.setAttribute("videoCount", videoCount);	  
pageContext.setAttribute("videoList", videoList);
com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
pageContext.setAttribute("dietsvc", dietsvc);
%>
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
 <div class="main-wrapper">
        <!-- Courses Enroll Start -->
        <div class="section">

            <!-- Courses Enroll Wrapper Start -->
            <div class="courses-enroll-wrapper">

                <!-- Courses Video Player Start -->
                <div class="courses-video-player">

                    <!-- Courses Video Container Start -->
                    <div class="vidcontainer">
                        <video id="myvid"></video>

                        <div class="video-play-bar">
                            <div class="topControl">
                                <div class="progress">
                                    <span class="bufferBar"></span>
                                    <span class="timeBar"></span>
                                </div>
                                <div class="time">
                                    <span class="current"></span> /
                                    <span class="duration"></span>
                                </div>
                            </div>

                            <div class="controllers">
                                <div class="controllers-left">
                                    <button class="prevvid disabled" title="Previous video"><i class="icofont-ui-previous"></i></button>
                                    <button class="btnPlay" title="Play/Pause video"></button>
                                    <button class="nextvid" title="Next video"><i class="icofont-ui-next"></i></button>
                                    <button class="sound sound2" title="Mute/Unmute sound"></button>
                                    <div class="volume" title="Set volume">
                                        <span class="volumeBar"></span>
                                    </div>
                                </div>

                                <div class="controllers-right">
                                    <button class="btnspeed" title="Video speed"><i class="fa fa-gear"></i></button>
                                    <ul class="speedcnt">
                                        <li class="spdx50">1.5</li>
                                        <li class="spdx25">1.25</li>
                                        <li class="spdx1 selected">Normal</li>
                                        <li class="spdx050">0.5</li>
                                    </ul>
                                    <button class="btnFS" title="full screen"></button>
                                </div>
                            </div>
                        </div>

                        <div class="bigplay" title="play the video">
                            <i class="fa fa-play"></i>
                        </div>

                        <div class="loading">
                            <div class="spinner-border spinner">
                                <span class="visually-hidden">Loading...</span>
                            </div>
                        </div>

                    </div>
                    <!-- Courses Video Container End -->

                    <!-- Courses Enroll Content Start -->
                    <div class="courses-enroll-content">

                        <!-- Courses Enroll Title Start -->
                        <div class="courses-enroll-title">
                            <h2 class="title">${courseVO.cname}</h2>
                            <p><i class="icofont-eye-alt"></i> <span>${courseVO.ctotalPeople}</span> Students are watching</p>
                        </div>
                        <!-- Courses Enroll Title End -->

                        <!-- Courses Enroll Tab Start -->
                        <div class="courses-enroll-tab">
                            <div class="enroll-tab-menu">
                                <ul class="nav">
                                    <li><button class="active" data-bs-toggle="tab" data-bs-target="#tab1">Overview</button></li>
                                    <li><button data-bs-toggle="tab" data-bs-target="#tab2">Description</button></li>
                                    
                                  
                                </ul>
                            </div>
                           
                        </div>
                        <!-- Courses Enroll Tab End -->

                        <!-- Courses Enroll Tab Content Start -->
                        <div class="courses-enroll-tab-content">
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="tab1">

                                    <!-- Overview Start -->
                                    <div class="overview">
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="enroll-tab-title">
                                                    <h3 class="title">課程資訊</h3>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="enroll-tab-content">
                                                    <p>${courseVO.cdescription}</p>

                                                    <table class="table">
                                                        <tbody>
                                                            <tr>
                                                                <th>講師 <span>:</span></th>
                                                                <td>${dietsvc.findByPrimaryKey(courseVO.dno).dname}</td>
                                                            </tr>
                                                            <tr>
                                                                <th>課程時間 <span>:</span></th>
                                                                <td>
                                                                <fmt:formatDate value="${totalTime}"
																		pattern="HH:mm:ss " />
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th>講座 <span>:</span></th>
                                                                <td><%=videoCount %></td>
                                                            </tr>
                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Overview End -->

                                </div>
                                <div class="tab-pane fade" id="tab2">

                                    <!-- Description Start -->
                                    <div class="description">
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <div class="enroll-tab-title">
                                                    <h3 class="title">課程介紹</h3>
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="enroll-tab-content">
                                                

                                                    <p class="text">${courseVO.cintroduction}</p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Description End -->

                                </div>
                                <div class="tab-pane fade" id="tab3">

                                   

                                </div>
                                <div class="tab-pane fade" id="tab4">

                                   

                                </div>
                            </div>
                        </div>
                        <!-- Courses Enroll Tab Content End -->

                    </div>
                    <!-- Courses Enroll Content End -->
                </div>
                <!-- Courses Video Player End -->

                <!-- Courses Video Playlist Start -->
                <div class="courses-video-playlist">
                    <div class="playlist-title">
                        <h3 class="title">Course Content</h3>
                        
                    </div>
					
                    <!-- Video Playlist Start  -->
                    <div class="video-playlist">
                        <div class="accordion" id="videoPlaylist">

                            <!-- Accordion Items Start  -->
                            <div class="accordion-item">
                                <button class="collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne">
                                    <span class="lesson">${courseVO.cname}</span>
                                    <span class="total-duration">  <fmt:formatDate value="${totalTime}"
																		pattern="HH小時mm分鐘ss秒 " /></span>
                                </button>
                                <div id="collapseOne" class="accordion-collapse collapse" data-bs-parent="#videoPlaylist">
                                    <nav class="vids">
	                                    <c:forEach var="videoVO" items="${videoList}">
	                                        <a class="link active" href="<%=request.getContextPath()%>${videoVO.vfile}" type="video/mp4">
	                                            <p> ${videoVO.vname}</p>
	                                            <span class="total-duration">${videoVO.vlength}</span>
	                                        </a>
	                                    </c:forEach>
                                    </nav>
                                </div>
                            </div>
                            <!-- Accordion Items End  -->
                   
                        </div>
                    </div>
                    <!-- Video Playlist End  -->

                </div>
                <!-- Courses Video Playlist End -->

            </div>
            <!-- Courses Enroll Wrapper End -->

        </div>
        <!-- Courses Enroll End -->

        <!--Back To Start-->
        <a href="#" class="back-to-top">
            <i class="icofont-simple-up"></i>
        </a>
        <!--Back To End-->

    </div>

    <!-- JS
    ============================================ -->

    <!-- Modernizer & jQuery JS -->
    <script src="assets/js/vendor/modernizr-3.11.2.min.js"></script>
    <script src="assets/js/vendor/jquery-3.5.1.min.js"></script>

    <!-- Bootstrap JS -->
    <!-- <script src="assets/js/plugins/popper.min.js"></script>
    <script src="assets/js/plugins/bootstrap.min.js"></script> -->

    <!-- Plugins JS -->
    <!-- <script src="assets/js/plugins/swiper-bundle.min.js"></script>
    <script src="assets/js/plugins/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/plugins/jquery.nice-select.min.js"></script>
    <script src="assets/js/plugins/video-playlist.js"></script>
    <script src="assets/js/plugins/ajax-contact.js"></script> -->

    <!--====== Use the minified version files listed below for better performance and remove the files listed above ======-->
    <script src="assets/js/plugins.min.js"></script>


    <!-- Main JS -->
    <script src="assets/js/main.js"></script>

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