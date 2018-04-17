var recipient;
var typeid;
var sll = "";
var sta = 0;
var startTime;//服务开始时间
var endTime;//服务结束时间
$(document).ready(function () {
    search();
    $("#typeShows").change(function () {
        firstType();
        SecondType();
    });
    $("#typeShows1").change(function () {
        SecondType();
    });
    $("#typeShows2").change(function () {
        Second2Type();
    });


    TypeBind();
    $("#type").change(function () {
        firstTypeNewService();
        SecondTypeNewService();
    })
    $("#type1").change(function () {
        SecondTypeNewService();
    })

    //图片容初始化


    $('#startServer').on('show.bs.modal', function (event) {});
    //图片初始化
    imgUpload({
        inputId: 'file', //input框id
        imgBox: 'imgBox', //图片容器id
        buttonId: 'btn', //提交按钮id
        upUrl: '/test/controlller',  //提交地址
        data: 'file', //参数名
        num: "9",//最多上传图片个数
    })
    imgUpload({
        inputId: 'fileN', //input框id
        imgBox: 'imgBoxN', //图片容器id
        buttonId: 'btnN', //提交按钮id
        upUrl: '/test/controlller',  //提交地址
        data: 'file', //参数名
        num: "9",//最多上传图片个数
    })


    //弹窗初始化
    $('#exampleModal').on('show.bs.modal', function (event) {
        console.log("开始---"+sll);
        sll = "";

        var button = $(event.relatedTarget) // 触发事件的按钮
        recipient = button.data('whatever') // 解析出data-whatever内容
        console.log(recipient);
        $.ajax({
            url: "/customer/detail",
            type: "POST",
            dataType: "json",
            data: {id: recipient},
            success: function (json) {
                $("#file").val("");
                serviceId = json.data[0].customerServiceDto.id;
                questionId = json.data[0].customerProblemDescriptionModels[0].id;
                solveId = json.data[0].customerProblemSolves[0].id;
                problemId = json.data[0].id;
                typeid = json.data[0].typeId;
                var data = json.data;

                //根据状态判断是否能重写
                var status = data[0].customerServiceDto.status;
                var solvetable = "<textarea class='form-control' rows='3' name='solve' style='width:70%;' >" + data[0].customerProblemSolves[0].description + "</textarea>"
                if (status == 0) {
                    rmlockedit();
                    sta = 0;
                    $("#waitdeal").show();
                    $("#finishdeal").hide();
                    $("#solvebox").html(solvetable);
                }
                if (status == 1) {
                    lockedit();
                    sta = 1;
                    $("#waitdeal").hide();
                    $("#newsolve").show();
                    $("#newsolve").val("");
                    $("#finishdeal").show();
                    $("#updateimage").hide();
                }
                if (status == 2) {
                    blockedit();
                    sta = 2;
                    $("#waitdeal").hide();
                    $("#finishdeal").hide();
                    $("#newsolve").hide();

                }

                $("#phoneNumber").val(data[0].customerServiceDto.phoneNumber);
                $("#phoneType").val(data[0].customerServiceDto.phoneType);
                $("#realName").val(data[0].customerServiceDto.name);
                // $("#typeShows").val(data[0].customerServiceDto.typeId);
                $("#title").val(data[0].title);
                $("#description").val(data[0].customerProblemDescriptionModels[0].description);
                //遍历问题处理
                if (status != 0) {
                    var solvehead = "";

                    for (var i = 0; i < data[0].customerProblemSolves.length; i++) {
                        solvehead += "<div id='solvedetail' class='solvedetail'>处理时间：<span >" + formatDate(data[0].customerProblemSolves[i].gmtCreate) + "</span>--处理人：<span >" + data[0].customerServiceDto.username + "</span></div>" +
                            "<textarea class='form-control' rows='3'   style='width:70%;' readonly id='solvedata'>" + data[0].customerProblemSolves[i].description + "</textarea>"
                    }
                    $("#solvebox").html(solvehead);
                    $("#solve").show();
                    sll = $("#solvedata").val();
                    console.log("结束---"+sll);
                    if(sll == ""){
                        $("#solve").hide();
                    }
                }
                //遍历图片
                var str = "<div >图片：</div>";
                for (var i = 0; i < data[0].customerPictureModels.length; i++) {
                    str += "<img src='" + data[0].customerPictureModels[i].picUrl + "' style='width:25%;cursor:pointer;' onclick='imgDisplay(this)'>"
                }
                $("#pic").html(str);

                $.ajax({
                    url: "/customer/type_init",
                    type: "POST",
                    dataType: "json",
                    data: {typeId: typeid},
                    success: function (json) {
                        typeBind(json.data.firId);
                        secondInit(json.data.firId,json.data.scId);
                        thirdInit(json.data.scId,json.data.thId);
                    }
                })
            }, error: function () {
                alert("查看失败。");
            }
        });
    });
})

//分页显示DataTable
var table3;
var tap = 0;
var pa;
//获取列表信息
var idButton;
var statusV
var search =  function (status) {

    statusV = status;
    if (statusV == null){
        statusV = $("#status option:selected").val();
    }

    if (tap != 0){
        table3.ajax.reload();
    }else{
        table3 = $('#datatables').DataTable({
            "searching": false, // 从结果搜索
            "bJQueryUI": true,
            "ordering" : true, // 排序
            "aaSorting": [0, "desc"], // 按Id倒序排列
            "sPaginationType": "full_numbers",
            "serverSide": false, // true代表后台分页，false代表前台分页
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },

            ajax: function (data, callback, settings) {
                    pa = getQueryCondition(data,statusV);

                // 开启遮罩效果
                //$("#content").showLoading();
                $.ajax({
                    type: "GET",
                    url: '/customer/getServiceByCondition',
                    cache: false,  //禁用缓存
                    data: pa,    //传入已封装的参数
                    dataType: "json",
                    success: function (result) {
                        //$("#content").hideLoading();
                        callback(result.data);
                        /*var
                            // 关闭遮罩效果
                            $("#content").hideLoading();*/
                        /*if (result.code==202){alert(result.message)}
                        if (result.code==203){alert(result.message)}
                        if (result.code==204){alert(result.message)}
                        if (result.code == 200) {callback(result.data);}*/
                    }
                });
            },
            "columns": [
                {
                    "sClass": "text-center",
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        idButton = data;
                        return validate(data);
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "serviceNo",
                    "render": function (data, type, full, meta) {
                        return validate(data);
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "phoneNumber",
                    "render": function (data, type, full, meta) {
                        return validate(data);
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "name",
                    "render": function (data, type, full, meta) {
                        return validate(data);
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "workName",
                    "render": function (data, type, full, meta) {
                        return validate(data);
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "gmtCreate",
                    "render": function (data, type, full, meta) {
                        return formatDate(validate(data));
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "gmtModified",
                    "render": function (data, type, full, meta) {
                        return formatDate(validate(data));
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": "status",
                    "render": function (data, type, full, meta) {
                        var st;
                        if(data == 0){
                            st = "草稿";
                        }
                        if(data == 1){
                            st = "待处理";
                        }
                        if(data == 2){
                            st = "已处理";
                        }
                        if(data == 3){
                            st = "待评价";
                        }
                        if(data == 4){
                            st = "已完成";
                        }
                        return st;
                    },
                    "bSortable": false
                },
                {
                    "sClass": "text-center",
                    "data": null,
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
                {
                    targets: 8, "data": "id",
                    render: function (data, type, full, meta) {
                        return '<button type="button"  class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="'+idButton+'">查看/修改</button>';
                    }
                },
                {"orderable": false, "targets": 8},


            ],
        });
        statusV = null;
        tap++;
        pa = null;
    }
}
/*
phoneNumber: $('[name="phoneNumber"]').val(),
                        workerNumber: $('[name="workNumber"]').val(),
                        status: statusV,
                        beginTime: $('#datetimeBegin').val(),
                        endTime: $('#datetimeEnd').val(),
                        */
function getQueryCondition(data,a) {
    var param = {};
    param.phoneNumber = $('[name="phoneNumber"]').val();
    param.workerNumber = $('[name="workNumber"]').val();
    param.status = a;
    param.beginTime = $('[name="datetimeBegin"]').val();
    param.endTime = $('[name="datetimeEnd"]').val();
    param.start = data.start;
    param.length = data.length;
    param.draw = data.draw;
    return param;
}


//空字符串转换
var validate = function (data) {
    if (data == null) {
        return "";
    }
    return data;
}
//锁定编辑
function blockedit() {
    $("#phoneNumber").attr("readonly", "readonly");
    $("#phoneType").attr("readonly", "readonly");
    $("#realName").attr("readonly", "readonly");
    $("#typeShows").attr("disabled", "disabled");
    $("#typeShows2").attr("disabled", "disabled");
    $("#typeShows1").attr("disabled", "disabled");
    $("#title").attr("readonly", "readonly");
    $("#description").attr("readonly", "readonly");
    $("#updateimage").hide();

}

//锁定编辑
function lockedit() {
    $("#phoneNumber").attr("readonly", "readonly");
    $("#phoneType").attr("readonly", "readonly");
    $("#realName").attr("readonly", "readonly");
    $("#typeShows").attr("disabled", "disabled");
    $("#typeShows2").attr("disabled", "disabled");
    $("#typeShows1").attr("disabled", "disabled");
    $("#title").attr("readonly", "readonly");
    $("#description").attr("readonly", "readonly");
    $("#updateimage").hide();
    $("#newsolveShow").show();
}

//解除锁定编辑
function rmlockedit() {
    $("#phoneNumber").attr("readonly", false);
    $("#phoneType").attr("readonly", false);
    $("#realName").attr("readonly", false);
    $("#typeShows").removeAttr("disabled");
    $("#typeShows2").removeAttr("disabled");
    $("#typeShows1").removeAttr("disabled");
    $("#title").attr("readonly", false);
    $("#description").attr("readonly", false);
    $("#updateimage").show();
    $("#newsolve").hide();
}

//日期转换
function formatDate(now) {
    if (now == "") {
        return "";
    }
    return new Date(now).toLocaleString();
}

//上传图片处理

var imgSrc = [];  //存放图片路径
var imgFile = []; //存放文件流
var imgName = []; //存放图片名字
//选择图片的操作
function imgUpload(obj) {
    var oInput = '#' + obj.inputId;
    var imgBox = '#' + obj.imgBox;
    var btn = '#' + obj.buttonId;
    //用on是因为它可以动态的绑定事件
    $(oInput).on("change", function () {
        //获取type=file的input
        var fileImg = $(oInput)[0];
        //得到所有的图片列表
        var fileList = fileImg.files;
        for (var i = 0; i < fileList.length; i++) {
            //得到每个图片的路径
            var imgSrcI = getObjectURL(fileList[i]);
            //向文件名的数组末尾添加此文件名
            imgName.push(fileList[i].name);
            //向路径的数组末尾添加路径
            imgSrc.push(imgSrcI);
            //在文件流数组的末尾添加文件
            imgFile.push(fileList[i]);
        }
        //将图片展示出去
        addNewContent(imgBox);
    })

    $(btn).on('click', function () {
        if (!limitNum(obj.num)) {
            alert("最多只能上传" + obj.num + "张照片!");
            return false;
        }

        //用FormData对象上传
        var fd = new FormData($('#upload')[0]);
        //由于fd对象中已经包含<input type='file'>的input标签，如果不删除，就会造成重复上传
        fd.delete("file");
        //将文件流循环添加到FormData对象中
        for (var i = 0; i < imgFile.length; i++) {
            fd.append(obj.data, imgFile[i]);
        }
        //上传所有的图片
        test();
        submitPicture(obj.upUrl, fd);

    })
}

//图片展示
function addNewContent(obj) {
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img width="300" height="300" title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ');closePicture(this)" class="imgDelete">删除</p></div>');
    }
}

//删除
function removeImg(obj, index) {
    //向数组中删除元素
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
    addNewContent(boxId);
}

//限制图片个数
function limitNum(num) {
    if (!num) {
        return true;
    } else if (imgFile.length > num) {
        return false;
    } else {
        return true;
    }
}

//上传(将文件流数组传到后台)
function submitPicture(url, data) {
    for (var p in data) {
        console.log(p);
    }
    if (url && data) {
        $.ajax({
            type: "post",
            url: url,
            async: true,
            data: data,
            //下面这两个要写成false，要不然上传不了。
            processData: false,
            contentType: false,
            success: function (dat) {
                console.log(phoneType);
                alert("成功");
                console.log(dat);
            }
        });
    } else {
        alert('数据格式不正确!');
    }
}

//当鼠标移到图片上时，显示x删除
function imgDisplay(obj) {
    var src = $(obj).attr("src");
    var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
    $('body').append(imgHtml);
}

//关闭
function closePicture(obj) {
    $(obj).parent("div").remove();
}

//图片预览路径
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

//问题三级联动 start

//一级问题类型初始化
function typeBind(firId) {
    var str = "";
    $.ajax({
        type: "post",
        url: "/customer/type?parentId=0",
        async: false,
        typedata: "json",
        success: function (json) {
            for (var i = 0; i < json.data.length; i++) {
                var temp = "<option   value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
                if (firId==json.data[i].id){
                    temp =  "<option   value='" + json.data[i].id + "' selected='selected'>" + json.data[i].problemType + "</option>"
                }
                str += temp;
            }
            $("#typeShows").append(str);
        }
    })

}
//二级问题类型初始化
function secondInit(parentId,scId) {
    var str = "<option value='" + -1 + "' id='" + -1 + "'>---请选择---</option>";
    $.ajax({
        type: "post",
        url: "/customer/type",
        typedata: "json",
        async: false,
        data: {parentId: parentId},
        success: function (json) {
            for (var i = 0; i < json.data.length; i++) {
                var temp = "<option   value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
                if (scId==json.data[i].id){
                    temp =  "<option   value='" + json.data[i].id + "' selected='selected'>" + json.data[i].problemType + "</option>"
                }
                str += temp;
            }
            $("#typeShows1").html(str);
        }
    })
}
//三级问题类型初始化
function thirdInit(parentId,thId) {
    var str = "<option value='" + -1 + "' id='" + -1 + "'>---请选择---</option>";
    $.ajax({
        type: "post",
        url: "/customer/type",
        typedata: "json",
        async: false,
        data: {parentId: parentId},
        success: function (json) {
            for (var i = 0; i < json.data.length; i++) {
                var temp = "<option   value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
                if (thId==json.data[i].id){
                    temp =  "<option   value='" + json.data[i].id + "' selected='selected'>" + json.data[i].problemType + "</option>"
                }
                str += temp;
            }
            $("#typeShows2").html(str);
        }
    })
}




function show_sub(v) {
    var str = null;

}

function firstType() {
    var type = $("#typeShows").attr("value");
    //判断一级菜单这个下拉框选中的值是否为空
    if (type == "") {
        $("#typeShows1").empty();
    }
    $("#typeShows1").html("");
    var r = $("#typeShows option:selected").val();
    console.log(r);
    /!* console.log(r);*!/
    var str = "<option value='" + -1 + "' id='" + -1 + "'>---请选择---</option>";
    $.ajax({
        type: "post",
        url: "/customer/type",
        typedata: "json",
        async: false,
        data: {parentId: r},
        success: function (json) {
            //从服务器获取数据进行绑定
            for (var i = 0; i < json.data.length; i++) {
                str += "<option id='" + json.data[i].id + "'  value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
            }
            $("#typeShows1").append(str);
        }
    })

}
function SecondType() {
    $("#typeShows2").html("");
    var r = $("#typeShows1 option:selected").val();
    if(r == 0){
        r = r-1;
    }

    var str = "<option>---请选择---</option>";

    $.ajax({
        type:"post",
        url:"/customer/type",
        typedata:"json",
        async: false,
        data:{parentId:r},
        success:function (json) {
            //从服务器获取数据进行绑定
            for(var i = 0 ; i<json.data.length;i++) {
                str += "<option value='" + json.data[i].id + "'  id='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
            }
            $("#typeShows2").html(str);
            console.log($("#typeShows option:selected").attr("id"));
            console.log($("#typeShows1 option:selected").attr("id"));
            console.log($("#typeShows2 option:selected").attr("id"));
        }
    })
}
function Second2Type() {
    console.log($("#typeShows2 option:selected").attr("id"));
}
//问题类型三级联动 end
var serviceId;
var problemId;
var solveId;
var questionId;
//获取更新数据
function getParam(status) {
    var formdata = new FormData();
    formdata.append("serviceId",serviceId);
    formdata.append("problemId",problemId);
    formdata.append("questionId",questionId);
    formdata.append("solveId",solveId);
    formdata.append("phoneNumber",$("#phoneNumber").val());
    formdata.append("phoneType",$("#phoneType").val());
    formdata.append("name",$("#realName").val());
    formdata.append("typeId",$("#typeShows2").val());
    formdata.append("title",$("#title").val());
    formdata.append("description",$("#description").val());
    formdata.append("staats",status);
    if (status==2){formdata.append("solve",$("#newsolve").val());}
    if (status==1){formdata.append("solve",$("[name='solve']").val());}
    //将文件流循环添加到FormData对象中
    if (status==1){
        for(var i=0;i<imgFile.length;i++){
            formdata.append("file",imgFile[i]);
        }
    }

    return formdata;
}
function updateService(status) {
    var dataform = getParam(status);
    $.ajax({
        url:"/customer/update",
        type:"post",
        dataType:"json",
        data:dataform,
        processData:false,
        contentType:false,
        success:function (json) {
            alert(json.message);
            $("#exampleModal").modal("hide");
            search(status);
        },
        error:function () {
            alert("请求失败")
        }

    })

}

function updateServiceNewSolve(status) {
    var dataform = getParam(status);
    $.ajax({
        url:"/customer/updateSolve",
        type:"post",
        dataType:"json",
        data:dataform,
        processData:false,
        contentType:false,
        success:function (json) {
            alert(json.message);
            $("#exampleModal").modal("hide");
            search(status);
        },
        error:function () {
            alert("请求失败")
        }
    })
}

//获取表单数据
function getData() {
    var formdata = new FormData();
    formdata.append("beginTime", startTime);
    formdata.append("endTime", getNowTime());
    formdata.append("phoneNumber", $("#phoneNumberN").val());
    formdata.append("phoneType", $("#phoneType").val());
    formdata.append("name", $("#nameN").val());
    formdata.append("typeId", $("#type2").val());
    formdata.append("title", $("#titleN").val());
    formdata.append("description", $("#descriptionN").val());
    formdata.append("solve", $("#solveN").val());
    //将文件流循环添加到FormData对象中
    for (var i = 0; i < imgFile.length; i++) {
        formdata.append("file", imgFile[i]);
    }
    return formdata;
}

//插入服务
function insertService(status) {
    startTime = getNowTime();
// 参数验证

    var phoneNumber = $("#phoneNumberN").val();
    var name = $("#nameN").val();
    var type = $("#type").val();
    var type1 = $("#type1").val();
    var type2 = $("#type2").val();

    // 当userId都为空时flag==true
    var flag = (phoneNumber == null || phoneNumber == "");
    if (flag) {
        layer.msg("手机号不能都为空");
        return;
    }
    if (phoneNumber != null && phoneNumber != "") {
        var mobileReg = /^[1][0-9]{10}$/;
        if (!mobileReg.test(phoneNumber)) {
            layer.msg("手机号格式不正确");
            return;
        }
    }
    if (name == null || name == "") {
        layer.msg("客户名不能为空");
        return;
    }
    var dataform = getData();
    dataform.append("status", status);
    console.log(status);
    $.ajax({
        url: "/customer/add",
        type: "post",
        dataType: "json",
        data: dataform,
        processData: false,
        contentType: false,
        success: function (json) {
            layer.alert(json.message);
            $("#NewSe")[0].reset();
            $("#imgBoxN").val("");
            $("#startServer").modal("hide");
            search(status);
        },
        error: function () {
            alert("请求失败")
        }
    })
}



var type_json = "";

function TypeBind() {
    $("#type").html("");

    var str = "<option value='" + -1 + "' id='" + -1 + "'>---请选择---</option>";
    $.ajax({
        type: "post",
        url: "/customer/type?parentId=0",
        async: false,
        typedata: "json",
        success: function (json) {
            type_json = json;
            //从服务器获取数据进行绑定
            for (var i = 0; i < json.data.length; i++) {
                str += "<option  id='" + json.data[i].id + "'  value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
            }
            $("#type").append(str);
        }
    })

}

function firstTypeNewService() {
    var type = $("#type").attr("value");
    //判断一级菜单这个下拉框选中的值是否为空
    if (type == "") {
        $("#type1").empty();
    }
    $("#type1").html("");
    var r = $("#type option:selected").attr("id");
    console.log(r);
    /* console.log(r);*/
    var str = "<option value='" + -1 + "' id='" + -1 + "'>---请选择---</option>";
    $.ajax({
        type: "post",
        url: "/customer/type",
        typedata: "json",
        async: false,
        data: {parentId: r},
        success: function (json) {
            /* console.log(json.data);
             console.log(json.data[0].problemType);*/
            //从服务器获取数据进行绑定
            for (var i = 0; i < json.data.length; i++) {
                str += "<option id='" + json.data[i].id + "'  value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
                /*console.log("aaa");
                console.log(json.data[i].problemType);*/
            }
            $("#type1").append(str);
        }
    })

}

function SecondTypeNewService() {
    $("#type2").html("");
    var r = $("#type1 option:selected").attr("id");
    /* console.log("bbb");
     console.log(r);*/
    if (r == 0) {
        r = r - 1;
    }

    var str = "<option>---请选择---</option>";

    $.ajax({
        type: "post",
        url: "/customer/type",
        typedata: "json",
        async: false,
        data: {parentId: r},
        success: function (json) {
            /*  console.log(json.data);
              console.log(json.data[0].problemType);*/
            //从服务器获取数据进行绑定
            for (var i = 0; i < json.data.length; i++) {
                str += "<option value='" + json.data[i].id + "' >" + json.data[i].problemType + "</option>";
            }
            $("#type2").html(str);
        }
    })
}

//开始服务
function startServer(a) {
    var obj = document.getElementById(a);
    if (obj.style.display == "") {
        obj.style.display = "none";
    } else {
        obj.style.display = "";
        startTime = getNowTime();
    }
}

//获取当前时间戳
function getNowTime() {
    var now = new Date().getTime();
    return (now);
}
//新增服务提交
function putService(status){
    insertService(status);

    search(status);
}