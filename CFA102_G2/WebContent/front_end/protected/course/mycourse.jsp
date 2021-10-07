<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	CourseService courseSvc = new CourseService();
	CourseVO courseVO = new CourseVO();
	List<CourseVO> list = (List<CourseVO>) request.getAttribute("courseList"); //list是給分頁用的
	pageContext.setAttribute("list", list);
	MemberVO memberVO1 = (MemberVO) session.getAttribute("memberVO1");//取得登陸的會員物件
%>

<jsp:useBean id="DieticianSvc" scope="page"
	class="com.dietician.model.DieticianService" />
<%
	com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
	pageContext.setAttribute("dietsvc", dietsvc);
%>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->


		<!-- Courses Start -->
		<div class="section section-padding">
			<div class="container">

				<!-- Courses Category Wrapper Start  -->
				<div class="courses-category-wrapper">
					<div >
<!-- 						<input type="text" placeholder="Search here"> -->
<!-- 						<button> -->
<!-- 							<i class="icofont-search"></i> -->
<!-- 						</button> -->
					</div>

					<ul class="category-menu">
						<li><a class="active" href="#">All Courses</a></li>
						<li><a
							href="<%=request.getContextPath()%>/front_end/protected/course_favorite/mycourse_favorite.jsp">Collections</a></li>
					</ul>
				</div>
				<!-- Courses Category Wrapper End  -->

				<!-- Courses Wrapper Start  -->
				<div class="courses-wrapper-02">
					<div class="row gx-xxl-5">
						<c:forEach var="courseVO" items="${list}">
							<div class="col-lg-4 col-md-6">
								<!-- Single Courses Start -->
								<div class="single-courses-02">
									<div class="courses-images">
										<a
											href="<%=request.getContextPath()%>/course/course.do?action=learnCourse&cno=${courseVO.cno}"><img
											src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"
											alt="courses" width="350" height="350" /> </a>

									</div>
									<div class="courses-content">
										<p class="author">
											By: <a href="#">${dietsvc.findByPrimaryKey(courseVO.dno).dname}</a>
										</p>

										<h4 class="title">
											<a href="courses-details-left-sidebar.html">${courseVO.cname}</a>
										</h4>

										<div class="courses-rating">
<!-- 											<p>38% Complete</p> -->

											<div class="rating-progress-bar">
<!-- 												<div class="rating-line" style="width: 50%;"></div> -->
											</div>

											<div class="rating-meta">
<!-- 												<span class="rating-star"> <span class="rating-bar" -->
<!-- 													style="width: 90%;"></span> -->
<!-- 												</span> -->
<!-- 												<p>Your rating</p> -->
											</div>
										</div>
									</div>
								</div>
								<!-- Single Courses End -->

							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Courses Wrapper End  -->

			</div>
		</div>
		<!-- Courses End -->




		<!-- Courses Section End -->





		<!-- JS
    ============================================ -->

		<!-- Modernizer & jQuery JS -->
		<script src="assets/js/vendor/modernizr-3.11.2.min.js"></script>
		<script src="assets/js/vendor/jquery-3.5.1.min.js"></script>


		<!--====== Use the minified version files listed below for better performance and remove the files listed above ======-->
		<script src="assets/js/plugins.min.js"></script>


		<!-- Main JS -->
		<script src="assets/js/main.js"></script>

		<!-- 服務很好先做一個置中的div(結束) -->

		<!-- *************每一頁body最後面都要include這個footer連結******************* -->
		<%@ include file="/front_end_example/footer_link.jsp"%>
		<!-- *************每一頁body最後面都要include這個footer連結******************* -->
		<!-- *************每一頁body最後面都要include這個js連結******************* -->
		<%@ include file="/front_end_example/js_link.jsp"%>
		<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>