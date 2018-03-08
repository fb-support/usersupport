
$(function() {

	//打开模态框--查看图书详情
	$(".lookSingleItem").click(function() {
		$("#hidden-opera-item").fadeIn();
		$("#opera-dialog").fadeIn(1000);
		var input = $(this).next();
	});

	//打开模态框--添加图书
	$(".addItem2openDialog").click(function() {
		//初始化页面
		clearDataFromItemDetail();
		$("#hidden-opera-item").fadeIn();
		$("#opera-dialog").fadeIn(1000);
	});
	//打开模态框--批量导入图书
	$(".addItemFromFile").click(function() {
		$("#hidden-opera-item").fadeIn();
		$("#file-input-dialog").fadeIn(1000);
	});
	
	//设置img事件监听
	$('.pic-single').on('mouseenter mouseleave','.single-pic', function(){
		$(this).next().slideDown(200);
	});

	//删除照片
	$(".btn-delete").click(function() {
		$(this).parent().remove();
	});
	
});

var loadFlag = 0; //判定变量，让所有类目只加载一次

/**
 * 商品详情页面初始化函数（清空数据）
 */
function clearDataFromItemDetail() {
	$("#item_iid").val("");//清除商品编号
	$("#storeId").val("");//清除商品所属商店编号
	$("#categoryId").val("");//清除商品所属类目编号
	$("#item_title").val("");//清除商品标题
	$("#item_price").val("");//清除商品价格
	$("#book_author").val(""); //清除图书作者
	$("#book_publishing_home").val(""); //清除图书出版社
	$("#book_publishing_date").val(""); //清除图书出版时间
	$("#book_profile").val(""); //清除商品简介
	$("#book_isbn").val(""); //清除图书isbn
	$("#bookSize").val(""); //清除图书开本
	$("#pageNum").val(""); //清除图书页数
	$("#wordNum").val(""); //清除图书字数
	
	//初始化规格
	var specificationList = document.getElementById("t-specificate-list");
	specificationList.innerHTML = "";
	
	//初始化图片区域
	var picList = document.getElementsByClassName("t-pic-list")[0];
	var old = document.getElementsByClassName("pic-single");
	for (var i = 0; i < old.length;) {
		picList.removeChild( old[0] );
	}
	$(".t-pic-placeholder").show(); //显示占位符
	$("#hiddenInputFileForPic").val(""); //清除图片地址集合隐藏input
	$("#item_content").val(""); //清除商品详情内容
	item_editor.html("");
}

/**
 * 将单个图书数据填充到模态框并打开模态框
 * @returns
 */
function fillInDialogOfItem(msg) {
	
	//页面初始化
	clearDataFromItemDetail();
	
	$("#item_iid").val(msg[0].iid);//设置商品编号(商品表)
	$("#storeId").val(msg[0].storeId);//设置店铺编号（商品表）
	$("#categoryId").val(msg[0].categoryId);//设置类别编号（商品表）
	$("#item_title").val(msg[0].title);//填充商品标题
	$("#item_price").val(msg[0].price/100);//填充商品价格
	$("#book_author").val(msg[0].author); //填充图书作者
	$("#book_publishing_home").val(msg[0].publishHome); //填充图书出版社
	$("#book_publishing_date").val(new Date(msg[0].publishDate).format("yyyy-MM-dd")); //填充图书出版时间
	$("#book_profile").val(msg[0].sellpoint); //填充商品简介
	$("#book_isbn").val(msg[0].isbn); //填充图书isbn
	$("#bookSize").val(msg[0].bookSize); //填充图书开本
	$("#pageNum").val(msg[0].pageNum); //填充图书页数
	$("#wordNum").val(msg[0].wordNum); //填充图书字数
	
	/*填充商品所属类目*/
	var select = document.getElementById("category-select")
	var option = document.createElement("option");
	option.value = msg[0].categoryId;
	option.innerHTML = msg[0].categoryName;
	option.className = "category-option";
	option.selected = "selected";
	select.appendChild(option);
	
	/*填充图书规格*/
	for ( var i = 0; i < msg.length; i++ ) {
		if ( msg[i].specificName != null ) {
			addSpecificateInput(msg[i].sid); //添加规格框
			//获取规格文本框对象
			var inputText = document.getElementsByClassName("specificateName");
			//获取删除规格按钮对象
			var btn_sub = document.getElementsByClassName("btn-subSpecific");
			inputText[i].value = msg[i].specificName;
		}
	}
	
	//填充图片
	var picList = document.getElementsByClassName("t-pic-list")[0];
	var imgs = msg[0].img.split(",");
	for ( var i = 0; i < imgs.length ; i++ ) {
		$(".t-pic-placeholder").hide(); //隐藏占位符
		if ( imgs[i] != null && imgs[i] != "" ) {
			addPicShow(imgs[i]);
		}
	}
	
	//填充商品详情
	$("#item_content").val(msg[0].content);
	item_editor.html(msg[0].content);
	
	$(".t-btn-uploadpic").show();   //显示添加按钮
	//显示模态框
	$("#hidden-opera-item").fadeIn();
	$("#opera-dialog").fadeIn(1000);
}

/**
 * 打开查看图书详情模态框 -- 异步加载数据
 * iid : 商品编号
 */
function lookSingleItem(iid) {
	
	var url = getRootPath();//获取根路径
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/item/"+iid+".do", //请求路径 
		cache : false,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//将数据填充到模态框中
			fillInDialogOfItem(msg);
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

/**
 * 删除指定商品 -- 异步加载
 * @returns，返回状态（是否成功）
 * 成功-->弹出框显示删除成功
 * 失败-->弹框显示删除失败
 */
function deleteSingleItem(iid) {
	var url = getRootPath();//获取根路径
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/deleteitem/"+iid+".do", //请求路径 
		cache : false,
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//将数据填充到模态框中
			if ( msg.status == 200 ) {
				alert("删除成功！");
				runItemManager(1,8);
			} else if ( msg.status == 504 ) {
				alert("删除失败！");
			}
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

/**
 * 更新指定商品（上架，下架） -- 异步加载
 * @returns，返回状态（是否成功）
 * 成功-->弹出框显示操作成功
 * 失败-->弹框显操作失败
 */
function operaSingleItemStatus(iid, status) {
	var url = getRootPath();//获取根路径
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/updateitemofStatus/"+iid+".do", //请求路径 
		cache : false,
		data : "status=" + status,  //传参
		dataType : 'json', //返回值类型 
		success : function(msg) {
			//将数据填充到模态框中
			if ( msg.status == 200 ) {
				alert("操作成功！");
				runItemManager(1,8);
			} else if ( msg.status == 504 ) {
				alert("操作失败！");
			}
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

//关闭图书详情模态框
function closeCrossingBookDialog() {
	document.getElementById("opera-dialog").style.display = "none";
	document.getElementById("hidden-opera-item").style.display = "none";
}

//关闭批量导入模态框
function closeFileInputDialog() {
	document.getElementById("file-input-dialog").style.display = "none";
	document.getElementById("hidden-opera-item").style.display = "none";
}

//删除图片
function delSinglePic() {
	this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);//删除当前图片
	//显示添加按钮,
	$(".t-btn-uploadpic").show(); 
}

//更换图片
function changeSinglePic() {
	var img = this.parentNode.parentNode.childNodes[0];
	var formdata = new FormData();
	for (var i = 0; i < this.files.length; i++) {
		formdata.append("file[]", this.files[i]);
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
			//将数据填充到模态框中
			if ( msg[0].error == 200 ) {
				img.src = msg[0].url;
			} else {
				alert("更换失败！")
			}
		}
	});
}

//添加单个图片标签和布局
function addPicShow(url) {
	var body = document.getElementsByClassName("t-pic-list")[0] //获得外层标签
	
	/*创建图片排版标签*/
	var singleDiv = document.createElement("div");
	singleDiv.className = "pic-single";
	var img = document.createElement("img");
	img.className = "single-pic";
	img.src = url;
	img.alt = "商品图片";
	var operaDiv = document.createElement("div");
	operaDiv.className = "t-btn-about-pic";
	var delSpan = document.createElement("span");
	delSpan.className = "btn-delete";
	delSpan.innerHTML = "删除";
	//给删除按钮添加点击响应
	delSpan.onclick = delSinglePic;
	var changeSpan = document.createElement("span");
	changeSpan.className = "btn-update";
	changeSpan.innerHTML = "更换";
	//添加input file 标签，让其透明重叠在更换按钮上
	var hiddenInputFile = document.createElement("input");
	hiddenInputFile.type = "file";
	hiddenInputFile.className = "hiddenInputFileForPic";
	hiddenInputFile.name = "file";
	hiddenInputFile.accept = ".jpg,.png";
	hiddenInputFile.onchange = changeSinglePic;
	operaDiv.appendChild(delSpan);
	operaDiv.appendChild(changeSpan);
	operaDiv.appendChild(hiddenInputFile);
	singleDiv.appendChild(img);
	singleDiv.appendChild(operaDiv);
	
	//将创建的内容添加到外层标签中
	body.appendChild(singleDiv);
	
	//设置img事件监听
	$('.pic-single').on('mouseenter mouseleave','.single-pic', function(){
		$(this).next().slideDown(200);
	});
}

//
function getImgUrlStr() {
	//将所有图片地址通过“，”拼起来放到hidden input中
	var urlStr = ""; //定义地址常量
	var imgs = document.getElementsByClassName("single-pic");
	
	for (var k = 0; k < imgs.length; k++) {
		urlStr = urlStr + imgs[k].src + ",";
	}
	$("#imgUrlStr").val(urlStr);
}

//上传图片文件按钮监听
function uploadPics() {
	
	var input = document.getElementById("input_uploadPics");
	var hadPic = document.getElementsByClassName("pic-single"); //获取图片标签
	if (input.files.length + hadPic.length >5) {
		confirm("选取文件数超过限制，您还能选择"+(5-hadPic.length)+"个文件。")
	} else if (input.files.length + hadPic.length <=5) {
		if (input.files.length + hadPic.length ==5) {
			//如果文件个数超过五个，则不显示添加按钮
			$(".t-btn-uploadpic").hide(); //隐藏添加按钮,
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
				$(".t-pic-placeholder").hide(); //隐藏占位符
				//1.将数据填充到img标签的src中显示
				for ( var i = 0; i < msg.length; i++ ) {
					if ( msg[i].error == 200 ) {
						addPicShow(msg[i].url);//回显
						getImgUrlStr();
					} else{
						alert("加载失败！");
					}
				}
				
			}
		});
	}
}

//选择批量excel文件改变监听
function batchUpload(this_id) {
	var target = document.getElementById(this_id);
	var container = document.getElementById("t-file-single");
	container.innerHTML = "";  //清除列表中原来的内容
	for (var i = 0; i < target.files.length; i++) {
		var strs = target.files[i].name.split(".");
		var type = strs[strs.length-1]; //文件类型
		var name = "";                  //文件名称
		for (var j = 0; j < strs.length-1; j++) {
			name = name + strs[j];
		}
		var size = target.files[i].size/1024; //文件大小
		var tr = document.createElement("tr");
		var td_name = document.createElement("td");
		var td_size = document.createElement("td");
		var td_type = document.createElement("td");
		td_name.innerHTML = name;
		tr.appendChild(td_name);
		td_size.innerHTML = size.toFixed(2) + "KB";
		tr.appendChild(td_size);
		td_type.innerHTML = type;
		tr.appendChild(td_type);
		container.appendChild(tr);
	}
}

//删除规格input框
function subSpecificateInput() {
	//删除之前，将要被删除的规格id写入input中，
	var input = this.parentNode.childNodes[0];
	$("#deleteSpecific_list_temp").val($("#deleteSpecific_list_temp").val()+","+input.value);
	this.parentNode.parentNode.removeChild(this.parentNode);
}

//添加规格input栏
function addSpecificateInput(sid) {
	var target = document.getElementById("t-specificate-list");
	var div = document.createElement("div");
	var input = document.createElement("input");
	var inputId = document.createElement("input");
	var a = document.createElement("a");
	input.type = "text";
	input.name = "specificName";
	input.className = "specificateName";
	inputId.type = "hidden";
	inputId.name = "sid";
	if ( sid != null ) {
		inputId.value = sid;
	}
	a.className = "btn-subSpecific glyphicon glyphicon-minus";
	a.onclick = subSpecificateInput;
	div.appendChild(inputId);
	div.appendChild(input);
	div.appendChild(a);
	target.appendChild(div);
}

//将异步加载的商品类目data填充到下拉框中
function fillCategory(data) {
	console.log(data);
	/*填充商品所属类目*/
	var select = document.getElementById("category-select");
	var category = document.getElementById("categoryId").value;
	//初始化
	select.innerHTML = "";
	//获取本商品原商品类目
	var oldCategory = document.getElementsByClassName("category-option");
	for ( var i = 0; i < data.length; i++ ) {
		var option = document.createElement("option");
		option.value = data[i].cid;
		option.innerHTML = data[i].categoryName;
		option.className = "category-option";
//		if ( data[i].cid == category ) {
//			option.selected = "selected";
//		}
		select.appendChild(option);
		
	}
	
	//让商品原有类目设为选定。
	
}

/**
 * 当选定类目发生变化时，将新的类目填充到input中
 * @returns
 */
function getSelectedCategory() {
	var newCategory = $("#category-select>option:selected")[0];
	$("#categoryId").val(newCategory.value);//设置类别编号（商品表）
}


/**
 * 加载所有的类目 -- 异步加载
 */
function loadAllCategory() {
	if ( loadFlag == 0 ) {
		var url = getRootPath();//获取根路径
		//异步加载类目
		$.ajax({
			type : "POST", //请求方式  
			url : url+"/category.do", //请求路径 
			cache : false,
			dataType : 'json', //返回值类型 
			success : function(msg) {
				//将数据填充到模态框中
				if ( msg.status == 200 ) {
					console.log(msg.data1);
					fillCategory(msg.data1); //将获得的类目填充到下拉框中
					loadFlag = 1; //将判定变量设为1
				} else {
					alert("获取类目失败！");
				}
			},
			error : function() {
				alert("发生了一个未知错误。请刷新重试。");
			}
		});
	}
	
}

/**
 * 提交表单响应函数
 * @returns
 */
function form_item_submit() {
	console.log("更新");
	//构造基本地址
	var url = getRootPath();//获取根路径
	//获取商品编号标签的值
	var iid = $("#item_iid").val();
	//判断是新增商品还是修改商品（查看是否有iid值）
	if ( iid != null && iid != "" ) {
		//不为空则是修改商品
		url = url + "/updateitem.do";
	} else {
		//为空则是新增商品
		url = url + "/additem.do";
	}
	//获取商品详情内容
	$("#item_content").val(item_editor.html());
	//规范商品价格。如2.5元转为250存储
	$("#item_price_standard").val( $("#item_price").val()*100 );
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url,
		data: $("#form_item").serialize(),//将表单数据序列化
		cache : false,
		dataType : 'json', //返回值类型
		success : function(msg) {
			//结果回显
			if ( msg.status == 200 ) {
				$("#form_item_submit_status").text(msg.msg);
				$("#form_item_submit_status").show(600);
				var temp = function() {
					$("#form_item_submit_status").hide(1000);
				}
				setTimeout( temp, 2000);		
			}
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}

/**
 * 提交表单响应函数并退出
 * @returns
 */
function form_item_submit_and_exit() {
	form_item_submit();//提交表单
	$("#hidden-opera-item").fadeOut(1500);
	$("#opera-dialog").hide(800);
}
