<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="com.dietician.model.*"%>
<% DieticianVO dietician = (DieticianVO)request.getAttribute("dieticianVO"); %>

<!DOCTYPE html>
<html>

<head> 
<title>新增營養師</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<style>

	

	
	.container {
		margin: 0 auto;
		margin-top: 50px;
		
	}
	
/* 	.col-2{ */
/* 	margin-top: 1300px; */

/* 	} */

	#pic {
	height: 200px;
	width: 200px;
	margin-bottom: 5px;
	}

	img {
	width: 100%;
	max-height: 200px;
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

	.readonly {
	background-color: #D3D3D3;
	
	}
	
	
	  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
	
</style>


</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->










		
<form method="post" action="<%=request.getContextPath()%>/dietician/dietician.do" enctype="multipart/form-data">
<div class="container">
		<div class="row">
					<div class="col-2 bb">
						<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
							<ul>
	    					<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					</div>
					<div class="col-4 r1c1 aa">
						<br>
						<br>
						<br>
						<br>
						<br>
						
						<h3>修改營養師會員資料</h3>
						<input type="hidden" name="dno" value="<%= dietician.getDno()%>">
						<input type="hidden" name="dstate" value="<%= dietician.getDstate()%>">
						<input type="hidden" name="totalScore" value="<%= dietician.getTotalScore()%>">
						<input type="hidden" name="totalReviewer" value="<%= dietician.getTotalReviewer()%>">
						<input type="hidden" name="donState" value="<%= dietician.getDonState()%>">
						<input type="hidden" name="offDay" value="<%= dietician.getOffDay()%>">
						<input type="hidden" name="optTime" value="<%= dietician.getOptTime()%>">
						<input type="hidden" name="dpic" value="<%= dietician.getDpic()%>">
				
						
						<div id="pic">
  							<img  id="dieticianPic" src="<%=request.getContextPath()%><%= dietician.getDpic()%>">
  						</div>
  						
  						<label for="dpic_changed" class="buttom">修改大頭照</label>    
        				<input type="file" id="dpic_changed" name="dpic_changed" style="visibility:hidden;" onchange="previewPic()" >
        									<!-- update的頁面中, 上傳圖片的地方name改成新圖片 -->
					
						<p><label for="dname">姓名: </label><input type="text" id="dname" name="dname" value="<%= (dietician==null)? "" : dietician.getDname()%>" placeholder="請輸入姓名"  required></p>
						<p><label for="daccount">帳號: </label><input type="text" class="readonly" id="daccount" name="daccount" value="<%= (dietician==null)? "" : dietician.getDaccount()%>" placeholder="請輸入帳號"  required readonly="true"></p>
						<p><label for="dpassword">密碼: </label><input type="password" id="dpassword" name="dpassword" value="<%= (dietician==null)? "" : dietician.getDpassword()%>" placeholder="請輸入密碼" name="dpassword" value="" required></p>
						<p><label for="dbirthDay">生日: </label><input type="date" id="dbirthDay" name="dbirthDay" value="<%= (dietician==null)? "" : dietician.getDbirthDay()%>" required></p>
						<p><label for="dphone">電話: </label><input type="text" id="dphone" name="dphone" value="<%= (dietician==null)? "" : dietician.getDphone()%>" placeholder="請輸入電話" required></p>
						<p><label for="daddress">地址: </label><input type="text" id="daddress" name="daddress" value="<%= (dietician==null)? "" : dietician.getDaddress()%>" placeholder="請輸入地址" required></p>
						<p><label for="demail">Email: </label><input type="email" id="demail" name="demail" value="<%= (dietician==null)? "" : dietician.getDemail()%>" placeholder="請輸入email" required></p>
					
						<p><input class="buttom bottom2" type="submit" value="確認修改"></p>
						<p>
							<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
	    					<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>
						</p>
						
					</div>
					
					
					<div class="col-6 r1c2">
						<br>
						<br>	
						<br>
						<br>
						<br>
				
						<p><label for="intro">自我介紹: </label><br><textarea id="intro" name="intro" value="" rows="3" cols="50" maxlength="255"><%= (dietician==null)? "" : dietician.getIntro()%></textarea></p>
						<p><label for="edu">學歷簡介: </label><br><textarea id="edu" name="edu" value="" rows="3" cols="50" maxlength="255"><%= (dietician==null)? "" : dietician.getEdu()%></textarea></p>
						<p><label for="exp">經歷簡介: </label><br><textarea id="exp" name="exp" value="" rows="3" cols="50" maxlength="255"><%= (dietician==null)? "" : dietician.getExp()%></textarea></p>
						<p><label for="lic">證照簡介: </label><br><textarea id="lic" name="lic" value="" rows="3" cols="50" maxlength="255"><%= (dietician==null)? "" : dietician.getLic()%></textarea></p>
						<p><label for="prof">專長簡介: </label><br><textarea id="prof" name="prof" value="" rows="3" cols="50" maxlength="255"><%= (dietician==null)? "" : dietician.getProf()%></textarea></p>
						<p><label for="clPrice">諮詢價格：	</label><input type="text" id="clPrice" name="clPrice" value="<%= dietician.getClPrice()%>" size="5">元/每次</p>
						<p><label for="mprice">專屬營養師月費：	</label><input type="text" id="mprice" name="mprice" value="<%= dietician.getMprice()%>" size="5">元/每月</p>

						<input type="hidden" name="action" value="update1">
						
					</div>
					
					
		</div>

</div>

</form>















		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script>
	function previewPic() {
		var pic= document.getElementById("dieticianPic");
		pic.src= URL.createObjectURL(event.target.files[0]);
	}
	if(${not empty errorMsgs}){
		$("#demail").focus();
	}
</script>
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
  
        
</script>


</body>



</html>