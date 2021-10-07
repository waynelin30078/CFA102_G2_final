<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<html>
<head>
	<title>我的購物車</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>

<body>
<br>
	<% @SuppressWarnings("unchecked")
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
	%>
	
	<%MemberVO memberVO = (MemberVO)session.getAttribute("memberVO"); %>

	<%if (buylist == null || (buylist.size() == 0)) {%>
		<font size="+3">購物車沒有商品</font>
		 <p><a href="<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp"><font size="+1"><i class="far fa-hand-point-left"></i> 繼 續 購 物</font></a>
	<%}%>
	
	<%if (buylist != null && (buylist.size() > 0)) {%>
		<font size="+3">目前購物車的項目如下：</font>

		<table id="table-1">
    		<tr> 
				<th width="200">商品名稱</th>
      			<th width="100">商品單價</th>
      			<th width="100">商品數量</th>
      			<th width="120"><img src="<%=request.getContextPath()%>/front_end/protected/product/images/view-cart.png"></th>
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
              				<input type="submit" value="刪 除" class="button">
          				</form>
          			</td>
				</tr>
			<%}%>
		</table>
		<br>
		<p>
    	<form name="checkoutForm" action="<%=request.getContextPath()%>/Shopping/Shopping.do" method="POST">
			<input type="hidden" name="action"  value="CHECKOUT"> 
			<input type="submit" value="付款結帳" class="button">
		</form>
		<input type="button" name="Submit" value="繼續購物" class="btn" onclick="location.href='<%=request.getContextPath()%>/front_end/free/product/listAllProduct.jsp'" />
		<input name="Submit2" type="button" class="btn" onclick="location.href='javascript:history.go(-1);'" value="返 回 上 一 頁" />
	<%}%>
</body>
</html>