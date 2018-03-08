$(function() {
//切换“发布公告”和“查看公告”
	$(".option_for_announcement").click(function() {
		if($(this).attr("value") == 1) {
			window.parent.run_deliverAnnouncement();
		} else if($(this).attr("value") == 2){
			window.parent.run_lookAnnouncement(1);
		}
	});
	
	/**
	 * 获取发布通告的消息类型。。并将其填充到hidden中
	 */
	$("input[name='notice_type']").click(function() {
		var value = $(this).val();
		$("#hidden_noticeType").val(value);
	});

})

/**
 * 获取查看发布通告的 时间区间，将其填充到hidden中
 * @returns
 */
function change_dateScope() {
	var value = $("#announcement_dateScope_select option:selected").val();
	$("#hidden_dateScope").val(value);
}
