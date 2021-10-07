<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.dietician.model.*"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
DieticianVO dieticianVO =  (DieticianVO) session.getAttribute("dieticianVO2");
%>

<!-- 		<td>營養師編號: 需再確認營養師建立是否有效</td> -->
<html>

<head>
<title>營材食教</title>
<style>
  a{
  font-color:green;
  }
	
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width:60%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
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

</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->



<body bgcolor='white'>

	
		 <a href="<%=request.getContextPath()%>/front_end/protected/course/select_page.jsp">回首頁</a>
	


<h3>課程新增:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" name="form1"  enctype="multipart/form-data">
<table>
	<jsp:useBean id="courseSvc" class="com.course.model.CourseService" />
	<tr>
<!-- 		<td>營養師編號: 需再確認營養師建立是否有效</td> -->
		<td><input type="hidden" name="dno" size="45" 
			 value="<%= (dieticianVO==null)? "1" : dieticianVO.getDno()%>" /></td>
	</tr>
	
	<tr>
		<td>課程名稱:</td>
		<td><input type="TEXT" name="cname" size="45"
 			 value="<%= (courseVO==null)? "" : courseVO.getCname()%>"placeholder= "請輸入課程名稱"/></td>	
 	</tr>
 	<tr>
		<td>課程預覽說明:</td>
		<td><input type="text"  name="cdescription" size="45"
			value="<%= (courseVO==null)? "" :courseVO.getCdescription()%>"placeholder= "請輸入課程預覽說明"/></td>											
	</tr>
	<tr>
		<td>課程價格:</td>
		<td><input type="text" name="cprice" size="45" required
			 value="<%= (courseVO==null)? "0" : courseVO.getCprice()%>" /></td>
	</tr>
	
	
	<tr>
		<td>課程介紹:</td>
<!-- 		<td><input type="text"  name="cintroduction" size="45" -->
<%-- 			value="<%= (courseVO==null)? "" : courseVO.getCintroduction()%>"placeholder= "請輸入課程介紹"/></td>	 --%>
						<td><textarea rows="6" cols="40" name="cintroduction"placeholder= "請輸入課程介紹" ></textarea></td>									
												
	</tr>

	
	<tr>
		<td>課程類型:</td>
		<td><select size="1" name="ctype">
				<option value="1">飲食知識</option>
				<option value="2">健身</option>
				<option value="3">餐點製作</option>
		</select></td>
	</tr>
	<tr>
		<td>課程預覽圖:</td>
		<td><input type="file" name="cpic" enctype="multipart/form-data" 
			 value="<%= (courseVO==null)? "" : courseVO.getCpic()%>"
			 onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])"
	 /> <img id="blah" alt="your image" width="100" height="100" />
	 </td>
	</tr>

	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



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