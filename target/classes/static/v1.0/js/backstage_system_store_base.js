$(function() {

	$(".store-service").click(function() {
		$(".store_selected").removeClass("store_selected");
		$(this).addClass("store_selected");
		var old_value = $(".store_selected").attr("value");
		var new_value = $(this).attr("value");
		$(".t-synch-class").hide(); 
		switch(new_value) {
			case "1":
				$(".t-content-base").show();
				$(".btn-storemessage-submit").show();
				break;
			case "2":
				$(".t-content-pic").show();
				$(".btn-storemessage-submit").hide();
				break;
		};
	});
});

//显示选择的头像图片
function showPicFromFile(id){
	var r= new FileReader();
	var input = document.getElementById(id).files[0];
	r.readAsDataURL(input);
	r.onload=function  (e) {
		document.getElementById('store_logo_1').src=this.result;
		document.getElementById('store_logo_2').src=this.result;
		document.getElementById('store_logo_3').src=this.result;
	};
}

/**
 * 提交店铺基本信息表单的函数
 */
function form_store_base_submit() {
	var url = getRootPath();//获取根路径
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/update-store-base.do",
		data:$("#form_store_base").serialize(),//将表单数据序列化
		cache : false,
		dataType : 'json', //返回值类型
		success : function(msg) {
			alert("成功");
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

/**
 * 修改店铺logo（提交至后端）
 */
function uploadPicOfStore() {
	//获取图片
	var input = document.getElementById("store_logo_input");
	//获取店铺编号
	var storeId = document.getElementById("store_id");
	var formdata = new FormData();
	console.log(input);
	formdata.append("file", input.files[0]);
	formdata.append("storeId", storeId.value);
	var url = getRootPath();//获取根路径
	$.ajax({
		type: "POST",
		url: url+"/uploadpic-store"+".do",
	    cache: false,
	    data: formdata,
	    processData: false,
	    contentType: false,
	    dataType : 'json', //返回值类型 
		success : function(msg) {
			//1.将数据填充到img标签的src中显示
			if ( msg.error == 200 ) {
				document.getElementById('store_logo_1').src=msg.url;
				document.getElementById('store_logo_2').src=msg.url;
				document.getElementById('store_logo_3').src=msg.url;
				alert("成功");
			} else {
				alert(msg.message);
			}
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

//关闭订单模态框
function closeOrderDialog() {
	document.getElementById("store_order_opera-dialog").style.display = "none";
	document.getElementById("hidden-opera-order").style.display = "none";
}
