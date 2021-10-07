<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%
	CourseService courseSvc = new CourseService();
	List<CourseVO> list = courseSvc.getAll();//list是給分頁用的
	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="DieticianSvc" scope="page" class="com.dietician.model.DieticianService" />
<%com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
pageContext.setAttribute("dietsvc", dietsvc);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>後台管理</title>

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
	width: 95%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid black;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</style>

	<!-- 此include包含了，head的設定與css的連結 -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- ----------------------------------------------------------- -->
</head>

<body id="page-top">

	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫 -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分-->

	<div class="container-fluid">

<table id="table-1">
	<tr><td>
		 <h3>所有課程資料 - listAllCourse.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/course/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}"</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<table>
	<tr>
		<th>課程編號</th>
		<th>營養師姓名</th>
		<th>課程名稱</th>
		<th>課程價格</th>	
		<th>上架時間</th>
		<th>課程主題</th>
		<th>購買人數</th>
		<th>課程評價總人數</th>
		<th>課程評價總分數</th>
		<th>課程圖片</th>
		<th>審核課程</th>
		<th>課程狀態</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="courseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 	<c:forEach var="courseVO" items="${list}"> --%>
		
		<tr>
			<td>${courseVO.cno} </td>
			<td>${dietsvc.findByPrimaryKey(courseVO.dno).dname}</td> <!-- ${DieticianSvc.findByPrimaryKey(courseVO.dno).dname}有取到但是顯示為空-->
			<td>${courseVO.cname}</td>
			<td>${courseVO.cprice}</td>
			<td> <fmt:formatDate value="${courseVO.cshelfDate}" pattern="yyyy-MM-dd"/></td>
				<c:if test="${courseVO.ctype==1}">
						<td>飲食知識</td>
					</c:if>
					<c:if test="${courseVO.ctype==2}">
						<td>健身</td>
					</c:if>
					<c:if test="${courseVO.ctype==3}">
						<td>餐點製作</td>
					</c:if>
			<td>${courseVO.quantity}</td> 
			<td>${courseVO.ctotalPeople}</td> 
			<td>${courseVO.ctotalScore}</td>
			<td><img src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"width ='150px' height='150px'/></td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" style="margin-bottom: 0px;">
			     <input type="submit" value="查看內容">
			     <input type="hidden" name="cno"  value="${courseVO.cno}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="audit"></FORM>
			</td>
			<c:if test="${courseVO.cstate ==0}" >
			<td>審核中</td>
			</c:if>
			<c:if test="${courseVO.cstate ==1}" >
			<td>上架</td>
			</c:if>
			<c:if test="${courseVO.cstate ==2}" >
			<td>下架</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->

<script>
$(document).ready(function(){
	$("tr:even").css("background-color","lightgray");
});
</script>

</body>

</html>