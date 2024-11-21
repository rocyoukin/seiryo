/**
 * 主界面商品遍历
 */
$(function(){
	
	$("#button_borrow").click(function(){
		var bookId = $(this).prev("input").val();
		$.ajax({
			url:"/javaWeb_xhsd/BookServlet?flag=borrowBookById",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(data){
				if(date = 1){
					alert("恭喜您，借阅成功！请开始阅读吧！");
					window.location.href = "/javaWeb_xhsd/LogServlet?flag=selectAllbook_LogList";
				}else if(date = 2){
					alert("请先登录后再借阅吧！");
					window.location.href = "/javaWeb_xhsd/login.jsp";
				}else{
					alert("很抱歉，由于不明原因，借阅失败。");
				}
			},
			error:function(){
				alert("网络错误，请稍后再试！");
			}
		});
	});
	
	$("#button_return").click(function(){
		var bookId = $(this).prev("input").val();
		$.ajax({
			url:"/javaWeb_xhsd/BookServlet?flag=returnBookById",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(data){
				if(date = 2){
					alert("恭喜您，归还成功！");
					window.location.href = "/javaWeb_xhsd/LogServlet?flag=selectAllbook_LogList";
				}else{
					alert("很抱歉，由于不明原因，归还失败。");
				}
			},
			error:function(){
				alert("网络错误，请稍后再试！");
			}
		});
	});
	
});