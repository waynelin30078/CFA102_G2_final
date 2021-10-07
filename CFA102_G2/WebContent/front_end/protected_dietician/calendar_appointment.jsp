<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
   
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
    <link href='<%=request.getContextPath()%>/source/fullcalendar/main.css' rel='stylesheet' />
    <script src='<%=request.getContextPath()%>/source/fullcalendar/main.js'></script>
    <script src='<%=request.getContextPath()%>/source/fullcalendar/locales-all.js'></script>
     <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
    $.ajax({
    	url:"<%=request.getContextPath()%>/apt_order/apt_order.do?action=showOrder",
    			method:"get",
    			dataType:"json",
    })
.done(
    	function (e){	
        var calendar = new FullCalendar.Calendar(calendarEl, {
    			  headerToolbar: {
   			      left: 'prev,next today',
  			      center: 'title',
		          right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
//    initialDate: '2020-09-12',
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
   	  events: e,
        });
        calendar.render();
      });
}
);

    </script>
<style>
    
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }
  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }
</style>
</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->










這邊可以開始放自己的東西了
    <div id='calendar'></div>














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