
//点击退出按钮时添加日志处理
function beforeLogoutForLog() {
    $.ajax({
        url: "/beforeLogoutForLog",
        async: false,
        type: "POST",
        dataType: "JSON",
        success: function (data) {
            window.location.href = "/logout";
        }
    });
}