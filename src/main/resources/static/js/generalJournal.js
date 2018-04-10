//分页显示DataTable
var table3;

$(function () {
    var today = new Date();
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(0);

    $("#datetimeStart").val(Format(today, "yyyy-MM-dd HH:mm:ss"));
    $("#datetimeEnd").val(Format(new Date(), "yyyy-MM-dd HH:mm:ss"));

    table3 = $('#datatables').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/log/generalPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    if (result.code==202){alert(result.message)}
                    if (result.code==203){alert(result.message)}
                    if (result.code==204){alert(result.message)}
                    if (result.code == 200) {callback(result.data);}
                }
            });
        },
        "columns": [
            {
                "sClass": "text-center",
                "data": "id",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "journaltype",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "userid",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "nickname",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "serialnumber",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "starttime",
                "render": function (data, type, full, meta) {
                    return  formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "endtime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "relationid",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "inouttype",
                "render": function (data, type, full, meta) {
                    var inoutString;
                    if (data == 0) {
                        inoutString = "支出";
                    } else {
                        inoutString = "收入";
                    }
                    return inoutString;
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "amount",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "balance",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "remarks",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },{
                "sClass": "text-center",
                "data": "status",
                "render": function (data, type, full, meta) {
                    return validate(data);
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "createtime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "modifytime",
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
            {"orderable": false, "targets": 4},
            {"orderable": false, "targets": 5},
            {"orderable": false, "targets": 6},
            {"orderable": false, "targets": 7},
            {"orderable": false, "targets": 8},
            {"orderable": false, "targets": 9},
            {"orderable": false, "targets": 10},
            {"orderable": false, "targets": 11},
            {"orderable": false, "targets": 12},
            {"orderable": false, "targets": 13},
            {"orderable": false, "targets": 14},
        ],
    });

});

function search() {
    table3.ajax.reload();
}

//封装查询参数
function getQueryCondition(data) {
    var param = {};
    param.mobile = $("#mobile").val();//查询条件
    param.type = $("#type").val();//查询条件
    param.starttime = new Date($("#datetimeStart").val()).getTime();//查询条件
    param.endtime = new Date($("#datetimeEnd").val()).getTime();//查询条件
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

//null处理
var validate = function (data) {
    if (data == null) {
        return "";
    }
    return data;
}

//日期处理
function formatDate(now) {
    if (now == "") {
        return "";
    }
    return new Date(now).toLocaleString();
}