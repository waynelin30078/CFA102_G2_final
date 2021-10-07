<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="listProduct_ByCompositeQuery" scope="request" type="java.util.List<ProductVO>" />

<!DOCTYPE html>
<html>
<head>
	<title>後台_商品管理(複合查詢)</title>
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
				<b>＋</b>新增商品
			</a>
    		<a href="<%=request.getContextPath()%>/back_end/product/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回商品管理
    		</a>
    	</div> 
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>商品編號</th>
					<th>商品類別名稱</th>
					<th>商品名稱</th>
					<th>商品單價</th>
					<th>商品數量</th>
					<th>商品上架日期</th>
					<th>商品圖片1</th>
					<th>商品狀態</th>
					<th>詳情</th>
					<th>修改</th>
				</tr>
				
				<%@ include file="/back_end/product/pages/page1_ByCompositeQuery.file" %>
				<c:forEach var="productVO" items="${listProduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle' ${(productVO.pno==param.pno) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
						<td>${productVO.pno}</td>
						<td>${productVO.categoryName}</td>
						<td>${productVO.pname}</td>
						<td>${productVO.pprice}</td>
						<td>${productVO.pquantity}</td>
						<td>${productVO.ponDate}</td>
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
						<td>
							<c:choose>
								<c:when test="${productVO.pstate==0}">下架</c:when>
								<c:otherwise>上架</c:otherwise>
							</c:choose>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="詳情">
								<input type="hidden" name="pno" 		value="${productVO.pno}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" 		value="getOne_For_Display">
							</FORM>
						</td>						
						<td>
			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="修改"> 
			     				<input type="hidden" name="pno"      value="${productVO.pno}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action"	    value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/product/pages/page2_ByCompositeQuery.file" %>
		</div>
	</div>		

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>