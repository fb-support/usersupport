//分页显示DataTable
var launchForm_table;

$(document).ready(function () {
    launchForm_table = $('#launchTable').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = launchForm_getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/online-process/getLaunchFormByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);

                    //进行不同状态显示不同的操作按钮
                    $(".status_launchForm").each(function() {
                        switch($(this).attr("value")) {
                            case "0":
                                //待运维上线
                                $(this).parent().next().find(".btn-launchForm_opera[value='2']").show();
                            case "1":
                                //待运维重新上线
                                $(this).parent().next().find(".btn-launchForm_opera[value='2']").show();
                                break;
                            case "2":
                                //运维上线中...
                                $(this).parent().next().find(".btn-launchForm_opera[value='3']").show();
                                break;
                            case "3":
                                //待开发验证
                                $(this).parent().next().find(".btn-launchForm_opera[value='4']").show();
                                break;
                            case "4":
                                //开发验证中
                                $(this).parent().next().find(".btn-launchForm_opera[value='5']").show();
                                $(this).parent().next().find(".btn-launchForm_opera[value='6']").show();
                                break;
                            case "5":
                                //开发验证未通过，
                                $(this).parent().next().find(".btn-launchForm_opera[value='1']").show();
                                break;
                            case "6":
                                //开发验证通过
                                $(this).parent().next().find(".btn-launchForm_opera[value='7']").show();
                                break;
                            case "7":
                                //测试验证中...
                                $(this).parent().next().find(".btn-launchForm_opera[value='8']").show();
                                $(this).parent().next().find(".btn-launchForm_opera[value='9']").show();
                                break;
                            case "8":
                                //测试验证未通过
                                $(this).parent().next().find(".btn-launchForm_opera[value='1']").show();
                                break;
                            case "9":
                                //测试验证通过
                                $(this).parent().next().find(".btn-launchForm_opera[value='10']").show();
                                break;
                            case "10":
                                //完成，已关闭
                                $(this).parent().next().find(".info_launchForm_opera").show();
                                $(this).parent().next().find(".info_launchForm_opera")[0].innerHTML = "完成，已关闭";
                                $(this).parent().next().find(".info_launchForm_opera")[0].classList.add("label-success");
                                break;


                        }
                    });
                }
            });
        },

        "columns": [
            {"data": "formId"},
            {
                "sClass": "text-center",
                "data": "formContent",
                "render": function (data, type, full, meta) {
                    return '<div class="launchForm_formContent" title="' + data + '">' + data + '</div>';
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "gmtCreate",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {"data": "createUsername"},
            {"data": "acceptDevelopUsername"},
            {"data": "acceptTestUsername"},
            {
                "sClass": "text-center",
                "data": "formStatus",
                "render": function (data, type, full, meta) {
                    switch (data) {
                        case 0:
                            return '<span value="0" class="label label-default status_launchForm">待上线</span>';
                            break;
                        case 1:
                            return '<span value="1" class="label label-default status_launchForm">待重新上线</span>';
                            break;
                        case 2:
                            return '<span value="2" class="label label-primary status_launchForm">上线中...</span>';
                            break;
                        case 3:
                            return '<span value="3" class="label label-primary status_launchForm">待开发验证中...</span>';
                            break;
                        case 4:
                            return '<span value="4" class="label label-primary status_launchForm">开发验证中...</span>';
                            break;
                        case 5:
                            return '<span value="5" class="label label-danger status_launchForm">开发验证未通过</span>';
                            break;
                        case 6:
                            return '<span value="6" class="label label-success status_launchForm">开发验证通过</span>';
                            break;
                        case 7:
                            return '<span value="7" class="label label-primary status_launchForm">测试验证中...</span>';
                            break;
                        case 8:
                            return '<span value="8" class="label label-danger status_launchForm">测试验证未通过</span>';
                            break;
                        case 9:
                            return '<span value="9" class="label label-success status_launchForm">测试验证通过</span>';
                            break;
                        case 10:
                            return '<span value="10" class="label label-default status_launchForm">运维关闭工单</span>';
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
                    return '<button type="button" class="btn btn-danger btn-xs btn-launchForm_opera" value="1" onclick="updateLaunchForm(1, ' + data + ');" >Bug</button> ' +
                        '<button type="button" class="btn btn-info btn-xs btn-launchForm_opera" value="2" onclick="updateLaunchForm(2, ' + data + ');" >Accept</button> ' +
                        '<button type="button" class="btn btn-success btn-xs btn-launchForm_opera" value="3" onclick="updateLaunchForm(3, ' + data + ');" >OK</button> ' +
                        '<button type="button" class="btn btn-info btn-xs btn-launchForm_opera" value="4" onclick="updateLaunchForm(4, ' + data + ');" >Accept</button> ' +
                        '<button type="button" class="btn btn-danger btn-xs btn-launchForm_opera" value="5" onclick="updateLaunchForm(5, ' + data + ');" >Bug</button> ' +
                        '<button type="button" class="btn btn-success btn-xs btn-launchForm_opera" value="6" onclick="updateLaunchForm(6, ' + data + ');" >OK</button> ' +
                        '<button type="button" class="btn btn-info btn-xs btn-launchForm_opera" value="7" onclick="updateLaunchForm(7, ' + data + ');" >Accept</button> ' +
                        '<button type="button" class="btn btn-danger btn-xs btn-launchForm_opera" value="8" onclick="updateLaunchForm(8, ' + data + ');" >Bug</button> ' +
                        '<button type="button" class="btn btn-success btn-xs btn-launchForm_opera" value="9" onclick="updateLaunchForm(9, ' + data + ');" >OK</button> ' +
                        '<button type="button" class="btn btn-info btn-xs btn-launchForm_opera" value="10" onclick="updateLaunchForm(10, ' + data + ');" >Finish</button> ' +
                        '<span style="display: none;" class="label info_launchForm_opera"></span>';
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

function launchForm_search() {
    launchForm_table.ajax.reload();
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
function launchForm_getQueryCondition(data) {
    var param = {};
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    } else {
        param.projectId = $("#launchForm_projectId").val();
    }
    param.testFormId = $("#testFormSearch").val();//查询条件
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

//
function getTestForm() {
    //打开模态框
    $('#createLaunchForm').modal();

    // 封装参数
    var param = {};
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    } else {
        layer.msg("未绑定项目，无法获取项目下相关的提测工单");
    }

    $.ajax({
        type: "POST",
        url: '/online-process/getTestForm',
        cache: false,  //禁用缓存
        dataType: 'json',
        data: param,
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
                layer.msg('操作成功');
                $('#createLaunchForm').modal('hide');
                launchForm_search();
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

//修改上线工单
// function updateForm() {
//     $.ajax({
//         type: "POST",
//         url: '/online-process/updateLaunchForm',
//         cache: false,  //禁用缓存
//         data: $("#updateForm").serialize(),
//         dataType: 'json',
//         success: function (result) {
//             if (result.code == 1) {
//                 $('#updateLaunchForm').modal('hide');
//                 layer.msg('操作成功');
//                 launchForm_search();
//             } else {
//                 layer.msg(result.error);
//             }
//         }
//     });
//     status = null;
// }

/**
 * 修改上线工单状态
 */
function updateLaunchForm(status, formId) {
    var param = {};
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    }
    param.formStatus = status;
    param.formId = formId;
    $.ajax({
        type: "POST",
        url: '/online-process/updateLaunchForm',
        cache: false,  //禁用缓存
        data: param,
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#updateLaunchForm').modal('hide');
                layer.msg('操作成功');
                launchForm_search();
            } else {
                layer.msg("失败。请重试。");
            }
        }
    });
}

//时间戳转换日期格式
function getLocalTime(nS) {
    return new Date(parseInt(nS)).toLocaleString().substr(0, 9).replace("/", "-").replace("/", "-");
}
