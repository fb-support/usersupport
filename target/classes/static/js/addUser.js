$(function() {

    // 失去焦点验证用户名格式
    $("#username").blur(function () {
        //用户名正则，4到16位（字母，数字，下划线，减号）
        var usernameReg = /^[a-zA-Z0-9_-]{4,16}$/;
        var inputUsername = $('#username').val();
        if (!usernameReg.test(inputUsername)) {
            $(this).parent().next().children().text("用户名格式错误，请输入正确用户名！");
        }
    });

    // 失去焦点验证邮箱格式
    $("#email").blur(function () {
        var emailReg = /^[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.[a-zA-Z][a-zA-Z\.]*[a-zA-Z]$/;
        var inputEmail = $('#email').val();
        if (!emailReg.test(inputEmail)) {
            $(this).parent().next().children().text("邮箱格式错误，请输入正确邮箱！");
        }
    });

    //失去焦点验证手机格式
    $("#phone").blur(function () {
        var phoneReg = /^1[34578]\d{9}$/;
        var inputPhone = $('#email').val();
        if (!phoneReg.test(inputPhone)) {
            $(this).parent().next().children().text("手机号格式错误，请输入正确手机号！");
        }
    });

    // 失去焦点验证第二次密码
    $("#password_verity").blur(function () {
        var inputPassword = $('#password').val();
        var inputverity = $('#password_verity').val();
        if (inputPassword!=inputverity) {
            $(this).parent().next().children().text("两次输入的密码不一致！");
        }
    });

})