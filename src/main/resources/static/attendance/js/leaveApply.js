/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
// $(document).ready(function () {
//     //日期插件初始化
//     $('#datetimeStart').datetimepicker({
//         language: 'zh-CN',
//         // format:'yyyy-mm-dd',
//         minView: "month", //选择日期后，不会再跳转去选择时分秒
//         format: 'yyyy-mm-dd',
//         todayBtn:  1,
//         autoclose: 1,
//         pickerPosition: "bottom-left"
//     }).on("changeDate", function (ev) {  //值改变事件
//         //选择的日期不能大于第二个日期控件的日期
//         if (ev.date) {
//             $("#datetimeEnd").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#datetimeEnd").datetimepicker('setStartDate', null);
//         }
//     });
//     window.document.getElementById("datetimeStart").value = new Date().Format("yyyy-MM-dd 00:00");
//     window.document.getElementById("datetimeEnd").value = new Date().Format("yyyy-MM-dd hh:mm");;
//     $('#datetimeEnd').datetimepicker({
//         language: 'zh-CN',
//         minView: "month", //选择日期后，不会再跳转去选择时分秒
//         format: 'yyyy-mm-dd',
//         todayBtn:  1,
//         autoclose: 1,
//         pickerPosition: "bottom-left",
//
//     }).on("changeDate", function (ev) {
//         /*选择的日期不能小于第一个日期控件的日期*/
//         if (ev.date) {
//             $("#datetimeStart").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#datetimeStart").datetimepicker('setEndDate', new Date());
//         }
//     });
//
// //日期插件初始化
//     $('#show_startTime').datetimepicker({
//         language: 'zh-CN',
//         // format:'yyyy-mm-dd',
//         format: 'yyyy-mm-dd hh:ii',
//         weekStart: 1, /*以星期一为一星期开始*/
//         todayBtn: 1,
//         autoclose: 1,
//         // minView:2, /*精确到天*/
//         todayHighlight: 1,
//         startView: 2,
//         forceParse: 0,
//         showMeridian: 1,
//         pickerPosition: "bottom-left"
//     }).on("changeDate", function (ev) {  //值改变事件
//         //选择的日期不能大于第二个日期控件的日期
//         if (ev.date) {
//             $("#show_endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#show_endTime").datetimepicker('setStartDate', null);
//         }
//     });
//     $('#show_endTime').datetimepicker({
//         language: 'zh-CN',
//         format: 'yyyy-mm-dd hh:ii',
//         weekStart: 1, /*以星期一为一星期开始*/
//         todayBtn: 1,
//         autoclose: 1,
//         // minView:2, /*精确到天*/
//         todayHighlight: 1,
//         startView: 2,
//         forceParse: 0,
//         showMeridian: 1,
//         pickerPosition: "bottom-left"
//     }).on("changeDate", function (ev) {
//         /*选择的日期不能小于第一个日期控件的日期*/
//         if (ev.date) {
//             $("#show_startTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#show_startTime").datetimepicker('setEndDate', new Date());
//         }
//     });
//
//     //日期插件初始化
//     $('#add_startTime').datetimepicker({
//         language: 'zh-CN',
//         // format:'yyyy-mm-dd',
//         format: 'yyyy-mm-dd hh:ii',
//         weekStart: 1, /*以星期一为一星期开始*/
//         todayBtn: 1,
//         autoclose: 1,
//         // minView:2, /*精确到天*/
//         todayHighlight: 1,
//         startView: 2,
//         forceParse: 0,
//         showMeridian: 1,
//         pickerPosition: "bottom-left"
//     }).on("changeDate", function (ev) {  //值改变事件
//         //选择的日期不能大于第二个日期控件的日期
//         if (ev.date) {
//             $("#add_endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#add_endTime").datetimepicker('setStartDate', null);
//         }
//     });
//     $('#add_endTime').datetimepicker({
//         language: 'zh-CN',
//         format: 'yyyy-mm-dd hh:ii',
//         weekStart: 1, /*以星期一为一星期开始*/
//         todayBtn: 1,
//         autoclose: 1,
//         // minView:2, /*精确到天*/
//         todayHighlight: 1,
//         startView: 2,
//         forceParse: 0,
//         showMeridian: 1,
//         pickerPosition: "bottom-left"
//     }).on("changeDate", function (ev) {
//         /*选择的日期不能小于第一个日期控件的日期*/
//         if (ev.date) {
//             $("#add_startTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
//         } else {
//             $("#add_startTime").datetimepicker('setEndDate', new Date());
//         }
//     });
//
//
// })

$(".checkall").click(function () {
    // var check = $(this).prop("checked");
    $(".checkchild").attr("checked", true);
});

/**
 * 添加的显示模态框
 * @param id
 */
function showAddModel() {
    $('#addApply').modal();
}
//封装查询参数
function getQueryCondition(data) {
    var param = {};
    param.username = $("#name-search").val();//查询条件
    param.workNumber = $("#work-search").val();//查询条件
    param.phone = $("#phone-search").val();//查询条件
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

/**
 * 提交申请
 */
function addApply() {
    // alert("添加成功");
    var startTimeval = $('#add_startTime').val();
    var endTimeval = $('#add_endTime').val();
    var duration = $('#timeLength').val();
    var applyCause = $('#add_leaveCause').val();
    if(startTimeval != "" && endTimeval != "" && duration != "" && applyCause != ""){

        $.ajax({
            url:'/attendance/addLeaveApply',
            type:'POST',
            async: false,
            cache: false,
            data:{
                startTime:new Date(startTimeval),
                endTime:new Date(endTimeval),
                duration:duration,
                applyCause:applyCause
            },
            dataType:'JSON',
            success:function (result) {
                console.info(result);
                if(result.code == 1){
                    layer.msg("提交申请成功");
                    $('#add_startTime').val(""),
                    $('#add_endTime').val(""),
                    $('#timeLength').val(""),
                    $('#add_leaveCause').val(""),
                    $('#addApply').modal('hide');
                    search();

                }else{
                    alert("发生错误，请稍后重试!")
                }
            }
        });
    }else {
        alert("请假申请开始和结束时间、请假时长和原因都不能为空!")
    }

}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 修改和查看详情信息的显示模态框
 * @param id
 */
function showUpdateModel(rowindex) {
    $('#showDetail').modal();
    var data = $('#datatable').DataTable().rows(rowindex).data()[0];
    // console.info($('#datatable').DataTable().rows(rowindex).data()[0]);
    // console.info(rowindex);
    $('#show_applyId').val(data.id);
    $('#show_workNumber').val(data.workNumber);
    $('#show_empName').val(data.empName);

    $('#show_workNumber').attr("disabled", true);
    $('#show_empName').attr("disabled", true);

    $('#show_startTime').val(new Date(data.startTime).Format('yyyy-MM-dd hh:mm'));
    // alert(data.startTime);
    $('#show_endTime').val(new Date(data.endTime).Format('yyyy-MM-dd hh:mm'));
    // alert(data.endTime);
    $('#show_applyDate').attr("readOnly", true);

    $('#show_applyDate').val(new Date(data.applyDate).Format('yyyy-MM-dd'));
    $('#show_duration').val(data.duration);
    $('#show_leaveCause').val(data.applyCause);
    if (data.status == 0) {
        $('#show_dealType').val("未审核");
        $('#show_startTime').attr("disabled", false);
        $('#show_endTime').attr("disabled", false);
        $('#show_duration').attr("disabled", false);
        $('#show_dealType').attr("disabled", true);
        $('#show_leaveCause').attr("disabled", false);
        $('#updateApplyDetail').removeClass('hide');
    } else if (data.status == 2 ) {
        $('#show_dealType').val("批准");
        detailModelstyle();
    }else if(data.status == 3){
        $('#show_dealType').val("不批准");
        //待定
        // $('#show_person').val(data);
        detailModelstyle();
        $('#notpassCause').removeClass('hide');
        $('#show_notpassCause').removeClass('hide');
        $('#show_notpassCause').val(data.notpassCause);
    }else{
        $('#show_dealType').val("审核中");
        detailModelstyle();
        // $('#deal_person').prev().addClass('hide');
        // $('#deal_person').addClass('hide');
    }
}

/**
 * 模态框样式
 */
function detailModelstyle() {
    // $('#deal_person').prev().removeClass('hide');
    // $('#deal_person').removeClass('hide');

    // $('#notpassCause').removeClass('hide');
    $('#updateApplyDetail').addClass('hide');
    $('#show_notpassCause').addClass('hide');
    $('#notpassCause').addClass('hide');
    //    设只读
    $('#show_startTime').attr("disabled", true);
    $('#show_endTime').attr("disabled", true);
    $('#show_applyDate').attr("disabled", true);
    $('#show_duration').attr("disabled", true);
    $('#show_leaveCause').attr("disabled", true);
    $('#show_dealType').attr("disabled", true);
    // $('#deal_person').attr("disabled",true);
    $('#show_notpassCause').attr("disabled", true);


}

function updateApplyDetail() {
    var id = window.document.getElementById("show_applyId").value;
    var startTimeval = $('#show_startTime').val();
    var endTimeval = $('#show_endTime').val();
    var duration = $('#show_duration').val();
    var applyCause = $('#show_leaveCause').val();
    // console.info(id+","+startTimeval+","+endTimeval+","+duration+","+applyCause);
    if (startTimeval != "" && endTimeval != "" && duration != "" && applyCause != "") {
        $.ajax({
            url:'/attendance/updateApplyRecord',
            type:'POST',
            async: false,
            cache: false,
            data:{
                id:id,
                startTime:new Date(startTimeval),
                endTime:new Date(endTimeval),
                duration:duration,
                applyCause:applyCause
            },
            dataType:'JSON',
            success:function (result) {
                console.info(result);
                if(result.code == 1){
                    layer.msg("修改申请成功");
                    $('#showDetail').modal('hide');
                    search();
                }else{
                    alert("发生错误，请稍后重试!")
                }
            }
        });
    }else {
        alert("请假申请开始和结束时间、请假时长和原因都不能为空!");
    }
}
