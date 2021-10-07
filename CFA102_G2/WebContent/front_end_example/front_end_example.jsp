<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.course.model.*"%>
<%@ page import="java.util.Vector" %>
<%
Vector<CourseVO> buylist = (Vector<CourseVO>) session.getAttribute("shoppingcart");
Integer total = (Integer)request.getAttribute("total");
%>
<jsp:useBean id="DieticianSvc" scope="page"
	class="com.dietician.model.DieticianService" />
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->


<h3>以購買成功本次購買項目為:</h3>




 <font size="+3">目前您購物車的內容如下：</font><p>

<table border="1" width="740" class="table table-sm">
	<tr bgcolor="#999999">
		<th width="200">課程名稱</th><th width="100">營養師</th><th width="100">價格</th>
		
	</tr>
	
	<%
	 for (int index = 0; index < buylist.size(); index++) {
		 CourseVO order = buylist.get(index);
	%>
	<tr>
		<td width="200"><div align="center"><b><%=order.getCname()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getDno()%></b></div></td>
		<td width="100"><div align="center"><b><%=order.getCprice()%></b></div></td>
		
	</tr>
	<%}%>
	<tr>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td> <font color="red"><b>$<%=total %></b></font> </td>
		
	</tr>
</table>
          <a href="<%=request.getContextPath()%>/front_end/free/course/course_front_page.jsp">繼續瀏覽課程</a>
<%--           <form method="get" action="<%=request.getContextPath()%>/course/course.do"> --%>
<!--          	<input type="hidden" name="action" value="mycourse"> -->
<!-- 			<input type="submit" value="觀看我的課程"></FORM> -->
<!--           </form> -->
           <a href="<%= request.getContextPath() %>/course/course.do?action=mycourse">觀看我的課程</a>
          








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