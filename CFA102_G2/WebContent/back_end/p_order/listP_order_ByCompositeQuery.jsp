<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.p_order.model.*"%>

<jsp:useBean id="listP_order_ByCompositeQuery" scope="request" type="java.util.List<P_orderVO>" />

<!DOCTYPE html>
<html>
<head>
	<title>��x_�q��޲z(�ƦX�d��)</title>
	<!-- ��include�]�t�F�Ahead���]�w�Pcss���s��--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		#btnback{
			margin-bottom:10px;
			margin-top:0px;
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
		<h1 class="h3 mb-4 text-gray-800">�Ҧ��q��</h1>
		<div class="row col-md-12 col-md-offset-2 custyle">
    		<a href="<%=request.getContextPath()%>/back_end/p_order/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> �^�q��޲z
    		</a>
    	</div> 
    	<div class="row col-md-12 col-md-offset-2 custyle">
    		<table class="table table-striped custab">
				<tr>
					<th>�ӫ~�q��s��</th>
					<th>�|���s��</th>
					<th>�q�榨�߮ɶ�</th>
					<th>�`���B</th>
					<th>����H�m�W</th>
					<th>�I�ڤ覡</th>
					<th>�B�e�覡</th>
					<th>�q�檬�A</th>
					<th>�Ա�</th>
					<th>�ק�</th>
				</tr>
				
				<%@ include file="/back_end/p_order/pages/page1_ByCompositeQuery.file" %>
				
				<c:forEach var="p_orderVO" items="${listP_order_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${(p_orderVO.porderNo==param.porderNo) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
						<td>${p_orderVO.porderNo}</td>
						<td>${p_orderVO.mno}</td>
						<td>${p_orderVO.porderDate}</td>
						<td>${p_orderVO.porderTotal}</td>
						<td>${p_orderVO.porderName}</td>
						<td>
							<c:choose>
								<c:when test="${p_orderVO.ppayment==1}">�f��I��</c:when>
								<c:when test="${p_orderVO.ppayment==2}">�H�Υd</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${p_orderVO.pshipping==1}">�W�Ө��f</c:when>
								<c:when test="${p_orderVO.pshipping==2}">�v�t</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${p_orderVO.porderState==0}">���X�f</c:when>
								<c:when test="${p_orderVO.porderState==1}">�w�X�f</c:when>
								<c:when test="${p_orderVO.porderState==2}">�w��f</c:when>
								<c:when test="${p_orderVO.porderState==3}">�q�����</c:when>						
								<c:otherwise>�h�f</c:otherwise>							
							</c:choose>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�Ա�">
								<input type="hidden" name="porderNo" 	value="${p_orderVO.porderNo}">
								<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage" 	value="<%=whichPage%>"><!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action" 		value="getOne_For_Display">
							</FORM>
						</td>						
						<td>
			 				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" style="margin-bottom: 0px;">
								<input type="submit" class="btn btn-primary btn-sm" value="�ק�"> 
			     				<input type="hidden" name="porderNo"      value="${p_orderVO.porderNo}">
			     				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
								<input type="hidden" name="action"	    value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="/back_end/p_order/pages/page2_ByCompositeQuery.file" %>
		</div>
	</div>		
	<!-- /.container-fluid -->
	
	<!-- ��include�]�t�Ffoot�B�n�X���T�w�e���BBootstrap core JavaScript�BCore plugin JavaScript�B Custom scripts for all pages���s��-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>