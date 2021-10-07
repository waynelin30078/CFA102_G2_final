<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>新增餐次飲食</title>
    <link href="<%= request.getContextPath() %>/Innap-template/vendor/jquery-nice-select/css/nice-select.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/Innap-template/css/style.css" rel="stylesheet">
    <style>

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

<div class="container">
   
    <div class="row">
        <div class="col-xl-12">
            <h3>新增餐次飲食紀錄</h3>
            <p>回到飲食日記</p>
        </div>
    </div>

	    <div class="row">
	        <div class="col-xl-12">
	        	<br>
	            <h6>新增至2021年09月08日飲食紀錄</h6>
	            <label for="mealName">選擇餐次:</label>
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
                                            <tr>
                                                <td>燒餅</td>
                                                <td>1份</td>
                                                <td>500大卡</td>
                                                <td>60克</td>
                                                <td>30克</td>
                                                <td>15克</td>
                                                <td>
                                                    <a href="#" class="btn btn-danger btn-xs sharp"><i class="fa fa-trash"></i></a>
                                                </td>
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
                                            <input type="text" id="">依名稱<br>
                                            <br>
                                            <input type="text" id="">依廠牌或來源
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

                                    <input type="number" name="fdPortion" ><br>

                                    <input type="radio" name="countBy" id="byWeight" value="依重量">
                                    <label for="byWeight">依重量</label>

                                    <input type="number" name="fdWt" >
                                    <br>
                                    <br>
                                    <input class="btn btn-rounded btn-primary" type="submit" value="新增">
                                    <input class="btn btn-rounded btn-light" type="reset" value="清空">

                                </div>





                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile">
                        <div class="pt-4">
                            <div class="row">
                                <div class="col-xl-6">
                                    <h4>從我的食物中尋找</h4>
                                    <input type="text" id="">依名稱<br>
                                    <br>
                                    <input type="text" id="">依廠牌或來源
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

                            <input type="number" name="fdPortion" ><br>

                            <input type="radio" name="countBy" id="byWeight" value="依重量">
                            <label for="byWeight">依重量</label>

                            <input type="number" name="fdWt" >
                            <br>
                            <br>
                            <input class="btn btn-rounded btn-primary" type="submit" value="新增">
                            <input class="btn btn-rounded btn-light" type="reset" value="清空">

                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane fade" id="contact">
                <div class="pt-4">
                   <p><label for="fdName">請輸入食物名稱 </label><br><input type="text" id="fdName" name="fdName" ></p>
                   <p><label for="brandName">請輸入廠牌或來源名稱 </label><br><input type="text" id="brandName" name="brandName"></p>
                   <div class="fact">
                       <p> 營養成分</p>
                       <table class="foodfact" id="addMyFood" >
                        <tr><td colspan="3">每一份<input type="text" size="2">克</td></tr>
                        <tr><td></td><td>每份</td><td>每100克</td></tr>
                        <tr><th>熱量           </th><td><input type="text" size="2">大卡</td><td></td><td>大卡</td></tr>
                        <tr><th>碳水化合物</th><td><input type="text" size="2">公克</td><td></td><td>公克</td></tr>
                        <tr><th>蛋白質       </th><td><input type="text" size="2">公克</td><td></td><td>公克</td></tr>
                        <tr><th>脂肪           </th><td><input type="text" size="2">公克</td><td></td><td>公克</td></tr>
                    </table>
                    <br>
                    <br>
                    <input class="btn btn-rounded btn-primary" type="submit" value="新增">
                    <input class="btn btn-rounded btn-light" type="reset" value="清空">

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






<script src="<%= request.getContextPath() %>/Innap-template/vendor/global/global.min.js"></script>
<script src="<%= request.getContextPath() %>/Innap-templatevendor/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="<%= request.getContextPath() %>/Innap-template/js/custom.min.js"></script>
<script src="<%= request.getContextPath() %>/Innap-template/js/deznav-init.js"></script>

</body>
</html>