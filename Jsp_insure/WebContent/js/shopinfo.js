/**
 * shop.js文件
 */
//设置js方法来操作界面下拉框，动态设置值
function js(obj,v){
	var sum = parseFloat(obj.value) * 10;
	sessionStorage.setItem(v,sum + "");
	var sum_value = 0;
	var bo = true;
	if(getData() != ""){
		var $value = getData().split(",");
		for(var i = 0; i < $value.length; i++){
			if(v == $value[i]){
				bo = false;
				break;
			}
		}
	}
	if(bo){
		sessionStorage.setItem("dataValue",getData() + v);
	}
	if(getData() != ""){
		var $value = getData().split(",");
		for(var i = 0; i < $value.length -1; i++){
			sum_value = sum_value + parseFloat(sessionStorage.getItem($value[i]));
		}
	}
	sum_value = sum_value + parseFloat(document.getElementById("price_value").value);
	document.getElementById("price_view").innerHTML = sum_value;
}

//获取内容
function getData(){
	var dataValue = sessionStorage.getItem("dataValue");
	if(dataValue != null){
		return dataValue + ","
	}
	return "";
}

//设置当前时间
function setDate(){
	var date = new Date();
	document.getElementById("staDateTime").value = date.getFullYear();
}

//每次打开此网页进行清空sessionStorage避免出现金额增多情况
window.onload = function(){
	sessionStorage.clear();
	//运行
	setDate();
}

$(function(){
	$("#buyCommodity").unbind("click").bind("click",function(){
		//获取页面上的信息，ページの情報を取得する
		var userName2 = $("#userName2").val();
		var userCity2 = $("#userCity2").val();
		var userPhone2 = $("#userPhone2").val();
		var order_commodity_time_start = $("#staDateTime").val();
		var order_commodity_time_length = $("#endDataTime").val();
		alert(order_commodity_time_length);
		var order_sum_price = $("#price_view").html();
		var order_commodity_liability1 = $("#yw option:selected").text();
		var order_commodity_liability2 = $("#sc option:selected").text();
		var order_commodity_liability3 = $("#cj option:selected").text();
		var order_commodity_liability4 = $("#zy option:selected").text();
		var order_commodity_liability5 = $("#zd option:selected").text();
		var order_commodity_liability6 = $("#jt option:selected").text();
		var order_commodity_liability7 = $("#jb option:selected").text();
		
		//设置一个变量进行验证用户输入信息，ユーザーが入力した情報を検証するために変数「bo」を構築する
		var bo = false;
		
		if(userName2.length>0){
			if(userCity2.length>0){
				//正则表达式正确判断手机号
				var regP = /^1[3,4,5,6,7,8,9][0-9]{9}$/;
				if(regP.test(userPhone2)){
					bo = true;
				}else{
					alert("入力した携帯番号に問題がありますよ！");
				}
			}else{
				alert("被保険者のとしを空にすることはできませんよ！");
			}
		}else{
			alert("被保険者の名前を空にすることはできませんよ！");
		}
		
		//验证成功，検証に成功すると
		if(bo){
			if (window.confirm("本当にこのアイテムを購入しますか？？")) {
				//如果用户点击"确定"，执行这里的代码
				//使用ajax传递数据判断
				$.ajax({
					url:"/Jsp_insure/OrderServlet?flag=insertOrder",
					type:"post",
					data:{
						"order_commodity_liability1":order_commodity_liability1,
					    "order_commodity_liability2":order_commodity_liability2,
					    "order_commodity_liability3":order_commodity_liability3,
					    "order_commodity_liability4":order_commodity_liability4,
					    "order_commodity_liability5":order_commodity_liability5,
					    "order_commodity_liability6":order_commodity_liability6,
					    "order_commodity_liability7":order_commodity_liability7,
					    "userName2":userName2,
					    "userCity2":userCity2,
					    "userPhone2":userPhone2,
					    "order_commodity_time_start":order_commodity_time_start,
					    "order_commodity_time_length":order_commodity_time_length,
					    "order_sum_price":order_sum_price,
					},
					dataType:"json",
					success:function(data){
						if(data = 1){
							alert("ご注文おめでとうございます。注文詳細画面を遷移します！");
							window.location.href = "/Jsp_insure/OrderServlet?flag=select_order_by_user_id";
						}else if(data = 2){
							alert("残高不足のため、購入前に補充してください。");
							window.location.href = "/Jsp_insure/my_info.jsp";
						}else{
							alert("被保険者の年齢が補償範囲を超える");
						}
					},
					error:function(){
						alert("ネットワークにエラーがありました。後で再試行してください！");
					}
				});
			}
		}
	});
});