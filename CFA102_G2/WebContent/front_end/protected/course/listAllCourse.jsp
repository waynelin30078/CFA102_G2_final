<%@page import="java.util.stream.Collectors"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.dietician.model.*"%>
<%
	DieticianVO dieticianVO = (DieticianVO) session.getAttribute("dieticianVO2");
	CourseService courseSvc = new CourseService();
	List<CourseVO> list = courseSvc.getAll().stream().filter(c -> c.getDno() == dieticianVO.getDno())
			// .filter(c->c.getDno()==1)
			.collect(Collectors.toList());

	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="DieticianSvc" scope="page"
	class="com.dietician.model.DieticianService" />
<%
	com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
	pageContext.setAttribute("dietsvc", dietsvc);
%>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
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
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->










		<table id="table-1">
			<tr>
				<td>
					<h3>我的課程管理</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/front_end/protected/course/select_page.jsp">回首頁</a>
					</h4>
				</td>
			</tr>
		</table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}"</li>
				</c:forEach>
			</ul>
		</c:if>
		<%
			if (list != null && (list.size() > 0)) {
		%>
		<table>
			<tr>
				<!-- 		<th>課程編號</th> -->
				<!-- 		<th>營養師姓名</th> -->
				<th>課程名稱</th>
				<th>課程價格</th>
				<th>上架時間</th>
				<th>課程主題</th>
				<th>購買人數</th>
				<!-- 		<th>課程評價</th> -->
				<th>課程圖片</th>
				<th>新增影片</th>
				<th>影片一覽</th>
				<th>修改課程</th>
				<th>課程狀態</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="courseVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<%-- 	<c:forEach var="courseVO" items="${list}"> --%>

				<tr>
					<%-- 			<td>${courseVO.cno} </td> --%>
					<%-- 			<td>${dietsvc.findByPrimaryKey(courseVO.dno).dname}</td> <!-- ${DieticianSvc.findByPrimaryKey(courseVO.dno).dname}有取到但是顯示為空--> --%>
					<td>${courseVO.cname}</td>
					<td>${courseVO.cprice}</td>
					<td><fmt:formatDate value="${courseVO.cshelfDate}"
							pattern="yyyy-MM-dd" /></td>
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
					<%-- 			<c:if test="${courseVO.ctotalScore/courseVO.ctotalPeople>1}"> --%>
					<%-- 			<td><fmt:formatNumber type="number"  maxFractionDigits="1" value="${courseVO.ctotalScore/courseVO.ctotalPeople}" /></td>  --%>
					<%-- 			</c:if> --%>
					<td><img
						src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"
						width='150px' height='150px' /></td>
					<td>
						<FORM METHOD="get"
							ACTION="<%=request.getContextPath()%>/video/video.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="新增影片"> <input type="hidden"
								name="cno" value="${courseVO.cno}"> <input type="hidden"
								name="action" value="insert_video_page">
						</FORM>
					</td>
					<td>
						<FORM METHOD="get"
							ACTION="<%=request.getContextPath()%>/video/video.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="影片管理"> <input type="hidden"
								name="cno" value="${courseVO.cno}"> <input type="hidden"
								name="action" value="getAllVideoByCno">
						</FORM>
					</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/course/course.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改"> <input type="hidden"
								name="cno" value="${courseVO.cno}"> <input type="hidden"
								name="requestURL" value="<%=request.getServletPath()%>">
							<!--送出本網頁的路徑給Controller-->
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<c:if test="${courseVO.cstate ==0}">
						<td>審核中</td>
					</c:if>
					<c:if test="${courseVO.cstate ==1}">
						<td>上架</td>
					</c:if>
					<c:if test="${courseVO.cstate ==2}">
						<td>下架</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>

		<%@ include file="page2.file"%>
		<%
			} else {
		%>
		<p>您目前沒有課程</p>
		<a
			href="<%=request.getContextPath()%>/front_end/protected/course/addCourse.jsp">請新增課程</a>

		<%
			}
		%>


		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>