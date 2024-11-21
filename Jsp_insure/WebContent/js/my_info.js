/**
 * 信息提交修改
 */
$(function(){
	$("#updateuserinfo").click(function(){
		
		//获取用户手机号、密码和充值金额
		var userPhone = $("input[name='userPhone']").val();
		var password = $("input[name='userPass']").val();
		var upMoney = $("input[name='upMoney']").val();
		
		//前端验证
		if(userPhone == null || userPhone == ""){
			alert("携帯電話番号を入力してください！");
			return false;
		}
		if(password == null || password == ""){
			alert("パスワードを入力してください！");
			return false;
		}
		if(!(/(^[1-9]\d*$)/.test(upMoney))){
			alert("半角数字を入力してください！");
			return false;
		}
		
		//调用AJAX
		$.ajax({
			type:"post",
			url:"UserServlet",
			data:{"opt":"user_upDate","password":password,"userPhone":userPhone,"upMoney":upMoney},
			dataType:"json",
			//代表 ajax 的数据提交到后台之后，后台如果成功返回到 ajax 则进入 success 否则进入 error
			success:function(dataValue){
				if(dataValue = 1){
					alert("更新成功しました。");
					window.location.href = "my_info.jsp";
				}else if(dataValue = 2){
					alert("あなたが入力した情報は間違っています！");
				}
			},
			error:function(){
				alert("ネットワークにエラーがありました。後で再試行してください！");
			}
		});
	});
});