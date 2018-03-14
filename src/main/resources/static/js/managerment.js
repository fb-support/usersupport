$(".checkall").click(function () {
    var check = $(this).prop("checked");
    $(".checkchild").prop("checked", check);
});

//分页显示DataTable
var table;

//userId
var userId;

$(document).ready(function () {
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
                url: '/um/getUserByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                }
            });
        },

        "columns": [
            {
                "sClass": "text-center",
                "data": "userId",
                "render": function (data, type, full, meta) {
                    return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                },
                "bSortable": false
            },
            {"data": "userId"},
            {"data": "workNumber"},
            {"data": "username"},
            {"data": "email"},
            {"data": "phone"},
            {"data": "status"},
            {
                "sClass": "text-center",
                "data": "userId",
                "render": function (data, type, full, meta) {
//                    class="btn btn-primary"
                    return '<button type="button" onclick="showModel(' + data + ');" >修改信息</button>';
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
    param.username = $("#name-search").val();//查询条件
    param.workNumber = $("#work-search").val();//查询条件
    param.phone = $("#phone-search").val();//查询条件
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

/**
 * 删除用户
 */
function deleteUser() {
    var id = [];
    $(".checkchild:checked").each(function () {
        id.push($(this).val());
    });

    if ($(".checkchild:checked").length < 1) {
        layer.msg('请选择一条数据');
        return;
    }

    var parm = {"id": id};
    layer.confirm('您确定要删除这些客服用户吗？', {
        btn: ['确认', '取消'] //按钮
    }, function () {
        $.ajax({
            type: "POST",
            url: '/um/deleteUserByIds',
            cache: false,  //禁用缓存
            data: parm,
            dataType: 'json',
            success: function (result) {
                if (result.code == 1) {
                    layer.msg('操作成功');
                    table.ajax.reload();
                } else {
                    layer.msg(data.error);
                }
            }
        });
    });

}

/**
 * 显示模态框
 * @param id
 */
function showModel(id) {
    userId = id;
    $.ajax({
        type: "GET",
        url: '/sc/getByUserId?userId=' + id,
        cache: false,  //禁用缓存
        dataType: 'json',
        success: function (result) {
            if (result.code == 1) {
                $('#updateUser').modal();
                $('#m_userName').val(result.data.username);
                $('#m_workNumber').val(result.data.workNumber);
                $('#m_phone').val(result.data.phone);
                $('#m_email').val(result.data.email);
            } else {
                layer.msg(result.error);
            }
        }
    });


}

/**
 * 修改信息
 */
function updateUser() {
    var param = {};
    param.userId = userId;
    param.username = $('#m_userName').val();
    param.workNumber = $('#m_workNumber').val();
    param.phone = $('#m_phone').val();
    param.email = $('#m_email').val();


    var emailReg = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
    var phoneReg = /^1[34578]\d{9}$/;
    if (!phoneReg.test(param.phone)) {
        layer.msg("手机号码格式错误");
        return;
    }

    if (!emailReg.test(param.email)) {
        layer.msg("邮箱号码格式错误");
        return;
    }

        $.ajax({
            type: "GET",
            url: '/um/check',
            cache: false,  //禁用缓存
            data: param,
            dataType: 'json',
            success: function (result) {
                var toast = "";
                if (result.code == 1) {
                    if(result.data.phoneRepeat==true){
                        toast += "手机号码重复！";}
                    else if(result.data.emailRepeat==true){
                        toast += "邮箱号码重复！";}
                    else if(result.data.workNumberRepeat==true){
                        toast += "工号重复！";
                    }else if(result.data.usernameRepeat==true){
                        toast += "用户名重复！";
                    }

                    if (toast.length>1){
                        layer.msg(toast);
                        return;
                    }
                    else{
                        $.ajax({
                            type: "POST",
                            url: '/sc/updateBaseInfoMationById',
                            cache: false,  //禁用缓存
                            data: param,
                            dataType: 'json',
                            success: function (result) {
                                if (result.code == 1) {
                                    $('#updateUser').modal('hide');
                                    layer.msg('操作成功');
                                    table.ajax.reload();
                                } else {
                                    layer.msg(result.error);
                                }
                            }
                        });
                    }
                } else {
                    layer.msg(result.error);
                }
            }
        })


}