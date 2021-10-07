<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>


<!DOCTYPE html>
<html>

<head>
<title>營養商城_我的購物車</title>

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

#deleteProduct{
	width:30px;
	height:30px;
	line-height:30px;
  	padding-right:45px;
  	padding-left:10px;
}

#checkBill{
	margin-top:40px;
}





</style>

</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp" %>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
	<!-- 服務很好先做一個置中的div(開頭) -->

	<% @SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
	%>
	
	<%MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>
	
	<%if (buylist == null || (buylist.size() == 0)) {%>
		<div class="row">
			<div class="col-md-4 col-md-offset-2 custyle"></div>		
			<div class="col-md-4 col-md-offset-2 custyle">
				<h1 class="h3 mb-4 text-gray-800">購物車目前沒有商品~</h1>
				<a href="<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp" class="btn btn-sm">
					<font size="+1"><i class="far fa-hand-point-left"></i> 回營養商城</font></a>
			</div>
			<div class="col-md-4 col-md-offset-2 custyle"></div>
		</div>
	<%}%>
	
	<%if (buylist != null && (buylist.size() > 0)) {%>
	<div class="row">
		<div class="col-md-12 col-md-offset-2 custyle">
			<h1 class="h3 mb-4 text-gray-800">目前購物車的項目如下：</h1>
		</div>	
	</div>	
	<div class="row">
		<div class="col-md-6 col-md-offset-2 custyle">
			<table id="table table-striped custab">
    			<tr> 
					<th width="200">商品名稱</th>
      				<th width="100">商品單價</th>
      				<th width="100">商品數量</th>
      				<th width="100">刪除項目</th>
    			</tr>
			</table>
			<table>
			<%
	 			for (int index = 0; index < buylist.size(); index++) {
		 		ProductVO order = buylist.get(index);
			%>
				<tr>
					<td width="200"><%=order.getPname()%>     </td>
					<td width="100"><%=order.getPprice()%>   </td>
					<td width="100"><%=order.getQuantity()%>   </td>
        			<td width="120">
          				<form name="deleteForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST">
							<input type="hidden" name="action"  value="DELETE">
              				<input type="hidden" name="del" value="<%= index %>">
              				<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
              				<input type="submit" value="刪 除" class="btn btn-sm" id="deleteProduct">
          				</form>
          			</td>
				</tr>
				<%}%>
			</table>		
		</div>
		<div class="col col-md-offset-2 custyle"></div>		
	</div>
	
	<div class="row" id="checkBill">
		<div class="col-1"></div>
		<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
			<FORM name="checkoutForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST">
				<input type="hidden" name="action"  value="CHECKOUT"> 
				<input type="hidden" name="requestURL" 	value="<%=request.getServletPath()%>">
				<input type="submit" value="付款結帳" class="btn btn-sm">
			</FORM>	
		</div>
		<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
			<input type="submit" value="繼續購物" class="btn btn-sm" onclick="location.href='<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp'" />
		</div>								
	</div>	
	
	
	
	
	
	
	
	
	</div>
<!-- 	<input name="Submit2" type="button" class="btn" onclick="location.href='javascript:history.go(-1);'" value="返 回 上 一 頁" />		 -->
<%}%>

<!-- 服務很好先做一個置中的div(結束) -->
</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>