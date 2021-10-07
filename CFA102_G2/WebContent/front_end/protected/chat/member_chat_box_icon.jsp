<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.dietician.model.*"%>
<%@ page import="java.util.*"%>

<%

DieticianService dieticianSvc = new DieticianService();
request.setAttribute("dieticianSvc", dieticianSvc);

%>




<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
<style>

.chat_icon{
	width: 65px;
	position: fixed;
	bottom:  80px;
	right : 50px;
	z-index: 6;
	cursor: pointer;
}

#unread {
	width: 90px;
	position: fixed;
	bottom:  138px;
	right : 30px;
	z-index: 6;
	color: red;
	font-weight:bold;

}



.chat_box_container {
	width: 30%;
	position: fixed;
	bottom:  142px;
	right : 50px;
	background-color: white;
	border: 1px solid grey;
	border-radius: 10px;
	z-index: 5;
}

.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}

.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}

.mesgs {
	
  padding: 30px 15px 5px 25px;
 
}

  #statusOutput {
  font-weight: bold;
  color: grey;
  }


 .sent_msg p {
  background: #40ff00 none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}

.sent_msg>p {
color: black;
}


.sent_msg {
  float: right;
  width: 46%;
}

.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
  
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}

.msg_send_btn {
  background: #0084ff none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 20px;
  top: 30px;
  width: 33px;
}

.messaging { padding: 0 0 5px 0;}

.msg_history {
  height: 400px;
  overflow-y: auto;
}		

</style>

</head>


<body onload="connect();">
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
        <!-- Slider Section Start -->
        <div class="slider-section slider-active section">
            <div class="swiper-container">
                <div class="swiper-wrapper">

                    <!-- Single Slider Start -->
                    <div class="swiper-slide animation-style-01 single-slider d-flex align-items-center" style="background-image: url(<%= request.getContextPath() %>/front_end_example/images/slider-1.jpg);">
                        <div class="container">

                            <!-- Slider Content Start -->
                            <div class="slider-content">
                                <h6 class="sub-title">Fitness & Nutrition</h6>
                                <h1 class="main-title">This life style for your fitness, not only diet.</h1>
                                <p>It has survived not only five centuries but also</p>
                                <a href="#" class="btn btn-primary btn-hover-secondary">Start Course</a>
                            </div>
                            <!-- Slider Content End -->

                        </div>

                        <!-- Slider Social Start -->
                        <div class="slider-social">
                            <div class="container">
                                <div class="social-wrapper">
                                    <p>Connect with us:</p>
                                    <ul class="social">
                                        <li><a href="#"><i class="icofont-facebook"></i></a></li>
                                        <li><a href="#"><i class="icofont-skype"></i></a></li>
                                        <li><a href="#"><i class="icofont-twitter"></i></a></li>
                                        <li><a href="#"><i class="icofont-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- Slider Social End -->
                    </div>
                    <!-- Single Slider End -->
                </div>
            </div>
        </div>
        <!-- Slider Section End -->


 <div id="chat_box_container" class="chat_box_container" style="display:none;">
<h3 id="statusOutput" class="text-center">專屬營養師－${dieticianSvc.findByPrimaryKey(memberVO1.dno).dname}(不在線)</h3>
<div class="messaging">
      <div class="inbox_msg">
        <div  id="messagesArea" class="mesgs">
         
         
          <div id="msg_history" class="msg_history">

          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input id="sendMessage" type="text" class="write_msg" placeholder="Type a message" onkeydown="if (event.keyCode == 13) sendMessage();" disabled/>
              <button class="msg_send_btn" type="button" onclick="sendMessage();"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
            </div>
          </div>
        </div>
      </div>
    </div>
   </div>
 


<img id="chat_icon" class="chat_icon" src="<%=request.getContextPath()%>/front_end_example/images/msn-icon.png" alt="">
<p id="unread"></p>


</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<script>


$("#chat_box_container").hide();

$("#chat_icon").click(function() {
	
	if($("#chat_box_container").is(":hidden")){
	$("#chat_box_container").show();
	showHistory();
	var showUnread = document.getElementById("unread");
	showUnread.innerHTML='';
	}else {
		$("#chat_box_container").hide();
	}
});

var MyPoint = "/ChatWithDietician/${memberVO1.mid}";   //用EL接NameServlet傳過來的req.setAttribute("userName", userName);
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));   //indexOf('/', 1), 後面的1指的是從path的第一個index(W)開始找起, 不然會找到前面最一開始的/而不是/chat.do那個"/"
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint + '?memberSide';


var statusOutput = document.getElementById("statusOutput");  //顯示聊天對象的地方
var msg_history = document.getElementById("msg_history");  //聊天視窗
var self = '${memberVO1.mid}';   //用EL接的userName
var webSocket;    //在connect()執行的時候一併註冊webSocket的各種事件對應

function connect() {     
	// create a websocket
	webSocket = new WebSocket(endPointURL);     //自己上線, 開一個ws session
	webSocket.onopen = function(event) {      //event是js websocket實做好onopen得到的event object
		console.log("Connect Success!");
		document.getElementById('sendMessage').disabled = false;
	};
	
	webSocket.onmessage = function(event) {       //這邊可以視為前端的controller
		var jsonObj = JSON.parse(event.data);     //event裡面放什麼是websocket在js實作好的event object內容
		//var messages = JSON.parse(jsonObj.message);
		
		
		if ("open" === jsonObj.type) {		//  equals.(action)  有人上線觸發後端onOpen事件時, 這邊就會收到一個type是open的State.java物件
			statusOutput.style.color = "green";
			statusOutput.innerHTML = '專屬營養師－${dieticianSvc.findByPrimaryKey(memberVO1.dno).dname}(在線中)';
			showUnreadCount();
			
		} else if ("history" === jsonObj.type) {    //show出之前的對話紀錄, 延續對話
			var messages = JSON.parse(jsonObj.message);
			msg_history.innerHTML = '';
			
			for (var i = 0; i < messages.length; i++) {
		
				var historyData = JSON.parse(messages[i]);
		
				if(historyData.sender === self){     
				
					var chat_div = document.createElement('div');  
					chat_div.className='outgoing_msg';
					
					
					var sent_msg_div = document.createElement('div')
					sent_msg_div.className='sent_msg';
					
					var message_sent = document.createElement('p')
					message_sent.innerHTML = historyData.message;
					
					var message_time = document.createElement('span')
					
					if(historyData.readState !== undefined){
					message_time.innerHTML = historyData.time + historyData.readState;
					}else {
					message_time.innerHTML = historyData.time;
					}
					
					message_time.className='time_date';
					
					msg_history.appendChild(chat_div);
					chat_div.appendChild(sent_msg_div);
					sent_msg_div.appendChild(message_sent);
					sent_msg_div.appendChild(message_time);
				
				}else {
					var chat_div = document.createElement('div'); 
					chat_div.className='incoming_msg';
					
					
					var recrive_msg_div_img = document.createElement('div')
					recrive_msg_div_img.className='incoming_msg_img';
					
					var recrive_img = document.createElement('img')
					recrive_img.src='<%=request.getContextPath()%>${dieticianSvc.findByPrimaryKey(memberVO1.dno).dpic}';
					
					
					var recrive_msg = document.createElement('div')
					recrive_msg.className='received_msg';
					
					var received_withd_msg = document.createElement('div')
					received_withd_msg.className='received_withd_msg';
					
					var message_sent = document.createElement('p')
					message_sent.innerHTML = historyData.message;
					
					var message_time = document.createElement('span')
					message_time.innerHTML = historyData.time;
					
					msg_history.appendChild(chat_div);
					chat_div.appendChild(recrive_msg_div_img);
					chat_div.appendChild(recrive_msg);
					recrive_msg_div_img.appendChild(recrive_img);
					recrive_msg.appendChild(received_withd_msg);
					received_withd_msg.appendChild(message_sent);
					received_withd_msg.appendChild(message_time);
				}
			}
			
			if(msg_history.scrollTop < 1){   //滾到中間不會被強制推到最下面
			msg_history.scrollTop = msg_history.scrollHeight;
			}
	
		
		} else if ("chat" === jsonObj.type) {   

			
			if(jsonObj.sender === self){     
				
				var chat_div = document.createElement('div');  
				chat_div.className='outgoing_msg';
				
				
				var sent_msg_div = document.createElement('div')
				sent_msg_div.className='sent_msg';
				
				var message_sent = document.createElement('p')
				message_sent.innerHTML = jsonObj.message;
				
				var message_time = document.createElement('span')
				message_time.innerHTML = jsonObj.time
				message_time.className='time_date';
				
				msg_history.appendChild(chat_div);
				chat_div.appendChild(sent_msg_div);
				sent_msg_div.appendChild(message_sent);
				sent_msg_div.appendChild(message_time);
			
			}else {
				var chat_div = document.createElement('div');  
				chat_div.className='incoming_msg';
				
				
				var recrive_msg_div_img = document.createElement('div')
				recrive_msg_div_img.className='incoming_msg_img';
				
				var recrive_img = document.createElement('img')
					recrive_img.src='<%=request.getContextPath()%>${dieticianSvc.findByPrimaryKey(memberVO1.dno).dpic}';
				
				var recrive_msg = document.createElement('div')
				recrive_msg.className='received_msg';
				
				var received_withd_msg = document.createElement('div')
				received_withd_msg.className='received_withd_msg';
				
				var message_sent = document.createElement('p')
				message_sent.innerHTML = jsonObj.message;
				
				var message_time = document.createElement('span')
				message_time.innerHTML = jsonObj.time;
				
				msg_history.appendChild(chat_div);
				chat_div.appendChild(recrive_msg_div_img);
				chat_div.appendChild(recrive_msg);
				recrive_msg_div_img.appendChild(recrive_img);
				recrive_msg.appendChild(received_withd_msg);
				received_withd_msg.appendChild(message_sent);
				received_withd_msg.appendChild(message_time);
			}
		
			msg_history.scrollTop = msg_history.scrollHeight;
		
		} else if ("showUnreadCount" === jsonObj.type){
			if(jsonObj.unreadCount>0){
			var showUnread = document.getElementById("unread");
			showUnread.innerHTML=jsonObj.unreadCount+ '則未讀訊息';
			}
		} else if ("close" === jsonObj.type) {
			statusOutput.style.color = "grey";
			statusOutput.innerHTML = '專屬營養師－${dieticianSvc.findByPrimaryKey(memberVO1.dno).dname}(不在線)';
		}
		
	};

	webSocket.onclose = function(event) {
		console.log("Disconnected!");
	};

}

function sendMessage() {
	var inputMessage = document.getElementById("sendMessage");
	var friend = '${dieticianSvc.findByPrimaryKey(memberVO1.dno).daccount}';  
	var message = inputMessage.value.trim();
	var now = new Date();
	now= moment().format('h:mma') + " | " + moment().format('MMMM.DD');
	
	if (message === "") {
		alert("Input a message");
		inputMessage.focus();
	} else if (friend === "") {
		alert("您尚無專屬營養師");
	} else {
		
		var jsonObj = {
			"type" : "chat",     
			"sender" : self,   
			"receiver" : friend,  
			"message" : message,
			"time" : now
		};
		
		webSocket.send(JSON.stringify(jsonObj));   
		inputMessage.value = "";
		inputMessage.focus();
		//console.log(now);
	}
}


function showHistory() {

	var now = new Date();
	now= moment().format('h:mma') + " | " + moment().format('MMMM.DD');
		
		var friend = '${dieticianSvc.findByPrimaryKey(memberVO1.dno).daccount}';
		var jsonObj = {
				"type" : "history",	
				"sender" : self,
				"receiver" : friend,
				"message" : "",
				"time" : now
				
			};
		webSocket.send(JSON.stringify(jsonObj));  

	//console.log('listener');
}


function checkMessage() {
	
	if(!$("#chat_box_container").is(":hidden")){
	var friend = '${dieticianSvc.findByPrimaryKey(memberVO1.dno).daccount}';
	var jsonObj = {
			"type" : "check", 
			"sender" : self,   
			"receiver" : friend  

		};
		webSocket.send(JSON.stringify(jsonObj)); 
	}
}

function refreshRead() {
	
	if(!$("#chat_box_container").is(":hidden")){
		showHistory();	 
	}
}

function showUnreadCount() {
	
	if($("#chat_box_container").is(":hidden")){
		var friend = '${dieticianSvc.findByPrimaryKey(memberVO1.dno).daccount}';
		var jsonObj = {
				"type" : "showUnreadCount",     //一般聊天就丟type是chat物件java
				"sender" : self,   //self就是${userName}
				"receiver" : friend  //從statusOutput來的
			};
			webSocket.send(JSON.stringify(jsonObj)); 
		}
}


$(document).ready(function(){
	setInterval(refreshRead,500);
	setInterval(showUnreadCount,500);
});



</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>
</body>
</html>