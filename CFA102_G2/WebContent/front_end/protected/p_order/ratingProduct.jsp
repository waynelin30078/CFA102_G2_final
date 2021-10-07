<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order_detail.model.*"%>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
	<title>評價商品</title>
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
				<h1 class="h3 mb-4 text-gray-800"><i class="far fa fa-star"></i> 評價商品:</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2 custyle">
				<table class="table table-striped custab">
					<tr>
						<td>訂單編號</td>
						<td>${p_order_detailVO.porderNo}</td>
					</tr>
					<tr>
						<td>商品名稱</td>
							<c:forEach var="productVO" items="${productSvc.all}">
                    			<c:if test="${p_order_detailVO.pno==productVO.pno}">${productVO.pno}【${productVO.pname}】</c:if>
                			</c:forEach>
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