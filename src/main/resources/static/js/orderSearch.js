$(function(){
    /*//日期插件初始化
    $("#datetimeStart").datetimepicker({
        language:  'zh-CN',
        // format:'yyyy-mm-dd',
        format:'yyyy-mm-dd hh:ii',
        weekStart: 1, /!*以星期一为一星期开始*!/
        todayBtn:  1,
        autoclose: 1,
        // minView:2, /!*精确到天*!/
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
        weekStart: 1, /!*以星期一为一星期开始*!/
        todayBtn:  1,
        autoclose: 1,
        // minView:2, /!*精确到天*!/
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        pickerPosition: "bottom-left"
    }).on("changeDate",function(ev){
        /!*选择的日期不能小于第一个日期控件的日期*!/
        if(ev.date){
            $("#datetimeStart").datetimepicker('setEndDate', null);
        }else{
            $("#datetimeStart").datetimepicker('setEndDate',new Date());
        }
    });*/

    var today = new Date();
    today.setHours(0);
    today.setMinutes(0);
    today.setSeconds(0);
    today.setMilliseconds(0);

    $("#datetimeStart").val(Format(today,"yyyy-MM-dd HH:mm"));
    $("#datetimeEnd").val(Format(new Date(),"yyyy-MM-dd HH:mm"));
});

var tableNotLoad = true;
var table3NotLoad = true;
var myTable;
var myTable2 = null;
var myTable3 = null;

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
            "searching": true, // 从结果搜索
            "bJQueryUI": true,
            "ordering" : true, // 排序
            "aaSorting": [1, "desc"], // 按第4列倒序排列
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
                    url : "/service/repaymentOrder",
                    type : "post",
                    data : param,
                    dataType : "json",
                    success : function(result) {
                        callback(result.data[0]);
                        if(result.data[0].recordsTotal > 0){
                            // 显示导出按钮
                            $("#exportExcel").removeClass("hide");
                            $("#getRepaymentDetail").removeClass("hide");
                        }
                        // 构建用户资产表格
                        loadTable2(result.data[1].data);
                        // 隐藏债权还款明细
                        $("#repaymentDetailDiv").addClass("hide");
                        // 关闭遮罩效果
                        $("#content").hideLoading();
                    },
                    error: function(err) {
                        $("#content").hideLoading();
                    }
                });
            },
            "columns": [
                {
                    "data": null,
                    "render": function (data, type, full, meta) {
                        return meta.row+1;
                    }
                },
                {"data": "orderId"},
                {"data": "userId"},
                {
                    "data": "planDate",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return new Date(parseInt(data)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                        }else{
                            return "";
                        }
                    },
                    "bSortable": true
                },
                {
                    "data": "bizStatus",
                    "render": function (data, type, full, meta) {
                        switch (data) {
                            case 100:
                                return "未还";
                                break;
                            case 200:
                                return "已还";
                                break;
                        }
                    }
                },
                {"data": "credPlanPrincipal"},
                {
                    "data": "credRealPrincipal",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {"data": "credPlanInterest"},
                {
                    "data": "credRealInterest",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "redPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "redRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "vipPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "vipRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "pfPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "pfRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
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
        myTable.ajax.reload();
    }
}

/**
 * 构建用户资产表格
 * @param data
 */
function loadTable2(data) {
    if(myTable2!=null){
        myTable2.destroy();
        initTable2(data)
    }else{
        initTable2(data)
    }
}

function initTable2(data){
    myTable2 = $('#datatable2').DataTable({
        "searching" : false, // 从结果搜索
        "serverSide" : false,
        "paging" : false,
        "ordering" : false, // 排序
        "info" : false, //取消显示行数信息的功能Showing 1 to 10 of 57 entries
        "data" : data, // 表格填充数据来源
        "columns": [
            {"data": "userId"},
            {"data": "realName"},
            {"data": "mobile"},
            {"data": "cash"},
            {"data": "frozenFpOrderCash"},
            {"data": "frozenWithDrawCash"},
            {"data": "totalCredPlanPrincipal"},
            {"data": "totalCredRealPrincipal"},
            {"data": "totalCredPlanInterest"},
            {"data": "totalCredRealInterest"},
            {"data": "totalRedPlanAmount"},
            {"data": "totalRedRealAmount"},
            {"data": "totalVipPlanAmount"},
            {"data": "totalVipRealAmount"},
            {"data": "totalPfPlanAmount"},
            {"data": "totalPfRealAmount"},
            {"data": "totalAssets"}
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
}

/**
 * 构建债权明细表格
 * @param data
 */
function loadTable3(data) {
    if(myTable3!=null){
        myTable3.destroy();
        initTable3(data);
    }else{
        initTable3(data);
    }
}

/**
 * 查询债权还款明细
 */
function searchRepaymentDetail(){
    // 判断orderId是否为空
    var orderId = $("#orderId").val();
    if(orderId == null || orderId == ""){
        layer.msg("请选择订单ID重新查询后再查看债权明细");
        return;
    }

    // 显示债权还款明细表格
    $("#repaymentDetailDiv").removeClass("hide");

    if(table3NotLoad){
        myTable3 = $('#datatable3').DataTable({
            "searching": true, // 从结果搜索
            "bJQueryUI": true,
            "ordering" : true, // 排序
            "aaSorting": [1, "desc"], // 按creditId倒序排列
            "sPaginationType": "full_numbers",
            "aLengthMenu" : [12, 24, 48, 96], //更改显示记录数选项
            "iDisplayLength" : 12, //默认显示的记录数
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
                    url : "/service/repaymentDetail",
                    type : "post",
                    data : param,
                    dataType : "json",
                    success : function(result) {
                        if(result.code != 1){
                            layer.msg(result.message);
                        }else{
                            callback(result.data);
                            if(result.data.recordsTotal > 0){
                                // 显示导出按钮
                                $("#exportExcel2").removeClass("hide");
                            }
                        }
                        // 关闭遮罩效果
                        $("#content").hideLoading();
                    },
                    error: function(err) {
                        $("#content").hideLoading();
                    }
                });
            },
            "columns": [
                /*{
                    "className": 'details-control',
                    "orderable": false,
                    "data": null,
                    "defaultContent": ''
                },*/
                {
                    "data": null,
                    "bSortable" : true,
                    "render": function (data, type, full, meta) {
                        /*return meta.row+1;*/
                        var startIndex = meta.settings._iDisplayStart;
                        return startIndex + meta.row + 1;
                    }
                },
                {"data": "creditId"},
                {"data": "orderId"},
                {"data": "userId"},
                {
                    "data": "planDate",
                    "render": function (data, type, full, meta) {
                        return new Date(parseInt(data)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
                    },
                    "bSortable": true
                },
                {
                    "data": "bizStatus",
                    "render": function (data, type, full, meta) {
                        switch (data) {
                            case 100:
                                return "未还";
                                break;
                            case 200:
                                return "已还";
                                break;
                        }
                    }
                },
                {"data": "credPlanPrincipal"},
                {"data": "credRealPrincipal"},
                {"data": "credPlanInterest"},
                {"data": "credRealInterest"},
                {
                    "data": "credTermNum",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data+"期";
                        }else{
                            return "";
                        }
                    }
                },
                {
                    "data": "redLocalInfo",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            var localInfoJson = JSON.parse(data);
                            return localInfoJson.model_name;
                        }else{
                            return "";
                        }
                    }
                },
                {
                    "data": "redPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "redRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
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
                            default:
                                return "";
                                break;
                        }
                    }
                },
                {
                    "data": "vipRate",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "";
                        }
                    }
                },
                {
                    "data": "vipPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "vipRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "pfPlanAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
                {
                    "data": "pfRealAmount",
                    "render": function (data, type, full, meta) {
                        if(data != null && data != ""){
                            return data;
                        }else{
                            return "0";
                        }
                    }
                },
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
                            default:
                                return "";
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
                "sSearch": "检索:",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
            }
        });
        table3NotLoad = false;
    }else {
        myTable3.ajax.reload();
    }
}

function initTable3(data){

}

/**
 * 重置表单
 */
function reset(){
    $("#repaymentForm")[0].reset();
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
    param.bizStatus = $("#bizStatus").val();

    // 获取当前iframe的id
    var iframeId = window.frameElement && window.frameElement.id || '';
    param.iframeId = iframeId;

    //组装分页参数
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}

/**
 * 将结果导出到excel
 */
function exportRepaymentOrder() {
    $("#repaymentForm").attr("action","/repaymentOrder/export");
    // 获取当前iframe的id
    var iframeId = window.frameElement && window.frameElement.id || '';
    $("#iframeId").val(iframeId)
    $("#repaymentForm").submit();
}

function exportRepaymentDetail(){
    $("#repaymentForm").attr("action","/repaymentDetail/export");
    // 获取当前iframe的id
    var iframeId = window.frameElement && window.frameElement.id || '';
    $("#iframeId").val(iframeId)
    $("#repaymentForm").submit();
}