//分页显示DataTable
var testForm_table;
var formId;

$(document).ready(function () {
    testForm_table = $('#dt').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = testForm_getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/tf/getFormByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                    //根据不同状态，显示隐藏操作按钮。
                    $(".status_testForm").each(function() {
                        switch($(this).attr("value")) {
                            case "1":
                                //测试中
                                $(this).parent().next().find(".btn-testForm_opera[value='1']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='2']").show();
                                $(this).parent().next().find(".btn-testForm_opera[value='3']").show();
                                break;
                            case "2":
                                //测试未通过
                                $(this).parent().next().find(".btn-testForm_opera[value='1']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='2']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='3']").hide();
                                $(this).parent().next().find(".info_testForm_opera").show();
                                $(this).parent().next().find(".info_testForm_opera")[0].innerHTML = "失败工单";
                                $(this).parent().next().find(".info_testForm_opera")[0].classList.add("label-danger");
                                break;
                            case "3":
                                //测试通过
                                $(this).parent().next().find(".btn-testForm_opera[value='1']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='2']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='3']").hide();
                                $(this).parent().next().find(".info_testForm_opera").show();
                                $(this).parent().next().find(".info_testForm_opera")[0].innerHTML = "成功工单";
                                $(this).parent().next().find(".info_testForm_opera")[0].classList.add("label-success");
                                break;
                            default:
                                //默认未测试状态
                                $(this).parent().next().find(".btn-testForm_opera[value='1']").show();
                                $(this).parent().next().find(".btn-testForm_opera[value='2']").hide();
                                $(this).parent().next().find(".btn-testForm_opera[value='3']").hide();
                                break;

                        }
                    });
                }
            });
        },

        "columns": [
            {"data": "formService"},
            {"data": "formBranch"},
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
            {"data": "createUsername"},
            {"data": "acceptUsername"},
            {
                "sClass": "text-center",
                "data": "formStatus",
                "render": function (data, type, full, meta) {
                    switch (data) {
                        case 0:
                            return '<span value="0" class="label label-default status_testForm">未测试</span>';
                            break;
                        case 1:
                            return '<span value="1" class="label label-primary status_testForm">测试中...</span>';
                            break;
                        case 2:
                            return '<span value="2" class="label label-danger status_testForm">测试未通过</span>';
                            break;
                        case 3:
                            return '<span value="3" class="label label-success status_testForm">测试通过</span>';
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
                    return '<button type="button" class="btn btn-info btn-xs btn-testForm_opera" value="1" onclick="updateStatus(1,'+data+')" >accept</button> ' +
                        '<button type="button" class="btn btn-success btn-xs btn-testForm_opera" value="3" onclick="updateStatus(3,'+data+')" > ok </button> ' +
                        '<button type="button" class="btn btn-danger btn-xs btn-testForm_opera" value="2" onclick="updateStatus(2,'+data+')" >error</button>' +
                        '<span style="display: none;" class="label info_testForm_opera"></span>';

                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "formId",
                "render": function (data, status, full, meta) {
                    return '<button type="button" class="btn btn-info btn-sm" onclick="showModelForLook('+data+')">查看</button>&nbsp;&nbsp;' +
                        '<button type="button" class="btn btn-info btn-sm" onclick="showModelForModify('+data+')">修改</button>';

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
        ],

    });
});

function testForm_search() {
    testForm_table.ajax.reload();
}

//封装查询参数
function testForm_getQueryCondition(data) {

    var param = {};
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    } else {
        param.projectId = $("#testForm_projectId").val();
    }
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
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    }
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
                    testForm_search();
                }else {
                    $('#addForm').modal('hide');
                    layer.msg("新建失败");
                }
            }
        })
    }


}

function showModelForModify(id) {

    //修改模态框标题
    $(".model-title-modify").text("修改工单");
    //将字段设为可编辑
    $('#u_form_service').attr("disabled",false);
    $('#u_form_branch').attr("disabled",false);
    $('#u_influence_scope').attr("disabled",false);
    $('#u_sql_change').attr("disabled",false);
    $('#u_configure_change').attr("disabled",false);
    $('#u_mq_change').attr("disabled",false);
    $('#u_other_change').attr("disabled",false);
    $('.radio_t').attr("disabled",false);
    $('.radio_r').attr("disabled",false);
    //显示修改按钮
    $(".btn_testForm_modify").show();

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

function showModelForLook(id) {

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
    //修改模态框标题
    $(".model-title-look").text("工单详情");
    //将字段设为不可编辑
    $('#u_form_service').attr("disabled",true);
    $('#u_form_branch').attr("disabled",true);
    $('#u_influence_scope').attr("disabled",true);
    $('#u_sql_change').attr("disabled",true);
    $('#u_configure_change').attr("disabled",true);
    $('#u_mq_change').attr("disabled",true);
    $('#u_other_change').attr("disabled",true);
    $('.radio_t').attr("disabled",true);
    $('.radio_r').attr("disabled",true);

    //隐藏修改按钮
    $(".btn_testForm_modify").hide();
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
                    testForm_table.ajax.reload();
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
    if($("#project-search") != null) {
        param.projectId = $("#project-search").val();
    }
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
                testForm_table.ajax.reload();
            }else {
                layer.msg("失败");
            }
        }
    })
}