//分页显示DataTable
var table;

$(document).ready(function () {
    //项目管理主页日期条件的插件初始化
    $('#projectSearchdatetimeStart').datetimepicker({
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
            $("#projectSearchdatetimeEnd").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
        } else {
            $("#projectSearchdatetimeEnd").datetimepicker('setStartDate', null);
        }
    });
    $('#projectSearchdatetimeEnd').datetimepicker({
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
            $("#projectSearchdatetimeStart").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
        } else {
            $("#projectSearchdatetimeStart").datetimepicker('setEndDate', new Date());
        }
    });

    //项目管理新增项目的插件初始化
    $('#m_beginTime').datetimepicker({
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
            $("#m_endTime").datetimepicker('setStartDate', new Date(ev.date.valueOf()));
        } else {
            $("#m_endTime").datetimepicker('setStartDate', null);
        }
    });
    $('#m_endTime').datetimepicker({
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
            $("#m_beginTime").datetimepicker('setEndDate', new Date(ev.date.valueOf()));
        } else {
            $("#m_beginTime").datetimepicker('setEndDate', new Date());
        }
    });

    table = $('#testProject_datatable').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,

        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = project_getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/online-process/getProjectsByPage',
                cache: false,  //禁用缓存
                data: param,    //传入已封装的参数
                dataType: "json",
                success: function (result) {
                    callback(result.data);
                }
            });
        },

        "columns": [
            {"data": "projectId"},
            {"data": "projectName"},
            {"data": "projectUrl"},
            {
                "sClass": "text-center",
                "data": "beginTime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "sClass": "text-center",
                "data": "endTime",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
                },
                "bSortable": false
            },
            {
                "data": "status",
                "render":function (status,type,full,meta) {
                    switch(status) {
                        case 0:
                            return "<span style='color:darkgrey'>已弃用</span>";
                            break;
                        case 1:
                            return "<span style='color:red'>进行中</span>";
                            break;
                        case 2:
                            return "<span style='color:dodgerblue'>已完成</span>";
                            break;
                    }
                }
            },
            {
                "sClass": "text-center",
                "data": "projectId",
                "render": function (data, type, full, meta) {
                    return "<span class='glyphicon glyphicon-list-alt btn_lookProjectDetail' onclick='lookProjectDetail(this)'></span>";
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

    // 点击开发人员、测试人员、运维人员等填写框时触发改操作
    $(".people_who_to_do").click(function() {
        switch($(this).attr("id")) {
            case "m_developPeople":
                //开发人员
                showModelForSelectPeople($(this).val());
                $("#saveSelectedUserButton").attr("value",1);
                break;
            case "m_testPeople":
                //测试人员
                showModelForSelectPeople($(this).val());
                $("#saveSelectedUserButton").attr("value",2);
                break;
            case "m_operationsPeople":
                //运维人员
                showModelForSelectPeople($(this).val());
                $("#saveSelectedUserButton").attr("value",3);
                break;
        }
    });

});


function searchAllProject() {
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
function project_getQueryCondition(data) {
    var param = {};
    param.projectName = $("#projectName-search").val();
    param.projectId = $("#projectId-search").val();
    if ($('#projectSearchdatetimeStart').val() != null && $('#projectSearchdatetimeStart').val().length > 0) {
        param.beginTime = parseData($('#projectSearchdatetimeStart').val());
    }
    if ($("#projectSearchdatetimeEnd").val() != null && $("#projectSearchdatetimeEnd").val().length > 0) {
        param.endTime = parseData($("#projectSearchdatetimeEnd").val());
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

/**
 * 为了查看指定项目编号的详细操作记录和内容
 */
function lookProjectDetail(obj) {
    console.log(obj);
    //obj.
    window.parent.runTestProjectDetail();
}

/**
 * 显示模态框
 * 为了新建新项目
 */
function showModelForNewProject() {
    $('#createNewProject').modal();

}

/**
 * 显示模态框
 * 为了弹出用户人员列表页
 * @param who 确定当前弹出人员列表选择的是哪个角色的人，
 * 1为开发。2为测试。3为运维
 */
function showModelForSelectPeople(who) {
    // 显示模态框
    $('#selectPeopleForProject').modal();
    // 初始化员工列表
    $("#beSelect_user").html("");
    // 解析who字符串成用户名数组
    var usernames = who.split(",");
    //获取待搜索内容
    var query = $('#search_username').val();
    $.ajax({
        type: "GET",
        url: '/online-process/getUserByQuery',
        cache: false,  //禁用缓存
        data: "query="+query,    //传入已封装的参数
        dataType: "json",
        success: function (data) {

            for(var i = 0; i < data.data.length; i++) {
                var flag = true;
                //判断当前遍历对象是否为被选中对象，若是，则在checkbox默认选中
                for(var j=0; j< usernames.length; j++) {
                    if(data.data[i].username == usernames[j]) {
                        //添加用户标签进入列表---默认选中
                        $("#beSelect_user").append("<tr>" +
                            "<td><input class='beSelect_checkbox' type='checkbox' checked='checked' value='" + data.data[i].userId +"'/></td>" +
                            "<td class='beSelect_userNumber'>" + data.data[i].workNumber +"</td>" +
                            "<td class='beSelect_username'>" + data.data[i].username +"</td>" +
                            "</tr>");
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    //添加用户标签进入列表---默认未选中
                    $("#beSelect_user").append("<tr>" +
                        "<td><input class='beSelect_checkbox' type='checkbox' value='" + data.data[i].userId +"'/></td>" +
                        "<td class='beSelect_userNumber'>" + data.data[i].workNumber +"</td>" +
                        "<td class='beSelect_username'>" + data.data[i].username +"</td>" +
                        "</tr>");
                }

            }
        }
    });

}

/**
 * 根据姓名/工号查询指定人员
 */
function searchUser() {
    showModelForSelectPeople();
}

/**
 * 保存选择的用户对象。
 */
function saveSelectedUser() {
    // 获取所有已选中的复选框对象
    var users = $(".beSelect_checkbox[type='checkbox']:checked");

    // 初始化用户编号集合字符串对象并赋值
    var userIdStr = "";
    // 初始化用户名称集合字符串对象并赋值
    var userStr = "";
    for(var i=0; i<users.length; i++) {
        // 获取用户编号
        userIdStr = userIdStr + users[i].value + ",";
        // 获取其存储用户名称的兄弟标签并拼凑
        var username = users[i].parentElement.nextElementSibling.nextElementSibling;
        userStr = userStr + username.innerText + ",";
    }

    //将所选择的值填入新建项目模态框的相应input中
    switch($("#saveSelectedUserButton").val()) {
        case '1':
            $("#m_developPeople").val(userStr);
            $("#m_hidden_developPeople").val(userIdStr);
            break;
        case '2':
            $("#m_testPeople").val(userStr);
            $("#m_hidden_testPeople").val(userIdStr);
            break;
        case '3':
            $("#m_operationsPeople").val(userStr);
            $("#m_hidden_operationsPeople").val(userIdStr);
            break;
    }


    // 隐藏员工列表model
    $('#selectPeopleForProject').modal('hide');

}

/**
 * 新增项目模态框页面保存按钮监听方法
 */
function saveTestProject() {
    $.ajax({
        type: "get",
        url: '/online-process/newTestProject',
        cache: false,  //禁用缓存
        data: $("#newTestProjectForm").serialize(),    //传入已封装的参数
        dataType: "json",
        success: function (data) {
            if(data.code == 1) {
                layer.msg("新建成功");
                // 隐藏项目列表model
                $('#createNewProject').modal('hide');
            } else {
                layer.msg("创建失败。请重新尝试！");
            }
        }
    });
}