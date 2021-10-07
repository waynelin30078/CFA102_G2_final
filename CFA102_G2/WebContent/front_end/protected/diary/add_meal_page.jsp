<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.food_record.model.*"%>   
<%@ page import="com.food.model.*"%>  
<%@ page import="com.diary.model.*"%>  
<%@ page import="java.util.*"%>   
   
   
    
<%   

FoodService foodSvc = new FoodService();
request.setAttribute("foodSvc", foodSvc);    

int i = 0;

if(request.getAttribute("myFoodList") != null){
	List<FoodVO> myFoodList = (List<FoodVO>) request.getAttribute("myFoodList");
	i = 1;
}

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
            height: 400px;
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

.food_result:hover {
background-color: #CDCDCD;
cursor: pointer;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
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
            <form method="post"  id="backForm" action="<%= request.getContextPath()%>/diary/diary.do">
            <p><a id="back">回到飲食日記首頁</a></p>
        	<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
			<input type="hidden" name="date" value="${diary.diaryDate}">
			<input type="hidden" name="action" value="show_diary_page">
        	</form>
        

        
        
        
        
        
        </div>
    </div>

	    <div class="row">
	        <div class="col-xl-12">
	        	<br>
	            <h6>新增至${diary.diaryDate}飲食紀錄</h6>

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
											<c:forEach var="foodRecord" items="${foodRecords}" varStatus="fdIndex">	
												<tr>
													<form method="post" action="<%= request.getContextPath()%>/meal/meal.do">
														<td>${foodSvc.findByFdNo(foodRecord.fdNo).fdName}</td>
														<td>${foodRecord.fdPortion == 0? foodRecord.fdWt += '克': foodRecord.fdPortion += '份'} </td>
														<td><fmt:formatNumber pattern="#,###.0" value="${foodRecord.singlelCal}" />大卡</td>
														<td><fmt:formatNumber pattern="#,###.0" value="${foodRecord.singleCho}" />克</td>
														<td><fmt:formatNumber pattern="#,###.0" value="${foodRecord.singlePro}" />克</td>
														<td><fmt:formatNumber pattern="#,###.0" value="${foodRecord.singleFat}" />克</td>
														<td><input class="btn btn-rounded btn-primary" style="background-color:orange; color:black; border: none; width: 80px;" type="submit" value="刪除"></td>
														<input type="hidden" name="mealNo" value="${foodRecord.mealNo}">
														<input type="hidden" name="fdNo" value="${foodRecord.fdNo}">
														<input type="hidden" name="fdIndex" value="${fdIndex.index}">
														<input type="hidden" name="diaryNo" value="${diary.diaryNo}">
														<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
														<input type="hidden" name="action" value="delete_food_record">
													</form>
												</tr>
											</c:forEach>
											
                                        </tbody>
                                    </table>

                                    


                                    
                                </div>
                                                              
				                   <form method="post" action="<%= request.getContextPath()%>/meal/meal.do">
				                     	<label for="mealName">選擇餐次:</label><br>
										<select name="mealName" id="mealName">
										  <option value="早餐">早餐</option>
										  <option value="午餐">午餐</option>
										  <option value="晚餐">晚餐</option>
										  <option value="點心">點心</option>
										</select>

	                                    <input class="btn btn-rounded btn-primary add-meal" type="submit" value="送出餐次飲食紀錄">
	                                    <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
										<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
										<input type="hidden" name="action" value="add_new_meal">
                                    </form>
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
                                <a class="nav-link active" data-bs-toggle="tab" id="open_source" href="#home"> 公開資料庫</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link " id="my_food_tab" data-bs-toggle="tab" href="#profile"> 我的食物</a>
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
                                             
                                             <form method="post" action="<%= request.getContextPath()%>/meal/meal.do">
	                                            <input type="text" name="fdName" style="height:20px; width: 30%; margin: 5px 0px;">依名稱<br>
	                                            <br>
	                                            <input type="text" name="fdBrand" style="height:20px; width: 30%; margin: 5px 0px;">依廠牌或來源
	                                            <br>
	                                            <input class="btn btn-rounded btn-danger" type="submit" value="搜尋">
	                                            <input type="hidden" name="fdState" value="1">
                                                <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
												<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
												<input type="hidden" name="action" value="search_food">
												
	                                          </form>

                                            
                                            <div class="results">
                                              <ul>
                                             
                                                 <c:forEach var="food" items="${foodList}">	
		                                             <form id="food_result${food.fdNo}" method="post" action="<%= request.getContextPath()%>/meal/meal.do">
		                                                 <li class="food_result" onclick="checkFood(${food.fdNo})">
		                                                   	 ${food.fdName}
		                                                    <p>${food.fdBrand}, 每份${food.wtPerPortion}克, ${food.calPerWt}大卡</p>
		                                                    <hr>
		                                                    <input type="hidden" name="fdNo" value="${food.fdNo}">
		                                                    <input type="hidden" name="fdName" value="${food.fdName}">
		                                                    <input type="hidden" name="fdState" value="1">
		                                                    <input type="hidden" name="fdName_maintain" value="${fdName}">
		                                                    <input type="hidden" name="fdBrand_maintain" value="${fdBrand}">
		                                                    <input type="hidden" name="fdState_maintain" value="1">
		                                                    <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
															<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
															<input type="hidden" name="action" value="check_food">
		                                                </li>
		                                            </form>
                                                </c:forEach>
                                             
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 fact">
                                        <div class="fact">
                                           <h3>${food.fdName}-${food.fdBrand}</h3>
                                           <p> 營養成分</p>
                                        
                                      
                                           <table class="foodfact" >
	                                            <tr><td colspan="3">每一份${food.wtPerPortion}克</td></tr>
												<tr><td></td><td>每份</td><td>每100克</td></tr>
												<tr><th>熱量           </th><td>${food.calPerWt}大卡</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty food.calPerWt? food.calPerWt*100 div food.wtPerPortion:''  }" />大卡</td></tr>
												<tr><th>碳水化合物</th><td>${food.choPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty food.choPerWt? food.choPerWt*100 div food.wtPerPortion:''  }" />公克</td></tr>
												<tr><th>蛋白質       </th><td>${food.proPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty food.proPerWt? food.proPerWt*100 div food.wtPerPortion:''  }" />公克</td></tr>
												<tr><th>脂肪           </th><td>${food.fatPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty food.fatPerWt? food.fatPerWt*100 div food.wtPerPortion:''  }" />公克</td></tr>
                                        	</table>

                                    </div>
                                    <br>
                                    <br>
                                     	<form method="post" action="<%= request.getContextPath()%>/meal/meal.do">   
	                                    <input type="radio" onclick="countByPortion()" name="countBy" id="byPortion" value="依份量">
	                                    
	                                    <label for="byPortion">依份量(份數)</label>
	                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" id="fdPortion" name="fdPortion" required><br>
	                                    
	                                    <input type="radio"  onclick="countByWt()" name="countBy"  id="byWeight" value="依重量">
	                                    
	                                    <label for="byWeight">依重量(克)	</label>
	                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" id="fdWt" name="fdWt" required>
	                                    <br>
	                                    <br>
	                                    <input class="btn btn-rounded btn-primary"  type="submit" value="新增">
	                                    <input class="btn btn-rounded btn-primary" style="height:30px; width: 100px; margin: 20px 0px; padding: 8.75px 14px; line-height: 5px;" type="reset" value="清空">
									 
							            <input type="hidden" name="fdNo" value="${food.fdNo}">
							            <input type="hidden" name="wtPerPortion" value="${food.wtPerPortion}">
                                		<input type="hidden" name="singlelCal" value="${food.calPerWt}">
                                        <input type="hidden" name="singleCho" value="${food.choPerWt}">
										<input type="hidden" name="singlePro" value="${food.proPerWt}">        
										<input type="hidden" name="singleFat" value="${food.fatPerWt}">                            			
                                        <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
										<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
										<input type="hidden" name="action" value="add_food_record">
									 
									 </form>
                                </div>





                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile">
                        <div class="pt-4">
                            <div class="row">
 											<div class="col-xl-6">
                                            <h4>從我的食物搜尋</h4>
                                             
                                             <form method="post" action="<%= request.getContextPath()%>/meal/meal.do">
	                                            <input type="text" name="fdName" style="height:20px; width: 30%; margin: 5px 0px;">依名稱<br>
	                                            <br>
	                                            <input type="text" name="fdBrand" style="height:20px; width: 30%; margin: 5px 0px;">依廠牌或來源
	                                            <br>
	                                            <input class="btn btn-rounded btn-danger" type="submit" value="搜尋">
	                                            <input type="hidden" name="fdState" value="2">
                                                <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
												<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
												<input type="hidden" name="action" value="search_my_food">
												
	                                          </form>

                                            
                                            <div class="results">
                                              <ul>
                                             
                                                 <c:forEach var="food" items="${myFoodList}">	
		                                             <form id="my_food_result${food.fdNo}" method="post" action="<%= request.getContextPath()%>/meal/meal.do">
		                                                 <li class="food_result" onclick="checkMyFood(${food.fdNo})">
		                                                   	 ${food.fdName}
		                                                    <p>${food.fdBrand}, 每份${food.wtPerPortion}克, ${food.calPerWt}大卡</p>
		                                                    <hr>
		                                                    <input type="hidden" name="fdNo" value="${food.fdNo}">
		                                                    <input type="hidden" name="fdName" value="${food.fdName}">
		                                                    <input type="hidden" name="fdState" value="2">
		                                                    <input type="hidden" name="fdName_my_maintain" value="${fdName}">
		                                                    <input type="hidden" name="fdBrand_my_maintain" value="${fdBrand}">
		                                                    <input type="hidden" name="fdState_my_maintain" value="1">
		                                                    <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
															<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
															<input type="hidden" name="action" value="check_my_food">
		                                                </li>
		                                            </form>
                                                </c:forEach>
                                             
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-xl-6 fact">
                                        <div class="fact">
                                           <h3>${myFood.fdName}-${myFood.fdBrand}</h3>
                                           <p> 營養成分</p>
                                        
                                       <form method="post" action="<%= request.getContextPath()%>/meal/meal.do">   
                                           <table class="foodfact" >
	                                            <tr><td colspan="3">每一份${myFood.wtPerPortion}克</td></tr>
												<tr><td></td><td>每份</td><td>每100克</td></tr>
												<tr><th>熱量           </th><td>${myFood.calPerWt}大卡</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty myFood.calPerWt? myFood.calPerWt*100 div myFood.wtPerPortion:''  }" />大卡</td></tr>
												<tr><th>碳水化合物</th><td>${myFood.choPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty myFood.choPerWt? myFood.choPerWt*100 div myFood.wtPerPortion:''  }" />公克</td></tr>
												<tr><th>蛋白質       </th><td>${myFood.proPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty myFood.proPerWt? myFood.proPerWt*100 div myFood.wtPerPortion:''  }" />公克</td></tr>
												<tr><th>脂肪           </th><td>${myFood.fatPerWt}公克</td><td><fmt:formatNumber pattern="#,###.0" value="${not empty myFood.fatPerWt? myFood.fatPerWt*100 div myFood.wtPerPortion:''  }" />公克</td></tr>
                                        	</table>

                                    </div>
                                    <br>
                                    <br>
	                                    <input type="radio" onclick="myCountByPortion()" name="countBy" id="my_byPortion" value="依份量">
	                                    
	                                    <label for="my_byPortion">依份量(份數)</label>
	                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" id="my_fdPortion" name="fdPortion" required><br>
	                                    
	                                    <input type="radio"  onclick="myCountByWt()" name="countBy"  id="my_byWeight" value="依重量">
	                                    
	                                    <label for="my_byWeight">依重量(克)	</label>
	                                    <input type="number" style="height:20px; width: 30%; margin: 5px 0px;" id="my_fdWt" name="fdWt" required>
	                                    <br>
	                                    <br>
	                                    <input class="btn btn-rounded btn-primary"  type="submit" value="新增">
	                                    <input class="btn btn-rounded btn-primary" style="height:30px; width: 100px; margin: 20px 0px; padding: 8.75px 14px; line-height: 5px;" type="reset" value="清空">
									 
							            <input type="hidden" name="fdNo" value="${myFood.fdNo}">
							            <input type="hidden" name="wtPerPortion" value="${myFood.wtPerPortion}">
                                		<input type="hidden" name="singlelCal" value="${myFood.calPerWt}">
                                        <input type="hidden" name="singleCho" value="${myFood.choPerWt}">
										<input type="hidden" name="singlePro" value="${myFood.proPerWt}">        
										<input type="hidden" name="singleFat" value="${myFood.fatPerWt}">                            			
                                        <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
										<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
										<input type="hidden" name="action" value="add_food_record">
									 
									 </form>
                                </div>

                    </div>
                </div>
            </div>

            <div class="tab-pane fade" id="contact">
                <div class="pt-4">
                  <form method="post" id="add_my_food_form" action="<%= request.getContextPath()%>/meal/meal.do">
                   
                   <p><label for="myFdName">請輸入食物名稱 </label><br><input type="text" style="height:20px; width: 30%; margin: 5px 0px;"  id="myFdName" name="fdName" required></p>
                   <p><label for="myFdBrand">請輸入廠牌或來源名稱 </label><br><input type="text" style="height:20px; width: 30%; margin: 5px 0px;"  id="myFdBrand" name="fdBrand" required></p>
                   <div class="fact">
                       <p> 營養成分</p>
                       <table class="foodfact" id="addMyFood" >
                        <tr><td colspan="3"  style="width: 100px;">每一份<input name="wtPerPortion" type="number" style="height:20px; width: 30%; margin: 5px 0px;" max="9999" required>克</td></tr>
                        <tr><th>熱量           </th><td><input name="calPerWt" type="number" style="height:20px; width: 40%; margin: 5px 0px;" step="0.1" max="9999" required>大卡</td></tr>
                        <tr><th>碳水化合物</th><td><input name="choPerWt" type="number" style="height:20px; width: 40%; margin: 5px 0px;" step="0.1" max="9999" required>公克</td></tr>
                        <tr><th>蛋白質       </th><td><input name="proPerWt" type="number" style="height:20px; width: 40%; margin: 5px 0px;" step="0.1" max="9999" required>公克</td></tr>
                        <tr><th>脂肪           </th><td><input name="fatPerWt" type="number" style="height:20px; width: 40%; margin: 5px 0px;" step="0.1" max="9999" required>公克</td></tr>
                    </table>
                    <br>
                    <br>
                    <input class="btn btn-rounded btn-primary"  id="add_my_food_btn" type="submit" value="新增">
                    <input type="hidden" name="fdState" value="2">
                    <input type="hidden" name="mno" value="${memberVO1.mno}">
                    <input type="hidden" name="diaryNo" value="${diary.diaryNo}">
					<input type="hidden" name="diaryDate" value="${diary.diaryDate}">
					<input type="hidden" name="action" value="add_my_food">
				  
				  </form>
				  
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
<script src="<%=request.getContextPath()%>/front_end/protected/diary/vendor/global/global.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/vendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/js/hscustom.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/protected/diary/js/deznav-init.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<script>


if(   <%= i %>          ) {
	$("#my_food_tab").addClass('active');
	$("#profile").addClass('active');
	$("#home").removeClass('active');
	$("#open_source").removeClass('active');
}

$("#add_my_food_form").submit(function(){
	  $("#add_my_food_btn").attr("disabled", true);
	});


document.getElementById("back").onclick = function() {
    document.getElementById("backForm").submit();
}

document.getElementById("deleteFoodRecord").onclick = function() {
    document.getElementById("deleteFoodRecordForm").submit();
}

function checkFood(data) {
    document.getElementById("food_result" + data).submit();
}

function checkMyFood(data) {
    document.getElementById("my_food_result" + data).submit();
}

function countByPortion() {
	var wtInput = document.getElementById("fdWt");
	wtInput.readOnly=true;
	wtInput.style.backgroundColor="#CDCDCD";
	wtInput.value="";
	var portionInput = document.getElementById("fdPortion");
	portionInput.readOnly=false;
	portionInput.style.backgroundColor="white";
}

function countByWt() {
	var wtInput = document.getElementById("fdWt");
	wtInput.readOnly=false;
	wtInput.style.backgroundColor="white";
	
	var portionInput = document.getElementById("fdPortion");
	portionInput.readOnly=true;
	portionInput.style.backgroundColor="#CDCDCD";
	portionInput.value="";
}

function myCountByPortion() {
	var wtInput = document.getElementById("my_fdWt");
	wtInput.readOnly=true;
	wtInput.style.backgroundColor="#CDCDCD";
	wtInput.value="";
	var portionInput = document.getElementById("my_fdPortion");
	portionInput.readOnly=false;
	portionInput.style.backgroundColor="white";
}

function myCountByWt() {
	var wtInput = document.getElementById("my_fdWt");
	wtInput.readOnly=false;
	wtInput.style.backgroundColor="white";
	
	var portionInput = document.getElementById("my_fdPortion");
	portionInput.readOnly=true;
	portionInput.style.backgroundColor="#CDCDCD";
	portionInput.value="";
}

if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}

</script>
</body>
</html>