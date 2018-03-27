//日期插件初始化
$('#datetimeStart').datetimepicker({
    language: 'zh-CN',
    // format:'yyyy-mm-dd',
    format: 'yyyy-mm-dd hh:ii',
    weekStart: 1, /*以星期一为一星期开始*/
    todayBtn: 1,
    autoclose: 1,
    // minView:2, /*精确到天*/
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    pickerPosition: "bottom-left"
}).on("changeDate", function (ev) {  //值改变事件
    //选择的日期不能大于第二个日期控件的日期
    if (ev.date) {
        $("#datetimeEnd").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
    } else {
        $("#datetimeEnd").datetimepicker('setStartDate', null);
    }
});
$('#datetimeEnd').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii',
    weekStart: 1, /*以星期一为一星期开始*/
    todayBtn: 1,
    autoclose: 1,
    // minView:2, /*精确到天*/
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    pickerPosition: "bottom-left"
}).on("changeDate", function (ev) {
    /*选择的日期不能小于第一个日期控件的日期*/
    if (ev.date) {
        $("#datetimeStart").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
    } else {
        $("#datetimeStart").datetimepicker('setEndDate', new Date());
    }
});

//日期插件初始化
$('#datetimeStart2').datetimepicker({
    language: 'zh-CN',
    // format:'yyyy-mm-dd',
    format: 'yyyy-mm-dd hh:ii',
    weekStart: 1, /*以星期一为一星期开始*/
    todayBtn: 1,
    autoclose: 1,
    // minView:2, /*精确到天*/
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    pickerPosition: "bottom-left"
}).on("changeDate", function (ev) {  //值改变事件
    //选择的日期不能大于第二个日期控件的日期
    if (ev.date) {
        $("#datetimeEnd2").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
    } else {
        $("#datetimeEnd2").datetimepicker('setStartDate', null);
    }
});
$('#datetimeEnd2').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii',
    weekStart: 1, /*以星期一为一星期开始*/
    todayBtn: 1,
    autoclose: 1,
    // minView:2, /*精确到天*/
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    pickerPosition: "bottom-left"
}).on("changeDate", function (ev) {
    /*选择的日期不能小于第一个日期控件的日期*/
    if (ev.date) {
        $("#datetimeStart2").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
    } else {
        $("#datetimeStart2").datetimepicker('setEndDate', new Date());
    }
});

$(".checkall").click(function () {
    var check = $(this).prop("checked");
    $(".checkchild").prop("checked", check);
});

/**
 * 修改信息的显示模态框
 * @param id
 */
function showUpdateModel(id) {
    userId = id;
    $.ajax({
        type: "GET",
        url: '/um/getByUserId?userId=' + id,
        cache: false,  //禁用缓存
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#updateDetail').modal();
                $('#m_workNumber').val(result.data.workNumber);
                $('#m_empName').val(result.data.empName);
                $('#m_startTime').val(result.data.startTime);
                $('#m_endTime').val(result.data.endTime);
                $('#m_applyDate').val(result.data.applayDate);
                $('#m_leaveCause').val(result.data.leaveCause);
                if (result.data.dealType == 0) {
                    $('#m_dealType').val("未审核");
                    $('#m_person').val();
                } else if (result.data.dealType == 2) {
                    $('#m_dealType').val("未通过");
                    //待定
                    $('#m_person').val(result.data);
                }

            } else {
                layer.msg(result.error);
            }
        }
    });
}

/**
 * 查看信息的显示模态框
 * @param id
 */
function showDetailModel(id) {
    userId = id;
    $.ajax({
        type: "GET",
        url: '/um/getByUserId?userId=' + id,
        cache: false,  //禁用缓存
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#showDetail').modal();
                $('#show_workNumber').val(result.data.workNumber);
                $('#show_empName').val(result.data.empName);
                $('#show_startTime').val(result.data.startTime);
                $('#show_endTime').val(result.data.endTime);
                $('#show_applyDate').val(result.data.applayDate);
                $('#show_leaveCause').val(result.data.leaveCause);
                if (result.data.dealType == 0) {
                    $('#show_dealType').val("未审核");
                    $('#show_person').val();
                } else if (result.data.dealType == 1) {
                    $('#show_dealType').val("通过");
                    //待定
                    $('#show_person').val(result.data);
                } else {
                    $('#show_dealType').val("未通过");
                    //待定
                    $('#show_person').val(result.data);
                    $("#show_notpassCause").removeClass("hide");
                    $("#notpassCause").removeClass("hide");
                    $('#show_notpassCause').val(result.data);

                }
            }
        }
    });
}
