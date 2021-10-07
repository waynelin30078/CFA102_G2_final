<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	CourseService courseSvc = new CourseService();
	List<CourseVO> list = (List<CourseVO>) request.getAttribute("courseList");
	System.out.print(list);
	if (list == null) {
		//只顯示狀態為上架的課程
		list = courseSvc.getAll().stream().filter(c -> c.getCstate() == 1).collect(Collectors.toList());
		pageContext.setAttribute("list", list);
		// 	List<CourseVO> list = courseSvc.getAll();
	} else {
		pageContext.setAttribute("list", list);
	}
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
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->


		<!-- Courses Section Start -->
		<div class="section section-padding-02">
			<div class="container">

				<div class="courses-select">
				<form METHOD="post"
						ACTION="<%=request.getContextPath()%>/course/course.do">
					<select name="ctype" id="ctype">
						<option data-display="所有課程" value="0">所有課程</option>
						<option value="1">飲食知識</option>
						<option value="2">健身</option>
						<option value="3">餐點製作</option>
					</select>
					<input type="hidden" name="action" value="searchctype">
					<input type="submit" value="送出查詢">
					</form>
				</div>

				<!-- 					Courses Search Start -->
				<div>
					<form METHOD="post"
						ACTION="<%=request.getContextPath()%>/course/course.do">
						<input type="text" placeholder="Search here" name="searchString">
						<input type="hidden" name="action" value="search"> <input
							type="submit" value="查詢">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<!-- 							<button> -->
						<!-- 								<i id="searchbutton" class="icofont-search"></i> -->
						<!-- 							</button> -->
					</form>
				</div>
				<!-- 					Courses Search End -->

				<!-- 				</div> -->


				<div class="courses-wrapper-02">
					<div class="row gx-xxl-5">
						<c:forEach var="courseVO" items="${list}">
							<div class="col-lg-4 col-sm-6">
								<!-- Single Courses Start -->

								<div class="single-courses">

									<div class="courses-images">
										<a
											href="<%=request.getContextPath()%>/course/course.do?action=get_course_detail&cno=${courseVO.cno}"><img
											src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"
											alt="courses" width="350" height="350" /> </a>

									</div>

									<div class="courses-content">
										<div class="courses-price">
											<span class="price">$${courseVO.cprice}</span>
										</div>
										<div class="content-wrapper">
											<p class="author">
												By: <a href="#">${dietsvc.findByPrimaryKey(courseVO.dno).dname}</a>
											</p>
											<h4 class="title">
												<a
													href="<%=request.getContextPath()%>/course/course.do?action=get_course_detail&cno=${courseVO.cno}">${courseVO.cname}</a>
											</h4>
											
										

										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Courses Wrapper End -->

			</div>
		</div>
	</div>

	<!-- Courses Section End -->

	<!-- JS
    ============================================ -->


	<script>
		
	</script>


	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>