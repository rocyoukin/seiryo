<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>添加图书</title>
		<link rel="stylesheet" href="css/Text.css" />
	</head>
	<body>
		<div class="all">
      		<div class="reg">
      			<h3>添加图书</h3>
   				<div class="input">
     				<span>名称: </span>
     				<input type="text" name="bookName" id="bookName" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>介绍: </span>
     				<input type="text" name="bookType" id="bookType" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>封面: </span>
     				<input type="text" name="bookImg" id="bookImg" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>种类: </span>
     				<input type="text" name="bookT" id="bookT" value="" class="input-text" />
   				</div>
   				<div class="text-center">
      				<input type="button" value="保存并提交" class="tijiao" id="insertBook" />
         		</div>
      		</div>
   		</div>
   		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/insertBook.js"></script>
	</body>
</html>