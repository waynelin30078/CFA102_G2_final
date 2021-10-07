<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="promotionSvc" scope="page" class="com.promotion.model.PromotionService" />
<jsp:useBean id="promotion_detailSvc" scope="page" class="com.promotion_detail.model.Promotion_detailService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
	<title>後台_優惠活動明細管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h1 mb-4 text-gray-800">優惠活動明細管理</h1>
		<h4 class="h4 mb-2 text-gray-800">列表:</h4>
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-lg-6">
    			<div class="list-group">
  					<a href="<%=request.getContextPath()%>/back_end/promotion_detail/listAllPromotion_detail.jsp"
     		   		  class="list-group-item list-group-item-action">● 優惠活動明細列表
  					</a>
				</div> 
    		</div>	
    	</div>	
    	
		<h4 class="h4 mb-2 mt-2 text-gray-800">查詢:</h4>	
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-lg-6">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion_detail/promotion_detail.do" name="form1">
					<ul class="list-group">
  						<li class="list-group-item">選擇優惠活動:
							<select size="1" name="promNo">
								<option value="" selected>
								<c:forEach var="promotionVO" items="${promotionSvc.all}">
									<option value="${promotionVO.promNo}">${promotionVO.promName}
								</c:forEach>
							</select> 					
  						</li>
  						<li class="list-group-item">選擇優惠商品:
							<select size="1" name="pno">
								<option value="" selected>
         						<c:forEach var="productVO" items="${productSvc.all}" > 
          							<option value="${productVO.pno}">${productVO.pno}.${productVO.pname}
        	 					</c:forEach>   
							</select> 				
  						</li>
  						<li class="list-group-item">商品價格:
							<input type="text" name="pprice" value="" size="5">
 							<b>至</b>
							<input type="text" name="pprice1" value="" size="5">	
  						</li>
  						<li class="list-group-item">
       						<input type="submit" class="btn btn-outline-primary btn-sm" value="送出">
       						<input type="hidden" name="action" value="listPromotion_detail_ByCompositeQuery">		
  						</li>    						
					</ul>
				</FORM>  		
    		</div>
  		</div>
  		
  		<h4 class="h4 mb-2 mt-4 text-gray-800">管理</h4>
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-lg-6">
    			<div class="list-group">
  					<a href="<%=request.getContextPath()%>/back_end/promotion_detail/addPromotion_detail.jsp"
     		   		   class="list-group-item list-group-item-action">● 新增優惠活動明細
  					</a>  		
				</div> 
    		</div>
  		</div>    	
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>