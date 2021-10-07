<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.promotion.model.*"%>	

<%
	PromotionService promotionSvc = new PromotionService();
    List<PromotionVO> list = promotionSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
	<title>後台_商品優惠活動管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		#btnAdd, #btnback {
			margin-bottom: 10px;
			margin-top: 0px;
		}
		#btnback {
			margin-left: 10px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">所有商品優惠活動</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/promotion/addPromotion.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd"> 
				<b>＋</b>新增優惠活動
			</a> 
			<a href="<%=request.getContextPath()%>/back_end/promotion/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
				<i class="far fa-hand-point-left"></i> 回商品優惠活動管理
			</a>
		</div>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<table class="table table-striped custab">
				<tr>
					<th>優惠活動編號</th>
					<th>優惠活動名稱</th>
					<th>開始日期</th>
					<th>結束日期</th>
					<th>修改</th>
				</tr> 
			
				<%@ include file="/back_end/promotion/pages/page1.file" %> 
				<c:forEach var="promotionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${promotionVO.promNo}</td>
						<td>${promotionVO.promName}</td>
						<td>${promotionVO.promStartDate}</td>
						<td>${promotionVO.promEndDate}</td>
						<td>
			  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion/promotion.do" style="margin-bottom: 0px;">
			     				<input type="submit" class="btn btn-primary btn-sm" value="修改">
			     				<input type="hidden" name="promNo" 		value="${promotionVO.promNo}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--送出當前是第幾頁給Controller-->			     				
			     				<input type="hidden" name="action"		value="getOne_For_Update">
			     			</FORM>
						</td>
					</tr>
				</c:forEach>
    		</table>
    		<%@ include file="/back_end/promotion/pages/page2.file" %>	
    	</div>		
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>