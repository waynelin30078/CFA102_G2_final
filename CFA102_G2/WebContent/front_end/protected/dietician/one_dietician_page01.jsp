<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="com.dietician.model.*"%>
<%@ page import="com.dietician.controller.*"%>
<%@ page import="com.d_license.model.*"%>
<%@ page import="com.d_license.controller.*"%>

<% DieticianVO dietician2 = (DieticianVO)session.getAttribute("dieticianVO2"); 
DieticianService dietician1 =new DieticianService();
DieticianVO dietician=(DieticianVO)dietician1.findByPrimaryKey(dietician2.getDno());

%>    
	
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<style>
	.container {
		margin: 0 auto;

		
	}

	#pic {
	height: 200px;
	width: 200px;
	margin-bottom: 5px;
	}

	#dieticianPic {
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
	.right-area {
		margin-left: auto;
		position: relative;
		right: 0;
		top: -75px;
	}	
	.xdsoft_datetimepicker .xdsoft_datepicker {
		width:  300px;   /* width:  300px; */
	}
	.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
		height: 151px;   /* height:  151px; */
	}
	.preview div {
		display: inline-block;
		width: 45%;
		margin: 5px;
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
					<div class="col-2">
					
					</div>
					<div class="col-4 r1c1">
						<br>
						<br>
						<br>
						<br>
						<br>
						<h3>營養師會員</h3>
						<p></p>
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
						<br>
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
						<h4 style="color:red">權限狀態：</h4>
						<p>
						<c:if test="<%= dietician.getDstate()==0%>">
						未審核
						</c:if>
						<c:if test="<%= dietician.getDstate()==1%>">
						通過審核
						</c:if>
						<c:if test="<%= dietician.getDstate()==2%>">
						未通過審核
						</c:if>
						</p>
						
					
						<input type="hidden" name="action" value="update_dietician_page">
				
					</div>
					
					
		</div>



</div>

</form>				

					<div class="col-6 right-area">
						<h4 id="AAA">證照上傳:</h4>
<%-- 						<form action="<%=request.getContextPath()%>/D_licenseServlet.do" --%>
<!-- 							method="post" enctype="multipart/form-data"> -->
<!-- 							<input type="text" name="licDesc" value="" placeholder="證照名稱"> -->
<!-- 							<input type="file" name="licFile"> <input type="submit" -->
<!-- 								value="上傳"> <input type="hidden" name="dno" -->
<%-- 								value="<%=dietician.getDno()%>"> <input type="hidden" --%>
<!-- 								name="action" value="insert"> -->
<!-- 						</form> -->
						
		<!-- 改成你自己的controller -->
		<form method="post" action="<%=request.getContextPath()%>/D_licenseServlet.do" enctype="multipart/form-data">
			<div class="pic-upload">
			<input type="text" name="licDesc" value="" placeholder="證照名稱">
				<input type="file" class="form-control upload-pic" id="formFile" accept="image/*" multiple name="licFile">
			</div>
			<div class="preview"></div>
			<div class="mb-3 d-flex justify-content-center align-items-center">
		<!-- 改成你自己的action和PK -->
				<input type="hidden" name="dno" value="<%=dietician.getDno()%>">
				<input type="hidden" name="action" value="insert">
				<button type="submit" class="buttom bottom2">新增</button>
			</div>
		</form>
		<!-- controller裡面也要改收多張圖片 -->
		<h3><a href="<%=request.getContextPath()%>/front_end/protected/appointment/appointment_infoEdit.jsp?dno=<%= dietician.getDno()%>" class="badge badge-info">修改預約時段</a></h3>  
<h3><a href="<%=request.getContextPath()%>/front_end/protected/appointment/calendar_appointment.jsp?dno=<%= dietician.getDno()%>" class="badge badge-info">月曆查看預約時間</a></h3>
					</div>
					 
	
<% 
  java.sql.Date dbirthDay = null;
  try {
	  dbirthDay = dietician.getDbirthDay();
   } catch (Exception e) {
	   
   }
%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>  

        $(document).ready(function() {
	        let upload = $(".upload-pic");
            upload.change(function(){
            	if (this.files) {
                    files = this.files;
                    let preview = $(this).parents(".pic-upload").next();
                    for (let i = 0; i < files.length; i++) {
                        if (files[i].type.indexOf("image") >= 0) {
                            let reader = new FileReader();
                            reader.addEventListener("load", (ex) => {
                                let div = document.createElement("div");
                                let img = document.createElement("img");
                                img.src = ex.target.result;
                                img.classList.add("previewImg");
                                div.append(img);
                                preview.append(div);
                            });
                            reader.readAsDataURL(files[i]);
                        } else {
                            window.close();
                        }
                    }
                }
            })
	        
        });

</script>

		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>