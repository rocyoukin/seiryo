/**
 * 注册JS模块
 */
$(function(){
	$("#regist").click(function(){
		//获取界面信息
		var user_name = $("#userName").val();
		var user_pass = $("#password").val();
		var user_name_info = $("#userNameInfo").val();
		var bo = false;
		if(userName.length > 0&&userName.length < 20){
			//正则表达式正确判断密码
			var regPass=/^(\w){6,16}$/;
			if(regPass.test(userPass)){
				if(user_name_info.length > 0){
					bo = true;
				}else{
					alert("昵名不能为空！");
				}
			}else{
				alert("密码必须是6至15位的数字！");
			}
		}else{
			alert("用户名不能为空！");
		}
		if(bo){
			//使用ajax传递数据判断
			$.ajax({
				url:"/javaWeb_xhsd/UserServlet?flag=user_regist",
				type:"post",
				data:{"userName":user_name,"userPass":user_pass,"userNameInfo":user_name_info},
				dataType:"json",
				success:function(date){
					if(date > 0){
						alert("恭喜你注册成功，请开始登录吧！");
						window.location.href = "/javaWeb_xhsd/login.jsp";
					}else{
						alert("很抱歉，您注册失败！");
					}
				},
				error:function(){
					alert("您输入的账号或密码错误，请重新输入！");
				}
			});
		}
	});
	
});