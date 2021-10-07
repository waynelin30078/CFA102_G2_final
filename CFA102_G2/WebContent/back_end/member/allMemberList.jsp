<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
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
	width: 85%;
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
<style type="text/css">
tr {
	heigh: 100px
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


		<table id="table1">
			<tr>
				<td>
					<h3>全部會員資料</h3>
					
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
				<th nowrap="nowrap">編號</th>
				<th nowrap="nowrap">會員姓名</th>
				<th nowrap="nowrap">帳號</th>
				<th nowrap="nowrap">密碼</th>
				<th nowrap="nowrap">郵件</th>
				<th nowrap="nowrap">手機</th>
				<th nowrap="nowrap">生日</th>
				<th nowrap="nowrap">自介</th>
				<th nowrap="nowrap">照片</th>
				<th nowrap="nowrap">權限</th>
			</tr>
			<%@ include file="pages/page1.file"%>
			<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr>


					<td>${memberVO.mno}</td>
					<td>${memberVO.mname}</td>
					<td>${memberVO.mid}</td>
					<td>${memberVO.mpsw}</td>
					<td>${memberVO.mmail}</td>
					<td>${memberVO.mphone}</td>
					<td>${memberVO.mbday}</td>
					<td>${memberVO.mintro}</td>
					<td><c:if test="${memberVO.mimg != null }">
							<img
								src="<%=request.getContextPath()%>/member/member.do?action=showPhoto&photo=${memberVO.mno}"
								width="150" height="150"></c:if>
						 <c:if test="${memberVO.mimg == null} ">查無圖片</c:if>
					</td>
					<td>
						<c:if test="${memberVO.mstate == 1}">正常</c:if>
						 <c:if test="${memberVO.mstate == 0}"><font style="color: red">停權</font></c:if>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/member/member.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="<c:if test="${memberVO.mstate == 1}">停權</c:if><c:if test="${memberVO.mstate == 0}">復權</c:if>"> 
							<input type="hidden" name="mstate" value="${memberVO.mstate}">
							<input type="hidden" name="mno" value="${memberVO.mno}"> 
						    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="action" value="disable">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pages/page2.file"%>

	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>