<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_favorite.model.*"%>

<%
	Integer mno = (Integer) session.getAttribute("mno");
%>
<%
	P_favoriteService p_favoriteSvc = new P_favoriteService();
	List<P_favoriteVO> list = p_favoriteSvc.getAll_ByMno(mno);
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />


<!DOCTYPE html>
<html>
<head>
	<title>我的收藏</title>
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
  			padding-right:5px;
  			padding-left:5px;
  			line-height: 30px;
  			height: 30px;
  			width:80px;
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
				<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-heart"></i> 我的收藏:</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2 custyle">
				<table class="table table-striped custab">		
					<tr>
						<th>商品名稱</th>
						<th>查看詳情</th>
						<th>刪除收藏</th>
					</tr>
	
					<c:forEach var="p_favoriteVO" items="${list}">
						<tr>
							<td>
								<c:forEach var="productVO" items="${productSvc.all}">
                    				<c:if test="${p_favoriteVO.pno==productVO.pno}">${productVO.pno}【${productVO.pname}】</c:if>
                				</c:forEach>		
							</td>							
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
									<input type="submit" class="btn btn-sm" value="查看詳情">
									<input type="hidden" name="pno" value="${p_favoriteVO.pno}">
									<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
<%-- 									<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller--> --%>
									<input type="hidden" name="action" 		value="getOneProduct_ByPno">
								</FORM>
							</td>							
							<td>			
			  					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_favorite/p_favorite.do" style="margin-bottom: 0px;"> 
 			    					<input type="submit" value="刪除收藏" class="btn btn-sm">
 			    					<input type="hidden" name="pno" value="${p_favoriteVO.pno}">
 			    					<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
			    					<input type="hidden" name="action" value="delete">
								</FORM> 
							</td>
						</tr>
					</c:forEach>
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