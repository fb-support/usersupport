//分页显示DataTable
var table;
$(document).ready(function () {
    table = $('#datatable_record').DataTable({
        "searching": false,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "serverSide": true,
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = getQueryCondition(data);

            $.ajax({
                type: "GET",
                url: '/rd/selectByPage',
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
            {"data": "formId"},
            {
                "data": "formType",
                "render":function (formType,type,full,meta) {
                    if(formType==1){
                        return "上线工单"
                    }
                    if(formType==0){
                        return "测试工单"
                    }
                },
                "bSortable": false
            },
            {"data": "operatingPeople"},
            {"data": "operatingContent"},
            {
                "sClass": "text-center",
                "data": "gmtCreate",
                "render": function (data, type, full, meta) {
                    return formatDate(validate(data));
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
        ],
    });
});
function search() {
    table.ajax.reload();
}
//封装查询参数
function getQueryCondition(data) {
    var param = {};
    param.projectId = $("#project-search").val();//查询条件
    param.operatingPeople = $("#people-search").val();//查询条件
    param.formType = $("#formType-search").val();//查询条件
    if(param.formType == "测试工单"){
        param.formType = 0;
    }
    if(param.formType == "上线工单"){
        param.formType = 1;
    }
    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
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
//格式化
function parseData(str) {
    var timestamp = Date.parse(new Date(str));
    return timestamp;
}