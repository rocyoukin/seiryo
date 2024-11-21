/**
 * 登陆
 */
$(function(){
	$("#login").click(function(){
		var userName = $("#userName").val();
		var userPass = $("#password").val();
		var msg = "user";
		var bo = false;
		if(userName.length > 0&&userName.length < 20){
			//正则表达式正确判断密码
			var regPass=/^(\w){6,16}$/;
			if(regPass.test(userPass)){
				bo = true;
			}else{
				alert("密码必须为6至15为数字！");
			}
		}else{
			alert("请输入账号！");
		}
		if(bo){
			$.ajax({
				url:"/javaWeb_xhsd/UserServlet?flag=user_login",
				type:"post",
				data:{"userName":userName,"userPass":userPass,"msg":msg},
				dataType:"json",
				success:function(date){
					if(date = 1){
						alert("恭喜你登录成功，欢迎来到新华书店！");
						window.location.href = "/javaWeb_xhsd/LogServlet?flag=selectAllbook_LogList";
					}else if(date = 3){
						alert("您的账号已被冻结！");
					}else{
						alert("您输入的账号或者密码错误，请重新输入！");
					}
				},
				error:function(){
					alert("网络有误，请稍后再试！");
				}
			});
		}
	});
	
	$("#loginAdmin").click(function(){
		var userName = $("#userName").val();
		var userPass = $("#password").val();
		var msg = "admin";
		var bo = false;
		if(userName.length > 0&&userName.length < 20){
			//正则表达式正确判断密码
			var regPass=/^(\w){6,16}$/;
			if(regPass.test(userPass)){
				bo = true;
			}else{
				alert("密码必须为6至15为数字！");
			}
		}else{
			alert("请输入账号！");
		}
		if(bo){
			$.ajax({
				url:"/javaWeb_xhsd/UserServlet?flag=user_login",
				type:"post",
				data:{"userName":userName,"userPass":userPass,"msg":msg},
				dataType:"json",
				success:function(date){
					if(date = 2){
						alert("管理员，恭喜你登录成功！");
						window.location.href = "/javaWeb_xhsd/UserServlet?flag=select_all";
					}else if(date = 3){
						alert("您的账号已被冻结！");
					}else{
						alert("您输入的账号或者密码错误，请重新输入！");
					}
				},
				error:function(){
					alert("网络有误，请稍后再试！");
				}
			});
		}
	});
});