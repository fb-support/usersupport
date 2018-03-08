$(function() {
//切换“发布公告”和“查看公告”
	$(".option_for_announcement").click(function() {
		if($(this).attr("value") == 1) {
			window.parent.run_deliverAnnouncement();
		} else if($(this).attr("value") == 2){
			window.parent.run_lookAnnouncement(1);
		}
	});
//选择发布公告的类型--通知/公告/通知+公告
	$(".announcement_type").click(function() {
		$(".announcement_type_selected").removeClass("announcement_type_selected");
		$(this).addClass("announcement_type_selected");
		$("#hidden_niticeType").val($(this).attr("value"));
	});
})

/**
 * 提交表单---创建新公告/通知
 * @returns
 */
function form_announcement_add_submit() {
	//获取根路径
	var url = getRootPath();
	//获取通告详情内容
	$("#announcement_editor_content").val(announcement_editor.html());
	//调用ajax查询数据。发回json数据
	$.ajax({
		type : "POST", //请求方式  
		url : url+"/notice/addnotice.do", //请求路径 
		cache : false,
		data: $("#form_add_announcement").serialize(),
		dataType : 'json', //返回值类型 
		success : function(msg) {
			console.log(msg);
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
}