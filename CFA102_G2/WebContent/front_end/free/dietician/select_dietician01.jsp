<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="java.util.*"%>

<%
  Set<DieticianVO> set = (Set<DieticianVO>) request.getAttribute("set");


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

<style>
.single-courses .courses-content .courses-price .price{

font-size: 10px;
}
	
.courses-images {
width: 100%; 
margin: 0 auto;

}
	
	.single-courses .courses-content .content-wrapper .author{
	
	color:#003E3E;
	}
	
	
	.price abc .price bbb{
		position:absolute;
		left:3rem;
		margin-top:20px;
		font-size: 30px;

	}
	
	
	
		span{

		color:#006400;
		
	}
	
	
	
	

	.btn {
	display: inline-block;
	margin-left: 5%;	
	margin-bottom: 10%;
	}

	h3 {
	color:orange;
	}
	
	#btn_row {
	
	margin: 30px 0px;
	}
	
	#dietician_col {
	
	margin: 5px auto;
	border: 1px solid blue;
	border-radius: 5px;
	
	}
	
	#select_search {
	display: flex;
  	align-items: top;
  	justify-content: center;
	}
	
	.findDietician {
	display: inline-block;
	margin: 0px 30px;
	verticle-align: top;
	}
	
	.buttom {
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	border: 1px solid #007bff;
	}
	
	#dieticians img {
	
	float: left;
	height: 100px;
	width: 100px;
	border: 3px solid pink;
	margin: 5px;
	}
	
	#dieticians table {
	text-align: left;
	}
	
	.intro {
	color: blue;
	
	}
	#range{
	padding: 0px ;
	}
	#minPrice{
	padding: 0px ;
	}
	#maxPrice{
	padding: 0px ;
	}
	
	#li{

	text-align:center
	}
	
</style>




</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
<!-- 	<div class="container this_page"> -->
		<!-- 服務很好先做一個置中的div(開頭) -->










	
	<div class="container">

<!-- 		<div class="row" id="header"> -->
<!-- 			<div class="col-12"> -->
<!-- 				<h3>尋找專屬營養師</h3> -->
<!-- 			</div> -->
<!-- 		</div> -->

		<div class="row " id="btn_row">
			<div class="col-12" id="select_search">
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
				<br>
				<br>
				<br>
				<br>
				<br>
				
					<input type="submit" value="觀看全部營養師" class="buttom"><br>
					<input type="hidden" name="action" value="getAll_for_display">
				</form>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dspecialty.do">
				<br>
				<br>
				<br>
				<br>
				<br>
					<input type="submit" value="依專長搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findBySpecialty">
  					<input type="checkbox" id="vehicle1" name="vehicle1" value="1">
  					<label for="vehicle1"> 體重控制</label><br>
  					<input type="checkbox" id="vehicle2" name="vehicle1" value="2">
  					<label for="vehicle2"> 運動營養</label><br>
  					<input type="checkbox" id="vehicle3" name="vehicle1" value="3">
  					<label for="vehicle3"> 孕產婦營養</label><br>
  					<input type="checkbox" id="vehicle4" name="vehicle1" value="4">
  					<label for="vehicle4"> 糖尿病飲食控制</label><br>
  					<input type="checkbox" id="vehicle5" name="vehicle1" value="5">
  					<label for="vehicle5"> 腎臟病飲食控制</label><br>
  					<input type="checkbox" id="vehicle6" name="vehicle1" value="6">
  					<label for="vehicle6"> 高血壓飲食控制</label><br>
				</form>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
					<br>
					<br>
					<br>
					<br>
					<br>
					
					<input type="submit" value="依評價搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findByScore">
						<input  type="range" name="avgScore" value="" max="5" min="1" step="1" id="range" oninput="output.innerText= '評價' + range.value + '分以上'"><br>
						<output id="output">評價3分以上</output>
				</form>
				<form  class="findDietician" method ="post" action="<%=request.getContextPath()%>/dietician/dietician.do">
					<br>
					<br>
					<br>
					<br>
					<br>
					<input type="submit" value="依價格搜尋" class="buttom"><br>
					<input type="hidden" name="action" value="findByPrice">
  					<label for="minPrice">最低價格</label>
  					<input type="text" id="minPrice" name="minPrice" size="3" value="">元<br>
  					<label for="maxPrice">最高價格</label>
  					<input type="text" id="maxPrice" name="maxPrice" size="3" value="">元<br>
				</form>
			</div>
		</div>
		
		<c:if test="${not empty errorMsgs}">
			<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li id="li" style="color:red">${message}</li>
		</c:forEach>
		</ul>
		</c:if>



<%-- 	<c:forEach var="dieticianVO" items="${list}">	 --%>
<%-- 		<a href="<%=request.getContextPath()%>/dietician/dietician.do?action=one_dietician_page&dno=${dieticianVO.dno}"> --%>
<!-- 		<div class="row"> -->
<!-- 			<div class="col-10" id="dietician_col"> -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col-12" id="dieticians"> -->
<%-- 						<img src="<%=request.getContextPath()%>${dieticianVO.dpic}"> --%>
<!-- 						<table> -->
<!-- 						<tr><td class="intro">營養師介紹</td></tr> -->
<%-- 						<tr><td>${dieticianVO.intro}</td></tr> --%>
<!-- 						<tr><td class="intro">資歷</td></tr> -->
<%-- 						<tr><td>${dieticianVO.exp}</td></tr> --%>
<!-- 						</table> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		</a> -->
<%-- 	</c:forEach> --%>
	</div>
 <div class="courses-wrapper-02">
 <div class="row gx-xxl-5">
<c:forEach var="dieticianVO" items="${set}">	
<c:if test="${dieticianVO.dstate==1}">
       <div class="col-lg-4 col-sm-6">
                            <!-- Single Courses Start -->
                            <div class="single-courses">
                                <div class="courses-images">
                                    <a href="<%=request.getContextPath()%>/dietician/dietician.do?action=one_dietician_page&dno=${dieticianVO.dno}"><img width="362px" height="362px" src="<%=request.getContextPath()%>${dieticianVO.dpic}" alt="courses"/></a>
                                </div>
                                <div class="courses-content">
                                    <div class="courses-price">
                                
                                  </div>
                                    
                                    <div class="content-wrapper">
                                        <h3><p class="author" style="margin-top:10px" >營養師:${ dieticianVO.dname}</p></h3>

                                               <span class="price abc">諮詢價格:${dieticianVO.clPrice}</span> 　　　　　　　　　　　　
                                     <span class="price bbb">月費:${ dieticianVO.mprice}</span>
                                     <br>
                                     <c:if test="${dieticianVO.totalScore==0||dieticianVO.totalReviewer==0 }">
                                      <br>
 										<span class="price abc">平均評價:0</span>
 										</c:if>
 										<c:if test="${dieticianVO.totalScore!=0&&dieticianVO.totalReviewer!=0 }">
 										 <br>
 										<span class="price abc">平均評價:${(dieticianVO.totalScore-(dieticianVO.totalScore%dieticianVO.totalReviewer))/ dieticianVO.totalReviewer}</span>
 										</c:if>
 										<br>
 										                 <span class="price abc">評等人數:${dieticianVO.totalReviewer }</span>
                                        <ul class="meta">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- Single Courses End -->
                        </div>
                        </c:if>
</c:forEach>
</div>
</div>









		<!-- 服務很好先做一個置中的div(結束) -->
	</div>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<%@ include file="/front_end_example/footer_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個footer連結******************* -->
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	<%@ include file="/front_end_example/js_link.jsp"%>
	<!-- *************每一頁body最後面都要include這個js連結******************* -->
	
	
	    <!-- Modernizer & jQuery JS -->
    <script src="assets/js/vendor/modernizr-3.11.2.min.js"></script>
    <script src="assets/js/vendor/jquery-3.5.1.min.js"></script>

    <!-- Bootstrap JS -->
    <!-- <script src="assets/js/plugins/popper.min.js"></script>
    <script src="assets/js/plugins/bootstrap.min.js"></script> -->

    <!-- Plugins JS -->
    <!-- <script src="assets/js/plugins/swiper-bundle.min.js"></script>
    <script src="assets/js/plugins/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/plugins/jquery.nice-select.min.js"></script>
    <script src="assets/js/plugins/video-playlist.js"></script>
    <script src="assets/js/plugins/ajax-contact.js"></script> -->

    <!--====== Use the minified version files listed below for better performance and remove the files listed above ======-->
    <script src="assets/js/plugins.min.js"></script>


    <!-- Main JS -->
    <script src="assets/js/main.js"></script>
	
</body>
</html>