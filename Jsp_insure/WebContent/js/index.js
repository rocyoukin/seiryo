/**
 * 主界面商品遍历
 */
$(function(){
	$.ajax({
		url:"/Jsp_insure/CommodityServlet?opt=selectAllCommodityList",
		type:"post",
		dataType:"json",
		success:function(data){
			//var CommodityList = eval(JSON.parse(data));
			var info = "";
			var sum = 0;
			for(var i = 0; i < data.length; i++){
				info = info + "<div class='div_context'>";
				info = info + "<div class='div_context_l'>";
				info = info + "<img src=" + data[i].commodity_img + " width='350px' height='260px'/>";
				info = info + "</div>";
				info = info + "<div class='div_context_r'>";
				info = info + "<div class='div_context_r_1'>";
				info = info + "<h2>" + data[i].commodity_name + "</h2>";
				info = info + "<p class='p1'>️" + data[i].commodity_introduce + "</p>";
				info = info + "</div>";
				info = info + "<div class='div_context_r_2'>";
				info = info + "<div class='price'>";
				info = info + "<span class='fh'><b>¥</b></span>";
				info = info + "<span class='price_a'><b>" + data[i].commodity_price + "</b></span>";
				info = info + "<span class='fh'><b>/起</b></span>";
				info = info + "</div>";
				info = info + "<a href='CommodityServlet?opt=commodityQueryById&commodity_id=" + data[i].commodity_id + "&pd=page_info.jsp'>";
				info = info + "<div class='img_a'>";
				info = info + "<b>詳細はこちら</b>";
				info = info + "</div>";
				info = info + "</a>";
				info = info + "</div>";
				info = info + "</div>";
				info = info + "</div>";
				sum ++;
			}
			sum = Math.ceil(sum/2) * 350;
			$("#context").css("height",sum + "px");
			$("#context_text").append(info);
		},
		error:function(){
			alert("ネットワークにエラーがありました。後で再試行してください！");
		}
	});
});