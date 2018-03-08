$(function() {

	//批量打印--->点击监听
	$("#batch-print").click(function() {
		var result = "";
		$('input:checkbox[name=cb_order]:checked').each(function(i){
	        var temp = operaPrint(this);
	        result =result + temp;
	    });
		printBatch(result);
	});

});

/**
 * 打开快递方式div
 * @returns
 */
function openDeliverWayListDiv() {
	$(".hidden-deliver-way-list").show();
}

//选择快递方式
function selectDeliverWay(obj) {
	obj.parentElement.previousSibling.value = obj.innerHTML;
	$(".hidden-deliver-way-list").hide();
}


//调用打印单个订单的函数
function printSingleOrder(obj) {
	console.log("调用单个打印函数");
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

	var orderDiv = document.createElement("div"); //创建订单根信息div
	var order_time = document.createElement("span"); //创建订单时间标签
	order_time.style["padding-right"] = "50px";
	order_time.innerHTML = target[0].getElementsByClassName("order_time")[0].innerHTML; //获得订单时间
	var order_code = document.createElement("span"); //创建订单编号标签
	order_code.innerHTML = target[0].getElementsByClassName("order_code")[0].innerHTML; //获得订单号
	orderDiv.appendChild(order_time); //将订单时间添加到订单根信息div中，
	orderDiv.appendChild(order_code); //将订单编号添加到订单根信息div中，
	bodyDiv.appendChild(orderDiv); //将订单根信息div添加到主div中

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
	userRemark.innerHTML = target[target.length-1].getElementsByClassName("order_remark")[0].innerHTML; //获得用户备注
	userDiv.appendChild(username); 
	userDiv.appendChild(userAddress);
	userDiv.appendChild(userPhone);
	userDiv.appendChild(userRemark);
	bodyDiv.appendChild(userDiv);

	return bodyDiv.innerHTML;
}	

/**
 * 发货。ajax存储快递信息
 * @param 订单oid
 */
function DeliverOrder(oid,obj) {
	var parent = obj.parentElement.parentElement.nextElementSibling.childNodes;
	//获取快递方式和快递单号
	var shippingName = parent[0].childNodes[1].value;
	var shippingCode = parent[1].childNodes[1].value;
	console.log(oid+shippingName+shippingCode);
	var url = getRootPath();//获取根路径
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/order/deliver.do", //请求路径 
		cache : false,
		data : "oid="+oid+"&shippingName="+shippingName+"&shippingCode="+shippingCode,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//将数据填充到模态框中
			if(msg.status == 200) {
				alert("成功");
			} else {
				alert(msg.msg);
			}
			
		}
	});
}
