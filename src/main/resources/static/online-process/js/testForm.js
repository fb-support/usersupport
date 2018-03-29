//分页显示DataTable
var table;
var formId;

$(document).ready(function () {
    table = $('#dt').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,
        "scrollX": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/tf/getFormByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                }
            });
        },

        "columns": [
            {"data": "formService"},
            {"data": "formBranch"},
            {"data": "sqlChange"},
            {"data": "configureChange"},
            {"data": "mqChange"},
            {
                "sClass": "text-center",
                "data": "isTest",
                "render": function (data, type, full, meta) {
                    if (data == 1) return '<span class="label label-success">已自测</span>';
                    else if (data == 0) return '<span class="label label-danger">未测试</span>';
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "isReview",
                "render": function (data, type, full, meta) {
                    if (data == 1) return '<span class="label label-success">已Review</span>';
                    else if (data == 0) return '<span class="label label-danger">未Review</span>';
                },
                "bSortable": false
            },
            {"data": "influenceScope"},
            {"data": "otherChange"},
            {"data": "createUsername"},
            {"data": "acceptUsername"},
            {
                "sClass": "text-center",
                "data": "formStatus",
                "render": function (data, type, full, meta) {
                    switch (data) {
                        case 0:
                            return '<span class="label label-default">未测试</span>';
                            break;
                        case 1:
                            return '<span class="label label-primary">测试中...</span>';
                            break;
                        case 2:
                            return '<span class="label label-danger">测试未通过</span>';
                            break;
                        case 3:
                            return '<span class="label label-success">测试通过</span>';
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
                "render": function (data, status, full, meta) {
                    return '<button type="button" class="btn btn-success btn-xs" onclick="updateStatus(3,'+data+')" >通过</button> ' +
                        '<br/>' +
                        '<button type="button" class="btn btn-danger btn-xs" onclick="updateStatus(2,'+data+')" >未通过</button>' +
                        '<br/>' +
                        '<button type="button" class="btn btn-info btn-xs" onclick="updateStatus(1,'+data+')" >确认测试</button>';

                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "formId",
                "render": function (data, status, full, meta) {
                    return '<button type="button" class="btn btn-info btn-sm" onclick="showModel('+data+')">修改工单</button>';

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
            {"orderable": false, "targets": 5},
            {"orderable": false, "targets": 6},
            {"orderable": false, "targets": 7},
            {"orderable": false, "targets": 8},
            {"orderable": false, "targets": 9},
            {"orderable": false, "targets": 10},
            {"orderable": false, "targets": 11},
            {"orderable": false, "targets": 12},
            {"orderable": false, "targets": 13},
        ],

    });
});

function search() {
    table.ajax.reload();
}

//封装查询参数
function getQueryCondition(data) {
    //TODO 拿到传入的projectId 根据projectId查询
    var param = {};
    param.formService = $("#service-search").val();//查询条件
    param.formStatus = $("#status-search").val();;
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

function getAddParam() {
    var param = {};
    param.projectId = 1;
    param.formService = $('#form_service').val();
    param.formBranch = $('#form_branch').val();
    param.influenceScope = $('#influence_scope').val();
    param.sqlChange = $('#sql_change').val();
    param.configureChange = $('#configure_change').val();
    param.mqChange = $('#mq_change').val();
    param.otherChange = $('#other_change').val();
    param.isTest = $('#is_test input[name="testMethod"]:checked ').val();
    param.isReview = $('#is_review input[name="reMethod"]:checked ').val();
    return param;
}

function getUpdateParam() {
    var param = {};
    param.projectId = 1;
    param.formService = $('#u_form_service').val();
    param.formBranch = $('#u_form_branch').val();
    param.influenceScope = $('#u_influence_scope').val();
    param.sqlChange = $('#u_sql_change').val();
    param.configureChange = $('#u_configure_change').val();
    param.mqChange = $('#u_mq_change').val();
    param.otherChange = $('#u_other_change').val();
    param.isTest = $('#u_is_test input[name="testMethod"]:checked ').val();
    param.isReview = $('#u_is_review input[name="reMethod"]:checked ').val();
    return param;
}

function saveForm() {
    //TODO 获取projectId，插入model

   var param = getAddParam();

    if (param.formService==null||param.formBranch==null||param.influenceScope==null
        ||param.sqlChange==null||param.configureChange==null||param.mqChange==null ||
        param.otherChange==null|| param.isTest==null||param.isReview==null)
    {
        layer.msg("请输入完整信息");
        return;
    }else{
        //新建
        $.ajax({
            type: "POST",
            url: '/tf/insertTestForm',
            cache: false,  //禁用缓存
            data: param,
            dataType: 'json',
            success: function (result) {
                if (result.code==1){
                    $('#addForm').modal('hide');
                    layer.msg("新建成功");
                    table.ajax.reload();
                }else {
                    $('#addForm').modal('hide');
                    layer.msg("新建失败");
                }
            }
        })
    }


}

function showModel(id) {

    formId = id;
    $.ajax({
        type: "GET",
        url: '/tf/getByFormId?formId=' + id,
        cache: false,  //禁用缓存
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#updateForm').modal();
                $('#u_form_service').val(result.data.formService);
                $('#u_form_branch').val(result.data.formBranch);
                $('#u_influence_scope').val(result.data.influenceScope);
                $('#u_sql_change').val(result.data.sqlChange);
                $('#u_configure_change').val(result.data.configureChange);
                $('#u_mq_change').val(result.data.mqChange);
                $('#u_other_change').val(result.data.otherChange);
                $('.radio_t').eq(result.data.isTest).attr("checked",true);
                $('.radio_r').eq(result.data.isReview).attr("checked",true);
                formId = result.data.formId;
            } else {
                layer.msg(result.error);
            }
        }
    });
}

function updateForm() {
    var param = getUpdateParam();

    if (param.formService==null||param.formBranch==null||param.influenceScope==null
        ||param.sqlChange==null||param.configureChange==null||param.mqChange==null ||
        param.otherChange==null|| param.isTest==null||param.isReview==null)
    {
        layer.msg("请输入完整信息");
        return;
    }else{
        //补充id
        param.formId = formId;
        //修改
        $.ajax({
            type:"POST",
            url:"/tf/updateTestForm",
            cache: false,  //禁用缓存
            data: param,
            dataType: 'json',
            success: function (result) {
                if (result.code==1){
                    $('#updateForm').modal('hide');
                    layer.msg("修改成功");
                    table.ajax.reload();
                }else {
                    $('#updateForm').modal('hide');
                    layer.msg("修改失败");
                }
            }
        })
    }
}

/**
 * 修改状态值
 * @param status
 */
function updateStatus(status,id) {
    var param = {};
    param.formStatus = status;
    param.formId = id;

    $.ajax({
        type:"POST",
        url:"/tf/updateTestFormStatus",
        cache: false,  //禁用缓存
        data: param,
        dataType: 'json',
        success: function (result) {
            if (result.code==1){
                layer.msg("成功");
                table.ajax.reload();
            }else {
                layer.msg("失败");
            }
        }
    })
}