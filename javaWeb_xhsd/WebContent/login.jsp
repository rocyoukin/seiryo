<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户登录</title>
		<link rel="stylesheet" href="css/Text.css" />
	</head>
	<body>
 		<div class="all">
        	<div class="log">
        		<div class="register">
           			<div class="register-top-grid">
           				<h3>用户登录</h3>
           				<div class="input">
             				<span>用户名 <label style="color: red">* </label></span>
           					<input type="text" id="userName" value="" class="input-text" />
           				</div>
           				<div class="input">
             				<span>密码 <label style="color: red">*</label></span>
             				<input type="password" id="password" value="" class="input-text" />
           				</div>
           			</div>
           			<div class="text-center">
        				<input type="button" value="提交" class="tijiao" id="login" />
           			</div>
       			</div>
      		</div>
      		<div class="reg">
        		<div class="reg_1">
	          		<h2>没有账号？</h2>
	          		<p>立即注册加入我们吧，和我们一起开启旅程吧</p>
	          		<a href="/javaWeb_xhsd/regist.jsp"><button type="button" class="sig">注册</button></a><br />
	          		<h2>如果你是管理员？</h2>
	          		<a href="/javaWeb_xhsd/loginAdmin.jsp"><button type="button" class="sil">管理员登录</button></a>
        		</div>
      		</div>
		</div>
		<div id="foot" align="center">
			<h5 class="h51">客服热线：4006666505</h5>
			<h5 class="h52">京ICP证：京B2—20171652号 &nbsp;&nbsp; Copyright © 2015-2024 新华互联电子商务有限责任公司  &nbsp;版权所有</h5>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/login.js"></script>
	</body>
</html>