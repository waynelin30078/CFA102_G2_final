<%@page
	import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.video.model.*"%>

<%
	VideoVO videoVO = new VideoVO();
%>
<jsp:useBean id="videoList" scope="request"
	type="java.util.List<VideoVO>" />
<jsp:useBean id="CourseSvc" scope="page"
	class="com.course.model.CourseService" />
<jsp:useBean id="VideoVO" class="com.video.model.VideoVO" />
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<style>
  table {
	width: 98%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->

<table id="table-1">
		<tr>
			<td>
				<h3>影片管理 </h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front_end/protected/course/listAllCourse.jsp">上一頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 	<h3>課程名稱:<%CourseSvc.getOneCourse(VideoVO.getCno()).getCname(); %></h3> --%>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
<!-- 			<th>影片編號</th> -->
			<th>影片名稱</th>
			<th>上傳時間</th>
			<th>影片長度</th>
			<th>刪除影片</th>
			<th>影片預覽</th>

		</tr>
		<c:forEach var="videoVO" items="${videoList}">
			<tr>
				<td>${videoVO.vname}</td>
<%-- 				<td>${CourseSvc.getOneCourse(videoVO.cno).cname}</td> --%>
				<td><fmt:formatDate value="${videoVO.vupdateTime}"
						pattern="yyyy-MM-dd " /></td>
				<td>${videoVO.vlength}</td>
				<%-- 				<td>${videoVO.vFile}</td> --%>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/video/video.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="vno" value="${videoVO.vno}"> <input type="hidden"
							name="action" value="delete">
					</FORM>

				</td>
				<td><video width="480px" height="360px" controls>
						<source
							src="<%=request.getContextPath()%>${videoVO.vfile}"
							type="video/mp4" />
					</video></td>
			</tr>
		</c:forEach>

	</table>
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