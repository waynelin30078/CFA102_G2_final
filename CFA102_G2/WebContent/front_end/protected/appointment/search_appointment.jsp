<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" %>
<%@ page import ="java.util.*"%>
<%@ page import="com.apt_order.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.dietician.model.*" %>
<%
	String mid = (String)session.getAttribute("account");
    Integer mno = (Integer)session.getAttribute("mno");
	MemberService memberSvc = new MemberService();
	MemberVO memberVO1 = memberSvc.getOneMemberByMid(mid);
	pageContext.setAttribute("memberVO1",memberVO1);
	

	
	Apt_orderService apt_orderSvc = new Apt_orderService();
	List<Apt_orderVO> list=apt_orderSvc.findByMno(mno);
	pageContext.setAttribute("list",list);

%>

<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

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
<h3>剛新增的訂單</h3>
<table class="table table-striped">
	<thead>
	<tr>
	<th>預約的營養師</th>
	<th>預約的時間段</th>
	<th>訂單產生時間</th>
	<th>諮詢金額</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>


		
		
		${apt_orderVO1.dno}
		
		
		
		</td>
		<td><fmt:formatDate value="${apt_orderVO1.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td><fmt:formatDate value="${apt_orderVO1.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${apt_orderVO1.clPrice}</td>
		</tr>
	</tbody>

</table>

<br>
<br>

<h3>查詢預約時段諮詢</h3>
<table class="table table-striped">
	<thead>
	<tr>
	<th>預約的營養師</th>
	<th>預約的時間段</th>
	<th>訂單產生時間</th>
	<th>諮詢金額</th>
	<th>訂單狀態</th>
	<th>取消預約</th>

	</tr>
	</thead>
	<tbody>

	<c:forEach var="apt_orderVO" items = "${list}">
	<tr>
		<td>
		<c:set var="dno" value="${apt_orderVO.dno}"></c:set>
					<% DieticianService dieticianSvc = new DieticianService();
					   DieticianVO dieticianVO = new DieticianVO();
					   Integer dno=(Integer)pageContext.getAttribute("dno");
					  
			   
			   DieticianVO dieticianVO1=dieticianSvc.findByPrimaryKey(dno);
			   pageContext.setAttribute("dieticianVO1",dieticianVO1);
			   %>
		
		
		
		
		${dieticianVO1.dname}</td>
		<td><fmt:formatDate value="${apt_orderVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td><fmt:formatDate value="${apt_orderVO.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${apt_orderVO.clPrice}</td>
		<td><c:if test="${apt_orderVO.clState ==1 }">正常</c:if>
			<c:if test="${apt_orderVO.clState ==0 }"><font style="color: red">已取消</font></c:if>
		</td>
		<td>
		<form method="post" action="<%=request.getContextPath()%>/apt_order/apt_order.do">
		
		
		<input type="hidden" name="orderTime" value="${apt_orderVO.orderTime}">
		<input type="hidden" name="aptOrderNo" value="${apt_orderVO.aptOrderNo}">
		<input type="hidden" name="mno" value="${apt_orderVO.mno}">
		<input type="hidden" name="dno" value="${apt_orderVO.dno}">
		<input type="hidden" name="orderDate" value="${apt_orderVO.orderDate}">
		<input type="hidden" name="clPrice" value="${apt_orderVO.clPrice}">
		<input type="hidden" name="clState" value="0">
		<input type="hidden" name="aptReviews" value="${apt_orderVO.aptReviews}">
		
		<input type="hidden" name="action" value="cancel_apt_order">
		
		
		
		<input type="submit" value="取消預約">
		
		</form>
		
		
		</td>
		</tr>
		</c:forEach>
		</tbody>
</table>



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
 		   value: 'new Date()', // value:   new Date(),
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