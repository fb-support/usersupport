//分页显示DataTable
var table;

$(document).ready(function () {
    table = $('#launchTable').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/online-process/getLaunchFormByPage',
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
            {"data": "formContent"},
            {"data": "gmtCreate"},
            {"data": "createUsername"},
            {"data": "acceptDevelopUsername"},
            {"data": "acceptTestUsername"},
            {
                "sClass": "text-center",
                "data": "formStatus",
                "render": function (data, type, full, meta) {
                    switch (data) {
                        case 0:
                            return "待上线";
                            break;
                        case 1:
                            return "运维人员已接单";
                            break;
                        case 2:
                            return "上线完成待验证";
                            break;
                        case 3:
                            return "开发验证未通过";
                            break;
                        case 4:
                            return "开发验证通过";
                            break;
                        case 5:
                            return "测试验证未通过";
                            break;
                        case 6:
                            return "测试验证通过";
                            break;
                        case 7:
                            return "运维关闭工单";
                            break;
                        default:
                            break;
                    }
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "formId",
                "render": function (data, type, full, meta) {
//                    class="btn btn-primary"
                    return '<button type="button" data-toggle="modal" data-target="#updateLaunchForm" onclick="showLaunchForm(' + data + ');" >修改信息</button>';
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
        ],
    });
});

function search() {
    table.ajax.reload();
}

//封装查询参数
function getQueryCondition(data) {
    var param = {};
    param.projectId = $("#projectIdSearch").val();//查询条件
    param.testFormId = $("#testFormSearch").val();//查询条件
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

//
function getTestForm() {
    $.ajax({
        type: "GET",
        url: '/online-process/getTestForm',
        cache: false,  //禁用缓存
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                var html = "";
                for (var i = 0; i < result.data.length; i++) {    //遍历data数组
                    var time = getLocalTime(result.data[i].gmtCreate);
                    html += '<option>' + result.data[i].projectId + ',' + result.data[i].formId + ',' + result.data[i].formService + ',' + time + '</option>';
                }
                $("#testForm").html(html);
            } else {
                layer.msg(result.error);
            }
        }
    });
}

//创建上线工单
function createForm() {
    var param = {};
    var arr = $("#testForm").val().split(",");
    param.formContent = $("#formContent").val();
    param.projectId = arr[0];
    param.testFormId = arr[1];
    $.ajax({
        type: "POST",
        url: '/online-process/createLaunchForm',
        cache: false,  //禁用缓存
        data: param,
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#createLaunchForm').modal('hide');
                layer.msg('操作成功');
                table.ajax.reload();
            } else {
                layer.msg(result.error);
            }
        }
    });
}

var status;
//获取上线工单详情
function showLaunchForm(id) {
    var formId = {"formId": id}
    $.ajax({
        type: "GET",
        url: '/online-process/findByFormId',
        cache: false,  //禁用缓存
        data: formId,
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $("#formContent2").val(result.data.formContent);
                $("#formId").val(result.data.formId);
                status = result.data.formStatus;
            } else {
                layer.msg(result.error);
            }
        }
    });
}

function updateStatus(statusCode) {
    status = statusCode;
}


//修改上线工单
function updateForm() {
    var param = {};
   /* var arr = $("#testForm2").val().split(",");
    param.formContent = $("#formContent2").val();
    param.projectId = arr[0];
    param.testFormId = arr[1];*/
    param.formId = $("#formId").val();
    param.formStatus = status;
    param.formContent = $("#formContent2").val();
    $.ajax({
        type: "POST",
        url: '/online-process/updateLaunchForm',
        cache: false,  //禁用缓存
        data: $("#updateForm").serialize(),
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#updateLaunchForm').modal('hide');
                layer.msg('操作成功');
                table.ajax.reload();
            } else {
                layer.msg(result.error);
            }
        }
    });
    status = null;
}

//时间戳转换日期格式
function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().substr(0, 9).replace("/", "-").replace("/", "-");
}
