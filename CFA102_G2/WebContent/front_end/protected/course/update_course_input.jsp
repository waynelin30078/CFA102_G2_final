<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.course.model.*"%>

<%
  CourseVO courseVO = (CourseVO) request.getAttribute("courseVO"); //EmpServlet.java (Concroller) �s�Jreq��courseVO���� (�]�A�������X��courseVO, �]�]�A��J��ƿ��~�ɪ�courseVO����)
%>
<html>

<head>
<title>�������</title>
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

<table id="table-1">
	<tr><td>
		 <h3>�ҵ{��ƭק�</h3>
		 <br>
		 <h4><a href="<%=request.getContextPath()%>/front_end/protected/course/listAllCourse.jsp">�^����</a></h4>
	</td></tr>
</table>



<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
<!-- 		<td>�ҵ{�s��:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="cno" size="45" value="<%=courseVO.getCno()%>"></td>
	</tr>
	<tr>
<!-- 		<td>��i�v�s��:<font color=red><b>*</b></font></td> -->
		<td><input type="hidden" name="dno" size="45" value="<%=courseVO.getDno()%>"></td>
	</tr>
	<tr>
		<td>�ҵ{�W��:<font color=red></font></td>
		<td><input type="text" name="cname" size="45" value="<%=courseVO.getCname()%>"></td>
	<tr>
		<td>�ҵ{�w������:</td>
		<td><input type="text"  name="cdescription" size="45"
			value="<%= (courseVO==null)? "�п�J�ҵ{�w������" :courseVO.getCdescription()%>"/></td>											
	</tr>
	<tr>
		<td>�ҵ{����:</td>
		<td><input type="text" name="cprice" size="45"	value="<%=courseVO.getCprice()%>" /></td>
	</tr>
	
	<tr>
		<td>�ҵ{����:</td>
<!-- 		<td><input type="text"  name="cintroduction" size="45" -->
<%-- 			value="<%= (courseVO==null)? "�п�J�ҵ{����" : courseVO.getCintroduction()%>"/></td>	
									 --%>
									 			<td><textarea rows="6" cols="40" name="cintroduction"><%=courseVO.getCintroduction()%></textarea></td>									
									 
	</tr>
			<tr>
		<td>�ҵ{���A:</td>
		<c:if test="${courseVO.cstate==0}">
		<td><select size="1" name="cstate">
				<option value="0">�f�֤�</option>
				<option value="1">�q�L�f�֨äW�[</option>
		</select></td>
		</c:if>
		<c:if test="${courseVO.cstate==1}">
		<td><select size="1" name="cstate">
				<option value="1">�W�[</option>			
				<option value="2">�U�[</option>
		</select></td>
		</c:if>
		<c:if test="${courseVO.cstate==2}">
		<td><select size="1" name="cstate">
				<option value="2">�U�[</option>
				<option value="1">�W�[</option>
		</select></td>
		</c:if>
	</tr>
	<tr>
<!-- 		<td>�ҵ{�w����:</td> -->
<%-- 		<td><input type="file"  name="cpic" value="<%= (courseVO==null)?"":courseVO.getCpic()%>" --%>
<!-- 	 onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])" -->
<%-- 	 /></td> <td><img id="blah" alt="your image" width="100%" height="100%" src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}"/></td> --%>
<!-- 	</tr> -->
	<!-- <tr> -->
  <td>�ҵ{�w����:</td>
  <td><input type="file" name="cpic" onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])"  value="<%= (courseVO==null)? "" : courseVO.getCpic()%>">
   <img src="<%=request.getContextPath()%>/course/course.do?action=showpic&cpic=${courseVO.cno}" id="blah" alt="your image" width="100" height="100" />
  </td>
 </tr>
</table>

<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--�u�Ω�:istAllEmp.jsp-->
<input type="submit" value="�e�X��s"></FORM>

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