<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="listProduct_ByCompositeQuery" scope="request" type="java.util.List<ProductVO>" />

<!DOCTYPE html>
<html>
<head>
	<title>��x_�ӫ~�޲z(�ƦX�d��)</title>
	<!-- ��include�]�t�F�Ahead���]�w�Pcss���s��--------------------- -->
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
	<!-- ��include�]�t�F�A�������B�n�J�n�X�A�s���q�o���g-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content ������g����----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">�Ҧ��ӫ~</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
			<a href="<%=request.getContextPath()%>/back_end/product/addProduct.jsp" class="btn btn-primary btn-xs pull-right" id="btnAdd">
				<b>��</b>�s�W�ӫ~
			</a>
    		<a href="<%=request.getContextPath()%>/back_end/product/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> �^�ӫ~�޲z
    		</a>
    	</div> 
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>�ӫ~�s��</th>
					<th>�ӫ~���O�W��</th>
					<th>�ӫ~�W��</th>
					<th>�ӫ~���</th>
					<th>�ӫ~�ƶq</th>
					<th>�ӫ~�W�[���</th>
					<th>�ӫ~�Ϥ�1</th>
					<th>�ӫ~���A</th>
					<th>�Ա�</th>
					<th>�ק�</th>
				</tr>
				
				<%@ include file="/back_end/product/pages/page1_ByCompositeQuery.file" %>
				<c:forEach var="productVO" items="${listProduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle' ${(productVO.pno==param.pno) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
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
								<c:when test="${productVO.pstate==0}">�U�[</c:when>
								<c:otherwise>�W�[</c:otherwise>
							</c:choose>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�Ա�">
								<input type="hidden" name="pno" 		value="${productVO.pno}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action" 		value="getOne_For_Display">
							</FORM>
						</td>						
						<td>
			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�ק�"> 
			     				<input type="hidden" name="pno"      value="${productVO.pno}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action"	    value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/product/pages/page2_ByCompositeQuery.file" %>
		</div>
	</div>		

	<!-- ��include�]�t�Ffoot�B�n�X���T�w�e���BBootstrap core JavaScript�BCore plugin JavaScript�B Custom scripts for all pages���s��-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>