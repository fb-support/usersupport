/**
 * @author zhanguo.huang
 * @date 2018-04-02
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
var applyType = null;
var flag = true;
function search(type) {
    applyType = type;
    // alert(applyType);
    if(flag){
        table = $('#datatable').DataTable({
            "searching": false,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,
            ajax: function (data, callback, settings) {
                // //封装请求参数
                // var param = getQueryCondition(data);
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
                    url: '/attendance/applyDeal',
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
                    "data": "workNumber"
                },
                {
                    "sClass": "text-center",
                    "data": "empName"
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
                    "data": "applyType",
                    "render": function (data, type, full, meta) {
                        if(data == 0){
                            return "加班";
                        }else if(data == 1){
                            return "请假";
                        }else{
                            return "调休";
                        }
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "duration",
                    "render": function (data, type, full, meta) {
                        return data;
                    }
                },
                // {
                //     "sClass": "text-center",
                //     "data": "status",
                //     "render": function (data, type, full, meta) {
                //         //                    class="btn btn-primary"
                //         if(data == 0){
                //             return '未审核'
                //         }else if(data == 2){
                //             return '批准'
                //         }else if(data == 3){
                //             return '不批准'
                //         }else{
                //             return '审核中'
                //         }
                //     }
                // },
                {
                    "sClass": "text-center",
                    "data": "workNumber",
                    "render": function (data, type, full, meta) {
                        //                    class="btn btn-primary"
                        return '<button type="button" data-rowindex="'+meta.row+'" onclick="showUpdateModel('+meta.row+');" >查看详细信息</button>';
                    }
                },
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        //                    class="btn btn-primary"
                        return '<button type="button" onclick="passApply('+data+');" >批准</button>'+
                            '<button type="button" onclick="showReject('+data+');" >不批准</button>';
                    }
                }
            ],
            columnDefs: [
                {"orderable": false, "targets": 1},
                {"orderable": false, "targets": 2},
                {"orderable": false, "targets": 3},
                {"orderable": false, "targets": 4},
                {"orderable": false, "targets": 5},
                {"orderable": false, "targets": 6}
                // {"orderable": false, "targets": 7}
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

/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 * @constructor
 */
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
    // $('#show_applyId').val(data.id);
    $('#show_workNumber').val(data.workNumber);
    $('#show_empName').val(data.empName);

    $('#show_workNumber').attr("disabled", true);
    $('#show_empName').attr("disabled", true);

    $('#show_startTime').val(new Date(data.startTime).Format('yyyy-MM-dd hh:mm'));
    $('#show_startTime').attr("disabled", true);
    // alert(data.startTime);
    $('#show_endTime').val(new Date(data.endTime).Format('yyyy-MM-dd hh:mm'));
    $('#show_endTime').attr("disabled", true);

    if(data.applyType == 0){
        $('#show_applyType').val("加班");
    }else if(data.applyType == 1){
        $('#show_applyType').val("请假");
    }else{
        $('#show_applyType').val("调休");
    }
    $('#show_applyType').attr("disabled", true);
    // alert(data.endTime);
    $('#show_applyDate').attr("disabled", true);
    $('#show_applyDate').val(new Date(data.applyDate).Format('yyyy-MM-dd'));
    $('#show_duration').val(data.duration);
    $('#show_duration').attr("disabled", true);
    $('#show_leaveCause').val(data.applyCause);
    $('#show_leaveCause').attr("disabled", true);
}

/**
 * 模态框样式
 */
// function detailModelstyle() {
//     // $('#deal_person').prev().removeClass('hide');
//     // $('#deal_person').removeClass('hide');
//
//     // $('#notpassCause').removeClass('hide');
//     $('#updateApplyDetail').addClass('hide');
//     $('#show_notpassCause').addClass('hide');
//     $('#notpassCause').addClass('hide');
//     //    设只读
//     $('#show_startTime').attr("disabled", true);
//     $('#show_endTime').attr("disabled", true);
//     $('#show_applyDate').attr("disabled", true);
//     $('#show_duration').attr("disabled", true);
//     $('#show_leaveCause').attr("disabled", true);
//     $('#show_dealType').attr("disabled", true);
//     // $('#deal_person').attr("disabled",true);
//     $('#show_notpassCause').attr("disabled", true);
//
//
// }

/**
 * 批准申请
 * @param id
 */
function passApply(id) {
    $.ajax({
        url:'/attendance/passApply',
        type:'POST',
        data:{
            id:id
        },
        dataType:'JSON',
        success:function (result) {
            if(result.code == 1){
                search(applyType);
            }else{
                alert("发生未知错误,请稍后重试!!!");
            }
        }
    })
}

/**
 * 显示不批准原因模态框
 * @param id
 */
function showReject(id) {
    window.document.getElementById("dealRecordId").value = id;
    $('#rejectApply').modal();
}

/**
 * 不批准
 */
function rejectApply() {
    var id = window.document.getElementById("dealRecordId").value;
    var rejectCause = window.document.getElementById("rejectApplyCause").value;
    alert(id+','+rejectCause);

    $.ajax({
        url:'/attendance/rejectApply',
        type:'POST',
        data:{
            id:id,
            notpassCause:rejectCause
        },
        dataType:'JSON',
        success:function (result) {
            if(result.code == 1){
                $('#rejectApply').modal('hide');
                search(applyType);
            }else{
                alert("发生未知错误,请稍后重试!!!");
            }
        }
    })
}

// function searchTest() {
//     $.ajax({
//         type: "POST",
//         url: '/attendance/applyDeal',
//         cache: false,  //禁用缓存
//         data: {
//             applyType:1
//         },
//         // data:param,   //传入已封装的参数
//         dataType: "json",
//         success: function (result) {
//             console.info(result.data)
//         }
//     });
// }