<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotion_detail.model.*"%>

<jsp:useBean id="listPromotion_detail_ByCompositeQuery" scope="request" type="java.util.List<Promotion_detailVO>" /> 
<jsp:useBean id="promotionSvc" scope="page" class="com.promotion.model.PromotionService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<!DOCTYPE html>
<html>
<head>
	<title>��x_�u�f���ʩ��Ӻ޲z(�ƦX�d��)</title>
	<!-- ��include�]�t�F�Ahead���]�w�Pcss���s��--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
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
	<!-- ��include�]�t�F�A�������B�n�J�n�X�A�s���q�o���g-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content ������g����----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">�Ҧ��u�f���ʩ���(�ƦX�d��)</h1>
		<div class="row col-md-12 custyle">
			<a href="<%=request.getContextPath()%>/back_end/promotion_detail/addPromotion_detail.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd">
				<b>��</b>�s�W�u�f���ʩ���</a>
    		<a href="<%=request.getContextPath()%>/back_end/promotion_detail/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> �^�u�f���ʩ��Ӻ޲z</a>	
    	</div> 
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>�u�f����</th>
					<th>�u�f�ӫ~</th>
					<th>�ӫ~����</th>
					<th>�ק�</th>
				</tr>
				
				<%@ include file="/back_end/promotion_detail/pages/page1_ByCompositeQuery.file" %>
				<c:forEach var="promotion_detailVO" items="${listPromotion_detail_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${((promotion_detailVO.promNo==param.promNo)&&(promotion_detailVO.pno==param.pno)) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
						<td>
							<c:forEach var="promotionVO" items="${promotionSvc.all}">
                    			<c:if test="${promotion_detailVO.promNo==promotionVO.promNo}">�i${promotionVO.promName}�j</c:if>
                			</c:forEach>
						</td>
						<td>
							<c:forEach var="productVO" items="${productSvc.all}">
                    			<c:if test="${promotion_detailVO.pno==productVO.pno}">${promotion_detailVO.pno}�i${productVO.pname}�j</c:if>
                			</c:forEach>				
						</td>
						<td>${promotion_detailVO.pprice}</td>
						<td>
			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion_detail/promotion_detail.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�ק�"> 
			     				<input type="hidden" name="promNo"      value="${promotion_detailVO.promNo}">
			     				<input type="hidden" name="pno"      	value="${promotion_detailVO.pno}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action"	    value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/promotion_detail/pages/page2_ByCompositeQuery.file" %>
		</div>
	</div>		

	<!-- ��include�]�t�Ffoot�B�n�X���T�w�e���BBootstrap core JavaScript�BCore plugin JavaScript�B Custom scripts for all pages���s��-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>