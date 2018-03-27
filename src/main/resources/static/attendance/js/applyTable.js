//分页显示DataTable
var table;

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
            {"data": "phone"},
            {"data": "status"},
            {
                "sClass": "text-center",
                "data": "userId",
                "render": function (data, type, full, meta) {
//                    class="btn btn-primary"
                    return '<button type="button" onclick="showUpdateModel(' + data + ');" >修改信息</button>' +
                        '<button type="button" onclick="showDetailModel(' + data + ');" >查看详情</button>';
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
            {"orderable": false, "targets": 8}
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
});


function search() {

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
