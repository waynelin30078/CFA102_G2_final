<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order.model.*"%>
<%@ page import="com.p_order_detail.model.*"%>

<%
	P_orderVO p_orderVO = (P_orderVO) request.getAttribute("p_orderVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>我的訂單_詳細資訊</title>
	<!-- *************每一頁head裡面都要include這個css連結******************* -->
	<%@ include file="/front_end_example/CSS_link.jsp" %>
	<!-- *************每一頁head裡面都要include這個css連結******************* -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		:root {
			--gradient: linear-gradient(to left top, #198754 10%, #0a4d3c 90%) !important;
		}
		.btn:hover, .btn:focus {
  			background: var(--gradient) !important;
  			-webkit-background-clip: none !important;
  			-webkit-text-fill-color: #fff !important;
  			box-shadow: #222 1px 0 10px;
  			text-decoration: underline;
		}
		.btn{
  			font-weight:bold;
  			padding-right:10px;
  			padding-left:10px;
  			line-height: 30px;
  			height: 30px;
  			width:100px;
  			border-color:#0a4d3c;
  			border-width:1px;
  			margin-bottom:10px;
  			display:inline;
		}
		@media all and (max-width: 575.98px) {
			#divBack{
				margin-top:40px;
			}
		}
		@media all and (min-width: 576px) and (max-width: 767.98px) {
			#divBack{
				margin-top:40px;
			}
		}
		#divBack{
			margin-bottom:20px;
		}		
	</style>
</head>

<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp" %>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
	<!-- 服務很好先做一個置中的div(開頭) -->
		<div class="row">
			<div class="col-12" id="divBack">
    			<a href="<%=request.getContextPath()%>/front_end/protected/p_order/listAllP_orderByMno.jsp" class="btn btn-sm" id="btnback">
    				<i class="far fa-hand-point-left"></i>我的訂單
    			</a>			
			</div>
		</div>		
		<div class="row">
			<div class="col-md-6 col-md-offset-2 custyle">
				<h1 class="h3 mb-4 text-gray-800"><i class="far fa-file-alt"></i> 我的訂單_詳細資訊:</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2 custyle">
				<table class="table table-striped custab">
					<tr>
						<td>訂單編號</td>
						<td>${p_orderVO.porderNo}</td>
					</tr>					
					<tr>
						<td>會員編號</td>
						<td>${p_orderVO.mno}</td>
					</tr>
					<tr>
						<td>訂單成立時間</td>
						<td><fmt:formatDate value="${p_orderVO.porderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>						
					<tr>
						<td>總金額</td>
						<td>${p_orderVO.porderTotal}</td>
					</tr>							
					<tr>
						<td>收件人姓名</td>
						<td>${p_orderVO.porderName}</td>
					</tr>							
					<tr>
						<td>收件人電話</td>
						<td>${p_orderVO.porderPhone}</td>
					</tr>
					<tr>
						<td>收件人地址</td>
						<td>${p_orderVO.porderAddress}</td>
					</tr>
					<tr>
						<td>付款方式</td>
						<td>
							<c:choose>
								<c:when test="${p_orderVO.ppayment==1}">貨到付款</c:when>
								<c:when test="${p_orderVO.ppayment==2}">信用卡</c:when>
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
						<td>運送方式</td>
						<td>
							<c:choose>
								<c:when test="${p_orderVO.pshipping==1}">超商取貨</c:when>
								<c:when test="${p_orderVO.pshipping==2}">宅配</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>訂單狀態</td>
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