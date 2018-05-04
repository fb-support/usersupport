$(function() {

    //日期插件初始化
    $('#datetimeStart').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        minView: 0,
        minuteStep:1
    }).on('changeDate',function(ev){
        var starttime=$("#datetimeStart").val();
        $("#datetimeEnd").datetimepicker('setStartDate',starttime);
        $("#datetimeStart").datetimepicker('hide');
    });
    $('#datetimeEnd').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        minView: 0,
        minuteStep:1
    });
});

//分页显示DataTable
var table;
$(document).ready(function () {
    table = $('#datatable_record').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/online-process/selectByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                }
            });
        },
        "columns": [
            {"data": "formId"},
            {
                "data": "formType",
                "render":function (formType,type,full,meta) {
                    if(formType==0){
                        return "项目";
                    }else if(formType==1){
                        return "测试工单";
                    } else {
                        return "上线工单";
                    }
                },
                "bSortable": false
            },
            {"data": "operatingPeople"},
            {"data": "operatingContent"},
            {
                "sClass": "text-center",
                "data": "gmtCreate",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
        ],
        columnDefs: [
            {"orderable": false, "targets": 0},
            {"orderable": false, "targets": 1},
            {"orderable": false, "targets": 2},
            {"orderable": false, "targets": 3},
            {"orderable": false, "targets": 4},
        ],
    });
});
function search() {
    table.ajax.reload();
}
//封装查询参数
function getQueryCondition(data) {
    var projectId;
    if(window.document.location.href.split("?") != null) {
        projectId = window.document.location.href.split("?")[1].split("=")[1];
    }

    var param = {};
    $("#project-search").val(projectId);
    param.projectId = projectId;//查询条件
    param.operatingPeople = $("#people-search").val();//查询条件
    param.formType = $("#record_form_type").val();//查询条件
    if(param.formType == -1){
        param.formType = null;
    }
    //查询时间
    if ($('#datetimeStart').val() != null && $('#datetimeStart').val().length > 0) {
        param.gmtCreate = parseData($('#datetimeStart').val());
    }
    //查询结束时间
    if ($('#datetimeEnd').val() != null && $('#datetimeEnd').val().length > 0) {
        param.gmtModify = parseData($('#datetimeEnd').val());
    }
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}
var validate = function (data) {
    if (data == null) {
        return "";
    }
    return data;
}

function formatDate(now) {
    if (now == "") {
        return "";
    }
    return new Date(now).toLocaleString();
}
//格式化
function parseData(str) {
    var timestamp = Date.parse(new Date(str));
    return timestamp;
}