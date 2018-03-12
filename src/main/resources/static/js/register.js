/**
 *  ajax异步查询封装的方法
 *  @param way 请求方式
 *  @param url 请求地址
 *  @param data 请求数据
 *  @return meg
 */
function syncQuery(way, url, verityObj, objType ) {
    var status = true;
    $.ajax({
		type : way, //请求方式
		url : url, //请求路径
		cache : false,
		data : "verityObj="+verityObj+"&objType="+objType,  //传参 页数
		dataType : 'json', //返回值类型
		success : function(msg) {
		    status = false;
		},
		error : function() {
			alert("发生了一个未知错误。请刷新重试。");
		}
	});
    return status;
}

/**
 * 异步查询唯一+判空
 * @param obj   验证数据
 * @param objName  验证数据名
 * @param objType  验证数据类型，1为工号，2为用户名。3为手机号码。4为邮箱
 * @param chinesName 验证数据中文名
 */
function emptyAndUniqueVerity(obj, objName, objType, chinesName) {

    // 1-1.判空
    if (obj != null &&
        obj.trim() != "") {
        // 1-2.异步查询是否唯一
        var status = syncQuery("post", "/sync/verity", obj, objType);
        if(!status) {
            $("#" + objName).parent().next().children().text(chinesName + "已被注册！");
        }
        return status;
    } else {
        $("#" + objName).parent().next().children().text(chinesName + "不能为空！");
        return false;
    }
}


$(function() {

    // 失去焦点异步查询是否唯一或者是否为空
    $(".syncVerity").blur(function () {
        if ($(this).val() == null | $(this).val() == "") {
            $(this).parent().next().children().text("此处不能为空！");
        }
    });

    //给点击表单提交之前进行是否注册的异步查询操作
    $("#btn_submit").click(function() {

    var usernameFlag = true;
    var emailFlag = true;
    var phoneFlag = true;
    var passwordFlag = true;


// 失去焦点验证用户名格式
    $("#username").blur(function () {
        //用户名正则，4到16位（字母，数字，下划线，减号）
        var usernameReg = /^[a-zA-Z0-9_-]{4,16}$/;
        var inputUsername = $('#username').val();
        if (!usernameReg.test(inputUsername)) {
            $(this).parent().next().children().text("用户名格式错误，请输入正确用户名！");
            usernameFlag = false;
        } else {
            $(this).parent().next().children().text("4到16位（字母，数字，下划线，减号）");
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

        /*
         * 判断是注册页面的还是用户管理添加用户页面的。
         */
        var currentUrl = window.location.pathname;

        if(flag && usernameFlag && emailFlag && phoneFlag && passwordFlag) {
            $.ajax({
                type : "POST", //请求方式
                url : "/register", //请求路径
                cache : false,
                data : $("#form_insertUser").serialize(),  //传参 页数
                dataType : 'json', //返回值类型
                success : function(msg) {
                    console.log(msg)
                    if(msg.code == 1) {
                        if (currentUrl == "/register") {
                            location.href = "/login";
                        } else {
                            alert("添加成功！");
                        }
                    } else {
                        if (url == "/register") {
                            location.href = "/register";
                        } else {
                            alert("添加失败！");
                        }

                    }
                }
            });
        }
    });
});
