<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll_ByPstate(1);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
	<title>營材食教</title>
	<!-- *************每一頁head裡面都要include這個css連結******************* -->
	<%@ include file="/front_end_example/CSS_link.jsp"%>
	<!-- *************每一頁head裡面都要include這個css連結******************* -->
	<style>
		:root {
			--gradient: linear-gradient(to left top, #198754 10%, #0a4d3c 90%) !important;
		}
		body {
			background: #FFFFFF !important;
		}
		.card-body {
			line-height: 6rem;
		}
		.card img {
    		max-width:100%;
    		width:200px;
    		height:200px;
    		z-index:10;
		}
		.card {
  			background: #fff;
  			border: 1px solid #fff;
  			color: rgba(250, 250, 250, 0.8);
  			margin-top: 2rem;
		}
		.card-text { 
  			margin-top: 2px; 
		}
		.card-title {
  			margin-top: 2px;
  			margin-bottom: 10px;
		}
		#price {
  			color:#ffa300;
  			text-align:left;
		}
		#ratings {
  			color:#ffa300;
  			text-align:left;
		}
		.btn-group {
  			margin-top:0.5rem;
  			margin-bottom:0.5rem;
		}
		.btn:hover, .btn:focus {
  			background: var(--gradient) !important;
  			-webkit-background-clip: none !important;
  			-webkit-text-fill-color: #fff !important;
  			box-shadow: #222 1px 0 10px;
 	 		text-decoration: underline;
		}
		.btn {
  			font-weight:bold;
  			padding-right:10px;
  			padding-left:10px;
  			line-height: 30px;
  			height: 30px;
  			width:100px;
  			border-color:#0a4d3c;
  			border-width:1px;
 	 		margin-bottom:10px;
		}
		input#includeSubmitBtn.btn.btn-outline-primary.btn-sm {
  			padding-right:3rem;
  			padding-left:1.25rem;
  			line-height: 30px;
  			height: 30px;
  			margin-left:5px;
 	 		text-align:center;
  			display: inline-block;
  			width:30px;
  			margin-top:5px;
  			border-color:#0a4d3c;
		}
		div.card-text {
  			margin-top:10px; 
		}
		body {
  			background-color: #ccc;
		}
		#right {
  			position: fixed;
  			top: 100px;
  			width: 50px;
  			height: 50px;
  			line-height: 50px;
  			text-align: center;
  			z-index:9999;
  			right: 50px;
		}
		.fa.fa-star, .fa.fa-star-o{
			color: #ffa300;
			font-size: 12px;
		}

		.fa-input { 
			font-family: FontAwesome; 
			position: absolute ;
			top: 200px;
			left: 190px;
			z-index:20;
			width:35px;
			border:none;
		}
		.fa-input:hover, .fa-input:focus {
			font-family: FontAwesome;
  			background: none !important;
  			-webkit-background-clip: none !important;
   			-webkit-text-fill-color: red !important; 
   			box-shadow: #fff 0px 0 0px; 
 	 		text-decoration: none;
		}
	
	</style>
</head>

<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page ">
		<!-- 服務很好先做一個置中的div(開頭) -->
		<div id="right">
			<a href="<%=request.getContextPath()%>/front_end/protected/product/Cart.jsp" id="goToCart">	
    			<img src="<%=request.getContextPath()%>/front_end/free/images/cart.png" id="cart">
    		</a>
		</div>

		<div class="row">
			<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4">
				<%@ include file="/front_end/free/product/page1.file"%>
			</div>
		</div>
		<div class="row">
			<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<div class="col-sm-6 col-md-4 col-lg-3 col-xl-3">
						<div class="card">
							<c:choose>	
								<c:when test="${productVO.pimage1.trim() == null || productVO.pimage1.trim().length() == 0}">
									<img src="<%=request.getContextPath()%>/back_end/product/images/null2.jpg"  border="0" class="card-img-top">
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath()%>${productVO.pimage1}" border="0" class="card-img-top">
								</c:otherwise>
							</c:choose>
							<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">
 								<FORM action="<%=request.getContextPath()%>/p_favorite/p_favorite.do" method="POST">
									<input type="submit" class="btn fa-input" value="&#xf08a" onclick="addFavorite()">
									<input type="hidden" name="pno" value="${productVO.pno}">
									<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
									<input type="hidden" name="action" value="addFavorite">
								</FORM>
							</div>
							<div class="card-body">
								<h5 class="card-title">${productVO.categoryName}</h5>
								<h4 class="card-title">${productVO.pname}</h4>
								<div class="row">
									<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
										<div class="card-text">
											<h4 id="price">售價 ${productVO.pprice}</h4>
										</div>
									</div>
									<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
										<div class="card-text">
											<h4 id="ratings">
												<c:choose>
													<c:when test="${(productVO.ptotalRatings/productVO.pratingsQuantity)==1}">
														<span class="fa fa-star"></span>
														<span class="fa fa-star-o"></span>
														<span class="fa fa-star-o"></span>
														<span class="fa fa-star-o"></span>
														<span class="fa fa-star-o"></span>								
													</c:when>
													<c:when test="${(productVO.ptotalRatings/productVO.pratingsQuantity)==2}">
														<span class="fa fa-star"></span>
														<span class="fa fa-star"></span>
														<span class="fa fa-star-o"></span>
														<span class="fa fa-star-o"></span>
														<span class="fa fa-star-o"></span>									
													</c:when>
														<c:when test="${(productVO.ptotalRatings/productVO.pratingsQuantity)==3}">
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star-o"></span>
															<span class="fa fa-star-o"></span>									
														</c:when>
														<c:when test="${(productVO.ptotalRatings/productVO.pratingsQuantity)==4}">
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star-o"></span>									
														</c:when>	
														<c:when test="${(productVO.ptotalRatings/productVO.pratingsQuantity)==5}">
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>
															<span class="fa fa-star"></span>									
														</c:when>	
														<c:otherwise>
															<span class="fa fa-star-o"></span>
															<span class="fa fa-star-o"></span>
															<span class="fa fa-star-o"></span>
															<span class="fa fa-star-o"></span>
															<span class="fa fa-star-o"></span>
													</c:otherwise>								
												</c:choose>		
											</h4>
										</div>
									</div>						
								</div>
								<div class="row">
									<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1">
											<input class="btn btn-sm" type="submit" value="查看詳情" id="btnInfo">
											<input type="hidden" name="pno" value="${productVO.pno}">
											<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="getOneProduct_ByPno">
										</FORM>
									</div>
									<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6">
 										<FORM name="shoppingForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST">
											<input class="btn btn-sm" type="submit" value="加入購物車" onclick="addCart()">
											<input type="hidden" name="pno" value="${productVO.pno}">
											<input type="hidden" name="quantity" value="1">
											<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="ADD">
										</FORM>
									</div>								
								</div>
  							</div>
  						</div>
  					</div> 
			</c:forEach>
  		</div>
		<div class="row">
			<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
				<%@ include file="/front_end/free/product/page2.file"%>  
			</div>
		</div>

	<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->

<script>

	function addFavorite() {
        alert("商品已加入收藏 !"); 
	}
	
	function addCart() {
        alert("商品已加入購物車 !"); 
	}	

</script>
</body>
</html>