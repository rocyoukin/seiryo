/**
 * 用户更改JS模块
 */
$(function(){
	$("#book_upDate").click(function(){
		//获取界面信息
		var bookId = $("#bookId").val();
		var bookName = $("#bookName").val();
		var bookType = $("#bookType").val();
		var bookImg = $("#bookImg").val();
		var bookT = $("#bookT").val();
		var bo = false;
		if(bookName.length > 0){
			if(bookT==1||bookT==2||bookT==3){
				bo = true
			}else{
				alert("图书类型错误！");
			}
		}else{
			alert("图书名称不能为空！");
		}
		if(bo){
			//使用ajax传递数据判断
			$.ajax({
				url:"/javaWeb_xhsd/BookServlet?flag=book_upDate",
				type:"post",
				data:{"bookId":bookId,"bookName":bookName,"bookType":bookType,"bookImg":bookImg,"bookT":bookT},
				dataType:"json",
				success:function(date){
					if(date > 0){
						alert("此图书信息更改成功！");
						window.location.href = "/javaWeb_xhsd/UserServlet?flag=select_all";
					}else{
						alert("很抱歉，此图书信息更改失败！");
					}
				},
				error:function(){
					alert("网络错误，请稍后再试！");
				}
			});
		}
	});
	
	$("#user_upDate").click(function(){
		//获取界面信息
		var userID = $("#userID").val();
		var userName = $("#userName").val();
		var userPass = $("#userPass").val();
		var userS = $("#userS").val();
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
					alert("用户状态只能为0或1！");
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
				url:"/javaWeb_xhsd/UserServlet?flag=user_upDate",
				type:"post",
				data:{"user_id":userID,"userPass":userPass,"userNameInfo":userNameInfo,"user_s":userS},
				dataType:"json",
				success:function(date){
					if(date > 0){
						alert("此用户信息更改成功！");
						window.location.href = "/javaWeb_xhsd/UserServlet?flag=select_all";
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