<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%
	AdminService adminSvc = new AdminService();
	List<AdminVO> list = adminSvc.getAll();
	pageContext.setAttribute("list", list);
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
	width: 800px;
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
					<h3>所有管理員資料</h3>
					<h4>
						<a href="<%=request.getContextPath()%>/back_end/admin/blank_select_page.jsp">回首頁<img
							src="<%=request.getContextPath()%>/back_end/admin/images/back.png"
							width="30" height="30" border="0"></a>
					</h4>
				</td>
			</tr>
		</table>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<table class="table">
		 <thead class="thead-dark">
			<tr>
				<th>管理員編號</th>
				<th>管理員名子</th>
				<th>管理員帳號</th>
				<th>管理員密碼</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="adminVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr ${(param.ano==adminVO.ano)?"style='background-color:#fff3cd'":"" }>
					<td>${adminVO.ano}</td>
					<td>${adminVO.aname}</td>
					<td>${adminVO.aid}</td>
					<td>${adminVO.apsw}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/admin/admin.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="ano" value="${adminVO.ano}"> <input type="hidden"
								name="action" value="getOne_For_Update">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/admin/admin.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除"> <input type="hidden"
								name="ano" value="${adminVO.ano}"> <input type="hidden"
								name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>