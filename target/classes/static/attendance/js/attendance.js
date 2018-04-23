$(function () {
    //日期插件初始化
    // $("#attendanceDate").datetimepicker({
    //     language:  'zh-CN',
    //     format: 'yyyy-mm',
    //     autoclose: true,
    //     todayBtn: false,
    //     startView: 'year',
    //     minView:'year',
    //     maxView:'decade'
    // });

    //一键上传
    $("#importExcel").upload({
        action:'/attendance/import',
        name:'file',
        onComplete: function (data, self, element) {
            alert(data);
            if(data == "0"){
                layer.msg("数据导入成功");
            }else{
                layer.msg("数据导入失败，请稍后重试");
            }
        }
    });

    // 初始化部门
    $.ajax({
        async: false,
        url: "/dept/getAllDept",
        type: "get",
        dataType: "json",
        success: function (result) {
            var depts = result.data;
            var str = "";
            for(var i = 0; i < depts.length; i++){
                str += "<option value='"+depts[i].deptNumber+"'>"+depts[i].deptName+"</option>"
            }
            $("#companySelect").append(str);
        }
    });
});

function getEmpByNumber() {
    var deptNumber = $("#companySelect").val();
    // 保存部门名称
    var deptName = $("#companySelect option:selected").text();
    $("#deptNameInput").val(deptName);
    // 初始化人员
    $.ajax({
        async: false,
        url: "/emp/getEmpListByDeptNumber?deptNumber="+deptNumber,
        type: "get",
        dataType: "json",
        success: function (result) {
            $("#empSelect").html("<option value=''>--请选择--</option>");
            var empList = result.data;
            var str = "";
            for(var i = 0; i < empList.length; i++){
                str += "<option value='"+empList[i].workNumber+"'>"+empList[i].name+"</option>"
            }
            $("#empSelect").append(str);
        }
    });
}

var tableNotLoad = true;
var attenTable;
// 查询考勤记录
function searchAttendance() {
    // 校验查询条件
    var deptNumber = $("#companySelect").val();
    var deptName = $("#companySelect option:selected").text();
    if(deptNumber == "" || deptNumber == null){
        layer.msg("请选择部门");
        return;
    }

    var workNumber = $("#empSelect").val();
    var attenDate = $("#attendanceDate").val();
    if(attenDate == "" || attenDate == null){
        layer.msg("请选择考勤时间");
        return;
    }

    if(tableNotLoad) {
        attenTable = $('#attendanceTable').DataTable({
            "searching": true, // 从结果搜索
            "bJQueryUI": true,
            "aaSorting": [3, "desc"], // 按第7列倒序排列
            "sPaginationType": "full_numbers",
            "serverSide": false, // true代表后台分页，false代表前台分页
            // 表格填充数据来源，使用ajax异步请求后台获取数据
            ajax: function (data, callback, settings) {
                var params = getQueryCondition(data)
                $.ajax({
                    async : true,
                    url : "/attendance/getAttendanceRecord",
                    type : "post",
                    data : params,
                    dataType : "json",
                    success : function(successDate) {
                        callback(successDate.data);
                        $("#exportExcel").removeClass("hide");
                    },
                    error: function(err) {
                    }
                });
            },
            "columns": [
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return meta.row + 1;
                    }
                },
                {"data": "workNumber"},
                {"data": "empName"},
                {
                    "data": "attendanceDate",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data),"yyyy-MM-dd")
                    }
                },
                {
                    "data": "startTime",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data)," HH:mm")
                    }
                },
                {
                    "data": "endTime",
                    "render": function (data, type, full, meta) {
                        return Format(new Date(data)," HH:mm")
                    }
                },
                {
                    "data": "status",
                    "render": function (data, type, full, meta) {
                        //类型：0导航菜单；1操作按钮。
                        switch (data) {
                            case 0:
                                return "正常";
                                break;
                            case 1:
                                return "迟到";
                                break;
                            case 2:
                                return "早退";
                                break;
                            case 3:
                                return "迟到早退";
                                break;
                            case 4:
                                return "旷工";
                                break;
                            case 5:
                                return "异常";
                                break;
                            case 6:
                                return "请假";
                                break;
                            case 7:
                                return "调休";
                                break;
                        }
                    }
                }
            ],
            "oLanguage": {    // 语言设置
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "检索:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
            }
        });

        tableNotLoad = false;
    }else {
        attenTable.ajax.reload();
    }
}

/**
 * 将考勤记录导出到excel
 */
function exportAttendance(){
    $("#attendanceForm").attr("action","/attendance/export");
    var attenDate = new Date($("#attendanceDate").val());
    $("#attendanceDateInput").val(attenDate);
    $("#attendanceForm").submit();
}

/**
 * 封装查询参数
 */
function getQueryCondition(data) {
    var params = {};
    params.deptNumber = $("#companySelect").val();
    params.deptName = $("#companySelect option:selected").text();
    params.workNumber = $("#empSelect").val();
    params.attendanceDate = new Date($("#attendanceDate").val());

    //组装分页参数
    params.draw = data.draw;
    return params;
}


