<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.course.model.*"%>
<%@ page import="java.util.Vector" %>
<%@ page import="com.dietician.*" %>
<jsp:useBean id="DieticianSvc" scope="page"
	class="com.dietician.model.DieticianService" />
	<%
	com.dietician.model.DieticianService dietsvc = new com.dietician.model.DieticianService();
	pageContext.setAttribute("dietsvc", dietsvc);
%>
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<style>
	

</style>
</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->





<div class="row" >







<%
	Vector<CourseVO> buylist = (Vector<CourseVO>) session.getAttribute("shoppingcart");
	String amount = (String) request.getAttribute("amount");
%>

<%if (buylist != null && (buylist.size() > 0)) {%>

 <font size="+3">目前您購物車的內容如下：</font><p>
<div class="col-6">
<table border="1"  class="table table-sm">
	<tr bgcolor="#999999">
		<th width="200">課程名稱</th>
		<th width="100">營養師</th>
		<th width="100">價格</th>
		<th width="120"></th>
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 CourseVO order = buylist.get(index);
		 pageContext.setAttribute("order", order);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getCname()%></b></div></td>
		<td width="100"><div align="center"><b>${dietsvc.findByPrimaryKey(order.dno).dname}</b></div></td>
		<td width="100"><div align="center"><b><%=order.getCprice()%></b></div></td>
		
		
		
		<td width="100"><div align="center">
          <form name="deleteForm" action="<%=request.getContextPath()%>/c_order/c_order.do" method="POST">
              <input type="hidden" name="action" value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪除" class="btn btn-light">
              </form>
              </div>
        </td>
	</tr>
	<%}%>
	<tr>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td> <font color="red"><b>$<%=amount %></b></font> </td>
		
	</tr>
</table>
</div>
<div class="col-6">
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/c_order/c_order.do" method="POST">
          		<h3>請填寫結帳資訊</h3><br>
        <table id = table1>

        
            <tr>
                <td>信用卡號: <input type="text" name="paymentInfo" placeholder="請輸入信用卡號:16個數字" maxlength="16" required pattern="[0-9]{16}">
                </td>
            </tr>
            <tr>
                <td>有效日期: <input type="text" name="validdates" placeholder="請輸入有效日期:mm/yy" maxlength="4" required >
                </td>
            </tr>
            <tr>
                <td>安全碼: <input type="text" name="csc" placeholder="請輸入安全碼" maxlength="3" required>
                </td>
            </tr>
        
        </table>
       			    <input type="hidden" name="paymentMethod" value="1">
        		<input type = "hidden" name="total" value="<%=amount %>">
              <input type="hidden" name="action"	value="CHECKOUT"> 
              <input type="submit" value="付款結帳">
          </form>
          <br>
          <a href="<%=request.getContextPath()%>/front_end/free/course/course_front_page.jsp">繼續瀏覽課程</a>
          </div> 
<%}else{%>
<font size="+3">目前您的購買清單的無內容</font><p>
<a href="<%=request.getContextPath()%>/front_end/free/course/course_front_page.jsp">繼續瀏覽課程</a>
<%} %>


</div>



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