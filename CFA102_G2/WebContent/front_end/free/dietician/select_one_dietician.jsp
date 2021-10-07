<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="com.dietician.model.*"%>
<%@ page import="com.dietician.controller.*"%>
<%@ page import="com.member.model.*"%>
<% 
DieticianVO dietician = (DieticianVO)request.getAttribute("dieticianVO"); 
MemberVO memberVO1=(MemberVO)session.getAttribute("memberVO1");
// pageContext.setAttribute("memberVO1", memberVO1);
%>    
	
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->








<div class="container">
		<div class="row">
					<div class="col-2">
					
					</div>
					<div class="col-4 r1c1">
				

					
						<div id="pic">
  							<img  id="dieticianPic" src="<%=request.getContextPath()%><%= dietician.getDpic()%>">
  						</div>
  						
						<p>姓名:<%= dietician.getDname()%></p>
						<p>生日:<%= dietician.getDbirthDay()%></p>
						<p>電話:<%= dietician.getDphone()%></p>
						<p>地址:<%= dietician.getDaddress()%></p>
						<p>Email:<%= dietician.getDemail()%></p>
						<br>
						<br>
						
<h2>預約諮詢</h2>
<form action="<%=request.getContextPath()%>/appointment/appointment.do">
<input type="hidden" name="dno" value="<%= dietician.getDno()%>">


<input type="hidden" name="action" value = "getone_appointment">
<input type="submit" value = "預約">
</form>
						
						<br>
						<br>
						<br>
						<br>
					
						
						
					</div>
					
					
					<div class="col-6 r1c2">
				
					
						<br>
						<br>
						<h4>自我介紹:</h4> 
						<p><%= dietician.getIntro()%></p>
						<br>
						<h4>學歷簡介:</h4> 
						<p><%= dietician.getEdu()%></p>
						<br>
						<h4>經歷簡介:</h4> 
						<p><%= dietician.getExp()%></p>
						<br>
						<h4>證照簡介:</h4>
						<p><%= dietician.getLic()%></p>
						<br>
						<h4>專長簡介:</h4> 
						<p><%= dietician.getProf()%></p>
						<br>
						<h4>諮詢價格：</h4>  
						<p><%= dietician.getClPrice()%>元/每次</p>
						<br>
						<h4>專屬營養師月費：</h4>
						<p><%= dietician.getMprice()%>元/每月</p>
						<p>
						<form method="post" action="<%=request.getContextPath()%>/dorder.do">
						<input type="hidden" name="action" value="openOrder">
						<input type="hidden" name="dno" value="<%=dietician.getDno() %>">
						<c:if test="${memberVO1.mno!=null }">
						<input type="hidden" name="mno" value="<%=memberVO1.getMno() %>">
						</c:if>
						<input type="hidden" name="mthFee" value="<%=dietician.getMprice() %>">
						<input type="hidden" name="autoSubs" value="1">
						<input type="submit" value="訂閱營養師">						
						</form>
						
					
					
						</p>
					</div>
		</div>
		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<script>
if(${orderOk==1}){
	alert("訂閱成功!!!");
}else if(${orderOk==2})
	{
	alert("取消訂閱!!!");
}
</script>
</body>
</html>