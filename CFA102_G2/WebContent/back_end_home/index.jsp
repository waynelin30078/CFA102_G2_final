<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>Login Form Design</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<style>
body {
	margin: 0;
	padding: 0;
	background: url(<%=request.getContextPath()%>/back_end_home/pic.jpg);
	background-size: cover;
	font-family: sans-serif;
}

.loginbox {
	width: 320px;
	height: 420px;
	background: #000;
	color: #fff;
	top: 50%;
	left: 50%;
	position: absolute;
	transform: translate(-50%, -50%);
	box-sizing: border-box;
	padding: 70px 30px;
}

.avatar {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	position: absolute;
	top: -50px;
	left: calc(50% - 50px);
}

h1 {
	margin: 0;
	padding: 0 0 20px;
	text-align: center;
	font-size: 22px;
}

.loginbox p {
	margin: 0;
	padding: 0;
	font-weight: bold;
}

.loginbox input {
	width: 100%;
	margin-bottom: 20px;
}

.loginbox input[type="text"], input[type="password"] {
	border: none;
	border-bottom: 1px solid #fff;
	background: transparent;
	outline: none;
	height: 40px;
	color: #fff;
	font-size: 16px;
}

.loginbox input[type="submit"] {
	border: none;
	outline: none;
	height: 40px;
	background: #fb2525;
	color: #fff;
	font: size 18px;
	border-radius: 20px;
}

.loginbox input[type="submit"]:hover {
	cursor: pointer;
	background: #ffc107;
	color: #000;
}

.loginbox a {
	text-decoration: none;
	font-size: 12px;
	line-height: 20px;
	color: darkgrey;
}

.loginbox a:hover {
	color: #ffc107;
}
</style>

</head>

<body>





	<div class="loginbox">
		<img
			src="<%=request.getContextPath()%>/back_end_home/Icon-Plankton.png"
			class="avatar">
		<h1>Login Here</h1>
		<form method="post"
			action="<%=request.getContextPath()%>/admin/adminLogin">
			<p>Username</p>
			<input type="text" name="aid" value="" placeholder="請輸入帳號">
			<p>Password</p>
			<input type="password" name="apsw" value="" placeholder="請輸入密碼">
			<p>
				<input type="hidden" name="action" value="login"> <input
					type="submit" value="登入">
			</p>
			<div>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul style="list-sytle: none; list-style-type: none">
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red; list-style-type: none">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</form>

	</div>

</body>


</html>