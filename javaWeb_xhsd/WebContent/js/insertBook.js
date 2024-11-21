/**
 * 添加用户JS模块
 */
$(function(){
	$("#insertBook").click(function(){
		//获取界面信息
		var bookName = $("#bookName").val();
		var bookType = $("#bookType").val();
		var bookImg = $("#bookImg").val();
		var bookT = $("#bookT").val();
		var bo = false;
		if(bookName!=null){
			if(bookT==1||bookT==2||bookT==3){
				bo = true;
			}else{
				alert("图书类型有误！");
			}
		}else{
			alert("书名不能为空！");
		}
		if(bo){
			//使用ajax传递数据判断
			$.ajax({
				url:"/javaWeb_xhsd/BookServlet?flag=insertBook",
				type:"post",
				data:{"bookName":bookName,"bookType":bookType,"bookImg":bookImg,"bookT":bookT},
				dataType:"json",
				success:function(date){
					if(date = 1){
						alert("图书添加成功！");
						window.location.href = "/javaWeb_xhsd/UserServlet?flag=select_all";
					}else{
						alert("很抱歉，图书添加失败！");
					}
				},
				error:function(){
					alert("网络错误，请稍后再试！");
				}
			});
		}
	});
});