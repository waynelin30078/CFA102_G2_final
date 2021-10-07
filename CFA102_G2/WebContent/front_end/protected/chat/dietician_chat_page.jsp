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



#statusOutput {
font-size: 1.3rem;
font-weight: bold;
}


.dot {
  height: 10px;
  width: 10px;
  background-color: #CDCDCD;
  border-radius: 50%;
  display: inline-block;
  margin-top: 3px;
  margin-left: 5px;
}

.chat_list:hover {
background-color: #03b1fc;
cursor: pointer;
}

.chat_list{
z-index: 999;
}



.chat_unread {
display: float;
float: right;
color: red;
}

.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow-y: auto;
  width: 40%; 

}

.top_spac{ margin: 20px 0 0;}

.recent_heading {float: left; width:40%;}

.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}

.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}

.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}

.chat_box_container {
	width: 100%;
	height: 50%;
	background-color: white;
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

.active_chat{ background:#03b1fc;}

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
float: left;
  padding: 30px 15px 5px 25px;
 border-left:1px solid #c4c4c4;
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
	
  width: 100%;
  
}



.msg_send_btn {
  background: #0084ff none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: relative;
  left: 93%;
  bottom: 43px;
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
<div class="container this_page">
 
 <h3>訂閱客戶線上諮詢</h3>
 <br>
 <div id="chat_box_container" class="chat_box_container">

<div class="messaging">
      <div class="inbox_msg row">
         <div class="inbox_people col-4">
訂閱客戶
          	<div class="inbox_chat">

         	</div>
        </div>
        <div  id="messagesArea" class="mesgs col-7">
         <p id="statusOutput" class="text-center">點選會員列表以開始進行對談</p>
         
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
 
</div>




</div>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<%@ include file="/front_end_example/footer_link.jsp" %>
<!-- *************每一頁body最後面都要include這個footer連結******************* -->
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<%@ include file="/front_end_example/js_link.jsp" %>
<!-- *************每一頁body最後面都要include這個js連結******************* -->
<script>





var MyPoint = "/ChatWithDietician/${dieticianVO2.daccount}";   //用EL接NameServlet傳過來的req.setAttribute("userName", userName);
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));   //indexOf('/', 1), 後面的1指的是從path的第一個index(W)開始找起, 不然會找到前面最一開始的/而不是/chat.do那個"/"
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint+ '?dieticianSide';


var statusOutput = document.getElementById("statusOutput");  //顯示聊天對象的地方
var msg_history = document.getElementById("msg_history");  //聊天視窗
var self = '${dieticianVO2.daccount}';   //用EL接的userName
var webSocket;    //在connect()執行的時候一併註冊webSocket的各種事件對應

function connect() {     
	// create a websocket
	webSocket = new WebSocket(endPointURL);     //自己上線, 開一個ws session
	webSocket.onopen = function(event) {      //event是js websocket實做好onopen得到的event object
		console.log("Connect Success!");
	};
	
	webSocket.onmessage = function(event) {       //這邊可以視為前端的controller
		var jsonObj = JSON.parse(event.data);     //event裡面放什麼是websocket在js實作好的event object內容
		//var messages = JSON.parse(jsonObj.message);
		
		
		if ("open" === jsonObj.type) {		//  equals.(action)  有人上線觸發後端onOpen事件時, 這邊就會收到一個type是open的State.java物件
			
			if(Array.isArray(jsonObj.users)){
				refreshClientList(jsonObj.users);
			}
			
			//clientListInfo();
			
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
					//msg_history.scrollTop = msg_history.scrollHeight;
				
				}else {
					var chat_div = document.createElement('div'); 
					chat_div.className='incoming_msg';
					
					
					var recrive_msg_div_img = document.createElement('div')
					recrive_msg_div_img.className='incoming_msg_img';
					
					var recrive_img = document.createElement('img')

					recrive_img.src='<%=request.getContextPath()%>/member/member.do?action=showPhoto&mid=' + statusOutput.textContent;
					
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
					//msg_history.scrollTop = msg_history.scrollHeight;
				}
			}
			
			
			if(statusOutput.textContent === jsonObj.receiver){
			$('.chat_unread.' +jsonObj.receiver).text('');
			}
		
			if(msg_history.scrollTop < 1){   //滾到中間不會被強制推到最下面
			msg_history.scrollTop = msg_history.scrollHeight;
			}
	
			
		
		} else if ("chat" === jsonObj.type && (statusOutput.textContent === jsonObj.sender ||(statusOutput.textContent === jsonObj.receiver))) {   

			//console.log(statusOutput.textContent === jsonObj.sender);
			
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
				
				//msg_history.scrollTop = msg_history.scrollHeight;
				
			}else {
				var chat_div = document.createElement('div');  
				chat_div.className='incoming_msg';
				
				var recrive_msg_div_img = document.createElement('div')
				recrive_msg_div_img.className='incoming_msg_img';
				
				var recrive_img = document.createElement('img')
				recrive_img.src='<%=request.getContextPath()%>/member/member.do?action=showPhoto&mid=' + statusOutput.textContent;
				
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
			//console.log('到這裡');

			if(statusOutput.textContent === jsonObj.sender){
				$('.chat_unread.' +jsonObj.sender).text('');
			} else {
				$('.chat_unread.' +jsonObj.receiver).text('');
			}
			
		
		} else if ("clientListInfo" === jsonObj.type){
						
			var info = JSON.parse(jsonObj.message);
			
			for(var i=0; i<info.length; i++ ) {
				
				var infoDetail = JSON.parse(info[i]);
				
				if($('.chat_list').hasClass(infoDetail.receiver)){
					
					
					$('.chat_date.'+infoDetail.receiver).text(infoDetail.time);
					$('p.'+infoDetail.receiver).text(infoDetail.message);
					
					if(infoDetail.isOnline === '1'){
						$('.dot.'+infoDetail.receiver).css("background-color", "green");
					} else {
						$('.dot.'+infoDetail.receiver).css("background-color", "#CDCDCD");
					}
					
					
					if(infoDetail.unreadCount >0){
						$('.chat_unread.' +infoDetail.receiver).text(infoDetail.unreadCount+'則未讀訊息');
					}
				}
			}

	
			if(statusOutput.textContent !== '點選會員列表以開始進行對談' ){
				$('.chat_unread.' +statusOutput.textContent).text('');
			}
			
			
		} else if ("close" === jsonObj.type) {
			
		}
		
	};

	webSocket.onclose = function(event) {
		console.log("Disconnected!");
	};

}





function sendMessage() {
	var inputMessage = document.getElementById("sendMessage");
	var friend = statusOutput.textContent;
	var message = inputMessage.value.trim();
	var now = new Date();
	now= moment().format('h:mma') + " | " + moment().format('MMMM.DD');
	
	if (message === "") {
		alert("Input a message");
		inputMessage.focus();
	} else if (friend === "") {
		alert("Choose a friend");
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
		
		var friend = statusOutput.textContent;
		var jsonObj = {
				"type" : "history",	
				"sender" : self,
				"receiver" : friend,
				"message" : "",
				"time" : now
				
			};
		webSocket.send(JSON.stringify(jsonObj));  
}


function checkMessage() {
	
		var friend = statusOutput.textContent;
		var jsonObj = {
			"type" : "check", 
			"sender" : self,   
			"receiver" : friend  
		};
		webSocket.send(JSON.stringify(jsonObj)); 
	}


function refreshRead() {
	
	
	
	//if(!$("#chat_box_container").is(":hidden")){
		showHistory();	 
		

		
		//checkMessage();
	//}
}


//營養師版的這裡要改==============================================
function clientListInfo() {  

		var jsonObj = {
				"type" : "clientListInfo",
				"sender" : self
			};
			webSocket.send(JSON.stringify(jsonObj)); 

}

function refreshClientList(clients) {
	
	var txt9 = '';
	
	clients.forEach(function(client) {
		
		var txt1 ='<div id="' + client + '" class="' + client + ' chat_list" onclick="updateFriendName(event.target.classList[0])">';
		var txt2 ='<div class="' + client + ' chat_people">';
		var txt3 ='<div class="' + client + ' chat_img"> <img  class="' + client + '" src="<%=request.getContextPath()%>/member/member.do?action=showPhoto&mid=' + client + '"> </div>';
		var txt4 ='<div class="' + client + ' chat_ib">';
		var txt5 ='<h5 class="' + client + '">'+ client + '<span class="' + client + ' dot"></span><span class="' + client + ' chat_date"></span></h5>';
		var txt6 =' <p style="display:inline" class="' + client + '"></p>';
		var txt7 ='<span class="' + client + ' chat_unread"></span>';
		var txt8 ='</div></div></div>';
		txt9 += ''.concat(txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8);
		
	});


	$(".inbox_people").html(txt9);
	//var msg_history = document.getElementById("msg_history");
	//msg_history.scrollTop = msg_history.scrollHeight;
}
	
function updateFriendName(name) {
	
	$('.chat_list').each(function(){
	
		if($(this).hasClass('active_chat')){
			$(this).removeClass('active_chat');
		}
	});
	
	
	statusOutput.innerHTML = name;    //聊天的對象, 會做為receiver的值	
	
	document.getElementById(name).classList.add('active_chat');
	document.getElementById('sendMessage').disabled = false;

	$('.chat_unread.' + name).text('');

	msg_history.innerHTML = '';
	
	
	showHistory();
	
}


$(document).ready(function(){
	//setInterval(checkMessage,500);
	setInterval(refreshRead,1000);
	setInterval(clientListInfo,1000);
});



</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>
</body>
</html>