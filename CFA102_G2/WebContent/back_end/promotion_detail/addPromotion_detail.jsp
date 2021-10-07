<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
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
		#btnback{
			margin-bottom:10px;
			margin-top:0px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">新增優惠活動明細</h1>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<div class="row col-md-12 col-md-offset-2 custyle">
    		<a href="<%=request.getContextPath()%>/back_end/promotion_detail/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回優惠活動明細管理</a>	
    	</div>
    	
    	<div class="row col-md-12 col-md-offset-2 custyle">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion_detail/promotion_detail.do" name="form1">    		
    			<table class="table table-striped custab">
					<tr>
						<td>優惠活動:</td>
						<td>
							<select size="1" name="promNo">
								<c:forEach var="promotionVO" items="${promotionSvc.all}">
										<option value="${promotionVO.promNo}" ${(promotion_detailVO.promNo==promotionVO.promNo)? 'selected':'' } >${promotionVO.promName}
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>優惠商品:</td>
						<td>
							<select size="1" name="pno">
								<c:forEach var="productVO" items="${productSvc.all}">
										<option value="${productVO.pno}" ${(promotion_detailVO.pno==productVO.pno)? 'selected':'' } >${productVO.pname}
								</c:forEach>
							</select>
						</td>		
					</tr>
					<tr>
						<td>商品單價:</td>
						<td><input type="TEXT" name="pprice" size="5" value="<%= (promotion_detailVO==null)? "" : promotion_detailVO.getPprice()%>" /> </td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="insert">
				<input type="submit" class="btn btn-outline-primary btn-sm" value="送出新增">
			</FORM>
    	</div>		
	</div>			
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>