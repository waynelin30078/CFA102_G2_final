<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.dietician.model.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    <%
    DieticianVO dieticianVO1 = (DieticianVO)session.getAttribute("dieticianVO2");
    Integer dno = new Integer(dieticianVO1.getDno());
	
    DieticianService dieticianSvc = new DieticianService();
    DieticianVO dieticianVO = dieticianSvc.findByPrimaryKey(dno);
    pageContext.setAttribute("dieticianVO",dieticianVO);

%>

<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
.wrapper{
width:200px;
padding:20px;
height: 150px;
}

.nice-select ul {
	height: 242px;
    overflow-y: auto !important;
}

</style>
<style type="text/css">
            .tab {
                height:100px;
                width:150px;
                text-align:center;
            }
            
            
</style>
</head>

<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->








<h2>營養師:<%=dieticianVO.getDname()%></h2>
<h3>目前公休日配置</h3>
<br>
<table class="table table-success table-striped">
        <tr>
            <td class="mon">星期一</td>
            <td class="tue">星期二</td>
            <td class="wed">星期三</td>
            <td class="thu">星期四</td>
            <td class="fri">星期五</td>
            <td class="sat">星期六</td>
            <td class="sun">星期日</td>
        </tr>
       <tr>
       <% String week=dieticianVO.getOffDay();%>
       <c:if test="<% char i=week.charAt(0);
       
       %>"></c:if>
            <td class="mon">
            <c:if test="<%=week.charAt(0) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(0) == '1' %>">營業</c:if>

            </td>
            <td class="tue">
            <c:if test="<%=week.charAt(1) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(1) == '1' %>">營業</c:if>
            
            
            </td>
            <td class="wed">
            <c:if test="<%=week.charAt(2) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(2) == '1' %>">營業</c:if>

			</td>
            <td class="thu">
            <c:if test="<%=week.charAt(3) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(3) == '1' %>">營業</c:if>
            
            </td>
            <td class="fri">
            <c:if test="<%=week.charAt(4) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(4) == '1' %>">營業</c:if>
            
            
            </td>
            <td class="sat">
			<c:if test="<%=week.charAt(5) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(5) == '1' %>">營業</c:if>

			</td>
            <td class="sun">
            <c:if test="<%=week.charAt(6) == '0' %>"><font style="color: red">公休</font></c:if>
            <c:if test="<%=week.charAt(6) == '1' %>">營業</c:if>
            
            
            </td>
        </tr>

    </table>

<%=dieticianVO.getOptTime()%><br>
<hr>
<c:if test="${not empty errorMsgs}">
	<ul>
	<c:forEach var = "message" items="${errorMsgs}">
		<li style="color:red">${message}</li>
	</c:forEach>
	</ul>
</c:if>
<h2>營養師修改預約時段</h2>
<form method="POST" action="<%=request.getContextPath()%>/dietician/dietician.do">
		<h4 class="mt-md-2">服務日</h4>
		
						<div class="schDate col-md-12">
						<table class="table table-striped">
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
						

<table class="time">
<tr>
 <th>開始時間</th>
 <th></th>
 <th>結束時間</th>
</tr>
<tr>
<td>
<div class="multi-selectbox"> 
  <select name="from" id="from">
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
  </div>
</td>
<td style=width:40px;>
<h1>~</h1>
</td>
<td>

  <select name="to" id="to">
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