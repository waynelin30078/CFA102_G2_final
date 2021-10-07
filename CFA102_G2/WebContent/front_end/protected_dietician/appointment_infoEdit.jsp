<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.dietician.model.*" %>
    <%
	String dmid = (String)session.getAttribute("daccount");

    DieticianService dieticianSvc = new DieticianService();
    DieticianVO dieticianVO = dieticianSvc.findByAccount(dmid);
%>
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








<%=dieticianVO.getDname()%><br>
<%=dieticianVO.getOffDay()%><br>
<%=dieticianVO.getOptTime()%><br>
這邊可以開始放自己的東西了
<h2>營養師修改預約時段</h2>
<form method="POST" action="<%=request.getContextPath()%>/dietician/dietician.do">
		<h4 class="mt-md-2">服務日</h4>
		
		
						<div class="schDate col-md-12">
						<table class="table table-sm">
						  <thead>
						    <tr>
						        <th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>日</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr class="workdate ">
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="0" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="1" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="2" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="3" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="4" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="5" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="6" name="schDate"></td>
						    </tr>
						  </tbody>
						</table>
						</div>
				<br>
				<br>
				<br>
				<br>
					<h4 class="mt-md-2">服務時間</h4>
						

<table>
<tr>
 <th>開始時間</th>
 <th>結束時間</th>
 </tr>
<tr>
<td>
  <select name="from" id="from" size="3">
    <option value="0">0:00</option>
    <option value="1">1:00</option>
    <option value="2">2:00</option>
    <option value="3">3:00</option>
    <option value="4">4:00</option>
    <option value="5">5:00</option>
    <option value="6">6:00</option>
    <option value="7">7:00</option>
    <option value="8">8:00</option>
    <option value="9">9:00</option>
    <option value="10">10:00</option>
    <option value="11">11:00</option>
    <option value="12">12:00</option>
    <option value="13">13:00</option>
    <option value="14">14:00</option>
    <option value="15">15:00</option>
    <option value="16">16:00</option>
    <option value="17">17:00</option>
    <option value="18">18:00</option>
    <option value="19">19:00</option>
    <option value="20">20:00</option>
    <option value="21">21:00</option>
    <option value="22">22:00</option>
    <option value="23">23:00</option>
    
  </select>
</td>
<td>
  <select name="to" id="to" size="3">
    <option value="0">0:00</option>
    <option value="1">1:00</option>
    <option value="2">2:00</option>
    <option value="3">3:00</option>
    <option value="4">4:00</option>
    <option value="5">5:00</option>
    <option value="6">6:00</option>
    <option value="7">7:00</option>
    <option value="8">8:00</option>
    <option value="9">9:00</option>
    <option value="10">10:00</option>
    <option value="11">11:00</option>
    <option value="12">12:00</option>
    <option value="13">13:00</option>
    <option value="14">14:00</option>
    <option value="15">15:00</option>
    <option value="16">16:00</option>
    <option value="17">17:00</option>
    <option value="18">18:00</option>
    <option value="19">19:00</option>
    <option value="20">20:00</option>
    <option value="21">21:00</option>
    <option value="22">22:00</option>
    <option value="23">23:00</option>
  </select>
  </td>
  </tr>
</table>
<select class="form-select" size="3" aria-label="size 3 select example">
  <option selected>Open this select menu</option>
  <option value="1">One</option>
  <option value="2">Two</option>
  <option value="3">Three</option>
</select>



						


					<input type="hidden" name="action" value="updateAppointmentInfo"> 
					<input type="hidden" name="dieticiandno" value="<%=dieticianVO.getDno()%>">
					<button type="submit" class="btn btn-info form-control mb-md-3 px-md-3 ">提交</button>


</form>


    








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