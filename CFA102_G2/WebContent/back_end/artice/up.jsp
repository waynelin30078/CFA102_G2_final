<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>


<!-- JavaScript & jQuery 版本-->

<!-- HTML part -->

<form action="/somewhere/to/upload" enctype="multipart/form-data">

   <input type="file" id="progressbarTWInput" accept="image/gif, image/jpeg, image/png" />

   <img id="preview_progressbarTW_img" src="#" />

</form>

<!-- JavaScript part -->


</body>
<script>

$("#progressbarTWInput").change(function(){

  readURL(this);

});

 

function readURL(input){

  if(input.files && input.files[0]){

    var reader = new FileReader();

    reader.onload = function (e) {

       $("#preview_progressbarTW_img").attr('src', e.target.result);

    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>
</html>