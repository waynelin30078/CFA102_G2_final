<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.course.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneCourse.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
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
<body bgcolor='white'>


	<table id="table-1">
		<tr>
			<td>
				<h3>課程資料 - ListOneCourse.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front_end/protected/course/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>課程編號</th>
			<th>營養師姓名</th>
			<th>課程名稱</th>
			<th>課程價格</th>
			<th>課程狀態</th>
			<th>上架時間</th>
			<th>課程主題</th>
			<th>購買人數</th>
			<th>課程評價總人數</th>
			<th>課程評價總分數</th>
			<th>課程圖片</th>
			<th>設定影片</th>
			<th>設定教材</th>
			<th>修改課程</th>
			<th>課程狀態</th>
		</tr>
		<tr>
			<td>${courseVO.cno}</td>
			<td>${courseVO.dno}</td>
			<!-- ${DieticianSvc.findByPrimaryKey(courseVO.dno).dname} -->
			<td>${courseVO.cname}</td>
			<td>${courseVO.cprice}</td>
			<td>${courseVO.cstate}</td>
			<td><fmt:formatDate value="${courseVO.cshelfDate}"
					pattern="yyyy-MM-dd" /></td>
			<td>${courseVO.ctype}</td>
			<td>${courseVO.quantity}</td>
			<td>${courseVO.ctotalPeople}</td>
			<td>${courseVO.ctotalScore}</td>
			<td><img
				src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"
				width='150px' height='150px' /></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/video/video.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="新增影片"> <input type="hidden"
						name="cno" value="${courseVO.cno}"> <input type="hidden"
						name="action" value="update_video_page">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/course/course.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="新增教材"> <input type="hidden"
						name="cno" value="${courseVO.cno}"> <input type="hidden"
						name="action" value="update_material">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/course/course.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="cno" value="${courseVO.cno}"> <input type="hidden"
						name="requestURL" value="<%=request.getServletPath()%>">
					<!--送出本網頁的路徑給Controller-->
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<c:if test="${courseVO.cstate ==0}">
				<td>審核中</td>
			</c:if>
			<c:if test="${courseVO.cstate ==1}">
				<td>上架</td>
			</c:if>
			<c:if test="${courseVO.cstate ==2}">
				<td>下架</td>
			</c:if>

		</tr>
	</table>

</body>
</html>