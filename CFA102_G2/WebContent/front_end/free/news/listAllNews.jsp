<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
    NewsService newsSvc = new NewsService();
    List<NewsVO> list = newsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>

<head>
<title>營材食教</title>

<!-- *************每一頁head裡面都要include這個css連結******************* -->
<%@ include file="/front_end_example/CSS_link.jsp" %>
<!-- *************每一頁head裡面都要include這個css連結******************* -->

</head>


<body>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<%@ include file="/front_end_example/header_link.jsp" %>
<!-- *************每一頁body最前面都要include這個header連結******************* -->
<div class="container this_page">
<!-- 服務很好先做一個置中的div(開頭) -->

<table>
	<tr>
		<th>文章發佈日期</th>
		<th>文章標題</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="NewsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td><fmt:formatDate value="${NewsVO.newsDate}" pattern="yyyy-MM-dd"/></td>
			<td>${NewsVO.newsTitle}</td>
			
	
			<td>
			  <div class="card-body">
                <a href="<%=request.getContextPath()%>/news/news.do?action=getOne_For_Display2&newsNo=${NewsVO.newsNo}" class="btn btn-primary">查看詳情</a>
            
              </div>
			</td>
		</tr>
	</c:forEach>
</table>

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