<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

<style>
.demail {
margin:25%;
width:40%;
}

</style>

</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->




<div class="demail">
<form action="<%=request.getContextPath()%>/dietician/dietician.do" method="post">
<input type="text" name="daccount" value="" placeholder="請輸入會員帳號" maxLength="50" ></br>
<input type="text" name="demail" value="" placeholder="請輸入電子郵件信箱" maxLength="50" ></br>
<center>請填寫您申請帳號時所用的電子郵件信箱</center>
<input type="hidden" name="action" value="find_demail">
<input type="submit">



</form>

<c:if test="${not empty errorMsgs}">
	<ul id="errorMsgs">
		<c:forEach var="message" items="${errorMsgs}">
			<li  style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


</div>











		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->

	
</body>
</html>