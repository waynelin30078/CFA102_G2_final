<%@page import="com.appointment.model.AppointmentService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.diary.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="com.appointment.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String mid = (String) session.getAttribute("account");

	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getOneMemberByMid(mid);
	pageContext.setAttribute("memberVO", memberVO);
%>

<%
	List<AppointmentVO> result1 = (List<AppointmentVO>) request.getAttribute("result");
	pageContext.setAttribute("result1", result1);
%>

<%
	DieticianService dieticianSvc = new DieticianService();
%>

<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

<style type="text/css">
<!--
#B ul li {
    display:inline;
}
-->
</style>
</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->

		<h3>營養師:${dieticianVO.dname}</h3>
		<br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<table class="table table-striped">
			<tr>

				<th>可預約日期</th>
				<th>選擇時段</th>
				<th>目前可預約時段</th>
				<th>預約</th>
			</tr>

			<c:forEach var="appointmentVO" items="${result1}">

				<tr>


					<td>${appointmentVO.rdate}</td>
					<form method="post" action="<%=request.getContextPath()%>/appointment/appointment.do">
						<td><c:set var="rstate" value="${appointmentVO.rstate}" /> <select
							name="rstate_num">
								<%
									String rstate = (String) pageContext.getAttribute("rstate");
										for (int i = 0; i < rstate.length(); i++) {
											char c = rstate.charAt(i);
											if (c == '0') {
								%>
								<option value="<%=i%>"><%=i%>:00
								</option>
								<%
									}
										}
								%>
						</select></td>
						<td>

							<div id="B">
								<ul>
								<%
									String rstate1 = (String) pageContext.getAttribute("rstate");
										for (int b = 0; b < rstate1.length(); b++) {
											char c = rstate1.charAt(b);
											if (c == '0') {
								%>
									<li><%=b%>:00 &nbsp</li>
									<%
										}
											}
									%>

								</ul>

							</div>
						</td>

					<td><input type="hidden" name="dno"
						value="${appointmentVO.dno}"> <input type="hidden"
						name="serchdate" value="${appointmentVO.rdate}"> <input
						type="hidden" name="mno" value="${memberVO.mno}"> <!-- 			<input type="text" name="rstate_num" value=""> -->
						<input type="hidden" name="aptno" value="${appointmentVO.aptNo}">
						<input type="hidden" name="rstate" value="${appointmentVO.rstate}">
						<input type="hidden" name="action" value="update_appointment">
						<input type="submit" value="預約"> <input type="hidden"
						name="clstate" value="1"> <input type="hidden"
						name="aptreviews" value="">
					</form>

					</td>
				</tr>



			</c:forEach>


		</table>










		<script type="text/javascript">
			let rstate = document.getElementByClassName("rstate").innerHTML;
			console.log(rstate);
		</script>








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