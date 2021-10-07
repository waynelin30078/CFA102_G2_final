<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.p_order.model.*"%>

<%
	P_orderVO p_orderVO = (P_orderVO) request.getAttribute("p_orderVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>後台_訂單管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		#btnback{
			margin-bottom:10px;
			margin-top:0px;
			margin-left:10px;
		}
		.table{
			width:600px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">訂單詳情</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
    		<a href="<%=request.getContextPath()%>/back_end/p_order/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回訂單管理</a>	
    	</div>
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>商品訂單編號</th>
					<td>${p_orderVO.porderNo}</td>
				</tr>
				<tr>
					<th>會員編號</th>
					<td>${p_orderVO.mno}</td>
				</tr>
				<tr>
					<th>訂單成立時間</th>
					<td><fmt:formatDate value="${p_orderVO.porderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>總金額</th>
					<td>${p_orderVO.porderTotal}</td>
				</tr>
				<tr>
					<th>收件人姓名</th>
					<td>${p_orderVO.porderName}</td>
				</tr>				
				<tr>
					<th>收件人電話</th>
					<td>${p_orderVO.porderPhone}</td>
				</tr>				
				<tr>
					<th>收件人地址</th>
					<td>${p_orderVO.porderAddress}</td>
				</tr>				
				<tr>
					<th>付款方式</th>
					<td>
						<c:choose>
							<c:when test="${p_orderVO.ppayment==1}">超商取貨</c:when>
							<c:when test="${p_orderVO.ppayment==2}">宅配</c:when>
						</c:choose>
					</td>	
				</tr>				
				<tr>
					<td>信用卡號</td>
					<td>
						<c:choose>
							<c:when test="${p_orderVO.pcreditCard.trim() == null || p_orderVO.pcreditCard.trim().length() == 0}">無</c:when>
							<c:otherwise>${p_orderVO.pcreditCard}</c:otherwise>
						</c:choose>							
					</td>
				</tr>						
				<tr>
					<td>信用卡檢查碼</td>
					<td>
						<c:choose>
							<c:when test="${p_orderVO.pcreditCardCVV.trim() == null || p_orderVO.pcreditCardCVV.trim().length() == 0}">無</c:when>
							<c:otherwise>${p_orderVO.pcreditCardCVV}</c:otherwise>
						</c:choose>								
					</td>
				</tr>
				<tr>
					<th>運送方式</th>				
					<td>
						<c:choose>
							<c:when test="${p_orderVO.pshipping==0}">超商取貨</c:when>
							<c:when test="${p_orderVO.pshipping==1}">宅配</c:when>
						</c:choose>
					</td>
				</tr>														
				<tr>
					<th>訂單狀態</th>				
					<td>
						<c:choose>
							<c:when test="${p_orderVO.porderState==0}">未出貨</c:when>
							<c:when test="${p_orderVO.porderState==1}">已出貨</c:when>
							<c:when test="${p_orderVO.porderState==2}">已到貨</c:when>
							<c:when test="${p_orderVO.porderState==3}">訂單取消</c:when>
							<c:when test="${p_orderVO.porderState==4}">退貨</c:when>	
						</c:choose>
					</td>
				</tr>
			</table>
    	</div>
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>