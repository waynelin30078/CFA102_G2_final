<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>後台_商品管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		.pic{
			width:100px;
			height:100px;
			border:0px;
		}
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
		<h1 class="h3 mb-4 text-gray-800">所有商品</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd">
    			<b>＋</b>新增商品</a>
    		<a href="<%=request.getContextPath()%>/back_end/product/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回商品管理</a>	
    	</div>
    	     	
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>商品編號</th>
					<td>${productVO.pno}</td>
				</tr>				
				<tr>
					<th>商品類別名稱</th>
					<td>${productVO.categoryName}</td>
				</tr>				
				<tr>
					<th>商品名稱</th>
					<td>${productVO.pname}</td>
				</tr>				
				<tr>
					<th>商品單價</th>
					<td>${productVO.pprice}</td>
				</tr>					
				<tr>
					<th>商品描述</th>
					<td>${productVO.pinfo}</td>
				</tr>					
				<tr>
					<th>商品數量</th>
					<td>${productVO.pquantity}</td> 
				</tr>
				<tr>
					<th>商品上架日期</th>
					<td>${productVO.ponDate}</td>
				</tr>								
				<tr>
					<th>商品下架日期</th>
					<td>${productVO.poffDate}</td>
				</tr>
				<tr>
					<th>商品圖片1</th>
					<td>
						<c:choose>
							<c:when test="${productVO.pimage1.trim() == null || productVO.pimage1.trim().length() == 0}">
								<img class="pic" src="<%=request.getContextPath()%>/back_end/product/images/null2.jpg">
							</c:when>
							<c:otherwise>
								<img class="pic" src="<%=request.getContextPath()%>${productVO.pimage1}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>商品圖片2</th>
					<td>
						<c:choose>
							<c:when test="${productVO.pimage2.trim() == null || productVO.pimage2.trim().length() == 0}">
								<img class="pic" src="<%=request.getContextPath()%>/back_end/product/images/null2.jpg">
							</c:when>
							<c:otherwise>
								<img class="pic" src="<%=request.getContextPath()%>${productVO.pimage2}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>商品圖片3</th>
					<td>
						<c:choose>
							<c:when test="${productVO.pimage3.trim() == null || productVO.pimage3.trim().length() == 0}">
								<img class="pic" src="<%=request.getContextPath()%>/back_end/product/images/null2.jpg">
							</c:when>
							<c:otherwise>
								<img class="pic" src="<%=request.getContextPath()%>${productVO.pimage3}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>評價總人數</th>
					<td>${productVO.pratingsQuantity}</td>
				</tr>	
				<tr>
					<th>評價總分數</th>
					<td>${productVO.ptotalRatings}</td>
				</tr>
				<tr>
					<th>商品狀態</th>
					<td><c:choose>
							<c:when test="${productVO.pstate==0}">下架</c:when>
							<c:otherwise>上架</c:otherwise>
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