<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.p_order.model.*"%>

<%
	P_orderVO p_orderVO = (P_orderVO) request.getAttribute("p_orderVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>修改訂單</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		#btnback{
			margin-bottom:10px;
			margin-top:0px;
		}
		#btnModify{
			margin-top:0px;
			margin-bottom:30px;
		}
		input.inputReadonly{
			border:none;
			background-color:transparent;
			outline: none;
			color: #858796;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分-->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">修改訂單</h1>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<div class="row col-md-12 col-md-offset-2 custyle">
    		<a href="<%=request.getContextPath()%>/back_end/p_order/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回訂單管理</a>	
    	</div>
    	
        <div class="row col-md-12 col-md-offset-2 custyle">	
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/p_order/p_order.do" name="form1">
				<table class="table table-striped custab">
					<tr>
						<td>商品訂單編號:<font color=red><b>*</b></font></td>
						<td><%=p_orderVO.getPorderNo()%></td>
					</tr>
					<tr>
						<td>會員編號:</td>
						<td><input class="inputReadonly" type="TEXT" name="mno" size="5" value="<%=p_orderVO.getMno()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>訂單成立時間:</td>
						<fmt:formatDate var="fmtDate" value="<%=p_orderVO.getPorderDate()%>" pattern="yyyy-MM-dd HH:mm:ss" />
						<td><input class="inputReadonly" type="TEXT" name="porderDate" size="20" readonly="readonly" value="${fmtDate}"/></td>
					</tr>					
					<tr>
						<td>總金額:</td>
						<td><input class="inputReadonly" type="TEXT" name="porderTotal" size="20" value="<%=p_orderVO.getPorderTotal()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>收件人姓名:</td>
						<td><input type="TEXT" name="porderName" size="45" value="<%= (p_orderVO==null)? "" : p_orderVO.getPorderName()%>" /></td>
					</tr>
					<tr>
						<td>收件人電話:</td>
						<td><input type="TEXT" name="porderPhone" size="45" value="<%= (p_orderVO==null)? "" : p_orderVO.getPorderPhone()%>" /></td>
					</tr>
					<tr>
						<td>收件人地址:</td>
						<td><textarea id="Address" name="porderAddress" rows="4" cols="42" maxlength="255"><%= (p_orderVO==null)? "" : p_orderVO.getPorderAddress()%></textarea></td>
					</tr>
					<tr>
						<td>付款方式:</td>
						<td><input class="inputReadonly" type="TEXT" name="ppayment" size="45" value="<%=p_orderVO.getPpayment()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>信用卡號:</td>
						<td><input class="inputReadonly" type="TEXT" name="pcreditCard" size="16" value="<%=p_orderVO.getPcreditCard()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>信用卡檢查碼:</td>
						<td><input class="inputReadonly" type="TEXT" name="pcreditCardCVV" size="3" value="<%=p_orderVO.getPcreditCardCVV()%>" readonly="readonly" /></td>
					</tr>															
					<tr>
						<td>運送方式:</td>
						<td><input class="inputReadonly" type="TEXT" name="pshipping" size="45" value="<%=p_orderVO.getPshipping()%>" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>訂單狀態:</td>
						<td>
							<select name="porderState">
								<option value="0" ${(p_orderVO.porderState==0)? 'selected':''}>未出貨</option>
								<option value="1" ${(p_orderVO.porderState==1)? 'selected':''}>已出貨</option>
								<option value="2" ${(p_orderVO.porderState==2)? 'selected':''}>已到貨</option>
								<option value="3" ${(p_orderVO.porderState==3)? 'selected':''}>訂單取消</option>
								<option value="4" ${(p_orderVO.porderState==4)? 'selected':''}>退貨</option>
							</select>
						</td>
					</tr>					
				</table>
				<br>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="porderNo" value="<%=p_orderVO.getPorderNo()%>">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->			
				<input type="submit" class="btn btn-outline-primary btn-sm" value="送出修改" id="btnModify">
			</FORM>
		</div>
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
</html>