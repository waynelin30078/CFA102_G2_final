<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.artice.model.*"%>

<%
  ArticeVO articeVO = (ArticeVO) request.getAttribute("articeVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章修改</title>

<style>
/*   table#table-1 { */
/* 	background-color: black; */
/*     border: 2px solid black; */
/*     text-align: center; */
/*   } */
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	width:1100px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
    
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
<!-- 		 <h3>最新消息資料修改</h3> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/back-end/news/select_news_page.jsp">回首頁</a></h4> --%>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" name="form1" enctype="multipart/form-data">
<table>

		<tr>
		<td>文章編號:<font color=red><b></b></font></td>
		<td><%=articeVO.getArticNo()%></td>
	</tr>
	
	<tr>
		<td>文章標題:</td>
		<td><input type="TEXT" name="articTitle" size="30"  value="<%=articeVO.getArticTitle()%>" /></td>
	</tr>
	

	<tr>
		<td>文章內容:</td>
		<td><input type="TEXT" style="width:400px; height:280px" name="articContent" size="30" value="<%=articeVO.getArticContent()%>" /></td>
	</tr>
	<tr>		
		<td>
		<input type="radio" name="articType" value="1" />研究
		<input type="radio" name="articType" value="2" />食物
		</td>
	</tr>		
	<tr>		
		<td>
		<input type="radio" name="articState" value="0" />隱藏文章
		<input type="radio" name="articState" value="1" />顯示文章
		</td>
	</tr>		

	<tr>
		<td>文章圖片:</td>
		<td>
		<img id="preview_progressbarTW_img"  width="400px" height="280px" src="<%=request.getContextPath()%>/PicFinder?pic=1&table=article&column=articPhoto1&idname=articno&id=<%=articeVO.getArticNo()%>" />
    	<input type="file" id="progressbarTWInput" name="articPhoto" accept="image/gif, image/jpeg, image/png">
		</td>
	</tr>
	
		<jsp:useBean id="articeSvc" scope="page" class="com.artice.model.ArticeService" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="articNo" value="<%=articeVO.getArticNo()%>">
<input type="submit" value="送出修改">

</FORM>
</body>


<script>

        $("#progressbarTWInput").change(function(){

  readURL(this);

});

 

function readURL(input){

  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {

       $("#preview_progressbarTW_img").attr('src', e.target.result);

    }

    reader.readAsDataURL(input.files[0]);

  }

}
        
        
        
</script>
</html>