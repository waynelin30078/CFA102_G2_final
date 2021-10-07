<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.artice.model.*"%>


<%
ArticeVO articeVO = (ArticeVO) request.getAttribute("articeVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>文章資料新增</title>
<%@ include file="/back_end_example/head_include.jsp"%>
</head>
<body class="bg-gradient-primary">
<%@ include file="/back_end_example/body_include.jsp"%>

<div class="container">
  <div class="card o-hidden border-0 shadow-lg my-5">
     <div class="card-body p-0">
        <div class="row">
           <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
              <div class="col-lg-7">
                 <div class="p-5">
                        
                        <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

       <div class="">
         <h3 class="h1 text-gray-900 mb-4">新增文章</h3>
       </div>
                            
                       
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/artice/artice.do" name="form1" enctype="multipart/form-data" >
                          
           <div class="">文章標題
         	<input type="text" style=width:400px; id="articTitle" name="articTitle" oninvalid="alert('Oh!標題沒有填寫!');" required
                   value="<%=articeVO==null ? "" : articeVO.getArticTitle() %>"></div>
                          
           <div class="user">文章內容
            <input type="text" style=width:400px;height:100px id="articContent" name="articContent1" style="height:400px ;width:400px; word-break:break-all" oninvalid="alert('Oh!內容沒有填寫!');" required
                   value="<%=articeVO==null ? "" : articeVO.getArticContent() %>">
           </div>
							
	    	<div>
			<input type="radio" name="articType" value="1" />研究
			<input type="radio" name="articType" value="2" />食物
			</div>
							
     

            <div class="">文章圖片
            <input type="file" id="upload" onchange="loadImageFile(event)" accept="image/gif, image/jpeg, image/png" name="articPhoto1" oninvalid="alert('Oh! 連照片沒有上傳!!!');" required
                   value="<%=articeVO==null ? "" : articeVO.getArticPhoto() %>">
    		<img id="image" src="" style="height:300px; weight:400px" > 
      		</div> 

   
            <input type="hidden" name="action" value="insert">
            <input type="submit"  value="送出新增">
    </FORM>
                      
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/back_end_example/foot_include.jsp"%>
</body>

<script>

// 上傳圖片預覽   
function loadImageFile(event){
	var image = document.getElementById('image');
	image.src = URL.createObjectURL(event.target.files[0]); 
	}; 

</script>
</html>