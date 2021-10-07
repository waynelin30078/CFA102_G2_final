<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html>

<head>
<title>結帳完成</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>

<style>

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
				<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-money-check-alt"></i> 結帳完成，感謝您的購買 !</h1>
			</div>
			<div class="col col-md-offset-2 custyle"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-12" id="divBack">
    			<h4>您可以繼續...</h4>
			</div>		
			<div class="col-12" id="divBack">
    			<a href="<%=request.getContextPath()%>/front_end/protected/p_order/listAllP_orderByMno.jsp" class="btn btn-sm">
    				<i class="far fa-hand-point-left"></i>查看我的訂單
    			</a>    			
			</div>
			<div class="col-12" id="divBack">
    			<a href="<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp" class="btn btn-sm">
    				<i class="far fa-hand-point-left"></i>瀏覽商品
    			</a>    			
			</div>
			<div class="col-12" id="divBack">
    			<a href="<%=request.getContextPath()%>/front_end/free/home.jsp" class="btn btn-sm">
    				<i class="far fa-hand-point-left"></i>回首頁
    			</a>    			
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




</script>
</body>
</html>