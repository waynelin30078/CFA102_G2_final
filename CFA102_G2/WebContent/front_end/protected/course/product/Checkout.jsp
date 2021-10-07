<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html>

<head>
<title>結帳</title>

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
  padding-right:10px;
  padding-left:10px;
  line-height: 30px;
  height: 30px;
  width:100px;
  border-color:#0a4d3c;
  border-width:1px;
  margin-bottom:10px;
  display:inline;
}

.table.table-striped.custab tr{ 
  width:100%; 
/*   height:20px; */
}

input{
 height: 30px;
 margin-top:0px;
} 
 
textarea{
 height: 60px;
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
				<h1 class="h3 mb-4 text-gray-800"><i class="fas fa-money-check-alt"></i> 結帳:</h1>
			</div>
			<div class="col col-md-offset-2 custyle"></div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-2 custyle">
					<table id="table-1" class="table table-striped custab">
						<tr>
							<th width="200">商品名稱</th>
							<th width="100">商品單價</th>
							<th width="100">商品數量</th>
							<th width="120">總價</th>
						</tr>
					</table>
					<table >
					<%  @SuppressWarnings("unchecked")
						Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
						String amount =  (String) session.getAttribute("amount");
					%>	
					<%	for (int i = 0; i < buylist.size(); i++) {
							ProductVO order = buylist.get(i);
							String pname = order.getPname();
							Integer pprice = order.getPprice();
							Integer quantity = order.getQuantity();
					%>
							<tr>
								<td width="200"><%=pname%></td>
								<td width="100"><%=pprice%></td>
								<td width="100"><%=quantity%></td>
								<td width="120"><%=pprice*quantity%></td>
							</tr>
					<%}%>
						<tr>
							<td colspan="6" style="text-align:right;"> 
		   						<h3>總金額： $<%=amount%></h3>
	    					</td>
						</tr>
					</table>
			</div>
			<div class="col-md-6 col-md-offset-2 custyle">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>		
				<FORM name="checkoutForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST">
					<table class="table table-striped custab">
						<tr>
							<td>收件人:</td>
							<td><input type="TEXT" name="porderName" size="20"/></td>
						</tr>
						<tr>
							<td>收件人電話:</td>
							<td><input type="TEXT" name="porderPhone" size="10"/></td>
						</tr>
						<tr>
							<td>收件人地址:</td>
							<td><textarea  name="porderAddress" rows="4" cols="22" maxlength="255"></textarea></td>
						</tr>
						<tr>
							<td>付款方式:</td>
							<td>
								<select name="ppayment">
									<option value="1" ${(p_orderVO.ppayment==1)? 'selected':''}>貨到付款</option>
									<option value="2" ${(p_orderVO.ppayment==2)? 'selected':''}>信用卡</option>
								</select>					
							</td>
						</tr>
						<tr>
							<td>信用卡號:</td>
							<td><input type="TEXT" name="pcreditCard" size="16"/></td>
						</tr>
						<tr>
							<td>信用卡檢查碼:</td>
							<td><input type="TEXT" name="pcreditCardCVV" size="3"/></td>
						</tr>
						<tr>
							<td>運送方式:</td>
							<td>
								<select name="pshipping">
									<option value="0" ${(p_orderVO.pshipping==1)? 'selected':''}>超商取貨</option>
									<option value="1" ${(p_orderVO.pshipping==2)? 'selected':''}>宅配</option>
								</select>
							</td>
						</tr>															
					</table>
					<div class="row">
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<input type="submit" value="繼續購物" class="btn btn-sm" onclick="location.href='<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp'" />
					</div>	
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<input type="hidden" name="action"  value="CHECKOUT_BillInfo">
						<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
						<input type="hidden" name="amount" 	value="<%=amount%>">
<%-- 						<input type="hidden" name="pno" 	value="${(p_orderVO.pno)}"> --%>
						<input type="submit" value="確認送出" class="btn btn-sm">		
					</div>
				</div>				
			</FORM>	
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
<script>




</script>
</body>
</html>