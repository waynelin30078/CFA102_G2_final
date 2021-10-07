<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.artice.model.*"%>

<%-- 取出 Controller Servlet.java已存入request的VO物件--%>
<%
ArticeVO articVO = (ArticeVO) request.getAttribute("articeVO");
%>



<html>
<head>
<title>文章資料 -  listOneArtice.jsp</title>

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
<%@ include file="/back_end_example/head_include.jsp"%>
</head>
<body bgcolor='white'>
<%@ include file="/back_end_example/body_include.jsp"%>
<table id="table-1">
	<tr><td>
		 <h3>文章資料 - listOneArtice.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/artice/select_artice_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章ID</th>
		<th>文章發佈日期</th>
		<th>文章標題</th>
		<th>文章內容</th>
		<th>文章圖片</th>
		<th>文章狀態</th>
	</tr>
	<tr>
	
	
		<td>${articeVO.articNo}</td>
		<td><fmt:formatDate value="${articeVO.articDate}" pattern="yyyy-MM-dd"/></td>
		<td>${articeVO.articTitle}</td>
		<td style="word-break:break-all">${articeVO.articContent}</td>
		<td><img width="400" alt="" src="<%=request.getContextPath()%>/PicFinder?pic=1&table=article&column=articPhoto1&idname=articNo&id=${articeVO.articNo}"/></td>
        <c:choose>
			<c:when test="${articeVO.articState == '0'}">
				<td>隱藏中</td>
			</c:when>
			<c:when test="${articeVO.articState == '1'}">
				<td>顯示中</td>
			</c:when>
		</c:choose>		
		


	</tr>
</table>
<%@ include file="/back_end_example/foot_include.jsp"%>
</body>
</html>