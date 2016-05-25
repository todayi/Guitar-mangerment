/**
 * 
 */

function search(){
	var keyword=$("#keyword").val();
	$("tr:gt(0)").remove();
	$.getJSON("GuitarMatched",{keyword:keyword}, function(json) {
		var html="";
		if(json.length==0){
			$(".table").append("<tr><td colspan=\"8\" class=\"warning\" align=\"center\">未找到匹配商品</td></tr>");
		}else{
			for(var i=0;i<json.length;i++){
				var serialNumber=json[i].serialNumber;
				var price=json[i].price;
				var builder=json[i].builder;
				var model=json[i].model;
				var type=json[i].type;
				var backwood=json[i].backwood;
				var topwood=json[i].topwood;
				var stringNum=json[i].stringNum;
				html+="<tr><td>"+serialNumber+"</td><td>"+builder+"</td><td>"+model+"</td><td>"+type+"</td>";
				html+="<td>"+stringNum+"</td><td>"+topwood+"</td><td>"+backwood+"</td><td>"+price+"</td></tr>";
			}
		}
		$(".table").append(html);
	});
}