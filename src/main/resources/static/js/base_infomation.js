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
        $("input").border="1px";
        $("#btn_modify").hide();
    });
    $(document).ready(function () {
        show_Infomation();
        update_Infomation();
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
                $("#gmt_create").text(data.data.gmtCreate);
                if(data.data.status == 1){
                    $("#status").val("在职");
                    return;
                }
                if(data.data.status == 2){
                    $("#status").val("离职");
                    return;
                }
            }else{
                alert("加载失败");
            }
        }
    });

}

// $(function () {
//     $("#btn_submit").click(function () {
//         var username = $("#username").val();
//         var phone = $("#phone").val();
//         var work_number = $("#work_number").val();
//         var email = $("#email").val();
//         $.ajax({
//             url:"/sc/updateBaseInfoMationById",
//             async:false,
//             type:"POST",
//             data:{
//                 workNumber:work_number,
//                 username:username,
//                 phone:phone,
//                 email:email
//             },
//             success:function (data) {
//                 console.log(data);
//                 if(data.code == 1){
//                 alert("修改成功");
//                 }else{
//                     alert("修改失败");
//                 }
//             }
//         });
//     });
// });

/**
 * 表单提交数据更新用户信息
 */
function  update_Infomation() {
    $("#base_infomation").validate({
        onsubmit:true,
        onfoucsout:false,
        onkeyup:false,
        rules: {
            username: {
                required: true
            },
            email: {
                required: true,
                email: true
            },
            phone: {
                required: true,
                minlength: 11,
                maxlength: 16
            }
        },
        messages: {
            username: {
                required: "请输入用户名"
            },
            email: {
                email: "请输入正确的邮箱格式"
            },
            phone: {
                minlength: "手机号码不少于11位",
                maxlength: "手机号码最多16位"
            }
        },
        submitHandler :function (form) {
            form.submit();
            var username = $("#username").val();
            var phone = $("#phone").val();
            var work_number = $("#work_number").val();
            var email = $("#email").val();
            $.ajax({
                url:"/sc/updateBaseInfoMationById",
                async:false,
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
                        alert("修改成功");
                    } else {
                        alert("修改失败");
                    }
                }
            });
        }
    });
}