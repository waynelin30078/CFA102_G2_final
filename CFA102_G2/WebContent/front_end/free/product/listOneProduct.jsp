<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) session.getAttribute("productVO");
%>

<!DOCTYPE html>
<html>

<head>
	<title>營材食教</title>
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
		}	
		img {
			max-width:100%;
			display:block;
			margin:auto;
		} 
		a {
			color:#fff;
		}
 		.carousel-control-prev-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%230a4d3c' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E"); */
		}
		.carousel-control-next-icon {
			background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%230a4d3c' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E"); */
		}
		button.qty-btn-minus.btn-light:focus, input.input-qty:focus { 
			outline: none; 
			box-shadow: none; 
		}
		#input-qty {
			margin: 0px 0px 0px;
			height:38px;
		}
		h6.tags.label.label-primary {
			margin-top:10px;
		}
		a, a:hover {
			text-decoration: none; 
		}
		.product-price {
			margin-top:20px;
			color:#ffa300;
		}
		.qty-container { 
 			display: flex; 
 			align-items: center; 
 			justify-content: center; 
		}
		.qty-container .input-qty { 
 			text-align: center; 
 			padding: 6px 10px; 
 			border: 1px solid #d4d4d4; 
 			max-width: 80px; 
		}
		.qty-container .qty-btn-minus, .qty-container .qty-btn-plus { 
 			border: 1px solid #d4d4d4; 
 			padding: 10px 13px;
 			font-size: 10px; 
 			height: 38px; 
 			width: 38px; 
 			transition: 0.3s; 
 		}	
		.qty-container .qty-btn-plus { 
 			margin-left: -1px; 
 		}	
		.qty-container .qty-btn-minus { 
			 margin-right: -1px; 
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
	</style>
</head>

<body>

	<!-- *************每一頁body最前面都要include這個header連結******************* -->
		<%@ include file="/front_end_example/header_link.jsp" %>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
	<!-- 服務很好先做一個置中的div(開頭) -->
		<div id="right">
			<a href="<%=request.getContextPath()%>/front_end/protected/product/Cart.jsp" id="goToCart">	
    			<img src="<%=request.getContextPath()%>/front_end/free/images/cart.png" id="cart">
    		</a>
		</div>
		<div class="row">
			<div class="col-12" id="divBack">
    			<a href="<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp" class="btn btn-sm" id="btnback">
    				<i class="far fa-hand-point-left"></i>商品
    			</a>			
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div id="carouselProducts" class="carousel slide" data-ride="carousel" data-interval="3000">
  					<ol class="carousel-indicators">
    					<li data-target="#carouselProducts" data-slide-to="0" class="active"></li>
    					<li data-target="#carouselProducts" data-slide-to="1"></li>
    					<li data-target="#carouselProducts" data-slide-to="2"></li>
  					</ol> 					
  					<div class="carousel-inner">
    					<div class="carousel-item active">
							<c:choose>	
								<c:when test="${productVO.pimage1.trim() == null || productVO.pimage1.trim().length() == 0}">
									<img src="<%=request.getContextPath()%>/back_end/product/images/null3.jpg"  border="0" class="d-block w-50 h-50">
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath()%>${productVO.pimage1}" border="0" class="d-block w-50 h-50">
								</c:otherwise>
							</c:choose>	    					
    					</div>
    					<div class="carousel-item">
							<c:choose>	
								<c:when test="${productVO.pimage2.trim() == null || productVO.pimage2.trim().length() == 0}">
									<img src="<%=request.getContextPath()%>/back_end/product/images/null3.jpg"  border="0" class="d-block w-50 h-50">
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath()%>${productVO.pimage2}" border="0" class="d-block w-50 h-50">
								</c:otherwise>
							</c:choose>
    					</div>
    					<div class="carousel-item">
							<c:choose>	
								<c:when test="${productVO.pimage3.trim() == null || productVO.pimage3.trim().length() == 0}">
									<img src="<%=request.getContextPath()%>/back_end/product/images/null3.jpg"  border="0" class="d-block w-50 h-50">
								</c:when>
								<c:otherwise>
									<img src="<%=request.getContextPath()%>${productVO.pimage3}" border="0" class="d-block w-50 h-50">
								</c:otherwise>
							</c:choose>
    					</div>
  					</div>
					<a class="carousel-control-prev" href="#carouselProducts" role="button" data-slide="prev">
    					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    					<span class="sr-only">Previous</span>
  					</a>
  					<a class="carousel-control-next" href="#carouselProducts" role="button" data-slide="next">
    					<span class="carousel-control-next-icon" aria-hidden="true"></span>
    					<span class="sr-only">Next</span>
  					</a>
				</div>	
    		</div>
    		<div class="col-md-6">
				<div class="row">
					<div class="col-md-12">
						<h1 class="main-title" id="pname">${productVO.pname}</h1>
					</div>      													
				</div>    		
				<div class="row">
					<div class="col-md-12">
						<h6 class="tags label label-primary" id="categoryName">商品類別 : ${productVO.categoryName}</h6>
					</div>
				</div>
				<br> 	
				<div class="row">
        			<div class="col-md-12">
          				<p class="description">商品成分 :<br> ${productVO.pinfo}</p>
        			</div>
      			</div>
      			<div class="row">
					<div class="col-md-12 bottom-rule">
						<h3 class="product-price">售價 ${productVO.pprice}</h3>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-6 col-md-6 mb-6">
		    			<div class="qty-container">
		        			<button class="qty-btn-minus btn-light" type="button" id="btnQtyMinus"><i class="fa fa-minus"></i></button>
		    				<input type="text" name="qty" value="1" class="input-qty" readonly="readonly" id="input-qty"/>
		        			<button class="qty-btn-plus btn-light" type="button"  id="btnQtyPlus"><i class="fa fa-plus"></i></button>
		    			</div>
					</div>
					<div class="col-sm-6 col-md-6 mb-6">
						<form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST" id="form">
		    				<input type="submit" class="btn btn-sm" value="放入購物車" onclick="addCart()" id="btnAddtoCart">
							<input type="hidden" name="pno" value="${productVO.pno}">
							<input type="hidden" name="quantity" id="getQty">
							<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="ADD">
						</form>	
					</div>
				</div>	     
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

<script>
	var buttonPlus  = $(".qty-btn-plus");
	var buttonMinus = $(".qty-btn-minus");
// 	var qty = $(".qty-btn-minus");

	var incrementPlus = buttonPlus.click(function() {
		var $n = $(this)
		.parent(".qty-container")
		.find(".input-qty");
		$n.val(Number($n.val())+1 );
	});

	var incrementMinus = buttonMinus.click(function() {
		var $n = $(this)
  		.parent(".qty-container")
  		.find(".input-qty");
  		var amount = Number($n.val());
  		if (amount > 0) {
	  		if((amount-1) != 0){
		  		$n.val(amount-1);
	  		}else{
		  		$n.val(1); 
	  		}
  		}
	});

	var form = document.getElementById('form');
	form.addEventListener('submit', function() {
		var inputQty = document.getElementById('input-qty');
		var getQty = document.getElementById('getQty');
		getQty.value = inputQty.value;
	});
	
	function addCart() {
        alert("商品已加入購物車 !"); 
	}	

</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</body>
</html>