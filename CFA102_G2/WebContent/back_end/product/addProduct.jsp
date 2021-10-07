<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!DOCTYPE html>
<html>
<head>
	<title>後台_商品管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
	<script src="https://kit.fontawesome.com/9c1820a324.js" crossorigin="anonymous"></script>
	<style>
		.pic{
			width:100px;
			height:100px;
			border:0px;
		}
		#btnback{
			margin-bottom:10px;
			margin-top:0px;
		}
	</style>
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h3 mb-4 text-gray-800">新增商品</h1>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>	
			
		<div class="row col-md-12 col-md-offset-2 custyle">
    		<a href="<%=request.getContextPath()%>/back_end/product/select_page.jsp" class="btn btn-primary btn-xs pull-right" id="btnback">
    			<i class="far fa-hand-point-left"></i> 回商品管理</a>	
    	</div>
    	     	
    	<div class="row col-md-12 col-md-offset-2 custyle">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">    		
    			<table class="table table-striped custab">
					<tr>
						<td>商品類別名稱:</td>
						<td><input type="TEXT" name="categoryName" size="45" value="<%=(productVO == null) ? "" : productVO.getCategoryName()%>" /></td>
					</tr>
					<tr>
						<td>商品名稱:</td>
						<td><input type="TEXT" name="pname" size="45" value="<%=(productVO == null) ? "" : productVO.getPname()%>" /></td>
					</tr>
					<tr>
						<td>商品單價:</td>
						<td><input type="TEXT" name="pprice" size="45" value="<%=(productVO == null) ? "" : productVO.getPprice()%>" /></td>
					</tr>
					<tr>
						<td>商品描述:</td>
						<td><textarea id="pinfo" name="pinfo" rows="4" cols="46" maxlength="500"><%=(productVO == null) ? "" : productVO.getPinfo()%></textarea></td>
					</tr>
					<tr>
						<td>商品數量:</td>
						<td><input type="TEXT" name="pquantity" size="45" value="<%=(productVO == null) ? "" : productVO.getPquantity()%>" /></td>
					</tr>
					<tr>
						<td>商品上架日期:</td>
						<td><input name="ponDate" id="f_date1" type="text"></td>
					</tr>
					<tr>
						<td>商品下架日期:</td>
						<td><input name="poffDate" id="f_date2" type="text"></td>
					</tr>
					<tr>
						<td>商品圖片1:</td>
						<td>
							<input type="file" name="pimage1" accept="image/*" onchange="document.getElementById('pimage1').src = window.URL.createObjectURL(this.files[0])" />
							<img id="pimage1" class="pic" />
						</td>
					</tr>
					<tr>
						<td>商品圖片2:</td>
						<td><input type="file" name="pimage2" accept="image/*" onchange="document.getElementById('pimage2').src = window.URL.createObjectURL(this.files[0])" />
							<img id="pimage2" class="pic" />
						</td>
					</tr>
					<tr>
						<td>商品圖片3:</td>
						<td><input type="file" name="pimage3" accept="image/*" onchange="document.getElementById('pimage3').src = window.URL.createObjectURL(this.files[0])" />
							<img id="pimage3" class="pic" /></td>
					</tr>
					<tr>
						<td>商品狀態:</td>	
						<td>
							<select name="pstate">
								<option value="1" ${(productVO.pstate==1)? 'selected':''}>上架</option>
								<option value="0" ${(productVO.pstate==0)? 'selected':''}>下架</option>
							</select>
						</td>	
					</tr>				
				</table>
				<br> 
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" class="btn btn-outline-primary btn-sm" value="送出新增">
			</FORM>			
    	</div>		
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date ponDate = null;
	java.sql.Date poffDate = null;
	try {
		ponDate = productVO.getPonDate();
		poffDate = productVO.getPoffDate();
	} catch (Exception e) {
		ponDate = new java.sql.Date(System.currentTimeMillis());
		poffDate = java.sql.Date.valueOf("9999-12-31");
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ponDate%>',  // value:new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:'-1970-01-01' // ,去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=poffDate%>', //value:new Date(),
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		   //startDate:	            '2017/07/10',  // 起始日
		   minDate : '-1970-01-01' // ,去除今日(不含)之前
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