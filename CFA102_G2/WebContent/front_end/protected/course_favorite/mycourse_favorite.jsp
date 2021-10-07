<%@page import="java.util.stream.Collectors"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.c_favorite.model.*"%>
<%
	DieticianVO dieticianVO =  (DieticianVO) session.getAttribute("dieticianVO2");
	CourseService courseSvc = new CourseService();

MemberVO memberVO1 = (MemberVO) session.getAttribute("memberVO1");//取得登陸的會員物件
C_FavoriteService courseFavoriteSvc = new C_FavoriteService();

Integer mno = memberVO1.getMno();
C_FavoriteVO cfVO = new C_FavoriteVO();
List<C_FavoriteVO> c_FavortiveList =courseFavoriteSvc.getALLCourseFavoriteBymno(mno);//取得該會員的收藏
List<CourseVO>list = new <CourseVO>ArrayList();
for(int i = 0;i<c_FavortiveList.size();i++){
	list.add(courseSvc.getOneCourse(c_FavortiveList.get(i).getCno()));
}

	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="DieticianSvc" scope="page" class="com.dietician.model.DieticianService" />
<%com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
pageContext.setAttribute("dietsvc", dietsvc);
%>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>



<style>
  table {
	width: 98%;
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
<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->

<table id="table-1">
	<tr><td>
		 <h3>我的課程收藏</h3>
<%-- 		 <h4><a href="<%=request.getContextPath()%>/front_end/protected/course/select_page.jsp">回首頁</a></h4> --%>
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
<%if(list != null &&(list.size() > 0)){ %>
	<table>
	<tr>
<!-- 		<th>課程編號</th> -->
<!-- 		<th>營養師姓名</th> -->
		<th>課程名稱</th>
		<th>課程價格</th>	
		<th>上架時間</th>
	
		<th>購買人數</th>
		<th>課程評價</th>
		<th>課程圖片</th>
		<th>觀看課程詳情</th>

		
	
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="courseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 	<c:forEach var="courseVO" items="${list}"> --%>
		
		<tr>
<%-- 			<td>${courseVO.cno} </td> --%>
<%-- 			<td>${dietsvc.findByPrimaryKey(courseVO.dno).dname}</td> <!-- ${DieticianSvc.findByPrimaryKey(courseVO.dno).dname}有取到但是顯示為空--> --%>
			<td>${courseVO.cname}</td>
			<td>${courseVO.cprice}</td>
			<td> <fmt:formatDate value="${courseVO.cshelfDate}" pattern="yyyy-MM-dd"/></td>
		
			<td>${courseVO.quantity}</td> 
			<c:if test="${courseVO.ctotalScore/courseVO.ctotalPeople!=null}">
			<td><fmt:formatNumber type="number"  maxFractionDigits="1" value="${courseVO.ctotalScore/courseVO.ctotalPeople}" /></td> 
			</c:if>
			<td><img src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"width ='150px' height='150px'/></td> 
			<td>
			<form>
<!-- 			<input type="submit" value="觀看課程詳情" id="btn1" > -->
<input type=button value="觀看課程詳情" onclick="window.location.href='<%=request.getContextPath()%>/course/course.do?action=get_course_detail&cno=${courseVO.cno}'" id="btn1"/>
			</form>
			</td>
			
		
		
		
			 
	
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>
<%}else{%>
	<p>您目前沒有收藏的課程</p>
	<a href="<%=request.getContextPath()%>/front_end/free/course/course_front_page.jsp">前往課程首頁</a>

<% } %>

<!-- 服務很好先做一個置中的div(結束) -->
</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>