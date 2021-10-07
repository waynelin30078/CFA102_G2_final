<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="com.dietician.model.*"%>
<% DieticianVO dietician = (DieticianVO)request.getAttribute("dieticianVO"); 
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<title>營養師個人頁面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<style>

	

	
	.container {
		margin: 0 auto;
		margin-top: 50px;
		
	}

	#pic {
	height: 200px;
	width: 200px;
	margin-bottom: 5px;
	}

	img {
	width: 100%;
	max-height: 200px;
	border: 2px solid grey;
	}
	
	.buttom {
	margin-left: 10%;
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	border: 1px solid #007bff;
	}
	
	h3 {
	color: orange;
	}

	h4 {
	color: blue;
	}


</style>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/dietician/dietician.do" enctype="multipart/form-data">
<div class="container">
		<div class="row">
					<div class="col-2">
					
					</div>
					<div class="col-4 r1c1">
						
						<h3>營養師會員</h3>
						<p><a href="<%=request.getContextPath()%>/front_end/free/dietician/select_dietician.jsp">回首頁</a></p>
						<input type="hidden" name="dno" value="<%= dietician.getDno()%>">
						<input type="hidden" name="dstate" value="<%= dietician.getDstate()%>">
						<input type="hidden" name="totalScore" value="<%= dietician.getTotalScore()%>">
						<input type="hidden" name="totalReviewer" value="<%= dietician.getTotalReviewer()%>">
						<input type="hidden" name="donState" value="<%= dietician.getDonState()%>">
						<input type="hidden" name="offDay" value="<%= dietician.getOffDay()%>">
						<input type="hidden" name="optTime" value="<%= dietician.getOptTime()%>">
						
						<div id="pic">
  							<img  id="dieticianPic" src="<%=request.getContextPath()%><%= dietician.getDpic()%>">
  						</div>
  						
						<p>姓名:<%= dietician.getDname()%></p>
						<p>帳號:<%= dietician.getDaccount()%></p>
						<p>生日:<%= dietician.getDbirthDay()%></p>
						<p>電話:<%= dietician.getDphone()%></p>
						<p>地址:<%= dietician.getDaddress()%></p>
						<p>Email:<%= dietician.getDemail()%></p>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<p><input class="buttom bottom2" type="submit" value="修改會員資料"></p>
						
						
					</div>
					
					
					<div class="col-6 r1c2">
						<br>
						<br>
						<h4>自我介紹:</h4> 
						<p><%= dietician.getIntro()%></p>
						<h4>學歷簡介:</h4> 
						<p><%= dietician.getEdu()%></p>
						<h4>經歷簡介:</h4> 
						<p><%= dietician.getExp()%></p>
						<h4>證照簡介:</h4>
						<p><%= dietician.getLic()%></p>
						<h4>專長簡介:</h4> 
						<p><%= dietician.getProf()%></p>
						<h4>諮詢價格：</h4>  
						<p><%= dietician.getClPrice()%>元/每次</p>
						<h4>專屬營養師月費：</h4>
						<p><%= dietician.getMprice()%>元/每月</p>

						<input type="hidden" name="action" value="update_dietician_page01">
						
					</div>
					
					
		</div>

</div>

</form>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<% 
  java.sql.Date dbirthDay = null;
  try {
	  dbirthDay = dietician.getDbirthDay();
   } catch (Exception e) {
	   
   }
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
	//如果沒有這一段, 只有chrome跟edge能用, firefox跟IE不能用
        
		$.datetimepicker.setLocale('zh');
        $('#dbirthDay').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=dbirthDay%>', // value:   new Date()為js寫法, 但錯誤時會req.setAttribute到最新日期, 就不是原先進來的日期了
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含, 不能預約的日期
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        //java用simpledateformat去格式
   		
        
        //以下為萬用判斷式
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
</body>
</html>
</body>
</html>