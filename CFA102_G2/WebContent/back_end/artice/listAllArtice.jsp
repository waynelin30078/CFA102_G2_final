<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.artice.model.*"%>

<%
    ArticeService articSvc = new ArticeService();
    List<ArticeVO> list = articSvc.getall();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>文章資訊檢視</title>

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
<%@ include file="/back_end_example/head_include.jsp"%>
</head>
<body bgcolor='white'>
<%@ include file="/back_end_example/body_include.jsp"%>

<table id="table-1">
	<tr><td>
		 <h3>文章資訊檢視</h3>
  <h4><a href="<%=request.getContextPath()%>/back_end/artice/select_artice_page.jsp">回首頁</a></h4> 
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
		<th>文章ID</th>
		<th>文章發佈日期</th>
		<th>文章標題</th>
		<th>文章分類</th>
		<th>文章狀態</th>
		<th>檢視詳細</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ArticeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ArticeVO.articNo}</td>
			<td><fmt:formatDate value="${ArticeVO.articDate}" pattern="yyyy-MM-dd"/></td>
			<td>${ArticeVO.articTitle}</td>
			
			<c:choose>
					<c:when test="${ArticeVO.articType == '1'}">
						<td>研究</td>
					</c:when>
					<c:when test="${ArticeVO.articType == '2'}">
						<td>食物</td>
					</c:when>
			</c:choose>
			<c:choose>
					<c:when test="${ArticeVO.articState == '0'}">
						<td>隱藏中</td>
					</c:when>
					<c:when test="${ArticeVO.articState == '1'}">
						<td>顯示中</td>
					</c:when>
			</c:choose>


	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" style="margin-bottom: 0px;">
			     <input type="submit" value="檢視詳細">
			     <input type="hidden" name="articNo"  value="${ArticeVO.articNo}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="articNo"  value="${ArticeVO.articNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="articNo"  value="${ArticeVO.articNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%@ include file="/back_end_example/foot_include.jsp"%>
</body>
</html>