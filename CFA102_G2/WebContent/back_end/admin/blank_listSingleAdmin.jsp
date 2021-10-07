<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

		<!-- Page Heading -->
		<table id="table-1">
	
			<tr>
				<td>
					<h3>管理員資料 </h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back_end/admin/blank_select_page.jsp">回首頁<img
							src="<%=request.getContextPath()%>/back_end/admin/images/back.png"
							width="30" height="30" border="0"></a>
					</h4>
				</td>
			</tr>
	
		</table>

		<table class="table">
			 <thead class="thead-dark">
			<tr>
				<th>管理員編號</th>
				<th>管理員名稱</th>
				<th>管理員帳號</th>
				<th>管理員密碼</th>
			</tr>
			</thead>
			<tr>
				<td><%=adminVO.getAno()%></td>
				<td><%=adminVO.getAname()%></td>
				<td><%=adminVO.getAid()%></td>
				<td><%=adminVO.getApsw()%></td>

			</tr>
	
		</table>

	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>