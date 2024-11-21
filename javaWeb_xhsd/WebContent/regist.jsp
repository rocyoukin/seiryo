<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册账号</title>
		<link rel="stylesheet" href="css/Text.css" />
	</head>
	<body>
		<div class="all">
      		<div class="reg">
        		<div class="reg_1">
          			<h2>已有账号？</h2>
          			<p>请使用你的账号进行登录！</p>
          			<a href="/javaWeb_xhsd/login.jsp"><button type="button" class="sig">登录</button></a>
		        </div>
	      	</div>
      		<div class="log">
        		<div>
          			<div class="register" id="tiao">
              			<div class="register-top-grid">
                			<h3>注册新用户</h3>
                			<div class="input">
                				<span>用户名 <label style="color: red">* </label></span>
                  				<input class="ll" type="text" value="" id="userName" />
                			</div>
                			<div class="input">
                				<span>密码 <label style="color: red">* </label></span>
                  				<input class="ll" type="password" value="" id="password" />
                			</div>
                			<div class="input">
                				<span>昵名 <label style="color: red">* </label></span>
                  				<input class="ll" type="text" value="" id="userNameInfo" />
                			</div>
                			<div class="clearfix"></div>
              			</div>
	              		<div class="text-center">
	                		<input type="button" value="注册" id="regist" />
	              		</div>
          			</div>
       			</div>
			</div>
    	</div>
    	<div id="foot" align="center">
			<h5 class="h51">客服热线：4006666505</h5>
			<h5 class="h52">京ICP证：京B2—20171652号 &nbsp;&nbsp; Copyright © 2015-2024 新华互联电子商务有限责任公司  &nbsp;版权所有</h5>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    	<script type="text/javascript" src="js/regist.js"></script>
	</body>
</html>