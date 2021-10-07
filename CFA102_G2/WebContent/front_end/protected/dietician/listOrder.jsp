<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.d_order.model.*"%>
<%@ page import="com.d_order.controller.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<% 
	List<DorderVO> order = (List<DorderVO>)request.getAttribute("order"); 
	%>
	
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
	
	
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
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
	width: 800px;
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


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->



<table>
	<tr>
		<th>會員名稱</th>
		<th>照片</th>
		<th>訂閱開始日期</th>
		<th>訂閱結束日期</th>
		<th>訂閱狀態</th>
	</tr>
	<c:forEach var="orderVO" items="${order}" >
		
		<tr>
		
		<td>	<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${orderVO.mno==memberVO.mno}">
					${memberVO.mname}
						</c:if>
						</c:forEach>	</td>
		
		
		<td>	<c:forEach var="memberVO" items="${memberSvc.all}">
					<c:if test="${orderVO.mno==memberVO.mno}">
					<img src="<%=request.getContextPath()%>/member/member.do?action=showPhoto&photo=${memberVO.mno}" width="150" height="150">
						</c:if>
						</c:forEach>	</td>
		
			<td>${orderVO.subStart}</td>
			<td>${orderVO.subEnd}</td>
			<c:if test="${orderVO.autoSubs==1}">
			<td>自動續訂中</td>
			</c:if>
			<c:if test="${orderVO.autoSubs==0}">
			<td>非自動續定</td>
			</c:if>
		</tr>
	</c:forEach>
</table>




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