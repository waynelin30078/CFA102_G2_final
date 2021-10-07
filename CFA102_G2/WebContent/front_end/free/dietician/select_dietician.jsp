<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="java.util.*"%>

<%
  List<DieticianVO> list = (List<DieticianVO>) request.getAttribute("list");
%>



<!DOCTYPE html>
<html lang="en">
<head>
	<title>專屬營養師</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<style>

	
	.container {
	margin: 0 auto;
	margin-top: 7%;
	text-align: center;
	}
	

	.btn {
	display: inline-block;
	margin-left: 5%;	
	margin-bottom: 10%;
	}

	h3 {
	color:orange;
	}
	
	#btn_row {
	
	margin: 30px 0px;
	}
	
	#dietician_col {
	
	margin: 5px auto;
	border: 1px solid blue;
	border-radius: 5px;
	
	}
	
	#select_search {
	display: flex;
  	align-items: top;
  	justify-content: center;
	}
	
	.findDietician {
	display: inline-block;
	margin: 0px 30px;
	verticle-align: top;
	}
	
	.buttom {
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	border: 1px solid #007bff;
	}
	
	#dieticians img {
	
	float: left;
	height: 100px;
	width: 100px;
	border: 3px solid pink;
	margin: 5px;
	}
	
	#dieticians table {
	text-align: left;
	}
	
	.intro {
	color: blue;
	
	}
	
	
</style>
</head>
<body>

	<div class="container">

		<div class="row" id="header">
			<div class="col-12">
				<h3>尋找專屬營養師</h3>
			</div>
		</div>

		<div class="row " id="btn_row">
			<div class="col-12" id="select_search">
<%-- 				<a href="<%=request.getContextPath()%>/front_end/free/dietician/add_dietician_page.jsp""><input type="submit" value="新增營養師" class="buttom"></a><br> --%>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
					<input type="submit" value="觀看全部營養師" class="buttom"><br>
					<input type="hidden" name="action" value="getAll_for_display">
				</form>
				<form  class="findDietician" method ="post" action="/dietician/dietician.do">
					<input type="submit" value="依專長搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findBySpecialty">
  					<input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">
  					<label for="vehicle1"> 體重控制</label><br>
  					<input type="checkbox" id="vehicle2" name="vehicle2" value="Car">
  					<label for="vehicle2"> 運動營養</label><br>
  					<input type="checkbox" id="vehicle3" name="vehicle3" value="Boat">
  					<label for="vehicle3"> 孕產婦營養</label><br>
  					<input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">
  					<label for="vehicle1"> 糖尿病飲食控制</label><br>
  					<input type="checkbox" id="vehicle2" name="vehicle2" value="Car">
  					<label for="vehicle2"> 腎臟病飲食控制</label><br>
  					<input type="checkbox" id="vehicle3" name="vehicle3" value="Boat">
  					<label for="vehicle3"> 高血壓飲食控制</label><br>
				</form>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
					<input type="submit" value="依評價搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findByScore">
						<input type="range" name="avgScore" value="" max="5" min="1" step="1" id="range" oninput="output.innerText= '評價' + range.value + '分以上'"><br>
						<output id="output">評價3分以上</output>
				</form>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
					<input type="submit" value="依價格搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findByPrice">
  					<label for="minPrice">最低價格</label>
  					<input type="text" id="minPrice" name="minPrice" size="3" value="">元<br>
  					<label for="maxPrice">最高價格</label>
  					<input type="text" id="maxPrice" name="maxPrice" size="3" value="">元<br>
				</form>
			</div>
		</div>
		
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
		</ul>
		</c:if>



	<c:forEach var="dieticianVO" items="${list}">	
		<a href="<%=request.getContextPath()%>/dietician/dietician.do?action=one_dietician_page&dno=${dieticianVO.dno}">
		<div class="row">
			<div class="col-10" id="dietician_col">
				<div class="row">
					<div class="col-12" id="dieticians">
						<img src="<%=request.getContextPath()%>${dieticianVO.dpic}">
						<table>
						<tr><td class="intro">營養師介紹</td></tr>
						<tr><td>${dieticianVO.intro}</td></tr>
						<tr><td class="intro">資歷</td></tr>
						<tr><td>${dieticianVO.exp}</td></tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		</a>
	</c:forEach>
	</div>




</body>
</html>