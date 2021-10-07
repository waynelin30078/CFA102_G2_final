<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>飲食日記</title>
<link
	href="<%=request.getContextPath()%>/Innap-template/vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/Innap-template/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>


.container{
margin: 5% auto;

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

img {
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

.btn-icon-start {
padding: 1px 6px;
margin-top: 1%;
margin-left: 1%;
}

.add-meal{
width: 200px;
}

</style>



</head>
<body>

	<div class="container">

		<div class="row">
			<div class="col-xl-12">
				<h3>2021年9月8日飲食日記</h3>
				<p>回到飲食日記首頁</p>
			</div>
		</div>

		<div class="row  summary">
			<div class="col-xl-3">
						<div id="pic">
  							<img  id="dieticianPic" src="<%=request.getContextPath()%>/front_end/free/dietician/images/dietician0.jpg">
  						</div>
  						
  						<label for="dpic" class="buttom ">上傳照片</label>
        				<input type="file" id="dpic" name="dpic" style="visibility:hidden;" onchange="previewPic()" >
			</div>
			
			<div class="col-xl-6 piechart">
			<div id="chartdiv"></div>
			</div>
			
			<div class="col-xl-3">
				<h4>體位紀錄</h4>
				<div  class="bodyrecord">
					<form method="post" action="">
					<br>
					<p><label for="dname">身高(cm)：　</label><input type="text" id="dname" name="dname" size="3"></p>
					<p><label for="daccount">體重(kg)：　</label><input type="text" id="daccount" name="daccount" size="3"></p>
					<p><label for="dname">體脂肪(%)：　</label><input type="text" id="dname" name="dname" size="3"></p>
					<p><label for="daccount">腰圍(cm)：　</label><input type="text" id="daccount" size="3"></p>
					<input class="btn btn-rounded btn-primary" type="submit" value="新增">
                    <input class="btn btn-rounded btn-light" type="reset" value="清空">
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
									<tr>
										<td>早餐</td>
										<td>500大卡</td>
										<td>60克</td>
										<td>30克</td>
										<td>15克</td>
										<td><a href="#" class="btn btn-danger btn-xs sharp"><i
												class="fa fa-trash"></i></a></td>
									</tr>

								</tbody>
							</table>
							<input class="btn btn-rounded btn-primary add-meal" type="submit" value="新增餐次飲食紀錄">
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-xl-4">
				<h4> 運動紀錄</h4><br>
				<h6>本日運動消耗熱量: </h6>
			      <a class="btn btn-primary" data-toggle="collapse" href="#multiCollapseExample1" role="button" aria-expanded="false" aria-controls="multiCollapseExample1"><span class="btn-icon-start text-info plus-icon"><i class="fa fa-plus color-info"></i></span>新增運動紀錄</a>
				<div class="row">
  					<div class="col">
    					<div class="collapse multi-collapse" id="multiCollapseExample1">
      						<div class="card card-body add-activity">
       						  					<label for="dname">選擇運動</label><input type="text" id="dname" name="dname" size="2">
												<label for="daccount">單位熱量消耗(kcal/kh/hr)</label><input type="text" id="daccount" name="daccount" size="2">
												<label for="dname">輸入體重(kg)</label><input type="text" id="dname" name="dname" size="2">
												<label for="daccount">輸入運動時間(min)</label><input type="text" id="daccount" size="2">
												<label for="daccount">消耗熱量　</label><input type="text" id="daccount" size="2">
												<input class="btn btn-rounded btn-primary" type="submit" value="新增">                   
      						</div>
    					</div>
 					 </div>
				</div>
			</div>
		</div>





<script>
	function previewPic() {
		var pic= document.getElementById("dieticianPic");
		pic.src= URL.createObjectURL(event.target.files[0]);
	}
</script>
<script src="<%=request.getContextPath()%>/Innap-template/vendor/global/global.min.js"></script>
<script src="<%=request.getContextPath()%>/Innap-templatevendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="<%=request.getContextPath()%>/Innap-template/js/custom.min.js"></script>
<script src="<%=request.getContextPath()%>/Innap-template/js/deznav-init.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/material.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>
<!-- Chart code -->
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
chart.data = [
  { "sector": "碳水化合物", "size": 25 },
  { "sector": "蛋白質", "size": 35 },
  { "sector": "脂肪", "size": 40 }
];

// Add label
chart.innerRadius = 100;
var label = chart.seriesContainer.createChild(am4core.Label);
label.text = "本日熱量\n500大卡";
label.horizontalCenter = "middle";
label.verticalCenter = "middle";
label.fontSize = 20;

// Add and configure Series
var pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "size";
pieSeries.dataFields.category = "sector";

// Animate chart data


}); // end am4core.ready()
</script>




</body>
</html>