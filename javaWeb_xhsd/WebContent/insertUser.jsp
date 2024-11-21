<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>添加用户</title>
		<link rel="stylesheet" href="css/Text.css" />
	</head>
	<body>
		<div class="all">
      		<div class="reg">
      			<h3>添加用户</h3>
   				<div class="input">
     				<span>用户账号: </span>
     				<input type="text" name="userName" id="userName" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>用户密码: </span>
     				<input type="text" name="userPass" id="userPass" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>用户类型: </span>
     				<input type="text" name="userT" id="userT" value="" class="input-text" />
   				</div>
   				<div class="input">
     				<span>用户昵名: </span>
     				<input type="text" name="userNameInfo" id="userNameInfo" value="" class="input-text" />
   				</div>
   				<div class="text-center">
      				<input type="button" value="保存并提交" class="tijiao" id="insertUser" />
         		</div>
      		</div>
   		</div>
   		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/insertUser.js"></script>
	</body>
</html>