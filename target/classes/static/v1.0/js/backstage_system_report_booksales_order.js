window.onload=function() {
	
	//js获取项目根路径
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var url = localhostPaht+projectName;
	//	异步查询数据
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式 
		url : url+"/forms/booksales-order.do", //请求路径 
		cache : false,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			console.log(msg);
			//显示销售额统计表
			// 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('main'));

		    // 指定图表的配置项和数据
		    var option = window.parent.getOptionOfSalesOrder(msg.category, msg.data1 );

		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		}
	});
}

$(function() {
	//切换销售额统计表（七天，今年十二个月。近年四年的收益,商品销售排行
	$(".timeIntervalOfSellNum span").click(function() {
		$(".sellNumForm-selected").removeClass("sellNumForm-selected");
		$(this).parent().addClass("sellNumForm-selected");
		//异步执行查询数据库操作
		getFormOfSellNumByValue($(this).parent().val());
	});
	
	//移到今年销售额按钮时显示选择月份
	$("#timeIntervalOfThisYearMouths").hover(function() {
		$("#hidden_yearForSelect").slideDown(200);
		var d = new Date();
		var nowYear = d.getFullYear();
		var options = document.getElementsByClassName("yearOption");
		for(var i=0; i<3; i++) {
			options[i].innerHTML = nowYear-i;
			options[i].value = nowYear-i;
		}
	},
	function() {
		$("#hidden_yearForSelect").slideUp(50);
	});
	
	//切换指定年份的表内容
	$("#hidden_yearForSelect li").click(function() {
		$(".year-fristOption").removeClass("year-fristOption");
		$(this).addClass("year-fristOption");
		//异步执行查询数据库操作
		getFormOfSellNumByValue($(this).val());
	});
});

//js获取项目根路径
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

//获取不同的销售额表函数
function getFormOfSellNumByValue(value) {
	console.log(value);
	var url = getRootPath();
	var d = new Date();
	var nowYear = d.getFullYear();
	switch(value) {
	case nowYear:
		url = url+"/forms/sellnumformsOfMouths/"+nowYear+".do/";
		break;
	case nowYear-1:
		url = url+"/forms/sellnumformsOfMouths/"+(nowYear-1)+".do/";
		break;
	case nowYear-2:
		url = url+"/forms/sellnumformsOfMouths/"+(nowYear-2)+".do/";
		break;
	case 1:
		url = url+"/forms/sellnumformsOf7Day.do";
		break;
	case 2:
		url = url+"/forms/sellnumformsOfMouths/"+nowYear+".do/";
		break;
	case 3:
		url = url+"/forms/sellnumformsOfYear.do";
		break;
	case 4:
		window.parent.run_bookSalesOrder();
		break;
	}

	//调用ajax查询数据。发回json数据
	if( value != 4) {
	$.ajax({
		type : "POST", //请求方式  
		url : url, //请求路径 
		cache : false,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//显示销售额统计表
			// 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('main'));		    

		    // 使用刚指定的配置项和数据显示图表。
		    var option = window.parent.getOptionOfSellNum( msg.legend, msg.category, msg.data1, msg.data2, msg.data3, value);
		    // 指定图表的配置项和数据
		    myChart.setOption(option);
		}
	});
	}
}