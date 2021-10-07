<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order.model.*"%>

<%
	P_orderService p_orderSvc = new P_orderService();
	List<P_orderVO> list = p_orderSvc.getAll();
	pageContext.setAttribute("list", list);
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
		#btnback {
			margin-bottom: 10px;
			margin-top: 0px;
			margin-left: 10px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">所有訂單</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/p_order/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
				<i class="far fa-hand-point-left"></i> 回訂單管理
			</a>
		</div>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<tr>
					<th>商品訂單編號</th>
					<th>會員編號</th>
					<th>訂單成立時間</th>
					<th>總金額</th>
					<th>收件人姓名</th>
					<th>付款方式</th>
					<th>運送方式</th>
					<th>訂單狀態</th>
					<th>詳情</th>
					<th>修改</th>
				</tr>

				<%@ include file="/back_end/p_order/pages/page1.file"%>
				
				<c:forEach var="p_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${(p_orderVO.porderNo==param.porderNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
						<td>${p_orderVO.porderNo}</td>
						<td>${p_orderVO.mno}</td>
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
								<input type="submit" class="btn btn-primary btn-sm" value="詳情">
								<input type="hidden" name="porderNo" 	value="${p_orderVO.porderNo}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" 		value="getOne_For_Display">
							</FORM>
						</td>						
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="修改">
								<input type="hidden" name="porderNo" 	value="${p_orderVO.porderNo}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" 		value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/p_order/pages/page2.file"%>
		</div>
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>