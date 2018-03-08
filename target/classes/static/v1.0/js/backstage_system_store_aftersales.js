$(function() {

	//批量打印--->点击监听
	$("#batch-print").click(function() {
		var result = "";
		$('input:checkbox[name=cb_aftersales]:checked').each(function(i){
	        var temp = operaPrint(this);
	        result =result + temp;
	    });
		printBatch(result);
	});
	
	//打开卖家填写退货地址的隐藏框
	$(".t-address-opera").click(function(){
		$(".t-address-opear-hidden-div").show(500);
	});
	
	//选择“同意退货”或者“异议并上传凭证”选项卡
	$(".seller_opera_aftersale").click(function() {
		$(".seller_opera_selected").removeClass("seller_opera_selected");
		$(this).addClass("seller_opera_selected");
		var old_value = $(".seller_opera_selected").attr("value");
		var new_value = $(this).attr("value");
		switch(new_value) {
			case "1":
				$(".t-agress-2-return").show();
				$(".t-disagress-2-return").hide();
				break;
			case "2":
				$(".t-agress-2-return").hide();
				$(".t-disagress-2-return").show();
				break;
		};
	});
	
	//同意退货确认按钮
	$("#btn_agress2Return").click(function() {
		var url = getRootPath();//获取根路径
		var asid = $("#dialog_asid").val();
		//调用ajax查询数据。发回json数据
		$.ajax({
			type : "POST", //请求方式
			url : url+"/aftersales/agress2return.do", //请求路径 
			cache : false,
			data : $("#t-seller_agress_form").serialize()+"&asid="+asid,
			dataType : 'json', //返回值类型 
			success : function(msg) {
				//将数据填充到模态框中
				if(msg.status == 200) {
					//将数据填充到模态框中
					alert("成功操作");
				} else {
					alert(msg.msg);
				}
				
			}
		});
	});
	

});

//调用打印单个订单的函数
function printSingleaftersales(obj) {
	var result = operaPrint(obj);

	var body = window.document.body.innerHTML;
	window.document.body.innerHTML = result;
	window.print();
	window.document.body.innerHTML = body;
}

//调用批量打印订单的函数
function printBatch(content) {

	var body = window.document.body.innerHTML;
	window.document.body.innerHTML = content;
	window.print();
	window.document.body.innerHTML = body;
}

//处理要打印的订单数据
function operaPrint(obj) {
	target = obj.parentElement.parentElement.children;
	// //获取需要的数据，存于printDiv中
	var bodyDiv = document.createElement("div"); //创建主div

	var aftersalesDiv = document.createElement("div"); //创建订单根信息div
	var aftersales_time = document.createElement("span"); //创建订单时间标签
	aftersales_time.style["padding-right"] = "50px";
	aftersales_time.innerHTML = target[0].getElementsByClassName("aftersales_time")[0].innerHTML; //获得订单时间
	var aftersales_code = document.createElement("span"); //创建订单编号标签
	aftersales_code.innerHTML = target[0].getElementsByClassName("aftersales_code")[0].innerHTML; //获得订单号
	aftersalesDiv.appendChild(aftersales_time); //将订单时间添加到订单根信息div中，
	aftersalesDiv.appendChild(aftersales_code); //将订单编号添加到订单根信息div中，
	bodyDiv.appendChild(aftersalesDiv); //将订单根信息div添加到主div中

	for ( var i = 1; i < target.length-1; i++ ) {
		var Div = document.createElement("div"); //创建订单项div
		var itemTitle = document.createElement("span"); //创建商品名称标签
		itemTitle.style["padding-right"] = "30px";
		var specific = document.createElement("span"); //创建商品规格标签
		specific.style["padding-right"] = "30px";
		var num = document.createElement("span"); //创建商品数量标签
		itemTitle.innerHTML = target[i].getElementsByClassName("book_title")[0].innerHTML; //获得商品名称
		specific.innerHTML = target[i].getElementsByClassName("book_specific")[0].innerHTML; //获得商品规格
		num.innerHTML = target[i].getElementsByClassName("book_num")[0].innerHTML; //获得商品数量
		Div.appendChild(itemTitle);  //将商品名称添加到订单项div中
		Div.appendChild(specific); //将商品规格添加到订单项div中
		Div.appendChild(num); //将商品数量添加到订单项div中
		bodyDiv.appendChild(Div);//将订单项div添加到主div中
	}

	var userDiv = document.createElement("div"); //创建用户div
	var username = document.createElement('span'); //创建用户名标签
	var userAddress = document.createElement('span'); //创建用户地址标签
	var userPhone = document.createElement('span'); //创建用户联系方式标签
	var userRemark = document.createElement("span"); //创建用户备注标签
	username.innerHTML = target[target.length-1].getElementsByClassName("receiver_name")[0].innerHTML; //获得用户名
	userAddress.innerHTML = target[target.length-1].getElementsByClassName("receiver_address")[0].innerHTML;//获得用户地址
	userPhone.innerHTML = target[target.length-1].getElementsByClassName("receiver_phone")[0].innerHTML; //获得用户电话
	userRemark.innerHTML = target[target.length-1].getElementsByClassName("aftersales_remark")[0].innerHTML; //获得用户备注
	userDiv.appendChild(username); 
	userDiv.appendChild(userAddress);
	userDiv.appendChild(userPhone);
	userDiv.appendChild(userRemark);
	bodyDiv.appendChild(userDiv);

	return bodyDiv.innerHTML;
}

//关闭售后记录模态框
function closeCrossingAftersaleDialog() {
	document.getElementById("store_aftersales_opera-dialog").style.display = "none";
	document.getElementById("hidden-opera-aftersales").style.display = "none";
}

//获取退货地址
function submit_address() {
	// 获取选定的省份
	var province = document.getElementById("provinces");
	var province_index = province.selectedIndex;
	var province_value = province.options[province_index].value;
	// 获取选定的市县
	var city = document.getElementById("cities");
	var city_index = city.selectedIndex;
	var city_value = city.options[city_index].value;
	//获取详细地址
	var address_detail = document.getElementsByClassName('address_detail')[0].value;
	//将上三项内容设置到隐藏标签中
	document.getElementsByClassName("hidden_province")[0].value = province_value;
	document.getElementsByClassName("hidden_city")[0].value = city_value;
	document.getElementsByClassName("hidden_address")[0].value = address_detail;
	//将三项地址串联并放在指定标签显示出来
	document.getElementsByClassName("t-address-show")[0].innerText = province_value + city_value + address_detail;
	//将地址存入hidden标签中，方便后续传入后端
	document.getElementById("return_address").value = province_value + city_value + address_detail;
	//隐藏地址编辑框
	var hidden_div = document.getElementsByClassName("t-address-opear-hidden-div");
	hidden_div[0].style.display = "none";
}

/**
 * 初始化售后模态框
 * @returns
 */
function initAftersaleDialog() {
	//1.清除该售后记录中的商品信息
	$("#t-dialog_aftersales_items").html("");
	//设置售后编号
	$("#dialog_asid_show").html("");
	//设置订单编号
	$("#dialog_oid_show").html("");
	//设置售后服务创建时间
	$("#dialog_createTime_show").html("");
	//设置订单实付金额
	$("#dialog_payment_show").html("");
	//设置买家退货的理由
	$("#buyer_reason").html("");
	//设置当前售后服务进度
	$("#dialog_aftersale_status").html("");
	//设置买家自述原因
	$("#buyer_reason-extend").html("");
	//设置买家凭证
	$(".buyer_voucher").html("");
	//设置卖家凭证
	$("#seller_voucher_pic_list").html("");
	//设置卖家理由
	$("#seller_reason_extend").val("");
	//设置卖家数据
	//1.退款金额
	$(".return_money").html("");
	//2.收款人
	$(".return_name").html("");
	//3.收款电话
	$(".return_phone").html("");
	//4.卖家提供地址
	$("#seller_give_address_show").html("");
	$(".t-disagress-2-return-submit").show();
	$(".t-agress-2-return-submit").show();
	$(".seller_opera_aftersale[value='2']").show();
}

/**
 * 将单条售后数据填充到模态框
 * @param obj aftersale对象
 * @returns
 */
function fillInDialogOfSingleAftersale( obj ) {
	//初始化售后模态框
	initAftersaleDialog();
	//显示模态框
	$("#hidden-opera-aftersales").fadeIn();
	$("#store_aftersales_opera-dialog").fadeIn(1000);
	//获取订单中的商品父标签
	var itemBody = document.getElementById("t-dialog_aftersales_items");
	for ( var i = 0; i < obj.length; i++ ) {
		if ( $(".t-dialog_single_item").length == 0 ) {
			//设置售后编号
			$("#dialog_asid_show").html(obj[i].asid);
			$("#dialog_asid").val(obj[i].asid);
			//设置订单编号
			$("#dialog_oid_show").html(obj[i].orderId);
			//设置售后服务创建时间
			$("#dialog_createTime_show").html(new Date(obj[i].createTime).format("yyyy/MM/dd hh:mm:ss"));
			//设置订单实付金额
			$("#dialog_payment_show").html("￥"+(obj[i].payment/100));
			//设置买家退货的理由
			switch( obj[i].buyerReason ) {
				case 1:
					$("#buyer_reason").html("七天无理由退货");
					break;
				case 2:
					$("#buyer_reason").html("拍多了/拍错了");
					break;
				case 3:
					$("#buyer_reason").html("不喜欢");
					break;
				case 4:
					$("#buyer_reason").html("商品质量不合格");
					break;
				case 5:
					$("#buyer_reason").html("非正版图书");
					break;
			}
			//设置当前售后服务进度
			switch( obj[i].aftersalesStatus ) {
				case 1:
					$("#dialog_aftersale_status").html("等待处理");
					break;
				case 2:
					$("#dialog_aftersale_status").html("正在协商中");
					$(".t-disagress-2-return-submit").hide();
					//$(".t-agress-2-return-submit").hide();
					break;
				case 3:
					$("#dialog_aftersale_status").html("正在退货中");
					$(".t-disagress-2-return-submit").hide();
					$(".t-agress-2-return-submit").hide();
					$(".seller_opera_aftersale[value='2']").hide();
					break;
				case 4:
					$("#dialog_aftersale_status").html("售后结束");
					$(".t-disagress-2-return-submit").hide();
					$(".t-agress-2-return-submit").hide();
					//隐藏确认按钮
					$(".t-disagress-2-return-submit").hide();
					$(".t-agress-2-return-submit").hide();
					break;
			}
			//设置买家自述原因
			$("#buyer_reason-extend").html(obj[i].buyerResonExtend);
			//设置买家凭证
			if (obj[i].buyerVoucher != null) {
				var vouchers = obj[i].buyerVoucher.split(",");
				for( var j=0; j<vouchers.length; j++ ) {
					if(vouchers[j] != null && vouchers[j] != "") {
						$(".buyer_voucher").append(
								'<img src="'+vouchers[j]+'" />');
					}
				}
			}
			//设置卖家凭证
			if ( obj[i].sellerVoucher != null ) {
				var seller_vouchers = obj[i].sellerVoucher.split(",");
				for( var j=0; j<
				seller_vouchers.length; j++ ) {
					if(seller_vouchers[j] != null && seller_vouchers[j] != "") {
						$("#seller_voucher_pic_list").append(
								'<img src="'+seller_vouchers[j]+'" />');
					}
				}
			}
			//设置卖家理由
			if( obj[i].sellerReason != null && obj[i].sellerReason != "" ) {
				$("#seller_reason_extend").val(obj[i].sellerReason);
			}
			//若卖家同意退货，则提供数据
			//1.退款金额
			$(".return_money").html("￥"+(obj[i].payment/100));
			//2.收款人
			$(".return_name").html(obj[i].receiverName);
			//3.收款电话
			$(".return_phone").html(obj[i].receiverPhone);
			//4.卖家提供地址
			$("#seller_give_address_show").html(obj[i].returnAddress);
		} //end if
		$("#t-dialog_aftersales_items").append(
				'<div class="t-dialog_single_item">'+
					'<ul class="nav">'+
						'<li class="t-single_item_img">'+
							'<img src="'+obj[i].img.split(",")[0]+'" />'+
						'</li>'+
						'<li class="t-single_item_detail">'+
							'<span class="t_single_item_title">'+obj[i].title+'</span>'+
							'<span class="t_single_item_speicification">'+obj[i].specificName+'&nbsp;</span>'+
							'<span class="t_single_item_num">&nbsp;×'+obj[i].num+'</span>'+
						'</li>'+
						'<li class="t-single_item_price">￥'+(obj[i].price/100)+'</li>'+
					 '</ul>'+
				 '</div>'
		);
	}
	
	
	
	
	
}

/**
 * 查询指定售后--根据售后编号
 * @param 售后编号asid 
 */
function openAftersaleDialog(asid) {
	var url = getRootPath();//获取根路径
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/store/aftersales-single.do", //请求路径 
		cache : false,
		data : "asid="+asid,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//将数据填充到模态框中
			if(msg.status == 200) {
				//将数据填充到模态框中
				console.log(msg.data1);
				fillInDialogOfSingleAftersale( msg.data1 );
			} else {
				alert(msg.msg);
			}
			
		}
	});
}

//获取卖家上传凭证的图片地址
function getSellerVoucherUrlStr() {
	var imgs = document.getElementsByClassName("seller_voucher_pic");
	var urlStr = ""; //定义地址常量
	for (var k = 0; k < imgs.length; k++) {
		urlStr = urlStr + imgs[k].src + ",";
	}
	$("input[name='sellerVoucher']").val(urlStr);
}

//为卖家凭证添加单个图片布局
function addSellerVoucherShow( url ){
	var body = document.getElementById("seller_voucher_pic_list");
	var img = document.createElement("img");
	img.className = "seller_voucher_pic";
	img.src = url;
	body.appendChild(img);
}


//上传图片文件按钮监听
function uploadVoucherFromFile() {
	
	var input = document.getElementById("input_seller_voucher");
	var hadPic = document.getElementsByClassName("seller_voucher_pic"); //获取图片标签
	if (input.files.length + hadPic.length >3 ) {
		confirm("选取文件数超过限制，您还能选择"+(3-hadPic.length)+"个文件。")
	} else if (input.files.length + hadPic.length <=3) {
		if (input.files.length + hadPic.length ==3) {
			//如果文件个数超过三个，则不显示添加按钮
			$(".input_seller_voucher").hide(); //隐藏添加按钮,
		}
		var formdata = new FormData();
		for (var i = 0; i < input.files.length; i++) {
			formdata.append("file[]", input.files[i]);
		}
		var url = getRootPath();//获取根路径
		$.ajax({
			type: "POST",
			url: url+"/uploadpic"+".do",
		    cache: false,
		    data: formdata,
		    processData: false,
		    contentType: false,
		    dataType : 'json', //返回值类型 
			success : function(msg) {
				//1.将数据填充到img标签的src中显示
				for ( var i = 0; i < msg.length; i++ ) {
					if ( msg[i].error == 200 ) {
						addSellerVoucherShow(msg[i].url);//回显
					} else{
						alert("加载失败！");
					}
				}
				getSellerVoucherUrlStr();
			}
		});
	}
}

/**
 * 不同意退货按钮
 */