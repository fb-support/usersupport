//分页显示DataTable
var table;

var userId;
//封装查询参数
function getQueryCondition(data) {
    var param = {};
    var startTime = $("#datetimeStart").val();
    var endTime = $("#datetimeEnd").val();
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
var flag = true;
function overTimeSearch() {
    if (flag) {
        table=$('#datatable').DataTable({
            "searching": false,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,

            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = getQueryCondition(data);

                $.ajax({
                    type: "POST",
                    url: '/overTime/search',
                    cache: false,  //禁用缓存
                    data: param,   //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        callback(result.data);
                    }
                });
            },

            "columns": [
                /*{
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                    "bSortable": false
                },*/

                {"data": "id"},
                {"data": "workNumber"},
                {"data": "empName"},
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
                {"data": "duration"},
                {
                    "sClass": "text-center",
                    "data": "status",
                    "render": function (data, type, full, meta) {
                        //                    class="btn btn-primary"
                        if (data == 0) {
                            return '未审核'
                        } else if (data == 1) {
                            return '批准'
                        } else {
                            return '不批准'
                        }
                    },
                },
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<button type="button" data-rowindex="' + meta.row + '" onclick="showUpdateModel('+ meta.row +');" >修改/查看信息</button>';
                    },
                    "bSortable": false
                },
            ],
            columnDefs: [
                {"orderable": false, "targets": 1},
                {"orderable": false, "targets": 2},
                {"orderable": false, "targets": 3},
                {"orderable": false, "targets": 4},
                {"orderable": false, "targets": 5},
                {"orderable": false, "targets": 6},
                {"orderable": false, "targets": 7},
                {"orderable": false, "targets": 8}
            ],

            /*是否开启主题*/
            "bJQueryUI": true,
            "oLanguage": {    // 语言设置
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
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
    } else {
        table.ajax.reload();
    }
}
    /**
     * 修改和查看详情信息的显示模态框
     * @param id
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

function showUpdateModel(rowindex) {
    $('#updateDetail').modal();
    var data = $('#datatable').DataTable().rows(rowindex).data()[0];

    $('#show_applyId').val(data.id);
    $('#show_workNumber').val(data.workNumber);
    $('#show_empName').val(data.empName);

    $('#show_workNumber').attr("disabled", true);
    $('#show_empName').attr("disabled", true);

    $('#show_startTime').val(new Date(data.startTime).Format('yyyy-MM-dd hh:mm'));
    $('#show_endTime').val(new Date(data.endTime).Format('yyyy-MM-dd hh:mm'));
    $('#show_applyDate').attr("readOnly", true);

    $('#show_applyDate').val(new Date(data.applyDate).Format('yyyy-MM-dd'));
    $('#show_duration').val(data.duration);
    $('#show_leaveCause').val(data.applyCause);
    $('#show_notpassCause').val(data.notpassCause);
    if (data.status == 0) {
        $('#show_dealType').val("未审核");
        $('#show_startTime').attr("disabled", false);
        $('#show_endTime').attr("disabled", false);
        $('#show_duration').attr("disabled", false);
        $('#show_dealType').attr("disabled", true);
        $('#show_leaveCause').attr("disabled", false);
        $('#updateApplyDetail').removeClass('hide');
        $('#show_notpassCause').addClass('hide');
        $('#notpassCause').addClass('hide');
    } else if (data.status == 2) {
        $('#show_dealType').val("通过");
        $('#show_notpassCause').addClass('hide');
        $('#notpassCause').addClass('hide');
        detailModelstyle();
    } else if (data.status == 3) {
        $('#show_dealType').val("未通过");
        //待定
        // $('#show_person').val(data);
        detailModelstyle();
        $('#notpassCause').removeClass('hide');
        $('#show_notpassCause').removeClass('hide');
    } else {
        $('#show_dealType').val("审核中");
        detailModelstyle();
        $('#show_notpassCause').addClass('hide');
        $('#notpassCause').addClass('hide');
    }
}
/**
 * 模态框样式
 */
function detailModelstyle() {
    $('#deal_person').prev().removeClass('hide');
    $('#deal_person').removeClass('hide');

    // $('#notpassCause').removeClass('hide');
    $('#updateApplyDetail').addClass('hide');

    //    设只读
    $('#show_startTime').attr("disabled", true);
    $('#show_endTime').attr("disabled", true);
    $('#show_applyDate').attr("disabled", true);
    $('#show_duration').attr("disabled", true);
    $('#show_leaveCause').attr("disabled", true);
    $('#show_dealType').attr("disabled", true);
    $('#deal_person').attr("disabled", true);
    $('#show_notpassCause').attr("disabled", true);

}
function updateApplyDetail() {

    var id = window.document.getElementById("show_applyId").value;
    var startTimeval = $('#show_startTime').val();
    var endTimeval = $('#show_endTime').val();
    var duration = $('#show_duration').val();
    var applyCause = $('#show_leaveCause').val();
    if (startTimeval != "" && endTimeval != "" && duration != "" && applyCause != "") {
        $.ajax({
            url:'/attendance/updateOverTime',
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
                    $('#updateDetail').modal('hide');
                    overTimeSearch();
                }else{
                    alert("发生错误，请稍后重试!")
                }
            }
        });
    }else {
        alert("请假申请开始和结束时间、请假时长和原因都不能为空!");
    }
}


