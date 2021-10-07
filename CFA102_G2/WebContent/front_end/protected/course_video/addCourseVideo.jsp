<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.video.model.*"%>

<%
	VideoVO videoVO = (VideoVO) request.getAttribute("videoVO");
%>
<jsp:useBean id="CourseSvc" scope="page"
	class="com.course.model.CourseService" />
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<style>
table {
	width: 45%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
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
				<h3>新增影片</h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front_end/protected/course/listAllCourse.jsp">回管理課程首頁</a>
				</h4>
			</td>
		</tr>
	</table>
<br>
	<h3>影片上傳:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/video/video.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<jsp:useBean id="videoSvc" class="com.video.model.VideoService" />
			<tr>
<!-- 				<td>課程編號:</td> -->
				<td><input type="hidden" name="cno" size="45"
					value="<%=(videoVO == null) ? "" : videoVO.getCno()%>" /></td>

			</tr>
			<tr>
				<td>影片名稱:</td>
				<td><input type="TEXT" name="vname" size="45"
					value="<%=(videoVO == null) ? "" : videoVO.getVname()%>" /></td>

			</tr>
			<tr>
				<td>上傳影片:</td>
				<td><input type="file" name="vFile" id="input"></td>
			</tr>
		</table>
		<input type="hidden" name="vlength" id="vid" value=""> <br>
		<input type="hidden" name="action" value="insert_video"> 
		<input type="submit" value="送出" id="mySubmit" disabled>
	</FORM>
	<script>
		// create the video element but don't add it to the page
		var vid = document.createElement('video');
		document.querySelector('#input').addEventListener('change', function() {
			document.getElementById("mySubmit").disabled = true;

			// create url to use as the src of the video
			var fileURL = URL.createObjectURL(this.files[0]);
			vid.src = fileURL;
			// wait for duration to change from NaN to the actual duration
			var vlength = vid.ondurationchange = function() {
				document.getElementById("vid").value = this.duration;
				document.getElementById("mySubmit").disabled = false;
			};
		});
	</script>
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