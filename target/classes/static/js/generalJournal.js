//分页显示DataTable
var table3;
var tap = 0;
$(function () {
    var today = new Date();
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(0);

    $("#datetimeStart").val(Format(today, "yyyy-MM-dd HH:mm:ss"));
    $("#datetimeEnd").val(Format(new Date(), "yyyy-MM-dd HH:mm:ss"));
});

function search() {
    // 参数验证
    var mobile = $("#mobile").val();

    var flag = (mobile == null || mobile == "");
    if(flag){
        layer.msg("手机号不能都为空");
        return;
    }
    if(mobile != null && mobile != ""){
        var mobileReg=/^[1][0-9]{10}$/;
        if(!mobileReg.test(mobile)){
            layer.msg("手机号格式不正确");
            return;
        }
    }
    var dateStart = $("#datetimeStart").val();
    var dateEnd = $("#datetimeEnd").val();
    if(dateStart != null && dateStart != "" && dateEnd != null && dateEnd != ""){
        if(new Date(dateEnd).getTime() < new Date(dateStart).getTime()){
            layer.msg("结束时间不能大于开始时间");
            return;
        }
    }

    if (tap != 0){
        table3.ajax.reload();
    }else {
        table3 = $('#datatables').DataTable({
            "searching": true, // 从结果搜索
            "bJQueryUI": true,
            "ordering" : true, // 排序
            "aaSorting": [1, "desc"], // 按creditId倒序排列
            "sPaginationType": "full_numbers",
            "serverSide": false, // true代表后台分页，false代表前台分页
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },

            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = getQueryCondition(data);
                // 开启遮罩效果
                $("#content").showLoading();
                $.ajax({
                    type: "GET",
                    url: '/log/generalPage',
                    cache: false,  //禁用缓存
                    data: param,    //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        // 关闭遮罩效果
                        $("#content").hideLoading();
                        if (result.code == 202) {
                            alert(result.message)
                        }
                        if (result.code == 203) {
                            alert(result.message)
                        }
                        if (result.code == 204) {
                            alert(result.message)
                        }
                        if (result.code == 200) {
                            callback(result.data);
                        }
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
                        return formatDate(validate(data));
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
                }, {
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
        tap++;
    }
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