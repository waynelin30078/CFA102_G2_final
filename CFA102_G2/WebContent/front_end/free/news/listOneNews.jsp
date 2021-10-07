<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.news.model.*" %>>
<%
NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>

<html>

<head>
<title>營材食教</title>
<div>

	<div style="height:100px;">
<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->
	</div>

</div>

</head>

<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
    <div class="page-header">
    <h1>${newsVO.newsTitle}</h1>
</div>
<div class="article-clean">
       <div class="container">
            <div class="row">
                    <div class="text" style="width: 600px;padding-right: 13px;padding-left: 18px;text-align: justify;font-size: 15px;">
                       <div class="content" style="word-break:break-all">
                        <p style="margin-left: 0px; font-size:25px ;color:gray; border-style: none;">${newsVO.newsContent}</p>
                        </div>
                        <div style="width: 200px; height:200px;">
                        <p class="text-left">發布時間 :<fmt:formatDate value="${newsVO.newsDate}" pattern="yyyy-MM-dd"/></p>
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
</body>
</html>