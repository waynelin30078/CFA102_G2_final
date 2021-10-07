<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.news.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller newsServlet.java已存入request的newsVO物件--%>
<%NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");%>


<html>
<head>
<title>消息內容 - listOneNews.jsp</title>

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
	width: 1250px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: left;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>最新消息資料 - listOneNews.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/news/select_news_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>最新消息ID</th>
		<th>最新消息發佈日期</th>
		<th>最新消息標題</th>
		<th>最新消息內容</th>
	</tr>
	<tr>
		<td>${newsVO.newsNo}</td>
		<td><fmt:formatDate value="${newsVO.newsDate}" pattern="yyyy-MM-dd"/></td>
		<td>${newsVO.newsTitle}</td>
		<td>${newsVO.newsContent}</td>
		
		
<%-- 		<td><%=newsVO.getNewsNo()%></td> --%>
<%-- 		<td><%=newsVO.getNewsDate()%></td> --%>
<%-- 		<td><%=newsVO.getNewsTitle()%></td> --%>
<%-- 		<td><%=newsVO.getNewsContent()%></td> --%>

	</tr>
</table>

</body>
</html>