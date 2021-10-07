<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dietician.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="com.meal.model.*"%>	
<%@ page import="com.member.model.*"%>
<%@ page import="java.sql.Date"%>
	
<% 

MemberVO member = (MemberVO)session.getAttribute("memberVO1");

DiaryService diarySvc= new DiaryService();
List<DiaryVO> diaries = diarySvc.findByMember(member.getMno());
request.setAttribute("diaries", diaries);

ArrayList<Date> date = new ArrayList<Date>();

for (DiaryVO diary : diaries) {
	date.add(diary.getDiaryDate());
}

request.setAttribute("date", date);


Date lastDate = new Date(Calendar.getInstance().getTime().getTime());


if(session.getAttribute("diary") != null){
	DiaryVO lastDiary = (DiaryVO)session.getAttribute("diary");
	lastDate = lastDiary.getDiaryDate();
	session.removeAttribute("diary");
}

request.setAttribute("lastDate", lastDate);

%>	
	
	
	
	
<!DOCTYPE html>
<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp"%>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
  <!-- jQuery v1.9.1 -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js"></script>	<!-- fullcalendar  CDN -->
  <!-- Moment.js v2.20.0 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.0/moment.min.js"></script>	<!-- fullcalendar  CDN -->
  <!-- FullCalendar v3.8.1 -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.css" rel="stylesheet"  />    <!-- fullcalendar  CDN -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.print.css" rel="stylesheet" media="print">	<!-- fullcalendar  CDN -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/fullcalendar.min.js"></script>	<!-- fullcalendar  CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.8.1/locale-all.js"></script>	<!-- fullcalendar  CDN -->
	
	
<style>

.fc-day {

height: 100%;
z-index: 5;
}

.fc-day:hover{
background-color: #fcf8e3;
}

td.fc-day-top:hover {
background-color: #fcf8e3;
}

.fc-content {

color: black;
padding-top: 3px;
font-size: 16px;
height: 30px;
text-align: center;
background-color: #aac7fe;
border: none;
}

.fc-content:hover {
background-color: #007bff;
}



.fc-event {
    position: relative;
    display: block;
    font-size: .85em;
	border: none;
    border-radius: 3px;
    background-color: #aac7fe;
    
}




</style>



</head>


<body>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<%@ include file="/front_end_example/header_link.jsp"%>
	<!-- *************每一頁body最前面都要include這個header連結******************* -->
	<div class="container this_page">
		<!-- 服務很好先做一個置中的div(開頭) -->

				

 <div id="nutrition-diary"></div>
 
 
 
  <script>
  var current_date = moment().format('YYYY-MM-DD')	
  $( "#nutrition-diary" ).fullCalendar({
  		// 參數設定[註1]
  		header: { // 頂部排版
  			left: "prev,next today", // 左邊放置上一頁、下一頁和今天
  			center: "title", // 中間放置標題
  			right: "" // 右邊放置月、周、天
  		},
  	    dayClick: function(date) {	    	
  	    	
  	    	
  	    	
  	    	if('${date}'.includes(date.format())){
  	    		
  	    		window.location="<%=request.getContextPath()%>/diary/diary.do?action=show_diary_page&date=" + date.format();
				//diary_page_included.jsp?mno=${member.mno}&date=date.format()																
  	    		//這邊給他DiaryVO findByDate(int mno, Date diaryDate)
  	    	} else {
  	    		var yes = confirm('新增飲食日記?');
  	    		if (yes && date.format()<=current_date) {
  	    			window.location="<%=request.getContextPath()%>/diary/diary.do?action=add_diary&date=" + date.format();
  	    		} else if(date.format()>current_date){
  	    			alert('不能新增未來日記');
  	    		}
  	    	}
  	    	
  	    },
  		defaultDate: "${lastDate}", // 起始日期
  		weekends: true, // 顯示星期六跟星期日
  		editable: false,  // 啟動拖曳調整日期
  		locale: 'zh-tw',
  		events: [ // 事件
  			<c:forEach var="diary" items="${diaries}" varStatus="s">
	  			
	
  				{ // 事件(設定連結)
	  				title: "查看飲食日記",
	  				url: "<%=request.getContextPath()%>/diary/diary.do?action=show_diary_page&date=${diary.diaryDate}",
	  				start: "${diary.diaryDate}"
	  			},

        	</c:forEach>
  			
  		]
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