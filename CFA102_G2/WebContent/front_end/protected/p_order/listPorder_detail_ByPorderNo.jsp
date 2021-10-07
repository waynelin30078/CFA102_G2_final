<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order_detail.model.*"%>
<%@ page import="com.p_order.model.*"%>

<%
	P_order_detailVO p_order_detailVO = (P_order_detailVO) request.getAttribute("p_order_detailVO");
%>

<jsp:useBean id="listPorder_detail_ByPorderNo" scope="request" type="java.util.Set<P_order_detailVO>" />
<jsp:useBean id="p_orderSvc" scope="page" class="com.p_order.model.P_orderService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<%-- <jsp:useBean id="discountProducts" scope="page" class="com.promotion_detail.model.Promotion_detailService" /> --%>


<!DOCTYPE html>
<html>
<head>
	<title>我的訂單明細</title>
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		.fa.fa-star, .fa.fa-star-o{
			color: #ffa300;
			font-size: 15px;
		}
		table.table-striped.custab{
			width:100%;
		}
		.hide {
  			display: none;
		}
		.clear {
			float: none;
  			clear: both;
		}
		.rating {
    		width: 110px;
    		unicode-bidi: bidi-override;
    		direction: rtl;
    		text-align: left;
    		position: relative;
    		font-size:20px;
		}
		.rating > label {
    		float: right;
    		display: inline;
    		padding: 0;
    		margin: 0;
   		 	position: relative;
   		 	width: 1.1em;
    		cursor: pointer;
    		color: #000;
		}
		.rating > label:hover,
		.rating > label:hover ~ label,
		.rating > input.radio-btn:checked ~ label {
    		color: transparent;
    		font-size:20px;
		}
		.rating > label:hover:before,
		.rating > label:hover ~ label:before,
		.rating > input.radio-btn:checked ~ label:before,
		.rating > input.radio-btn:checked ~ label:before {
			content: "\2605";
    		position: absolute;
   	 		left: 0;
    		color: #FFD700;
    		font-size:20px;
		}
		
		
	</style>
</head>

<body>
		<div class="row">
			<div class="col-md-6 col-md-offset-2 custyle">
				<h1 class="h3 mb-4 text-gray-800"><i class="far fa-file-alt"></i> 我的訂單明細:</h1>
			</div>
			<div class="col col-md-offset-2 custyle"></div>
		</div>
		
		<div class="row">
			<div class="col-md-12 col-md-offset-2 custyle">
				<table class="table table-striped custab">			
					<tr>
						<th>訂單編號</th>
						<th>商品名稱</th>
						<th>訂購數量</th>
						<th>商品單價</th>
						<th>商品評價</th>
					</tr>
	
				<c:forEach var="p_order_detailVO" items="${listPorder_detail_ByPorderNo}">
					<tr>
						<td>${p_order_detailVO.porderNo}</td>
						<td>
							<c:forEach var="productVO" items="${productSvc.all}">
                    			<c:if test="${p_order_detailVO.pno==productVO.pno}">${productVO.pno}【${productVO.pname}】</c:if>
                			</c:forEach>
						</td>
						<td>${p_order_detailVO.porderQuantity}</td>
						<td>${p_order_detailVO.pprice}</td>
						<td>
							<c:choose>
								<c:when test="${p_order_detailVO.pratings==1}">
									<span class="fa fa-star"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>								
								</c:when>
								<c:when test="${p_order_detailVO.pratings==2}">
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>									
								</c:when>
								<c:when test="${p_order_detailVO.pratings==3}">
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star-o"></span>
									<span class="fa fa-star-o"></span>									
								</c:when>
								<c:when test="${p_order_detailVO.pratings==4}">
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star-o"></span>									
								</c:when>	
								<c:when test="${p_order_detailVO.pratings==5}">
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
									<span class="fa fa-star"></span>									
								</c:when>
								<c:when test="${p_order_detailVO.pratings==0}">
        							<div class="rating">
            							<input id="star5" name="star" type="radio" value="5" class="radio-btn hide" />
            							<label for="star5" >☆</label>
            							<input id="star4" name="star" type="radio" value="4" class="radio-btn hide" />
            							<label for="star4" >☆</label>
            							<input id="star3" name="star" type="radio" value="3" class="radio-btn hide" />
            							<label for="star3" >☆</label>
            							<input id="star2" name="star" type="radio" value="2" class="radio-btn hide" />
            							<label for="star2" >☆</label>
            							<input id="star1" name="star" type="radio" value="1" class="radio-btn hide" />
            							<label for="star1" >☆</label>
            							<div class="clear"></div>
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order_detail/p_order_detail.do" style="margin-bottom: 0px;" id="form"> 
											<input type="submit" value="確定評價" class="btn btn-sm">
											<input type="hidden" name="porderNo" value="${p_order_detailVO.porderNo}">
											<input type="hidden" name="pno" value="${p_order_detailVO.pno}">
											<input type="hidden" name="pratings" id="ratingStar">
											<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>   
									</div>
								</c:when>								
							</c:choose>						
						</td>					
					</tr>
				</c:forEach>
			</table>	
		</div>
	</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script>

$(document).ready(function(){
	var userRating ="";
    // Check Radio-box 
    $(".rating input:radio").attr("checked", false); 

    $('.rating input').click(function() {
		$(".rating span").removeClass('checked');
		$(this).parent().addClass('checked'); 
    }); 

    $('input:radio').change(function(){ 
    	userRating = this.value;
    });
    
    var form = document.getElementById('form');
    form.addEventListener('submit', function() {
    	var ratingStart = document.getElementById('ratingStar');
    	ratingStart.value = userRating;
    }); 
});



</script> 
</body>
</html>