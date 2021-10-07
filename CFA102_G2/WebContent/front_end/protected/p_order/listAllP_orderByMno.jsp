<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order.model.*"%>
<%@ page import="com.p_order_detail.model.*"%>

<%
	Integer mno = (Integer) session.getAttribute("mno");
%>
<%
	P_orderService p_orderSvc = new P_orderService();
	List<P_orderVO> list = p_orderSvc.getAll_ByMno(mno);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
	<title>我的訂單</title>
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
	</style>
</head>

<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp" %>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
	<!-- 服務很好先做一個置中的div(開頭) -->
		<div class="row">
			<div class="col-md-6 col-md-offset-2 custyle">
				<h1 class="h3 mb-4 text-gray-800"><i class="far fa-file-alt"></i> 我的訂單:</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2 custyle">
				<table class="table table-striped custab">		
					<tr>
						<th>訂單編號</th>
						<th>訂單成立時間</th>
						<th>總金額</th>
						<th>收件人姓名</th>
						<th>付款方式</th>
						<th>運送方式</th>
						<th>訂單狀態</th>
						<th>訂單資訊</th>
						<th>查看明細</th>
					</tr>
				
					<c:forEach var="p_orderVO" items="${list}">
						<tr>
							<td>${p_orderVO.porderNo}</td>
							<td><fmt:formatDate value="${p_orderVO.porderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${p_orderVO.porderTotal}</td>
							<td>${p_orderVO.porderName}</td>
							<td>
								<c:choose>
									<c:when test="${p_orderVO.ppayment==1}">貨到付款</c:when>
									<c:when test="${p_orderVO.ppayment==2}">信用卡</c:when>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${p_orderVO.pshipping==1}">超商取貨</c:when>
									<c:when test="${p_orderVO.pshipping==2}">宅配</c:when>
								</c:choose>
							</td>						
							<td>
								<c:choose>
									<c:when test="${p_orderVO.porderState==0}">未出貨</c:when>
									<c:when test="${p_orderVO.porderState==1}">已出貨</c:when>
									<c:when test="${p_orderVO.porderState==2}">已到貨</c:when>
									<c:when test="${p_orderVO.porderState==3}">訂單取消</c:when>	
									<c:when test="${p_orderVO.porderState==4}">退貨</c:when>	
								</c:choose>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" style="margin-bottom: 0px;">
									<input type="submit" class="btn btn-sm" value="詳情">
									<input type="hidden" name="porderNo" 	value="${p_orderVO.porderNo}">
									<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 									<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller--> --%>
									<input type="hidden" name="action" 		value="getOne_For_Display_Front_End">
								</FORM>
							</td>
							<td>			
			  					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" style="margin-bottom: 0px;"> 
 			    					<input type="submit" value="查看明細" class="btn btn-sm">
 			    					<input type="hidden" name="porderNo" value="${p_orderVO.porderNo}">
			    					<input type="hidden" name="action" value="listPorder_detail_ByPorderNo">
								</FORM> 
							</td>
						</tr>
					</c:forEach>
				</table>		
			</div>
			<br>
			<div class="row">
				<%if (request.getAttribute("listPorder_detail_ByPorderNo")!=null){%>
       				<jsp:include page="/front_end/protected/p_order/listPorder_detail_ByPorderNo.jsp" />
				<%} %>	
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