var startTime;//服务开始时间
var endTime;//服务结束时间
$(function () {
    //初始化问题类型3级联动
    TypeBind();
    $("#type").change(function () {
        firstType();
        SecondType();
    })
    $("#type1").change(function () {
        SecondType();
    })
    //图片容初始化
    imgUpload({
        inputId: 'fileN', //input框id
        imgBox: 'imgBoxN', //图片容器id
        buttonId: 'btn', //提交按钮id
        upUrl: '/test/controlller',  //提交地址
        data: 'file', //参数名
        num: "9",//最多上传图片个数
    })

    $('#startServer').on('show.bs.modal', function (event) {});
})

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

            $("#startServer").modal("hide");
        },
        error: function () {
            alert("请求失败")
        }
    })
}


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
    $(imgBox).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img width="300" height="300" title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' onclick="imgDisplay(this)"><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
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
    var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
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
            /*     if(data[0]==0){
                     $("#type1").empty();      //清空下拉框先
                 }else if(data[0]==0){
                     $("#type2").empty();
                 }*/
            console.log("aaa");
            /*    console.log(data[0].problemType);*/
            console.log(json.data[0].problemType);
            //从服务器获取数据进行绑定
            for (var i = 0; i < json.data.length; i++) {
                str += "<option  id='" + json.data[i].id + "'  value='" + json.data[i].id + "'>" + json.data[i].problemType + "</option>";
            }
            $("#type").append(str);
        }
    })

}

function firstType() {
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

function SecondType() {
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