<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<%
  AdminVO adminVO = (AdminVO) request.getAttribute("adminVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_admin_input.jsp</title>

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
	width: 450px;
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>管理員資料修改 - update_admin_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath() %>/back_end/admin/blank.jsp"><img src="<%=request.getContextPath() %>/back_end/admin/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/admin/admin.do" name="form1">
<table>
	<tr>
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td><%=adminVO.getAno()%></td>
	</tr>
	<tr>
		<td>管理員名子:</td>
		<td><input type="TEXT" name="aname" size="45" value="<%=adminVO.getAname()%>" /></td>
	</tr>
	<tr>
		<td>管理員帳號:</td>
		<td><input type="TEXT" name="aid" size="45"	value="<%=adminVO.getAid()%>" /></td>
	</tr>
<tr>
		<td>管理員密碼:</td>
		<td><input type="TEXT" name="apsw" size="45"	value="<%=adminVO.getApsw()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ano" value="<%=adminVO.getAno()%>">
<input type="submit" value="送出修改"></FORM>
</body>




</html>