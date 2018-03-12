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
        //声明状态变量，为true的话，提交表单。默认为true
        var flag = true;
        /*
         * 获得表单上的数据
         */
        // 5.获得工号
        var workNumber = $("#workNumber").val();
        // 1-1.判空+异步查询唯一
        var isOK_workNumber = emptyAndUniqueVerity(workNumber, "workNumber",1, "工号");
        if (!isOK_workNumber) {
            flag = false;
            return;
        }

        // 1.获得用户名
        var username = $("#username").val();
        // 1-1.判空+异步查询唯一
        var isOK_username = emptyAndUniqueVerity(username, "username", 2,  "用户名");
        if (!isOK_username) {
            flag = false;
            return;
        }

        // 2.获得手机号码
        var phonenumber = $("#phone").val();
        // 1-1.判空+异步查询唯一
        var isOK_phonenumber = emptyAndUniqueVerity(phonenumber, "phone", 3, "手机号");
        if (!isOK_phonenumber) {
            flag = false;
            return;
        }

        // 3.获得邮箱
        var email = $("#email").val();
        var isOK_email = emptyAndUniqueVerity(email, "email", 4, "邮箱");
        if (!isOK_email) {
            flag = false;
            return;
        }

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

        if(flag) {
            $.ajax({
                type : "POST", //请求方式
                url : "/register", //请求路径
                cache : false,
                data : $("#form_register").serialize(),  //传参 页数
                dataType : 'json', //返回值类型
                success : function(msg) {
                    if(msg.code == 1) {
                        location.href = "/login";
                    } else {
                        location.href = "/register";
                    }
                },
                error : function() {
                    alert("发生了一个未知错误。请刷新重试。");
                }
            });
        }
    });
});