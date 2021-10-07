<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="com.d_license.model.*"%>
<%@ page import="com.d_license.controller.*"%>

<%
	DieticianService dieticianSvc = new DieticianService();
	List<DieticianVO> list = dieticianSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="dlicenseSvc" scope="page" class="com.d_license.model.DlicenseService" />
<!DOCTYPE html>
<html lang="en">

<head>
<title>營養師會員管理</title>


<!-- 此include包含了，head的設定與css的連結 -->
<%@ include file="/back_end_example/head_include.jsp"%>
<!-- ----------------------------------------------------------- -->

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
	width: 100%;
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

		<hr style="border: 3px solid black;" />
		<!-- Page Heading -->


		<table id="table-1">
			<tr>
				<td>
					<h3>所有營養師會員資料</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back_end/dietician/blank_select_pageDietician.jsp">回首頁<img
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


		<table>

			<tr>
				<th nowrap="nowrap">營養師會員編號</th>
				<th nowrap="nowrap">姓名</th>
				<th nowrap="nowrap">照片</th>
				<th nowrap="nowrap">學歷簡介</th>
				<th nowrap="nowrap">經歷簡介</th>
				<th nowrap="nowrap">證照簡介</th>
				<th nowrap="nowrap">證照名稱</th>
				<th nowrap="nowrap">證照照片</th>
				<th nowrap="nowrap">專長簡介</th>
				<th nowrap="nowrap">權限狀態</th>
				<th nowrap="nowrap">變更權限</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="dieticianVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr
					${(param.dno==dieticianVO.dno)?"style='background-color:#fff3cd'":"" }>
					<td>${dieticianVO.dno}</td>
					<td nowrap="nowrap">${dieticianVO.dname}</td>
					<td><img width="120px" height="100px"
						src="<%=request.getContextPath()%>/${dieticianVO.dpic}"></td>
					<td>${dieticianVO.edu}</td>
					<td>${dieticianVO.exp}</td>
					<td>${dieticianVO.lic}</td>
					<td>
					<c:forEach var="dlicenseVO" items="${dlicenseSvc.all}">
					<c:if test="${dlicenseVO.dno==dieticianVO.dno}">
					【${dlicenseVO.licDesc}】<br>
						</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="dlicenseVO" items="${dlicenseSvc.all}">
					<c:if test="${dlicenseVO.dno==dieticianVO.dno}">
					<a href="<%=request.getContextPath()%>${dlicenseVO.licFile}" target="_blank"><img width="120px" height="100px"
						src="<%=request.getContextPath()%>${dlicenseVO.licFile}"></a>
								</c:if>
						</c:forEach>
					</td>
				
					<td>${dieticianVO.prof}</td>
					<td nowrap="nowrap"><c:if test="${dieticianVO.dstate==0}">
					未審核
					</c:if> <c:if test="${dieticianVO.dstate==1}">
					通過審核
					</c:if> <c:if test="${dieticianVO.dstate==2}">
					未通過審核
					</c:if></td>


					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/dietician/dietician.do">

							<select size="1" name="dstate">
								<c:if test="${dieticianVO.dstate==0}">
									<option selected="selected" value="0">未審核</option>
									<option value="1">通過審核</option>
									<option value="2">未通過審核</option>
								</c:if>

								<c:if test="${dieticianVO.dstate==1}">
									<option value="0">未審核</option>
									<option selected="selected" value="1">通過審核</option>
									<option value="2">未通過審核</option>
								</c:if>

								<c:if test="${dieticianVO.dstate==2}">
									<option value="0">未審核</option>
									<option value="1">通過審核</option>
									<option selected="selected" value="2">未通過審核</option>
								</c:if>
							</select> <input type="hidden" name="dno" value="${dieticianVO.dno}">
							<input type="hidden" name="action" value="update_dstate">
							<input type="submit"> 
							<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> 
							<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
							<input type="hidden" name="self" value="selfAll">
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