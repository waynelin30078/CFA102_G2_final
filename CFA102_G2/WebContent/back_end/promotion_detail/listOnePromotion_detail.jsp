<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotion_detail.model.*"%>

<%
	Promotion_detailVO promotion_detailVO = (Promotion_detailVO) request.getAttribute("promotion_detailVO");
%>

<jsp:useBean id="promotionSvc" scope="page" class="com.promotion.model.PromotionService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
	<title>後台_優惠活動明細管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		#btnAdd, #btnback{
			margin-bottom:10px;
			margin-top:0px;
		}
		#btnback{
			margin-left:10px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">所有優惠活動明細</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/promotion_detail/addPromotion_detail.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd">
    			<b>＋</b>新增優惠活動明細</a>
    		<a href="<%=request.getContextPath()%>/back_end/promotion_detail/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回優惠活動明細管理</a>	
    	</div>
    	
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>優惠活動</th>
					<th>優惠商品</th>
					<th>商品單價</th>
				</tr>
				<tr>
					<td>${promotionSvc.getOnePromotion(promotion_detailVO.promNo).promName}</td>
					<td>${productSvc.getOneProduct(promotion_detailVO.pno).pname}</td>
					<td><%=promotion_detailVO.getPprice()%></td>
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