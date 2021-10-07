<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>

<%
  CourseVO courseVO = (CourseVO) request.getAttribute("courseVO"); //EmpServlet.java (Concroller) 存入req的courseVO物件 (包括幫忙取出的courseVO, 也包括輸入資料錯誤時的courseVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>後台管理</title>
<style>
  table {
	width:70%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	font: inline;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->


	<!-- 此include包含了，head的設定與css的連結 -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- ----------------------------------------------------------- -->
</head>

<body id="page-top">

	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫 -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分-->

	<div class="container-fluid">
<table id="table-1">
	<tr><td>
		 <h3>課程審核</h3>
		 <br>
		 <h4><a href="<%=request.getContextPath()%>/back_end/course/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
<!-- 		<td>課程編號:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="cno" size="45" value="<%=courseVO.getCno()%>"></td>
	</tr>
	<tr>
<!-- 		<td>營養師編號:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="dno" size="45" value="<%=courseVO.getDno()%>"></td>
	</tr>
	<tr>
		<td>課程名稱:<font color=red></font></td>
		<td><input type="text" name="cname" size="45" value="<%=courseVO.getCname()%>"readonly/></td>
	</tr>	
	<tr>
		<td>課程預覽說明:</td>
		<td><input type="text"  name="cdescription" size="45"
			value="<%= (courseVO==null)? "請輸入課程預覽說明" :courseVO.getCdescription()%>"readonly/></td>	
<%-- 			<td><textarea rows="6" cols="40" name="cdescription" readonly><%=courseVO.getCdescription()%></textarea></td>													 --%>
	</tr>
	
	<tr>
		<td>課程價格:</td>
		<td><input type="text" name="cprice" size="45"	value="<%=courseVO.getCprice()%>"readonly /></td>
	</tr>
	
	<tr>
		<td>課程介紹:</td>
<!-- 		<td><input type="text"  name="cintroduction" size="45" -->
<%-- 			value="<%= (courseVO==null)? "請輸入課程介紹" : courseVO.getCintroduction()%>"readonly/></td>	 --%>
			<td><textarea rows="6" cols="40" name="cintroduction" readonly><%=courseVO.getCintroduction()%></textarea></td>									
	</tr>
			<tr>
		<td>課程狀態:</td>
		<c:if test="${courseVO.cstate==0}">
		<td><select size="1" name="cstate">
				<option value="0">審核中</option>
				<option value="1">通過審核並上架</option>
		</select></td>
		</c:if>
		<c:if test="${courseVO.cstate==1}">
		<td><select size="1" name="cstate">
				<option value="1">上架</option>			
				<option value="2">下架</option>
		</select></td>
		</c:if>
		<c:if test="${courseVO.cstate==2}">
		<td><select size="1" name="cstate">
				<option value="2">下架</option>
				<option value="1">上架</option>
		</select></td>
		</c:if>
	</tr>
	<tr>
<!-- 		<td>課程預覽圖:</td> -->
<%-- 		<td><input type="file"  name="cpic" value="<%= (courseVO==null)?"":courseVO.getCpic()%>" --%>
<!-- 	 onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])" -->
<%-- 	 /></td> <td><img id="blah" alt="your image" width="100%" height="100%" src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"/></td> --%>
<!-- 	</tr> -->
	<!-- <tr> -->
  <td>課程預覽圖:</td>
  <td><input type="file" name="cpic" onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])"  value="<%= (courseVO==null)? "" : courseVO.getCpic()%>">
   <img src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}" id="blah" alt="your image" width="100" height="100" />
  </td>
 </tr>
</table>

<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出更新"></FORM>

	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>