$(function(){
    //日期插件初始化
    $("#datetimeStart").datetimepicker({
        language:  'zh-CN',
        // format:'yyyy-mm-dd',
        format:'yyyy-mm-dd hh:ii',
        weekStart: 1, /*以星期一为一星期开始*/
        todayBtn:  1,
        autoclose: 1,
        // minView:2, /*精确到天*/
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        pickerPosition: "bottom-left"
    }).on("changeDate",function(ev){  //值改变事件
        //选择的日期不能大于第二个日期控件的日期
        if(ev.date){
            $("#datetimeEnd").datetimepicker('setStartDate', null);
        }else{
            $("#datetimeEnd").datetimepicker('setStartDate',null);
        }
    });
    $("#datetimeEnd").datetimepicker({
        language:  'zh-CN',
        format:'yyyy-mm-dd hh:ii',
        weekStart: 1, /*以星期一为一星期开始*/
        todayBtn:  1,
        autoclose: 1,
        // minView:2, /*精确到天*/
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        pickerPosition: "bottom-left"
    }).on("changeDate",function(ev){
        /*选择的日期不能小于第一个日期控件的日期*/
        if(ev.date){
            $("#datetimeStart").datetimepicker('setEndDate', null);
        }else{
            $("#datetimeStart").datetimepicker('setEndDate',new Date());
        }
    });
});

var tableNotLoad = true;
var myTable;

/**
 * 查询还款信息
 */
function search(page,pageSize){
    // 参数验证
    var mobile = $("#mobile").val();
    var orderId = $("#orderId").val();
    // 当userId与orderId都为空时flag==true
    var flag = (mobile == null || mobile == "") && (orderId == null || orderId == "");
    if(flag){
        layer.msg("手机号与订单ID不能都为空");
        return;
    }
    if(mobile != null && mobile != ""){
        var mobileReg=/^[1][0-9]{10}$/;
        if(!mobileReg.test(mobile)){
            layer.msg("手机号格式不正确");
            return;
        }
    }
    var dateStart = $("#datetimeStart").val();
    var dateEnd = $("#datetimeEnd").val();
    if(dateStart != null && dateStart != "" && dateEnd != null && dateEnd != ""){
        if(new Date(dateEnd).getTime() < new Date(dateStart).getTime()){
            layer.msg("结束时间不能大于开始时间");
            return;
        }
    }

    if(tableNotLoad){
        myTable = $('#datatable').DataTable({
            "searching": false, // 从结果搜索
            "bJQueryUI": true,
            "ordering" : true, // 排序
            "aaSorting": [5, "desc"], // 按第6列倒序排列
            "sPaginationType": "full_numbers",
            "serverSide": false, // true代表后台分页，false代表前台分页
            // 表格填充数据来源，使用ajax异步请求后台获取数据
            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = getQueryCondition(data);

                // 开启遮罩效果
                $("#content").showLoading();
                // 查询还款数据
                $.ajax({
                    async : true,
                    url : "/service/repayment",
                    type : "post",
                    data : param,
                    dataType : "json",
                    success : function(result) {
                        // 关闭遮罩效果
                        $("#content").hideLoading();
                        callback(result.data);
                    },
                    error: function(err) {
                        $("#content").hideLoading();
                    }
                });
            },
            "columns": [
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<input type="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                    "bSortable": false
                },
                {"data": "credOrderId"},
                {"data": "credUserId"},
                {"data": "credPlanPrincipal"},
                {"data": "credPlanInterest"},
                {
                    "data": "credPlanDate",
                    "render": function (data, type, full, meta) {
                        return new Date(parseInt(data)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                    },
                    "bSortable": true
                },
                {
                    "data": "redLocalInfo",
                    "render": function (data, type, full, meta) {
                        var localInfoJson = JSON.parse(data);
                        return localInfoJson.model_name;
                    }
                },
                {"data": "redPlanAmount"},
                {
                    "data": "redPackageType",
                    "render": function (data, type, full, meta) {
                        // 红包类型：1000加息红包；1010返现红包。
                        switch (data) {
                            case 1000:
                                return "加息红包";
                                break;
                            case 1010:
                                return "返现红包";
                                break;
                        }
                    }
                },
                {
                    "data": "redTermNum",
                    "render": function (data, type, full, meta) {
                        return new Date(parseInt(data)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                    }
                },
                {"data": "vipRate"},
                {"data": "vipPlanAmount"},
                {"data": "vipTermNum"},
                {"data": "pfPlanAmount"},
                {"data": "pfTermNum"},
                {
                    "data": "pfType",
                    "render": function (data, type, full, meta) {
                        // 加息类型：1000加息红包；1010返现红包。
                        switch (data) {
                            case 100:
                                return "首购加息";
                                break;
                            case 200:
                                return "限时加息";
                                break;
                            case 300:
                                return "项目加息";
                                break;
                        }
                    }
                }
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
                "sSearch": "从结果中检索:",
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
        myTable.ajax.reload();
    }
}


/**
 * 重置表单
 */
function reset(){
    $("#repaymentForm")[0].reset();
    $("#datetimeStart").datepicker('clearDates');
    $("#datetimeEnd").datepicker('clearDates');
}

/**
 * 封装查询参数
 */
function getQueryCondition(data) {
    var param = {};

    var dateStart = $("#datetimeStart").val();
    var dateEnd = $("#datetimeEnd").val();
    if(dateStart != null && dateStart != ""){
        param.startTime = new Date(dateStart).getTime();
    }
    if(dateEnd != null && dateEnd != ""){
        param.endTime = new Date(dateEnd).getTime();
    }
    param.mobile = $("#mobile").val();
    param.orderId = $("#orderId").val();

    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}