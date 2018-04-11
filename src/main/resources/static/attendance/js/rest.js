$(document).ready(function () {
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
    $('#show_startTime').datetimepicker({
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
            $("#m_endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
        } else {
            $("#m_endTime").datetimepicker('setStartDate', null);
        }
    });
    $('#show_endTime').datetimepicker({
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
            $("#m_startTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
        } else {
            $("#m_startTime").datetimepicker('setEndDate', new Date());
        }
    });

    //日期插件初始化
    $('#addRestStartTime').datetimepicker({
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
            $("#addRestEndTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
        } else {
            $("#addRestEndTime").datetimepicker('setStartDate', null);
        }
    });
    $('#addRestEndTime').datetimepicker({
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
            $("#addRestStartTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
        } else {
            $("#addRestStartTime").datetimepicker('setEndDate', new Date());
        }
    });
})

//分页显示DataTable
var table;

/**
 * 显示添加的模态框
 */
function showAddModel() {
    $('#addRestModel').modal();
}

/**
 * 清空添加申请的表单数据
 */
function resetAddForm(){
    $("#addRestForm")[0].reset();
}

/**
 * ajax异步请求提交添加申请的表单
 */
function addApply(){
    var startTime = new Date($("#addRestStartTime").val());
    var endTime = new Date($("#addRestEndTime").val());
    var applyDuration = $("#applyDuration").val();
    var applyCause = $("#applyCause").val();

    $.ajax({
        async: true,
        url: "/attendance/addRestApply",
        type: "post",
        data: {startTime:startTime,endTime:endTime,applyDuration:applyDuration,applyCause:applyCause},
        dataType: "json",
        success: function (result) {
            if(result.code == 1){
                layer.msg("申请提交成功");
            }else {
                layer.msg("申请提交失败，请重新提交");
            }
            $('#addRestModel').modal('hide');
        },
        error: function (err) {
            layer.msg("申请提交失败，请重新提交");
            $('#addRestModel').modal('hide');
        }
    });
}

var flag = true;
/**
 * 查询当前用户下的调休申请记录
 * @param id
 */
function search() {
    if(flag){
        table = $('#datatable').DataTable({
            "searching": false,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,
            // 表格填充数据来源，使用ajax异步请求后台获取数据
            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = getQueryCondition(data);
                $.ajax({
                    type: "GET",
                    url: '/attendance/getRestApply',
                    cache: false,  //禁用缓存
                    data: param,    //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        callback(result.data);
                    }
                });
            },
            "columns": [
                {"data": "id"},
                {"data": "workNumber"},
                {"data": "empName"},
                {
                    "data": "startTime",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data),"yyyy-MM-dd HH:mm")
                    }
                },
                {
                    "data": "endTime",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data),"yyyy-MM-dd HH:mm")
                    }
                },
                {
                    "data": "applyDate",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data),"yyyy-MM-dd")
                    }
                },
                {
                    "data": "status",
                    "render": function (data, type, full, meta) {
                        //类型：0导航菜单；1操作按钮。
                        switch (data) {
                            case 0:
                                return "待审核";
                                break;
                            case 1:
                                return "审核中";
                                break;
                            case 2:
                                return "已通过";
                                break;
                            case 3:
                                return "未通过";
                                break;
                        }
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<button type="button" onclick="showDetailModel(' + meta.row + ');" >查看详情</button>';
                    },
                    "bSortable": false
                }
            ],
            /*是否开启主题*/
            "bJQueryUI": true,
            "oLanguage": {    // 语言设置
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "检索:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
            }
        });
        flag = false;
    }else {
        table.ajax.reload();
    }

}

//封装查询参数
function getQueryCondition(data) {
    var param = {};
    var startTime = $("#datetimeStart").val();
    var endTime = $("#datetimeEnd").val()
    if(startTime != null && startTime != ""){
        param.dateStartTime =new Date(startTime);
    }
    if(endTime != null && endTime != ""){
        param.dateEndTime =new Date(endTime);
    }
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

/**
 * 查看信息的显示模态框
 * @param id
 */
function showDetailModel(rowindex) {
    $('#showDetail').modal();
    var data = $('#datatable').DataTable().rows(rowindex).data()[0];
    $('#show_applyId').val(data.id);
    $('#show_workNumber').val(data.workNumber);
    $('#show_empName').val(data.empName);
    $('#show_startTime').val(Format(new Date(data.startTime),"yyyy-MM-dd HH:mm"));
    $('#show_endTime').val(Format(new Date(data.endTime),"yyyy-MM-dd HH:mm"));
    $('#show_applyDate').val(Format(new Date(data.applyDate),"yyyy-MM-dd"));
    $('#show_applyDuration').val(data.applyDuration);
    $('#show_applyCause').val(data.applyCause);
    $('#show_notpassCause').val(data.notpassCause);
    if(data.status == 0){
        $('#show_status').val("待审核");
        $('#show_startTime').attr("disabled",false);
        $('#show_endTime').attr("disabled",false);
        $('#show_applyDuration').attr("disabled",false);
        $('#show_applyCause').attr("disabled",false);
        $('#notpassCause').addClass('hide');
        $('#show_notpassCause').addClass('hide');
        $('#updateApply').attr("disabled",false);
    }else if(data.status == 3){
        $('#show_status').val("未通过");
        $('#show_startTime').attr("disabled",true);
        $('#show_endTime').attr("disabled",true);
        $('#show_applyDuration').attr("disabled",true);
        $('#show_applyCause').attr("disabled",true);
        $('#updateApply').attr("disabled",true);
        $('#notpassCause').removeClass('hide');
        $('#show_notpassCause').removeClass('hide');
        $('#updateApply').attr("disabled",true);
    }else{
        if(data.status == 1){
            $('#show_status').val("审核中");
        }else if(data.status == 2){
            $('#show_status').val("已通过");
        }
        $('#show_startTime').attr("disabled",true);
        $('#show_endTime').attr("disabled",true);
        $('#show_applyDuration').attr("disabled",true);
        $('#show_applyCause').attr("disabled",true);
        $('#updateApply').attr("disabled",true);
        $('#notpassCause').addClass('hide');
        $('#show_notpassCause').addClass('hide');
        $('#updateApply').attr("disabled",true);
    }
}

/**
 * 修改调休申请
 */
function updateRestApply() {
    var updateRestApplyData = getRestApplyCondition();
    $.ajax({
        async: true,
        url: "/attendance/updateRestApply",
        type: "post",
        data: updateRestApplyData,
        dataType: "json",
        success: function (result) {
            if(result.code == 1){
                layer.msg("修改成功成功");
            }else {
                layer.msg("修改失败，请稍后重试");
            }
            $('#showDetail').modal('hide');
        },
        error: function (err) {
            layer.msg("修改失败，请稍后重试");
            $('#showDetail').modal('hide');
        }
    });
}

//封装修改调休申请参数
function getRestApplyCondition() {
    var param = {};
    param.id = $("#show_applyId").val();
    var startTime = $("#show_startTime").val();
    var endTime = $("#show_endTime").val()
    if(startTime != null && startTime != ""){
        param.startTime =new Date(startTime);
    }
    if(endTime != null && endTime != ""){
        param.endTime =new Date(endTime);
    }
    param.applyDuration = $("#show_applyDuration").val();
    param.applyCause = $("#show_applyCause").val();
    return param;
}
