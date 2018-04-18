//分页显示DataTable
var table;

$(document).ready(function () {
    //日期插件初始化
    $('#datetimeStart').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        weekStart: 1, /*以星期一为一星期开始*/
        todayBtn: 1,
        autoclose: 1,
        minView: 2, /*精确到天*/
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
        format: 'yyyy-mm-dd',
        weekStart: 1, /*以星期一为一星期开始*/
        todayBtn: 1,
        autoclose: 1,
        minView: 2, /*精确到天*/
        pickerPosition: "bottom-left"
    }).on("changeDate", function (ev) {
        /*选择的日期不能小于第一个日期控件的日期*/
        if (ev.date) {
            $("#datetimeStart").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
        } else {
            $("#datetimeStart").datetimepicker('setEndDate', new Date());
        }
    });

    table = $('#datatable').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/ul/getUserByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                }
            });
        },

        "columns": [
            {"data": "username"},
            {"data": "workNumber"},
            {
                "sClass": "text-center",
                "data": "loginTime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "logoutTime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
        ],
        columnDefs: [
            {"orderable": false, "targets": 1},
            {"orderable": false, "targets": 2},
            {"orderable": false, "targets": 3},
        ],

    });
});


function search() {
    table.ajax.reload();
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

//封装查询参数
function getQueryCondition(data) {
    var param = {};
    param.username = $("#name-search").val();
    param.workNumber = $("#work-search").val();
    if ($('#datetimeStart').val() != null && $('#datetimeStart').val().length > 0) {
        param.loginTime = parseData($('#datetimeStart').val());
    }
    if ($("#datetimeEnd").val() != null && $("#datetimeEnd").val().length > 0) {
        param.logoutTime = parseData($("#datetimeEnd").val());
    }
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

//格式化
function parseData(str) {
    var timestamp = Date.parse(new Date(str));
    return timestamp;
}

