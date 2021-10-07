<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.dietician.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.activity_record.model.*"%>
<%@ page import="com.food_record.model.*"%>
<%@ page import="com.food.model.*"%>
<%@ page import="java.sql.Date"%>



<% 

DiaryVO diary = (DiaryVO) request.getAttribute("diary");
request.setAttribute("diary", diary);

ActivityRecordService actRecordSvc = new ActivityRecordService();
List<ActivityRecordVO> actRecords = actRecordSvc.findByDiaryNo(diary.getDiaryNo());
request.setAttribute("actRecords", actRecords);

MealService mealSvc = new MealService();
List<MealVO> meals = mealSvc.findByDiaryNo(diary.getDiaryNo());
request.setAttribute("meals", meals);

ActivityService activitySvc = new ActivityService();
request.setAttribute("activitySvc", activitySvc);

FoodRecordService foodRecordSvc = new FoodRecordService();
request.setAttribute("foodRecordSvc", foodRecordSvc);

FoodService foodSvc = new FoodService();
request.setAttribute("foodSvc", foodSvc);


%>


<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<link
	href="<%=request.getContextPath()%>/front_end/protected/diary/vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/front_end/protected/diary/css/style.css" rel="stylesheet">



<style>

.diary_page {
margin: 10% auto;

}

.buttom {
	margin-left: 20%;
	background-color: #007bff;
	color: white;
	padding-left: 20px;
	padding-top: 5px;
	border-radius: 5px;
	
	border: 1px solid #007bff;
	width: 100px;
	height: 30px;
	
}
.btn-rounded {
	margin: 10px 0px;
	line-height: 5px;
	width: 100px;
	height: 30px;
}

.results {
	width: 60%;
	height: 40%;
	overflow: scroll;
	border: 1px solid black;
}

.fact {
	margin-top: 30px;
}

#addMyFood {
	border: none;
}

.foodfact {
	color: black;
	border: 1px solid black;
	line-height: 24px;
	letter-spacing: 3px;
	　
}

th {
	text-align: left;
}

td {
	padding-left: 20px;
}

#pic {
height: 200px;
width: 200px;
margin-bottom: 5px;
}

#diarypic {
width: 100%;
max-height: 200px;
border: 2px solid grey;
}

#chartdiv {
	height: 220px;
}

.add-meal {
margin-bottom: 20px;
}


.piechart {
height: 100%;
}

.bodyrecord {
border: 1px solid orange;
border-radius: 5%;
height: 90%;
text-align: center;

}

.meal-record {
margin-top: 20px;
}

.add-activity {
margin-top: 5px;

}


.add-meal{
width: 200px;
}

.plus-activity {

margin: 1px 5px;
}

.bodyLabel {
display: float;
float:left;
margin-top: 5px;
width: 40%;
padding: 5px 8px;
margin-left: 5px;
}
.bodyLabel2 {
background-color: orange;
border: 1px solid orange;
}


.bottom2 {
display: inline-block;
margin-top: 5px;
margin-left: 5px;
width: 50%;
}

.bodyrecord {
display:flex;
align-items: center;
justify-content: center;

}

.summary {
height: 30%;

}

#bodyPic {
height: 100%;
}


.nice-select .list {
 max-height: 300px;
  overflow: scroll;
 
  }


select:invalid { color: gray; }

input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.actRecord {
border: 1px solid orange;
border-radius: 10px;
height: 100px;
margin:5px;
}

.calBurnToday {

font-size: 1.5rem;
}

.meal_row:hover {

cursor: pointer;
}

</style>


</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->

<div class="container diary_page">

	<div class="row ">
			<div class="col-xl-12">
				<h3>${diary.diaryDate}飲食日記</h3> 
				<p><a href="<%=request.getContextPath()%>/front_end/protected/diary/diary_calendar_page.jsp">回到飲食日記首頁</a></p>
			</div>
		</div>

		<div class="row  summary">
			<c:if test="<%= diary.getBodyPic().isEmpty()%>">
			
			<div class="col-xl-3">
				<form method="post" action="<%= request.getContextPath()%>/diary/diary.do" enctype="multipart/form-data">
					<div id="showpic1">
	  					<img  id="bodyPic1" src="<%= request.getContextPath() + "/front_end/protected/diary/images/diary0.jpg"%>">
	  				</div>
	  						
	  				<label for="bodyPic" class="buttom btn btn-primary bodyLabel bodyLabel2">選擇照片</label>
	  				<label for="submitBodyPic1" class="buttom btn btn-primary bodyLabel">確認上傳</label>
	        		<input type="file" id="bodyPic" name="bodyPic"  style="visibility:hidden;" onchange="previewPic1()" required>
					<input class="buttom bottom2" id="submitBodyPic1" style="visibility:hidden;" type="submit" value="確認上傳">
					<input type="hidden" name="action" value="add_bodyPic">
					<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
				</form>

			</div>  
			
			</c:if>
			
			
			
			<c:if test="<%=! diary.getBodyPic().isEmpty()%>">
			
			<div class="col-xl-3">
				<form method="post" action="<%= request.getContextPath()%>/diary/diary.do" enctype="multipart/form-data">
					<div id="showpic">
	  					<img  id="originBodyPic" src="<%= request.getContextPath() + diary.getBodyPic() %>">
	  				</div>
	
	  				<label for="bodyPic_changed" class="buttom btn btn-primary bodyLabel bodyLabel2">修改照片</label>
	  				<label for="submitBodyPic" class="buttom btn btn-primary bodyLabel">確認修改</label>
	        		<input type="file" id="bodyPic_changed" name="bodyPic_changed" style="visibility:hidden;" onchange="previewPic()" required>
	        		<input class="buttom bottom2" id="submitBodyPic" style="visibility:hidden;" type="submit" value="確認修改">
	        		<input type="hidden" name="action" value="update_bodyPic">
	        		<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
				</form>
			</div>
			
			</c:if>
			
			
			
			
			<div class="col-xl-6 piechart">
			<div id="chartdiv"></div>
			</div>
			
			<div class="col-xl-3">
				<h4>體位紀錄</h4>
				<div  class="bodyrecord">
					<form method="post" action="<%= request.getContextPath()%>/diary/diary.do">
					<br>
					<p>身高(cm)：<input type="number" style="height:20px; width: 50%;" id="ht" name="ht" value="<%= (diary.getHt() == 0)? "" : diary.getHt()%>"></p>
					<p>體重(kg)：<input type="number" style="height:20px; width: 50%;" id="wt" name="wt" value="<%= (diary.getWt() == 0)? "" : diary.getWt()%>"></p>
					<p>體脂肪(%)：<input type="number" step="0.1" style="height:20px; width: 50%;" id="bodyFat" name="bodyFat" value="<%= (diary.getBodyFat() == 0)? "" : diary.getBodyFat()%>"></p>
					<p>腰圍(cm)：<input type="number" style="height:20px; width: 50%;" id="wc" name="wc" value="<%= (diary.getWc() == 0)? "" : diary.getWc()%>"></p>
					<input class="btn btn-rounded btn-primary" style="width: 120px;" type="submit" value="確認記錄">
					<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
					<input type="hidden" name="action" value="update_bodyRecord">
                    
                    </form>
				</div>
			</div>
			
		</div>

		<div class="row">
			<div class="col-xl-12 meal-record">
				<div class="card">
					<div class="card-header">
						<h4 class="card-title">本日飲食紀錄</h4>
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-responsive-md">
								<thead>
									<tr>
										<th><strong>餐次</strong></th>
										<th><strong>熱量</strong></th>
										<th><strong>碳水化合物</strong></th>
										<th><strong>蛋白質</strong></th>
										<th><strong>脂肪</strong></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									
									<c:forEach var="meal" items="${meals}">	
										<form method="post" action="<%= request.getContextPath()%>/diary/diary.do">
										
										<tr class="meal_row" style="color:black; font-size: 1rem;">
											<td>${meal.mealName}</td>
											<td>${meal.mealCal}大卡</td>
											<td>${meal.mealCho}克</td>
											<td>${meal.mealPro}克</td>
											<td>${meal.mealFat}克</td>
											<td><input class="btn btn-rounded btn-primary" style="background-color:orange; color:black; border: none; width: 80px;" type="submit" value="刪除"></td>
										</tr>
									<c:forEach var="foodRecord" items="${foodRecordSvc.findByMealNo(meal.mealNo)}">	
										<tr class="record_row">
											<td>&nbsp&nbsp&nbsp-${foodSvc.findByFdNo(foodRecord.fdNo).fdName}(${foodRecord.fdPortion == 0? foodRecord.fdWt += '克': foodRecord.fdPortion += '份'})</td>
											<td>${foodRecord.singlelCal}大卡</td>
											<td>${foodRecord.singleCho}克</td>
											<td>${foodRecord.singlePro}克</td>
											<td>${foodRecord.singleFat}克</td>
										</tr>											
									</c:forEach>
											<input type="hidden" name="mealNo" value="${meal.mealNo}">
											<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
											<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
											<input type="hidden" name="action" value="delete_meal">
										</form>
									</c:forEach>
								</tbody>
							</table>
							<form method="post" action="<%=request.getContextPath()%>/meal/meal.do">
								<input class="btn btn-rounded btn-primary add-meal" type="submit" value="新增餐次飲食紀錄">
								<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
								<input type="hidden" name="action" value="add_meal_page">
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="row">
				<h4> 運動紀錄</h4><br>
				<h6>本日運動消耗熱量: <span class="calBurnToday">${diary.totalCalBurn}大卡</span> </h6>
			<c:forEach var="actRecord" items="${actRecords}" > 
			<div class="col-xl-3 actRecord">

			運動項目: ${activitySvc.findById(actRecord.actNo).actName}<br>
			運動時間: ${actRecord.actHr}小時<br>
			消耗熱量: ${actRecord.calBurn}大卡<br>
			<form method="post"  action="<%= request.getContextPath()%>/diary/diary.do">
			<input class="btn btn-rounded btn-primary" style="background-color:orange; color:black; border: none; margin-top: 0; height: 30px; width: 80px;" type="submit" value="刪除">
			<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
			<input type="hidden" name="actNo" value="${actRecord.actNo}">
			<input type="hidden" name="action" value="delete_activity_record">
			</form>
			</div>
			</c:forEach>
			<div class="col-xl-4">

			      <a class="btn btn-danger" data-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1"><i class="fa fa-plus color-info plus-activity"></i>新增運動紀錄</a>
				<div class="row">
  					<div class="col">
    					<div class="collapse multi-collapse" id="multiCollapseExample1">
      						<div class="card card-body add-activity">
							  		
			  					<form id="add_activity_form" method="post" action="<%= request.getContextPath()%>/diary/diary.do">

				  					<select id ="selectActivity" onchange="showInfo()" name="actNo" required>
									         <option value="" disabled selected hidden>請選擇運動</option>
								         
									         <c:forEach var="activity" items="${activitySvc.all}" > 
									          <option  value="${activity.actNo}" id="${activity.calPerKgHr}">${activity.actName}</option>
									         </c:forEach>   
									       </select>
									       <br>
									       <br>
									       <br>
									單位熱量消耗(kcal/kg/hr)
									<input type="text" id="calPerKgHr" onchange="calculateCal()" style="height:20px; width: 50%; margin: 2px 0px; background-color: #CDCDCD; color: black;"  name="calPerKgHr" size="2" readonly><br>
									輸入體重(kg)<br>
									<input type="number" onchange="calculateCal()" style="height:20px; width: 50%; margin: 2px 0px;" id="actWt" name="wt" size="2" required><br>
									輸入運動時間(hr)<br>
									<input type="number" onchange="calculateCal()" style="height:20px; width: 50%; margin: 2px 0px; " id="actHr" name="actHr" size="2" step="0.1" max="9999" required><br>
									消耗熱量(kcal)<br>
									
									<input type="text"  style="height:20px; width: 50%; margin: 2px 0px; background-color: #CDCDCD; color: black;" id="calBurn" name="calBurn" size="2" max="9999" readonly><br>
									<input id="add_activity_btn" class="btn btn-rounded btn-primary" type="submit" value="新增">
									<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
									<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
									<input type="hidden" name="action" value="add_activity_record">
									
									
								</form>                  
      						</div>
    					</div>
 					 </div>
				</div>
			</div>
		</div>
</div>








<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<script src="<%=request.getContextPath()%>/front_end/protected/diary/vendor/global/global.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/js/custom.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/js/deznav-init.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/material.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>
<script>
	
$(".record_row").css("visibility", "collapse");
	
$(".meal_row").click(function(e) {
	if (e.target instanceof HTMLInputElement) {return;}
	
	if($(this).nextUntil(".meal_row").css('visibility') === 'collapse'){
	
		$(this).nextUntil(".meal_row").css("visibility", "visible");
	}else {
		$(this).nextUntil(".meal_row").css("visibility", "collapse");
		
	}

});
	
	
	$("#add_activity_form").submit(function(){
	  $("#add_activity_btn").attr("disabled", true);
	});


	function showInfo() {
		var act  = document.getElementById("selectActivity");
		var info = act.options[act.selectedIndex].id
		document.getElementById("calPerKgHr").value=info;
		calculateCal();
	}
	

	function previewPic1() {
		var pic= document.getElementById("bodyPic1");
		pic.src= URL.createObjectURL(event.target.files[0]);
	}	
	
	
	function previewPic() {
		var pic= document.getElementById("originBodyPic");
		pic.src= URL.createObjectURL(event.target.files[0]);
	}
	

	
</script>
<script>
am4core.ready(function() {

// Themes begin
am4core.useTheme(am4themes_material);
am4core.useTheme(am4themes_animated);
// Themes end

/**
 * Define data for each year
 */


// Create chart instance
var chart = am4core.create("chartdiv", am4charts.PieChart);

// Add data

var cho = ${diary.totalCho}*4 /${diary.totalCal} * 100;
var pro = ${diary.totalPro}*4 /${diary.totalCal} * 100;
var fat = ${diary.totalFat}*9 /${diary.totalCal} * 100;

if(${diary.totalCal} === 0){
	chart.data = [
		{ "sector": "", "size": 100 },
		  { "sector": "碳水化合物", "size": 0 },
		  { "sector": "蛋白質", "size": 0 },
		  { "sector": "脂肪", "size": 0 }
		];

		// Add label
		chart.innerRadius = 100;
		var label = chart.seriesContainer.createChild(am4core.Label);
		label.text = "總攝取熱量\n${diary.totalCal}大卡";
		label.horizontalCenter = "middle";
		label.verticalCenter = "middle";
		label.fontSize = 20;
		
		var pieSeries = chart.series.push(new am4charts.PieSeries());
		pieSeries.dataFields.value = "size";
		pieSeries.dataFields.category = "sector";

} else{
	

chart.data = [
  { "sector": "碳水化合物", "size": cho },
  { "sector": "蛋白質", "size": pro },
  { "sector": "脂肪", "size": fat }
];

// Add label
chart.innerRadius = 100;
var label = chart.seriesContainer.createChild(am4core.Label);
label.text = "總攝取熱量\n${diary.totalCal}大卡";
label.horizontalCenter = "middle";
label.verticalCenter = "middle";
label.fontSize = 20;


// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "size";
pieSeries.dataFields.category = "sector";
}
// Animate chart data


}); // end am4core.ready()

function calculateCal() {
	var actWt  = parseFloat(document.getElementById("actWt").value);
	var actHr = parseFloat(document.getElementById("actHr").value);
	var calPerKgHr = parseFloat(document.getElementById("calPerKgHr").value);
	var calBurn = document.getElementById("calBurn");
	
	var calculateCal =  actWt*actHr*calPerKgHr;
	calculateCal = calculateCal.toFixed(2);
	if(!isNaN(calculateCal)){
	calBurn.value = calculateCal ;
	}
}

if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}



</script>
</body>
</html>