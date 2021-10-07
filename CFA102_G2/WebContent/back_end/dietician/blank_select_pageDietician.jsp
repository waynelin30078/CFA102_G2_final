<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>後台管理</title>


<!-- 此include包含了，head的設定與css的連結 -->
<%@ include file="/back_end_example/head_include.jsp"%>
<!-- ----------------------------------------------------------- -->

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</head>

<body id="page-top">

	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫 -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->







	<!-- Begin Page Content 中間填寫部分-->

	<div class="container-fluid">

		<!-- Page Heading -->
		<hr style="border: 3px solid black;" />


		<!-- /.container-fluid -->

		<table id="table-1">
			<tr>
				<td><h3>CFA102_G2 營養師會員管理</h3>
					<h4>( 查詢系統 )</h4></td>
			</tr>
		</table>


		<h3>資料查詢:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<ul>
			<li><a
				href='<%=request.getContextPath()%>/back_end/dietician/blank_listAllDietician.jsp'>查詢全部營養師會員</a></li>



			<jsp:useBean id="dieticianVO" scope="page"
				class="com.dietician.model.DieticianService" />

			<li>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/dietician/dietician.do">
					<b>依審核狀態找營養師:</b> 
					<select size="1" name="dstate">
					<option value="0">未審核</option>
					<option value="1">通過審核</option>
					<option value="2">未通過審核</option>
					
					

					</select> <input type="hidden" name="action" value="findByDieticianState">
					<input type="submit" value="送出">
				</FORM>
			</li>


		</ul>


	</div>



	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>