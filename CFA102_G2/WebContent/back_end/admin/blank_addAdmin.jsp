<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
	AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>後台管理</title>


<!-- 此include包含了，head的設定與css的連結 -->
<%@ include file="/back_end_example/head_include.jsp"%>
<!-- ----------------------------------------------------------- -->

<style>
table#table-1 {
	background-color: #bbded6;
	text-align: center;
	width: 100%;
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

<body id="page-top">

	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫 -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->






	<!-- Begin Page Content 中間填寫部分-->

	<div class="container-fluid">

		<!-- Page Heading -->
		<table id="table-1">
			<tr>
				<td>
					<h3>管理員資料新增</h3>

					<h4>
						<a
							href="<%=request.getContextPath()%>/back_end/admin/blank_select_page.jsp">回首頁<img
							src="<%=request.getContextPath()%>/back_end/admin/images/back.png"
							width="30" height="30" border="0"></a>
					</h4>
				</td>
			</tr>
		</table>

		<h3>資料新增:</h3>

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
			ACTION="<%=request.getContextPath()%>/admin/admin.do" name="form1">
			<table class="table">
			<thead class="thead-dark">
				<tr>
					<td>管理員名子:</td>
					<td><input type="TEXT" name="aname" size="45"
						value="<%=(adminVO == null) ? "" : adminVO.getAname()%>" /></td>
				</tr>
				<tr>
					<td>管理員帳號:</td>
					<td><input type="TEXT" name="aid" size="45"
						value="<%=(adminVO == null) ? "" : adminVO.getAid()%>" /></td>
				</tr>
				<tr>
					<td>管理員密碼:</td>
					<td><input type="text" name="apsw" size="45"
						value="<%=(adminVO == null) ? "" : adminVO.getApsw()%>" /></td>
				</tr>

				</thead>
			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>

	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>