/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
//分页显示DataTable
var table;

var userId;
//
// $(document).ready(function () {
//     table =
// });

//封装查询参数
function getQueryCondition(data) {
    var param = [];

    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    console.info(param.dateStartTime+","+param.dateEndTime+","+param.start+","+param.length+","+param.draw);
    return param;
}

var flag = true;
var applyType = null;
function search(type) {

    applyType = type;
    if(flag){
        table = $('#datatable').DataTable({
            "searching": true,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,

            ajax: function (data, callback, settings) {
                // //封装请求参数
                var param = getQueryCondition(data);
                //
                var startTime = $("#datetimeStart").val();
                var endTime = $("#datetimeEnd").val();
                if(startTime != null && startTime != ""){
                    var dateStartTime =new Date(new Date(startTime).Format('yyyy-MM-dd 00:00:00'));
                }
                if(endTime != null && endTime != ""){
                    var dateEndTime =new Date(endTime);
                }

                //组装分页参数
                var start = data.start;
                var length = data.length;
                var draw = data.draw;
                $.ajax({
                    type: "POST",
                    url: '/attendance/getApplyRecordByCondition',
                    cache: false,  //禁用缓存
                    data: {
                        dateStartTime:dateStartTime,
                        dateEndTime:dateEndTime,
                        start:start,
                        length:length,
                        draw:draw,
                        applyType:applyType
                    },
                    // data:param,   //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        console.info(result.data)
                        callback(result.data);
                    }
                });
            },

            "columns": [
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        // return meta.row+1;
                        return data;
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "workNumber"},
                {
                    "sClass": "text-center",
                    "data": "empName"},
                {
                    "sClass": "text-center",
                    "data": "startTime",
                    "render": function (data, type, full, meta) {
                        return new Date(data).Format('yyyy-MM-dd hh:mm');
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "endTime",
                    "render": function (data, type, full, meta) {
                        return new Date(data).Format('yyyy-MM-dd hh:mm');
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "applyDate",
                    "render": function (data, type, full, meta) {
                        return new Date(data).Format('yyyy-MM-dd');
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "applyDuration"},
                {
                    "sClass": "text-center",
                    "data": "status",
                    "render": function (data, type, full, meta) {
                        //                    class="btn btn-primary"
                        if(data== 0){
                            return '未审核'
                        }else if(data == 2){
                            return '批准'
                        }else if(data == 3){
                            return '不批准'
                        }else{
                            return '审核中'
                        }
                    },
                },
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        //                    class="btn btn-primary"
                        return '<button type="button" data-rowindex="'+meta.row+'" onclick="showUpdateModel('+meta.row+');" >修改/查看信息</button>';
                    },
                    "bSortable": false
                },
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
            },

        });
        flag = false;

    }else {
        table.ajax.reload();
    }

}

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
/**
 * 提交申请
 */
function addApply(type) {
    applyType = type;
    // alert("添加成功");
    var startTimeval = $('#add_startTime').val();
    var endTimeval = $('#add_endTime').val();
    var duration = $('#add_applyDuration').val();
    var applyCause = $('#add_applyCause').val();
    if(startTimeval != "" && endTimeval != "" && duration != "" && applyCause != ""){

        $.ajax({
            url:'/attendance/addApply',
            type:'POST',
            async: false,
            cache: false,
            data:{
                startTime:new Date(startTimeval),
                endTime:new Date(endTimeval),
                applyDuration:duration,
                applyCause:applyCause,
                applyType:applyType
            },
            dataType:'JSON',
            success:function (result) {
                console.info(result);
                if(result.code == 1){
                    layer.msg("提交申请成功");
                    $('#add_startTime').val(""),
                        $('#add_endTime').val(""),
                        $('#add_applyDuration').val(""),
                        $('#add_applyCause').val(""),
                        $('#addApply').modal('hide');
                    search(applyType);
                }else if(result.code == 0) {
                    alert(result.message);
                }else {
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
    $('#show_applyDuration').val(data.applyDuration);
    $('#show_applyCause').val(data.applyCause);
    if (data.status == 0) {
        $('#show_dealType').val("未审核");
        $('#show_startTime').attr("disabled", false);
        $('#show_endTime').attr("disabled", false);
        $('#show_applyDuration').attr("disabled", false);
        $('#show_dealType').attr("disabled", true);
        $('#show_applyCause').attr("disabled", false);
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
    $('#show_applyDuration').attr("disabled", true);
    $('#show_applyCause').attr("disabled", true);
    $('#show_dealType').attr("disabled", true);
    // $('#deal_person').attr("disabled",true);
    $('#show_notpassCause').attr("disabled", true);


}

function updateApplyDetail() {
    var id = window.document.getElementById("show_applyId").value;
    var startTimeval = $('#show_startTime').val();
    var endTimeval = $('#show_endTime').val();
    var duration = $('#show_applyDuration').val();
    var applyCause = $('#show_applyCause').val();
    // console.info(id+","+startTimeval+","+endTimeval+","+duration+","+applyCause);
    if (startTimeval != "" && endTimeval != "" && duration != "" && applyCause != "") {
        $.ajax({
            url:'/attendance/updateApply',
            type:'POST',
            async: false,
            cache: false,
            data:{
                id:id,
                startTime:new Date(startTimeval),
                endTime:new Date(endTimeval),
                applyDuration:duration,
                applyCause:applyCause
            },
            dataType:'JSON',
            success:function (result) {
                console.info(result);
                if(result.code == 1){
                    layer.msg("修改申请成功");
                    $('#showDetail').modal('hide');
                    search(applyType);
                }else if(result.code == 0) {
                    alert(result.message);
                }else{
                    alert("发生错误，请稍后重试!")
                }
            }
        });
    }else {
        alert("请假申请开始和结束时间、请假时长和原因都不能为空!");
    }
}


