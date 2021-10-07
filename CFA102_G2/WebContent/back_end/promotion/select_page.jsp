<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="promotionSvc" scope="page" class="com.promotion.model.PromotionService" />

<!DOCTYPE html>
<html>
<head>
	<title>後台_優惠活動管理</title>
	<!-- 此include包含了，head的設定與css的連結--------------------- -->
	<%@ include file="/back_end_example/head_include.jsp"%>
	<!-- --------------------------------------------------- -->
</head>

<body id="page-top">
	<!-- 此include包含了，側邊欄位、登入登出，連結從這邊填寫-------------- -->
	<%@ include file="/back_end_example/body_include.jsp"%>
	<!-- --------------------------------------------------- -->

	<!-- Begin Page Content 中間填寫部分----------------------- --->
	<div class="container-fluid">
		<h1 class="h1 mb-4 text-gray-800">優惠活動管理</h1>
		<h4 class="h4 mb-2 text-gray-800">列表:</h4>
		
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-5 col-lg-5 col-lg-5">
    			<div class="list-group">
  					<a href="<%=request.getContextPath()%>/back_end/promotion/listAllPromotion.jsp"
     		   		  class="list-group-item list-group-item-action">● 優惠活動列表
  					</a>
				</div> 
    		</div>	
    	</div>
    	
    	<h4 class="h4 mb-2 mt-2 text-gray-800">查詢:</h4>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-5 col-lg-5 col-lg-5">
    			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/promotion/promotion.do">
					<ul class="list-group">
  						<li class="list-group-item">選擇優惠活動編號:
							<select size="1" name="promNo">
								<option value="">
								<c:forEach var="promotionVO" items="${promotionSvc.all}">
									<option value="${promotionVO.promNo}">${promotionVO.promNo}
								</c:forEach>
							</select>
						</li>
  						<li class="list-group-item">選擇優惠活動:
							<select size="1" name="promName">
								<option value="">
								<c:forEach var="promotionVO" items="${promotionSvc.all}">
									<option value="${promotionVO.promName}">${promotionVO.promName}
								</c:forEach>
							</select>
						</li>
  						<li class="list-group-item">優惠活動期間:
							<input type="text" name="promStartDate" id="f_date1" size="10" autocomplete="off">
 							<b>至</b>
							<input type="text" name="promEndDate" id="f_date2" size="10" autocomplete="off">	
						</li>												
  						<li class="list-group-item">
							<input type="submit" class="btn btn-outline-primary btn-sm" value="送出">				
							<input type="hidden" name="action" value="listPromotion_ByCompositeQuery">
						</li>
					</ul>
				</FORM>
    		</div>
  		</div>	

		<h4 class="h4 mb-2 mt-4 text-gray-800">管理</h4>
  		<div class="row">
    		<div class="col-12 col-sm-12 col-md-5 col-lg-5 col-lg-5">
    			<div class="list-group">
  					<a href="<%=request.getContextPath()%>/back_end/promotion/addPromotion.jsp"
     		   		   class="list-group-item list-group-item-action">● 新增優惠活動
  					</a>
				</div> 
    		</div>
  		</div>    	
	</div>
	<!-- /.container-fluid -->

	<!-- 此include包含了foot、登出的確定畫面、Bootstrap core JavaScript、Core plugin JavaScript、 Custom scripts for all pages的連結-->
	<%@ include file="/back_end_example/foot_include.jsp"%>
	<!-- -------------------------------------------------------------------------------------------------------------- -->

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date promStartDate = null;
  java.sql.Date promEndDate = null;
%>
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
		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:'-1970-01-01' // ,去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:'-1970-01-01' // ,去除今日(不含)之前
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