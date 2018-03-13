/**
 *  ajax异步查询封装的方法
 *  @param way 请求方式
 *  @param url 请求地址
 *  @param data 请求数据
 *  @return meg
 */
// function syncQuery(way, url, verityObj, objType ) {
//
//     $.ajax({
// 		type : way, //请求方式
// 		url : url, //请求路径
// 		cache : false,
// 		data : "verityObj="+verityObj+"&objType="+objType,  //传参 页数
// 		dataType : 'json', //返回值类型
//             success : function(msg) {
// 		    console.log(msg);
//             if(msg.code == "1") {
//
//             } else {
//             }
// 		},
// 		error : function() {
// 			alert("发生了一个未知错误。请刷新重试。");
// 		}
// 	});
// }

/**
 * 异步查询唯一+判空
 * @param obj   验证数据
 * @param objName  验证数据名
 * @param objType  验证数据类型，1为工号，2为用户名。3为手机号码。4为邮箱
 * @param chinesName 验证数据中文名
 */
// function emptyVerity(obj, objName, objType, chinesName) {
//     // 1-1.判空
//     if (obj == null ||
//         obj.trim() == "") {
//         // 1-2.异步查询是否唯一
//         // var status = syncQuery("post", "/sync/verity", obj, objType);
//         // if(!status) {
//         //     $("#" + objName).parent().next().children().text(chinesName + "已被注册！");
//         // }
//         // return status;
//         $("#" + objName).parent().next().children().text(chinesName + "不能为空！");
//         return false;
//     }
//     return true;
// }


$(function() {

    // 失去焦点异步查询是否唯一或者是否为空
    $(".syncVerity").blur(function () {
        if ($(this).val() == null | $(this).val() == "") {
            $(this).parent().next().children().text("此处不能为空！");
        } else {
            $(this).parent().next().children().text("");
        }
    });

    var usernameFlag = true;
    var emailFlag = true;
    var phoneFlag = true;
    var passwordFlag = true;


// 失去焦点验证用户名格式
    $("#username").blur(function () {
        //用户名正则，4到16位（非纯数字/非纯字母/不能含有@字符）
        var usernameReg = /^(?![0-9]+$)(?![a-zA-Z]+$)(?!@+$)([\u4e00-\u9fa50-9A-Za-z]){6,16}$/;
        var inputUsername = $('#username').val();
        if (!usernameReg.test(inputUsername)) {
            $(this).parent().next().children().text("用户名格式错误，请输入正确用户名！");
            usernameFlag = false;
        } else {
            $(this).parent().next().children().text("4到16位（非纯数字/非纯字母/不能含有@字符）").attr("style","color:green");
            usernameFlag = true;
        }
    });

    // 失去焦点验证邮箱格式
    $("#email").blur(function () {
        var emailReg = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
        var inputEmail = $('#email').val();
        if (!emailReg.test(inputEmail)) {
            $(this).parent().next().children().text("邮箱格式错误，请输入正确邮箱！");
            emailFlag = false;
        } else {
            $(this).parent().next().children().text("");
            emailFlag = true;
        }
    });

    //失去焦点验证手机格式
    $("#phone").blur(function () {
        var phoneReg = /^1[34578]\d{9}$/;
        var inputPhone = $('#phone').val();
        if (!phoneReg.test(inputPhone)) {
            $(this).parent().next().children().text("手机号格式错误，请输入正确手机号！");
            phoneFlag = false;
        } else {
            $(this).parent().next().children().text("");
            phoneFlag = true;
        }
    });

    // 失去焦点验证第二次密码
    $("#password_verity").blur(function () {
        var inputPassword = $('#password').val();
        var inputverity = $('#password_verity').val();
        if (inputPassword!=inputverity) {
            $(this).parent().next().children().text("两次输入的密码不一致！");
            passwordFlag = false;
        } else {
            $(this).parent().next().children().text("");
            passwordFlag = true;
        }
    });

    //给点击表单提交之前进行是否注册的异步查询操作
    $("#btn_submit").click(function() {

        // //声明状态变量，为true的话，提交表单。默认为true
        // // var flag = true;
        // /*
        //  * 获得表单上的数据
        //  */
        // // 5.获得工号
        // var workNumber = $("#workNumber").val();
        // // 1-1.判空+异步查询唯一
        // var isOK_workNumber = emptyVerity(workNumber, "workNumber",1, "工号");
        // if (!isOK_workNumber) {
        //     flag = false;
        //     return;
        // }
        //
        // // 1.获得用户名
        // var username = $("#username").val();
        // // 1-1.判空+异步查询唯一
        // var isOK_username = emptyAndUniqueVerity(username, "username", 2,  "用户名");
        // if (!isOK_username) {
        //     flag = false;
        //     return;
        // }
        //
        // // 2.获得手机号码
        // var phonenumber = $("#phone").val();
        // // 1-1.判空+异步查询唯一
        // var isOK_phonenumber = emptyAndUniqueVerity(phonenumber, "phone", 3, "手机号");
        // if (!isOK_phonenumber) {
        //     flag = false;
        //     return;
        // }
        //
        // // 3.获得邮箱
        // var email = $("#email").val();
        // var isOK_email = emptyAndUniqueVerity(email, "email", 4, "邮箱");
        // if (!isOK_email) {
        //     flag = false;
        //     return;
        // }
        //
        // 4.获得密码
        var password = $("#password").val();
        if (password == null |
            password.trim() == "") {
            flag = false;
            return;
        }

        // 5.获得确认密码
        var password_verity = $("#password_verity").val();
        if (password_verity == null |
            password_verity.trim() == "") {
            flag = false;
            return;
        }

        //判断两次输入密码是否一致
        if (password != password_verity) {
            flag = false;
            return;
        }


        /*
         * 判断是注册页面的还是用户管理添加用户页面的。
         */
        var currentUrl = window.location.pathname;

        if(usernameFlag && emailFlag && phoneFlag && passwordFlag) {
            $.ajax({
                type : "POST", //请求方式
                url : "/register", //请求路径
                cache : false,
                data : $("#form_insertUser").serialize(),  //传参 页数
                dataType : 'json', //返回值类型
                success : function(msg) {
                    console.log(msg)
                    if(msg.code == "1") {
                        //成功注册。
                        if (currentUrl == "/register") {
                            location.href = "/login";
                        } else {
                            alert("添加成功！");
                        }
                    } else if(msg.code == "850") {
                        // 存在被注册
                        alert("操作失败，" + msg.message);
                    } else {
                        //其他情况
                        alert("发生了未知情况，错误信息为：" + msg.message);
                    }
                }
            });
        }
    });
});
