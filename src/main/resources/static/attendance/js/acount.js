$(document).ready(function () {
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
})
var searchType = null;
var flag = true;
function search(type) {
    searchType = type;
    if(flag){
        table = $('#datatable').DataTable({
            "searching": false,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "serverSide": true,
            ajax: function (data, callback, settings) {
                // //封装请求参数
                // var param = getQueryCondition(data);
                //
                var startTime = $("#datetimeStart").val();
                var workNumber = $("#empSelect option:selected").val();
                var deptName = $("#companySelect option:selected").text();
                if(startTime != null && startTime != ""){
                    var dateStartTime = new Date(startTime);
                }
                if(searchType == 1){
                    var year = dateStartTime;
                }
                if(searchType == 0){
                    var month = dateStartTime;
                }
                //组装分页参数
                var start = data.start;
                var length = data.length;
                var draw = data.draw;
                $.ajax({
                    url:'/attendance/getAcountAll',
                    type:'POST',
                    cache: false,  //禁用缓存
                    data: {
                        dateStartTime:dateStartTime,
                        start:start,
                        length:length,
                        draw:draw,
                        workNumber:workNumber,
                        deptName:deptName,
                        year:year,
                        month:month
                    },
                    // data:param,   //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        console.info(result.data)
                        callback(result.data);
                    }
                });
            },
            "columns": [
                {
                    "sClass": "text-center",
                    "data": "workNumber"
                },
                {
                    "sClass": "text-center",
                    "data": "empName"
                },
                {
                    "sClass": "text-center",
                    "data": "deptName"
                },
                {
                    "sClass": "text-center",
                    "data": "late"
                },
                {
                    "sClass": "text-center",
                    "data": "early"

                },
                {
                    "sClass": "text-center",
                    "data": "lateAndEarly"

                },
                {
                    "sClass": "text-center",
                    "data": "absent"

                },
                {
                    "sClass": "text-center",
                    "data": "exp"

                },
                {
                    "sClass": "text-center",
                    "data": "leaveDuration"

                },
                {
                    "sClass": "text-center",
                    "data": "restDuration"

                },
                {
                    "sClass": "text-center",
                    "data": "overtimeDuration",
                    "render": function (data, type, full, meta) {
                        return data + '(约 ' + (data/60).toFixed(2) + ' 小时)';
                    }
                }
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
                {"orderable": false, "targets": 10}
                // {"orderable": false, "targets": 11},
                // {"orderable": false, "targets": 12}
            ],

            /*是否开启主题*/
            "bJQueryUI": true,
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
            },

        });
        flag = false;

    }else {
        table.ajax.reload();
    }
}

function getEmpByNumber() {
    var deptNumber = $("#companySelect").val();
    // 保存部门名称
    var deptName = $("#companySelect option:selected").text();
    $("#deptNameInput").val(deptName);
    // 初始化人员
    // $.ajax({
    //     async: false,
    //     url: "/emp/getEmpListByDeptNumber?deptNumber="+deptNumber,
    //     type: "get",
    //     dataType: "json",
    //     success: function (result) {
    //         $("#empSelect").html("<option value=''>--请选择--</option>");
    //         var empList = result.data;
    //         var str = "";
    //         for(var i = 0; i < empList.length; i++){
    //             str += "<option value='"+empList[i].workNumber+"'>"+empList[i].name+"</option>"
    //         }
    //         $("#empSelect").append(str);
    //     }
    // });

    // 初始化员工姓名
    $.ajax({
        async: false,
        url: "/attendance/getAllEmpUser?deptName="+deptName,
        type: "get",
        dataType: "json",
        success: function (result) {
            var empUser = result.data;
            console.log(empUser);
            var str = "";
            for(var i = 0; i < empUser.length; i++){
                str += "<option value='"+empUser[i].workNumber+"'>"+empUser[i].empName+"</option>"
            }
            $("#empSelect").append(str);
        }
    });
}
