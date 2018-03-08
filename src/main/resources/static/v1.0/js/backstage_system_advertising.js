$(function() {
	$(".replace-img").click(function() {
		var nextNode = $(this).nextAll();
		document.getElementById("advertising_set_sideFramework").style.right = "400px";
		//将nextNode中的内容填充到弹出框中
//		<input type="hidden" name="cid">
//		<input type="hidden" name="title">
//		<input type="hidden" name="alt">
//		<input type="hidden" name="location">
//		<input type="hidden" name="adImg">
		$("#ad_setAdid").val(nextNode[0].value);
		$("#ad_setTitle").val(nextNode[1].value);
		$("#ad_setAlt").val(nextNode[2].value);
		$("#ad_setUrl").val(nextNode[3].value);
		$("#ad_setImg").val(nextNode[4].value);
		var imgs = document.getElementsByClassName("show_ad_pic")//获得图片标签
		imgs[0].src = nextNode[4].value;
		//判定为哪个位置的广告位，做相应对应处理
		var headInfo = document.getElementsByClassName("t-location-name")[0]; //获得弹出框头说明标签
		var img_input = $("#input_uploadAD_Pic"); //获取上传图片标签
		imgs[0].setAttribute("display","inline");
		switch(nextNode[0].value) {
		case "1":
			headInfo.innerHTML = "&nbsp;&nbsp;左侧长广告位";
			imgs[0].style.width = "60px";
			imgs[0].style.height = "200px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		case "2":
			headInfo.innerHTML = "&nbsp;&nbsp;主页黄金大广告位";
			var delSpan = document.getElementsByClassName("btn_del_adPic");
			var strs = nextNode[4].value.split(",");
			for(var i=0;i<strs.length;i++) {
				if( strs[i] != "" ) {
					imgs[i].style.width = "200px";
					imgs[i].style.height = "60px";
					imgs[i].src=strs[i];
					imgs[i].style.display = "inline";
					delSpan[i].style.display = "inline";
				}
			}
			img_input.attr("multiple","multiple");
			console.log("修改onchange");
			$("#input_uploadAD_Pic").attr("onchange","javascript:uploadKingAdPics()");
			break;
		case "3":
			headInfo.innerHTML = "&nbsp;&nbsp;中部长广告位";
			imgs[0].style.width = "250px";
			imgs[0].style.height = "50px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		case "4":
			headInfo.innerHTML = "&nbsp;&nbsp;中右方广告位";
			imgs[0].style.width = "200px";
			imgs[0].style.height = "200px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		case "5":
			headInfo.innerHTML = "&nbsp;&nbsp;右侧长广告位";
			imgs[0].style.width = "60px";
			imgs[0].style.height = "200px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		case "6":
			headInfo.innerHTML = "&nbsp;&nbsp;强推高流量黄金位";
			imgs[0].style.width = "200px";
			imgs[0].style.height = "200px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		case "7":
		case "8":
		case "9":
		case "10":
			headInfo.innerHTML = "&nbsp;&nbsp;小推荐位";
			imgs[0].style.width = "150px";
			imgs[0].style.height = "150px";
			imgs[1].src="";
			imgs[2].src="";
			break;
		}
	});
	
	$("#btn_save_ad").click(function() {
		var url = getRootPath();//获取根路径
		$.ajax({
			type: "POST",
			url: url+"/advertising/changeone.do",
		    data: $("#setAd-form").serialize(),
		    cache : false,
			dataType : 'json', //返回值类型
			success : function(msg) {
				if(msg.status == 200) {
					alert("修改成功!可刷新查看。");
					run_Advertising();//跳转到一览图，刷新。
				}
			}
		});
	});
});


//删除按钮，将原有广告图片删除（清除src内容，并设置为隐藏。）	
function delAdPic(event) {
	//获取当前标签
	var thisObj = event.target;
	//获取当前标签的父标签
	var parent = thisObj.parentElement;
	//删除当前对象
	parent.removeChild(thisObj.previousElementSibling);
	parent.removeChild(thisObj);
	//重新获取图片地址字符串存于hidden中
	var pic = document.getElementsByClassName("show_ad_pic");
	pic[0].className = "show_ad_pic pic1";
	var imgStr = "";
	for(var i=0;i<pic.length;i++ ) {
		if( pic[i].style.display == "inline" ) {
			imgStr = imgStr+pic[i].src+",";			
		}
	}
	$("#ad_setImg").val(imgStr);
	//往该区域添加个空对象
	$(".side_setAdvertising_body .t-uploadPic").append('<img class="show_ad_pic" src="" alt="" style="display:none">'+
				'<span class="btn_del_adPic" style="display:none" onclick="javascript:delAdPic(event)">删除</span>');
}

//上传广告图片文件按钮监听
function uploadAdPics() {
	
	var input = document.getElementById("input_uploadAD_Pic");
	var hadPic = document.getElementsByClassName("show_ad_pic"); //获取图片标签
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
					//回显
					document.getElementsByClassName("show_ad_pic")[0].src = msg[i].url;
					$("#ad_setImg").val(msg[i].url);
				} else{
					alert("加载失败！");
				}
			}
		}
	});
}

//黄金大广告位上传广告图片文件按钮监听
function uploadKingAdPics() {
	console.log("触发黄金大广告位图片改变监听函数");
	var input = document.getElementById("input_uploadAD_Pic");
	var hadPic = document.getElementsByClassName("show_ad_pic"); //获取图片标签
	var hadPic_count = 0;
	for(var i=0; i<3;i++) {
		if( hadPic[i].style.display == "inline" ) {
			hadPic_count++;
		}
	}
	var formdata = new FormData();
	for (var i = 0; i < input.files.length; i++) {
		if( i == 3-hadPic ) {
			break;
		}
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
			var imgStr = $("#ad_setImg").val();
			for ( var i = 0; i < msg.length; i++ ) {
				if ( msg[i].error == 200 ) {
					//回显
					var temp_pic = document.getElementsByClassName("show_ad_pic")[hadPic_count];
					var temp_btn = document.getElementsByClassName("btn_del_adPic")[hadPic_count];
					temp_pic.src = msg[i].url;
					temp_pic.style.display = "inline";
					temp_btn.style.display = "inline";
					hadPic_count++;
					imgStr = imgStr+msg[i].url+","						
				} else{
					alert("加载失败！");
				}
			}
			//拼接字符串
			$("#ad_setImg").val(imgStr);
		}
//		,
//		error : function() {
//			alert("加载失败，黄金广告位最多为三张广告图片，请删除后再添加！");
//		}
	});
}

