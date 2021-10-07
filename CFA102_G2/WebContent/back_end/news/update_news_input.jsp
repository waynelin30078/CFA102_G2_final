<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>

<%
  NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>最新消息資料修改</title>

<style>

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
	width:1100px;
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
<!-- 		 <h3>最新消息資料修改</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/back-end/news/select_news_page.jsp">回首頁</a></h4> --%>
	</td></tr>
</table>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do" name="form1">
<table>

		<tr>
		<td>最新消息編號:<font color=red><b></b></font></td>
		<td><%=newsVO.getNewsNo()%></td>
	</tr>
	
	<tr>
		<td>最新消息標題:</td>
		<td><input type="TEXT" name="newsTitle" size="30"  value="<%=newsVO.getNewsTitle()%>" /></td>
	</tr>
	

	<tr>
		<td>最新消息內容:</td>
		<td><input type="TEXT" style="width:400px; height:280px" name="newsContent" size="30" value="<%=newsVO.getNewsContent()%>" /></td>
	</tr>

	
		<jsp:useBean id="newsSvc" scope="page" class="com.news.model.NewsService" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="newsNo" value="<%=newsVO.getNewsNo()%>">
<input type="submit" value="送出修改"></FORM>


</body>