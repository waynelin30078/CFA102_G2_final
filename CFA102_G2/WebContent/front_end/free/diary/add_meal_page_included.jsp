<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="com.food_record.model.*"%>   
<%@ page import="com.food.model.*"%>  
<%@ page import="java.util.*"%>   
   
   
    
<%   
FoodRecordService foodRecordSvc = new FoodRecordService();
List<FoodRecordVO> foodRecords = foodRecordSvc.findByMealNo(1);
  
request.setAttribute("foodRecords", foodRecords);    

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
	href="<%=request.getContextPath()%>/Innap-template/vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/Innap-template/css/style.css" rel="stylesheet">

<style>

.add_meal {
margin: 10% auto;

}
        .btn-rounded {

            margin: 20px 0px;
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
            border-radius: 10%;　
			
        }


        th {
          text-align: left;
      }

      td {
        padding-left: 20px;
    }

.add-meal {
margin: 5px;
width: 200px;
}

</style>


</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->


<div class="container add_meal">
	
    <div class="row">
        <div class="col-xl-12">
            <h3>新增餐次飲食紀錄</h3>
            <p><a href="<%=request.getContextPath()%>/front_end/free/diary/diary_page_included.jsp">回到飲食日記首頁</a></p>
        </div>
    </div>

	    <div class="row">
	        <div class="col-xl-12">
	        	<br>
	            <h6>新增至2021年09月08日飲食紀錄</h6>
	            <label for="mealName">選擇餐次:</label><br>
				<select name="mealName" id="mealName">
				  <option value="早餐">早餐</option>
				  <option value="午餐">午餐</option>
				  <option value="晚餐">晚餐</option>
				  <option value="點心">點心</option>
				</select>
	        </div>
    	</div>

		    <div class="row">
	        <div class="col-xl-12">
                <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">餐次飲食紀錄</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-responsive-md">
                                        <thead>
                                            <tr>
                                                <th><strong>食物</strong></th>
                                                <th><strong>份量或重量</strong></th>
                                                <th><strong>熱量</strong></th>
                                                <th><strong>碳水化合物</strong></th>
                                                <th><strong>蛋白質</strong></th>
                                                <th><strong>脂肪</strong></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
											<c:forEach var="foodRecord" items="${foodRecords}">	
												<tr>
													<td>${foodSvc.findByFdNo(foodRecord.fdNo).fdName}</td>
													<td>${foodRecord.fdPortion == 0? foodRecord.fdWt += '克': foodRecord.fdPortion += '份'} </td>
													<td>${foodRecord.singlelCal}大卡</td>
													<td>${foodRecord.singleCho}克</td>
													<td>${foodRecord.singlePro}克</td>
													<td>${foodRecord.singleFat}克</td>
													<td><a href="" class="btn btn-danger btn-xs sharp"><i
															class="fa fa-trash"></i></a></td>
												</tr>
											</c:forEach>

                                        </tbody>
                                    </table>
                                    <input class="btn btn-rounded btn-primary add-meal" type="submit" value="新增餐次飲食紀錄">
                                </div>
                            </div>
                        </div>

	        </div>
    </div>

    <div class="row">
        <div class="col-xl-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">食物搜尋</h4>
                </div>
                <div class="card-body">
                    <!-- Nav tabs -->
                    <div class="default-tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-bs-toggle="tab" href="#home"> 公開資料庫</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-bs-toggle="tab" href="#profile"> 我的食物</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-bs-toggle="tab" href="#contact"> 新增我的食物</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="home" role="tabpanel">
                                <div class="pt-4">
                                    <div class="row">
                                        <div class="col-xl-6">
                                            <h4>從公開資料庫搜尋食物</h4>
                                            <input type="text" style="height:20px; width: 30%; margin: 5px 0px;" id="">依名稱<br>
                                            <br>
                                            <input type="text" style="height:20px; width: 30%; margin: 5px 0px;" id="">依廠牌或來源
                                            <br>
                                            <button type="button" class="btn btn-rounded btn-danger">搜尋</button>

                                            <div class="results">
                                              <ul>
                                             
                                                 <c:forEach var="food" items="${foodSvc.findByFoodName(param.fdName)}">	
	                                                 <li>
	                                                   	 ${food.fdName}
	                                                    <p>${food.fdBrand}, 每份${food.wtPerPortion}克, ${food.calPerWt}大卡</p>
	                                                    <hr>
	                                                </li>
                                                </c:forEach>
 
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 fact">
                                        <div class="fact">
                                           <h3>排骨飯-悟饕</h3>
                                           <p> 營養成分</p>
                                           <table class="foodfact" >
                                            <tr><td colspan="3">每一份400克</td></tr>
                                            <tr><td></td><td>每份</td><td>每100克</td></tr>
                                            <tr><th>熱量           </th><td>700大卡</td><td>175大卡</td></tr>
                                            <tr><th>碳水化合物</th><td>200公克</td><td>50公克</td></tr>
                                            <tr><th>蛋白質       </th><td>100公克</td><td>25公克</td></tr>
                                            <tr><th>脂肪           </th><td>100公克</td><td>25公克</td></tr>
                                        </table>

                                    </div>
                                    <br>
                                    <br>
                                    <input type="radio" name="countBy" id="byPortion" value="依份量">
                                    <label for="byPortion">依份量</label>

                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" name="fdPortion" ><br>

                                    <input type="radio" name="countBy"  id="byWeight" value="依重量">
                                    <label for="byWeight">依重量</label>

                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" name="fdWt" >
                                    <br>
                                    <br>
                                    <input class="btn btn-rounded btn-primary"  type="submit" value="新增">
                                    <input class="btn btn-rounded btn-primary" style="height:30px; width: 100px; margin: 20px 0px; padding: 8.75px 14px; line-height: 5px;" type="reset" value="清空">

                                </div>





                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile">
                        <div class="pt-4">
                            <div class="row">
                                <div class="col-xl-6">
                                    <h4>從我的食物中尋找</h4>
                                    <input type="text" style="height:20px; width: 30%; margin: 5px 0px;" id="">依名稱<br>
                                    <br>
                                    <input type="text" style="height:20px; width: 30%; margin: 5px 0px;" id="">依廠牌或來源
                                    <br>
                                    <button type="button" class="btn btn-rounded btn-danger">搜尋</button>

                                    <div class="results">
                                      <ul>
                                         <li>
                                           	 排骨飯
                                            <p>悟饕, 每份400克, 700大卡</p>
                                            <hr>
                                        </li>
                                        <li>
                                           	 排骨飯
                                            <p>悟饕, 每份400克, 700大卡</p>
                                            <hr>
                                        </li>
                                        <li>
                                            	排骨飯
                                            <p>悟饕, 每份400克, 700大卡</p>
                                            <hr>
                                        </li>
                                        <li>
                                           	 排骨飯
                                            <p>悟饕, 每份400克, 700大卡</p>
                                            <hr>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xl-6 fact">
                                <div class="fact">
                                   <h3>排骨飯-悟饕</h3>
                                   <p> 營養成分</p>
                                   <table class="foodfact" >
                                    <tr><td colspan="3">每一份400克</td></tr>
                                    <tr><td></td><td>每份</td><td>每100克</td></tr>
                                    <tr><th>熱量           </th><td>700大卡</td><td>175大卡</td></tr>
                                    <tr><th>碳水化合物</th><td>200公克</td><td>50公克</td></tr>
                                    <tr><th>蛋白質       </th><td>100公克</td><td>25公克</td></tr>
                                    <tr><th>脂肪           </th><td>100公克</td><td>25公克</td></tr>
                                </table>

                            </div>
                            <br>
                            <br>
                                    <input type="radio" name="countBy" id="byPortion" value="依份量">
                                    <label for="byPortion">依份量</label>

                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" name="fdPortion" ><br>

                                    <input type="radio" name="countBy"  id="byWeight" value="依重量">
                                    <label for="byWeight">依重量</label>

                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" name="fdWt" >
                                    <br>
                                    <br>
                                    <input class="btn btn-rounded btn-primary " type="submit" value="新增">
                                    <input class="btn btn-rounded btn-primary" style="height:30px; width: 100px; margin: 20px 0px; padding: 8.75px 14px; line-height: 5px;" type="reset" value="清空">

                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane fade" id="contact">
                <div class="pt-4">
                   <p><label for="fdName">請輸入食物名稱 </label><br><input type="text" style="height:20px; width: 30%; margin: 5px 0px;"  id="fdName" name="fdName" ></p>
                   <p><label for="brandName">請輸入廠牌或來源名稱 </label><br><input type="text" style="height:20px; width: 30%; margin: 5px 0px;"  id="brandName" name="brandName"></p>
                   <div class="fact">
                       <p> 營養成分</p>
                       <table class="foodfact" id="addMyFood" >
                        <tr><td colspan="3">每一份<input type="text" style="height:20px; width: 10%; margin: 5px 0px;" size="2">克</td></tr>
                        <tr><td></td><td>每份</td><td>每100克</td></tr>
                        <tr><th>熱量           </th><td><input type="text" style="height:20px; width: 10%; margin: 5px 0px;"  size="2">大卡</td><td></td><td>大卡</td></tr>
                        <tr><th>碳水化合物</th><td><input type="text" style="height:20px; width: 10%; margin: 5px 0px;"  size="2">公克</td><td></td><td>公克</td></tr>
                        <tr><th>蛋白質       </th><td><input type="text" style="height:20px; width: 10%; margin: 5px 0px;"  size="2">公克</td><td></td><td>公克</td></tr>
                        <tr><th>脂肪           </th><td><input type="text" style="height:20px; width: 10%; margin: 5px 0px;"  size="2">公克</td><td></td><td>公克</td></tr>
                    </table>
                    <br>
                    <br>
                    <input class="btn btn-rounded btn-primary" type="submit" value="新增">
                    <input class="btn btn-rounded btn-light" style="height:30px; width: 100px; margin: 20px 0px; padding: 8.75px 14px; line-height: 5px;" type="reset" value="清空">

                </div>											
            </div>
        </div>
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