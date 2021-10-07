<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>


<%
  NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>最新消息資料新增</title>
</head>
<body class="bg-gradient-primary">

<div class="container">
	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<div class="row">
			<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
			<div class="col-lg-7">
			<div class="p-5">

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤</font>
		<ul>
				<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
				</c:forEach>
		</ul>
	</c:if>

		<div class="">
		<h3 class="h1 text-gray-900 mb-4">新增最新消息</h3>
		</div>

<FORM METHOD="post"
	action="<%=request.getContextPath()%>/news/news.do" >
		<div class="">
			最新消息標題 <input type="text" style="width: 400px;" id="newsTitle"
					name="newsTitle" oninvalid="alert('最新消息標題沒有填寫!');"
					required value="<%=newsVO==null? "" : newsVO.getNewsTitle() %>" >
		</div>

		<div class="user">
			最新消息內容 <input type="text" style="width: 400px; height: 100px"
					id="newsContent" name="newsContent"
					style="height:400px ; width: 400px"
					oninvalid="alert('最新消息內容沒有填寫!');" required
					value="<%= newsVO == null? "" : newsVO.getNewsContent() %>">
		</div>
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="新增">
</FORM>

						</div>
					</div>
				</div>
			</div>
		</div>
</div>

</body>

</html>