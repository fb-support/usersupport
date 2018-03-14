
$(function () {
    $("#btn_reset_password").click(function () {
        var password = $("#password").val();
        var new_password = $("#new_password").val();
        var confirm_password = $("#confirm_password").val();
        $.ajax({
            url:"/sc/getByUserId",
            type:"GET",
            async : false,
            dataType:"JSON",
            data:{
                userId:1
            },
            success:function (data) {
                console.log(data)
                if(data.code == 1) {
                    var oldPassword = data.data.password;
                    if (password == oldPassword) {
                        if (new_password == confirm_password) {
                            $.ajax({
                                url: "/sc/updatePasswordById",
                                type: "POST",
                                async: false,
                                dateType: "JSON",
                                data: {
                                    userId: 1,
                                    password: new_password
                                },
                                success: function (data) {
                                    if (data.code == 1) {
                                        alert("修改成功");
                                    } else {
                                        alert("修改失败");
                                    }
                                }
                            });
                        } else {
                            alert("两次输入密码不一致");
                        }
                    } else {
                        alert("请输入正确的旧密码");
                    }
                } else {
                    alert("加载出错");
                }
            }
        });
    });
});