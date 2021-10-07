<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Article: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
<%@ include file="/back_end_example/head_include.jsp"%>
</head>
<body bgcolor='white'>
<%@ include file="/back_end_example/body_include.jsp"%>
<table id="table-1">
   <tr><td><h3>IBM Article: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Article: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllArtice.jsp'>List</a> all Article.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" >
        <b>標題編號:</b>
        <input type="text" name="articNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="artSvc" scope="page" class="com.artice.model.ArticeService" />
   
  
</ul>


<h3>最新消息管理</h3>

<ul>
  <li><a href='addartice.jsp'>Add</a> a new Article.</li>
</ul>
<%@ include file="/back_end_example/foot_include.jsp"%>
</body>
</html>