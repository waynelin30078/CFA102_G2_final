<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.dietician.model.*"%>
<%
	CourseVO courseVO = (CourseVO) request.getAttribute("courseVO");
DieticianVO dieticianVO =  (DieticianVO) session.getAttribute("dieticianVO2");
%>

<!-- 		<td>��i�v�s��: �ݦA�T�{��i�v�إ߬O�_����</td> -->
<html>

<head>
<title>�������</title>
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
<!-- *************�C�@��head�̭����ninclude�o��css�s��******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************�C�@��head�̭����ninclude�o��css�s��******************* -->

</head>


<body>
<!-- *************�C�@��body�̫e�����ninclude�o��header�s��******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************�C�@��body�̫e�����ninclude�o��header�s��******************* -->
<div class="container this_page">
<!-- �A�ȫܦn�����@�Ӹm����div(�}�Y) -->



<body bgcolor='white'>

	
		 <a href="<%=request.getContextPath()%>/front_end/protected/course/select_page.jsp">�^����</a>
	


<h3>�ҵ{�s�W:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
<!-- 		<td>��i�v�s��: �ݦA�T�{��i�v�إ߬O�_����</td> -->
		<td><input type="hidden" name="dno" size="45" 
			 value="<%= (dieticianVO==null)? "1" : dieticianVO.getDno()%>" /></td>
	</tr>
	
	<tr>
		<td>�ҵ{�W��:</td>
		<td><input type="TEXT" name="cname" size="45"
 			 value="<%= (courseVO==null)? "" : courseVO.getCname()%>"placeholder= "�п�J�ҵ{�W��"/></td>	
 	</tr>
 	<tr>
		<td>�ҵ{�w������:</td>
		<td><input type="text"  name="cdescription" size="45"
			value="<%= (courseVO==null)? "" :courseVO.getCdescription()%>"placeholder= "�п�J�ҵ{�w������"/></td>											
	</tr>
	<tr>
		<td>�ҵ{����:</td>
		<td><input type="text" name="cprice" size="45" required
			 value="<%= (courseVO==null)? "0" : courseVO.getCprice()%>" /></td>
	</tr>
	
	
	<tr>
		<td>�ҵ{����:</td>
<!-- 		<td><input type="text"  name="cintroduction" size="45" -->
<%-- 			value="<%= (courseVO==null)? "" : courseVO.getCintroduction()%>"placeholder= "�п�J�ҵ{����"/></td>	 --%>
						<td><textarea rows="6" cols="40" name="cintroduction"placeholder= "�п�J�ҵ{����" ></textarea></td>									
												
	</tr>

	
	<tr>
		<td>�ҵ{����:</td>
		<td><select size="1" name="ctype">
				<option value="1">��������</option>
				<option value="2">����</option>
				<option value="3">�\�I�s�@</option>
		</select></td>
	</tr>
	<tr>
		<td>�ҵ{�w����:</td>
		<td><input type="file" name="cpic" enctype="multipart/form-data" 
			 value="<%= (courseVO==null)? "" : courseVO.getCpic()%>"
			 onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])"
	 /> <img id="blah" alt="your image" width="100" height="100" />
	 </td>
	</tr>

	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>



<!-- �A�ȫܦn�����@�Ӹm����div(����) -->
</div>
<!-- *************�C�@��body�̫᭱���ninclude�o��footer�s��******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************�C�@��body�̫᭱���ninclude�o��footer�s��******************* -->
<!-- *************�C�@��body�̫᭱���ninclude�o��js�s��******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************�C�@��body�̫᭱���ninclude�o��js�s��******************* -->
</body>
</html>