<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.member.model.*" %>
    <%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>

<head>
<title>修改會員資料</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<style>
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
	width: 650px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left:auto; 
	margin-right:auto;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }

</style>
</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->











<h3>會員資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
	<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memberVO.getMno()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="mname" size="45" value="<%=memberVO.getMname()%>"/></td>
	</tr>
	<tr>
		<td>會員帳號:<font color=red><b>*</b></font></td>
		<td><%=memberVO.getMid()%></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="mpsw" size="45" value="<%=memberVO.getMpsw()%>"/></td>
	</tr>
	<tr>
		<td>會員信箱:</td>
		<td><input type="TEXT" name="mmail" size="45" value="<%=memberVO.getMmail()%>"/></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="mphone" size="45" value="<%=memberVO.getMphone()%>"/></td>
	</tr>
	<tr>
	<tr>
		<td>會員生日:</td>
		<td><input type="TEXT" id="f_date1" name="mbday" size="45" /></td>
	</tr>
	<tr>
		<td>會員性別:</td>
		<td>
	  <div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="msex" id="inlineRadio1" value="1" checked>
  <label class="form-check-label" for="inlineRadio1">男</label>
</div>
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="msex" id="inlineRadio2" value="2">
  <label class="form-check-label" for="inlineRadio2">女</label>
</div>
		</td>
	</tr>
	<tr>
		<td>自我介紹:</td>
		<td><textarea rows="6" cols="40" name="mintro"><%=memberVO.getMintro()%></textarea></td>
	</tr>
	<tr>
		<td>會員照片:</td>
		<td><input type="file" name="mimg" onchange="document.getElementById('blah').src = window.URL.createObjectURL(this.files[0])"  value="<%= (memberVO==null)? "" : memberVO.getMimg()%>">
			<img src="<%=request.getContextPath()%>/member/member.do?action=showPhoto&photo=${memberVO.mno}" id="blah" alt="your image" width="100" height="100" />
		</td>
	</tr>
	
	</table>
	<br>
	<div style="text-align:center;">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="mid" value=<%=memberVO.getMid() %>>
	<input type="hidden" name="mno" value="<%=memberVO.getMno()%>">
	<input type="submit" value="修改會員">
	</div>
	
	
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memberVO.getMbday()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>