/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
//分页显示DataTable
var table;

var userId;
//
// $(document).ready(function () {
//     table =
// });

//封装查询参数
function getQueryCondition(data) {
    var param = [];

    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    console.info(param.dateStartTime+","+param.dateEndTime+","+param.start+","+param.length+","+param.draw);
    return param;
}

var flag = true;
function search() {

    if(flag){
       table = $('#datatable').DataTable({
           "searching": true,
           "bJQueryUI": true,
           "sPaginationType": "full_numbers",
           "serverSide": true,

           ajax: function (data, callback, settings) {
               // //封装请求参数
               var param = getQueryCondition(data);
               //
               var startTime = $("#datetimeStart").val();
               var endTime = $("#datetimeEnd").val();
               if(startTime != null && startTime != ""){
                   var dateStartTime =new Date(new Date(startTime).Format('yyyy-MM-dd 00:00:00'));
               }
               if(endTime != null && endTime != ""){
                   var dateEndTime =new Date(endTime);
               }

               //组装分页参数
               var start = data.start;
               var length = data.length;
               var draw = data.draw;
               $.ajax({
                   type: "POST",
                   url: '/attendance/getApplyByCondition',
                   cache: false,  //禁用缓存
                   data: {
                       dateStartTime:dateStartTime,
                       dateEndTime:dateEndTime,
                       start:start,
                       length:length,
                       draw:draw
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
               // {
               //     "sClass": "text-center",
               //     "data": "id",
               //     "render": function (data, type, full, meta) {
               //         return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
               //     },
               //     "bSortable": false
               // },
               {
                   "sClass": "text-center",
                   "data": "id",
                   "render": function (data, type, full, meta) {
                       // return meta.row+1;
                       return data;
                   }
               },
               {"data": "workNumber"},
               {"data": "empName"},
               {
                   "sClass": "text-center",
                   "data": "startTime",
                   "render": function (data, type, full, meta) {
                       return new Date(data).Format('yyyy-MM-dd hh:mm');
                   }
               },
               {
                   "sClass": "text-center",
                   "data": "endTime",
                   "render": function (data, type, full, meta) {
                       return new Date(data).Format('yyyy-MM-dd hh:mm');
                   }
               },
               {
                   "sClass": "text-center",
                   "data": "applyDate",
                   "render": function (data, type, full, meta) {
                       return new Date(data).Format('yyyy-MM-dd');
                   }
               },
               {
                   "sClass": "text-center",
                   "data": "status",
                   "render": function (data, type, full, meta) {
                       //                    class="btn btn-primary"
                       if(data== 0){
                           return '未审核'
                       }else if(data == 2){
                           return '批准'
                       }else if(data == 3){
                           return '不批准'
                       }else{
                           return '审核中'
                       }
                   },
               },
               {
                   "sClass": "text-center",
                   "data": "id",
                   "render": function (data, type, full, meta) {
                       //                    class="btn btn-primary"
                       return '<button type="button" data-rowindex="'+meta.row+'" onclick="showUpdateModel('+meta.row+');" >修改/查看信息</button>';
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
               {"orderable": false, "targets": 7}
               // {"orderable": false, "targets": 8},
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

