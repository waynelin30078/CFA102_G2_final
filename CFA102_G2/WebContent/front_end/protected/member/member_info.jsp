<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.member.model.*" %>
    <%@ page import ="java.util.*"%>
    <%@ page import="com.dietician.model.*" %>
<%
	String mid = (String)session.getAttribute("account");
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getOneMemberByMid(mid);
%>

<%
	DieticianService DieticianSvc = new DieticianService();
	DieticianVO dieticianVO = DieticianSvc.findByPrimaryKey(memberVO.getDno());
%>

<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
  
  
  
</style>


<!-- 表格的css -->
<style type="text/css">    



.emp-profile{
    padding: 3%;
    margin-top: 3%;
    margin-bottom: 3%;
    border-radius: 0.5rem;
	background-color: #f8f6f4;
}
.profile-img{
    text-align: center;
}
.profile-img img{
    width: 70%;
    height: 100%;
}
.profile-img .file {
    position: relative;
    overflow: hidden;
    margin-top: -20%;
    width: 70%;
    border: none;
    border-radius: 0;
    font-size: 15px;
    background: #212529b8;
}
.profile-img .file input {
    position: absolute;
    opacity: 0;
    right: 0;
    top: 0;
}
.profile-head h5{
    color: #333;
}
.profile-head h6{
    color: #0062cc;
}
.profile-edit-btn{
    border: none;
    border-radius: 1.5rem;
    width: 70%;
    padding: 2%;
    font-weight: 600;
    color: #6c757d;
    cursor: pointer;
}
.proile-rating{
    font-size: 12px;
    color: #818182;
    margin-top: 5%;
}
.proile-rating span{
    color: #495057;
    font-size: 15px;
    font-weight: 600;
}
.profile-head .nav-tabs{
    margin-bottom:5%;
}
.profile-head .nav-tabs .nav-link{
    font-weight:600;
    border: none;
}
.profile-head .nav-tabs .nav-link.active{
    border: none;
    border-bottom:2px solid #0062cc;
}
.profile-work{
    padding: 14%;
    margin-top: -15%;
}
.profile-work p{
    font-size: 12px;
    color: #818182;
    font-weight: 600;
    margin-top: 10%;
}
.profile-work a{
    text-decoration: none;
    color: #495057;
    font-weight: 600;
    font-size: 14px;
}
.profile-work ul{
    list-style: none;
}
.profile-tab label{
    font-weight: 600;
}
.profile-tab p{
    font-weight: 600;
    color: #0062cc;
}
.col-md-6{
    display: flex;
    justify-content: center; 
    align-items: center; 
}

.cancel{
position: relative;
    left: 500px;
}
</style>
</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->

<div class="container emp-profile">
            <form method="post" ACTION="<%=request.getContextPath()%>/member/member.do">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="<%=request.getContextPath()%>/member/member.do?action=showPhoto&photo=<%=memberVO.getMno()%>" alt=""/>

                        </div>
                    </div>
<!--                     右上的DIV -->

                    <div class="col-md-2">
<!--                     	form傳到controller的值設定 -->
                    	  <input type="hidden" name="mno"  value="<%=memberVO.getMno()%>">
			    	      <input type="hidden" name="action"	value="getOne_For_Update">
                          <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                    
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
<!--                             <p>WORK LINK</p> -->
<!--                             <a href="">Website Link</a><br/> -->
<!--                             <a href="">Bootsnipp Profile</a><br/> -->
<!--                             <a href="">Bootply Profile</a> -->
<!--                             <p>SKILLS</p> -->
<!--                             <a href="">Web Designer</a><br/> -->
<!--                             <a href="">Web Developer</a><br/> -->
<!--                             <a href="">WordPress</a><br/> -->
<!--                             <a href="">WooCommerce</a><br/> -->
<!--                             <a href="">PHP, .Net</a><br/> -->
<br>
<br>
							            <div class="row">
                                            <div class="col-md-6">
                                                <label>信用卡卡號</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getCardID()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>信用卡到期日</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getCardDate()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>信用卡驗證碼</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getCardNum()%></p>
                                            </div>
                                        </div>
							
							
							
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>會員編號</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMno()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>會員姓名</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMname()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>會員帳號</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMid()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>e-mail</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMmail()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMphone()%></p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>自我介紹</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p><%=memberVO.getMintro()%></p>
                                             
                                            </div>
                                        </div>
</form>       
<form method="post" ACTION="<%=request.getContextPath()%>/dietician/dietician.do">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>專屬營養師</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>
                                                <%=dieticianVO.getDname() %>
                                                </p>
                                                 <select name="updateScore">
  												<option value="1">1</option>
    											<option value="2">2</option>
   											    <option value="3">3</option>
   											    <option value="4">4</option>
    											<option value="5">5</option>
												</select>
												<input type="hidden" name="dno" value="<%=dieticianVO.getDno()%>">
												<input type="hidden" name="action" value="updateTotalScore">
												<input type="submit" value="給予評分">
                                            </div>
                                        </div>
</form>
		<form method="post" action="<%=request.getContextPath()%>/dorder.do">
						<input type="hidden" name="action" value="closeOrder">
						<input type="hidden" name="dno" value="<%=dieticianVO.getDno() %>">
						<c:if test="${memberVO.mno!=null }"> 
						<input type="hidden" name="mno" value="<%=memberVO.getMno() %>">
						</c:if>
						<input type="hidden" name="mthFee" value="<%=dieticianVO.getMprice() %>">
						<input type="hidden" name="autoSubs" value="0">
						<input class="cancel" type="submit" value="取消訂閱營養師">						
						</form>
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Experience</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Hourly Rate</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>10$/hr</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Total Projects</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>230</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>English Level</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Availability</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>6 months</p>
                                            </div>
                                        </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                   
        </div>













<!-- 服務很好先做一個置中的div(結束) -->
</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
</body>
</html>