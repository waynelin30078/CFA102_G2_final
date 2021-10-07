<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.promotion.model.*"%>

<jsp:useBean id="listPromotion_ByCompositeQuery" scope="request" type="java.util.List<PromotionVO>" />

<!DOCTYPE html>
<html>
<head>
	<title>��x_�ӫ~�u�f���ʺ޲z(�ƦX�d��)</title>
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
		<h1 class="h3 mb-4 text-gray-800">�Ҧ��ӫ~�u�f����</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/promotion/addPromotion.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd">
				<b>��</b>�s�W�ӫ~�u�f����
			</a>
    		<a href="<%=request.getContextPath()%>/back_end/promotion/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> �^�ӫ~�u�f���ʺ޲z
    		</a>	
    	</div> 
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>�u�f���ʽs��</th>
					<th>�u�f���ʦW��</th>
					<th>�}�l���</th>
					<th>�������</th>
					<th>�ק�</th>
				</tr>
				
				<%@ include file="/back_end/promotion/pages/page1_ByCompositeQuery.file" %>
				
				<c:forEach var="promotionVO" items="${listPromotion_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${(promotionVO.promNo==param.promNo) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
						<td>${promotionVO.promNo}</td>
						<td>${promotionVO.promName}</td>
						<td>${promotionVO.promStartDate}</td>
						<td>${promotionVO.promEndDate}</td>
						<td>
			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion/promotion.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�ק�"> 
			     				<input type="hidden" name="promNo"      value="${promotionVO.promNo}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action"	    value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/promotion/pages/page2_ByCompositeQuery.file" %>
		</div>
	</div>		

	<!-- ��include�]�t�Ffoot�B�n�X���T�w�e���BBootstrap core JavaScript�BCore plugin JavaScript�B Custom scripts for all pages���s��-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>