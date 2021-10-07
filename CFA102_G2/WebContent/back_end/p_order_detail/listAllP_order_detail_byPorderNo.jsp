<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order_detail.model.*"%>

<%
	List<P_order_detailVO> list = (List<P_order_detailVO>) request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="p_orderSvc" scope="page" class="com.p_order.model.P_orderService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
	<title>後台_訂單明細管理</title>
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
		<h1 class="h3 mb-4 text-gray-800">所有訂單明細</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/p_order_detail/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
				<i class="far fa-hand-point-left"></i> 回訂單明細管理
			</a>
		</div>

		<div class="row col-md-12 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<tr>
					<th>商品訂單編號</th>
					<th>商品名稱</th>
					<th>訂購數量</th>
					<th>商品單價</th>
					<th>商品評價</th>
<!-- 					<th>修改</th> -->
				</tr>
				
				<%@ include file="/back_end/p_order_detail/pages/page1.file" %> 
				
				<c:forEach var="p_order_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${p_order_detailVO.porderNo}</td>									
						<td>
							<c:forEach var="productVO" items="${productSvc.all}">
                    			<c:if test="${p_order_detailVO.pno==productVO.pno}">${p_order_detailVO.pno}【${productVO.pname}】</c:if>
                			</c:forEach>
						</td>
						<td>${p_order_detailVO.porderQuantity}</td>
						<td>${p_order_detailVO.pprice}</td>
						<td>${p_order_detailVO.pratings}</td>
<!-- 						<td> -->
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion_detail/promotion_detail.do" style="margin-bottom: 0px;"> --%>
<!-- 								<input type="submit" class="btn btn-primary btn-sm" value="修改">  -->
<%-- 								<input type="hidden" name="porderNo" value="${p_order_detailVO.porderNo}"> --%>
<%-- 								<input type="hidden" name="pno" value="${p_order_detailVO.pno}"> --%>
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller-->								
<!-- 								<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 							</FORM> -->
<!-- 						</td> -->
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/p_order_detail/pages/page2.file" %>
		</div>
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>