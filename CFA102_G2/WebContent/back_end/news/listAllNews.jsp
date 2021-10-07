<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>最新消息資訊檢視</title>

<style>
  table#table-1 {
	background-color: #6495ed;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: black;
    display: inline;
  }
</style>

<style>
  table {
	width: 1100px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 2px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>最新消息資訊檢視</h3>
 <h4><a href="<%=request.getContextPath()%>/back_end/news/select_news_page.jsp">回首頁</a></h4> 
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

<table>
	<tr>
		<th>最新消息ID</th>
		<th>最新消息發佈日期</th>
		<th>最新消息標題</th>
		<th>最新消息內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${newsVO.newsNo}</td>
			<td><fmt:formatDate value="${newsVO.newsDate}" pattern="yyyy-MM-dd"/></td>
			<td>${newsVO.newsTitle}</td>
			<td>${newsVO.newsContent}</td>
			


	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="newsNo"  value="${newsVO.newsNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/news/news.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="newsNo" value="${newsVO.newsNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>