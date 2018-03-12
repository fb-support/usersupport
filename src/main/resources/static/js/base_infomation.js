/**
 * 将文本框的属性设置为可编辑状态
 * 将修改按钮隐藏，显示提交按钮
 * 加载用户基本信息
 */
$(function () {
    $("#btn_modify").click(function () {
        // $("#work_number").removeAttr("readonly");
        $("#username").removeAttr("readonly");
        $("#phone").removeAttr("readonly");
        $("#email").removeAttr("readonly");
        $("#btn_submit").removeClass("hide");
        $("#btn_submit").addClass("btn btn-large");
        // $("input").border="1px";
        $("#btn_modify").hide();
        // validateForm();
    });
    $(document).ready(function () {
        show_Infomation();
        // update_Infomation();
    });

});

/**
 * 显示用户信息
 */
function  show_Infomation() {
    $.ajax({
        url:"/sc/getByUserId",
        async:false,
        type:"GET",
        dataType:"JSON",
        data:{
            userId:1
        },
        success:function (data) {
            console.log(data);
            if(data.code == 1){
                $("#work_number").val(data.data.workNumber);
                $("#username").val(data.data.username);
                $("#phone").val(data.data.phone);
                $("#email").val(data.data.email);
                // $("#gmt_create").text(data.data.gmtCreate);
                if(data.data.status == 1){
                    $("#status").val("在职");
                    return;
                }
                if(data.data.status == 2){
                    $("#status").val("离职");
                    return;
                }
            }
        }
    });
}
/**
 * 表单提交数据更新用户信息
 */
function  update_Infomation() {
            // validateForm();
            var username = $("#username").val();
            var phone = $("#phone").val();
            var work_number = $("#work_number").val();
            var email = $("#email").val();
            var emailReg = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
            var phoneReg = /^1[34578]\d{9}$/;
            if (!phoneReg.test(phone)) {
                layer.msg("手机号码格式错误");
                return;
            }

            if (!emailReg.test(email)) {
                layer.msg("邮箱号码格式错误");
                return;
            }
            if(username == ""){
                layer.msg("用户名不能为空");
                return;
            }
            // if(syncQuery("username" ,username,"objType",2)){
            //     layer.msg("用户名已经存在");
            //     return;
            // }
            // if(syncQuery("workNumber" ,work_number,"objType",1)){
            //     layer.msg("用户名已经存在");
            //     return;
            // }
            // if(syncQuery("phone" ,phone,"objType",3)){
            //     layer.msg("用户名已经存在");
            //     return;
            // }
            // if(syncQuery("username" ,email,"objType",4)){
            //     layer.msg("用户名已经存在");
            //     return;
            // }
            $.ajax({
                url:"/sc/updateBaseInfoMationById",
                async:false,
                cache: false,  //禁用缓存
                type:"POST",
                data:{
                    userId:1,
                    workNumber:work_number,
                    username:username,
                    phone:phone,
                    email:email
                },
                success:function (data) {
                    if(data.code == 1) {
                        layer.msg("修改成功");
                        $("#btn_submit").removeClass("btn btn-large");
                        $("#btn_submit").addClass("hide");
                        $("#btn_modify").show();
                        $("#username").attr("readOnly",true);
                        $("#phone").attr("readOnly",true);
                        $("#email").attr("readOnly",true);
                    } else {
                        layer.msg("修改失败");
                    }
                }
            });
}
// /**
//  *  ajax异步查询封装的方法
//  *  @param way 请求方式
//  *  @param url 请求地址
//  *  @param data 请求数据
//  *  @return meg
//  */
// function syncQuery(verityObj, objValue,objType,objTypeValue ) {
//     $.ajax({
//         type : "post", //请求方式
//         url :  "/sync/verity", //请求路径
//         async:false,
//         cache : false,
//         data : {
//             verityObj:objValue,
//             objType : objTypeValue
//         },   //传参 页数
//         dataType : "JSON", //返回值类型
//         success : function(data) {
//             console.log(data);
//             if(data.code == 1){
//                 return false;
//             } else {
//                 return true;
//             }
//         }
//
//     });
// }

// /**
//  * 异步查询唯一+判空
//  * @param obj   验证数据
//  * @param objName  验证数据名
//  * @param objType  验证数据类型，1为工号，2为用户名。3为手机号码。4为邮箱
//  * @param chinesName 验证数据中文名
//  */
// function emptyAndUniqueVerity(obj, objName, objType, chinesName) {
//
//     //根据falg判断是否格式错误。
//     if ($("#flag_"+objName).val() == false) {
//         return false;
//     }
//     // 1-1.判空
//     if (obj != null &&
//         obj.trim() != "") {
//         // 1-2.异步查询是否唯一
//         var status = syncQuery("post", "/sync/verity", obj, objType);
//         if(!status) {
//             $("#" + objName).parent().next().children().text(chinesName + "已被注册！");
//         }
//         return status;
//     } else {
//         $("#" + objName).parent().next().children().text(chinesName + "不能为空！");
//         return false;
//     }
// }