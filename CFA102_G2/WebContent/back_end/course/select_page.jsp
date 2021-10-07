<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>後台管理</title>


	<!-- 此include包含了，head的設定與css的連結 -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- ----------------------------------------------------------- -->
	
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
</head>

<body id="page-top">

	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫 -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->







	<!-- Begin Page Content 中間填寫部分-->

	


<h3>課程資料查詢:</h3>

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
  <li><a href='<%=request.getContextPath()%>/back_end/course/listAllCourse.jsp'>List</a> all Course.  <br><br></li>
    <li><a href='<%=request.getContextPath()%>/back_end/course/listAllByCourseState.jsp'>查看待審核課程</a>   <br><br></li>
   
   <li>
       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" >
        <b>輸入課程編號 :</b>
        <input type="text" name="cno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
 <jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />
  
  <li>
  	   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" >
  	<b>請選擇課程編號</b>
  	<select size="1" name="cno">
  	<c:forEach var="courseVO" items="${courseSvc.all}">
  		<option value="${courseVO.cno}">${courseVO.cno}
  	</c:forEach>
  	</select>
  	 <input type="hidden" name="action" value="getOne_For_Display">
     <input type="submit" value="送出">
  	</FORM>
  </li>
  
    <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course/course.do" >
       <b>選擇課程名稱</b>
       <select size="1" name="cno">
         <c:forEach var="courseVO" items="${courseSvc.all}"> 
          <option value="${courseVO.cno}">${courseVO.cname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

  
	</div>
	<!-- /.container-fluid -->








	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- --------------------------------------------------- -->



</body>

</html>