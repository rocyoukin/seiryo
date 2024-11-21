<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>更改用户信息</title>
		<link rel="stylesheet" href="css/admin.css" />
	</head>
	<body>
		<div class="all">
      		<div class="reg">
      			<h3>修改用户信息</h3>
      			<div class="input">
     				<span>用户编号: </span>
   					<input type="text" id="userID" id="userID" value="${upDateUser.getUserID()}" class="input-text" readonly="readonly" />
   				</div>
   				<div class="input">
     				<span>用户账号: </span>
     				<input type="text" name="userName" id="userName" value="${upDateUser.getUserName()}" class="input-text" readonly="readonly" />
   				</div>
   				<div class="input">
     				<span>用户密码: </span>
     				<input type="password" name="userPass" id="userPass" value="${upDateUser.getUserPass()}" class="input-text" />
   				</div>
   				<div class="input">
     				<span>用户状态: </span>
     				<input type="text" name="userS" id="userS" value="${upDateUser.getUserS()}" class="input-text" />
   				</div>
   				<div class="input">
     				<span>用户昵名: </span>
     				<input type="text" name="userNameInfo" id="userNameInfo" value="${upDateUser.getUserNameInfo()}" class="input-text" />
   				</div>
   				<div class="text-center">
      				<input type="button" value="保存并提交" class="tijiao" id="user_upDate" />
         		</div>
      		</div>
   		</div>
   		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/upDate.js"></script>
	</body>
</html>