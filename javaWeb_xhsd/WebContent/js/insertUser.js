/**
 * 添加用户JS模块
 */
$(function(){
	$("#insertUser").click(function(){
		//获取界面信息
		var userName = $("#userName").val();
		var userPass = $("#userPass").val();
		var userT = $("#userT").val();
		var userNameInfo = $("#userNameInfo").val();
		var bo = false;
		if(userName.length > 0&&userName.length < 20){
			//正则表达式正确判断密码
			var regPass=/^(\w){6,16}$/;
			if(regPass.test(userPass)){
				if(userS==0||userS==1){
					if(userNameInfo.length > 0){
						bo = true;
					}else{
						alert("昵名不能为空！");
					}
				}else{
					alert("用户类型只能为0或1！");
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
				data:{"userName":userName,"userPass":userPass,"userNameInfo":userNameInfo,"userT":userT},
				dataType:"json",
				success:function(date){
					if(date = 1){
						alert("用户添加成功！");
						window.location.href = "/javaWeb_xhsd/UserServlet?flag=select_all";
					}else if(date = 2){
						alert("很抱歉，您的权限不足！");
					}else{
						alert("很抱歉，此用户信息更改失败！");
					}
				},
				error:function(){
					alert("网络错误，请稍后再试！");
				}
			});
		}
	});
});