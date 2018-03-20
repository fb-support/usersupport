function updataDate  () {
    var ids = new Array();
    $("input:checkbox:checked").each(function () {
        ids.push($(this).val());
    });
    $.ajax({
        url:"/role/updataMenu",
        type:"POST",
        dataType:"json",
        data:{ids:ids,id:recipient},
        success:function (json) {
            alert("提交成功")
        },error:function () {
            alert("提交失败，必须存在一条权限")
        }
    })
}
$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // 触发事件的按钮
    recipient = button.data('whatever') // 解析出data-whatever内容
    var ids=new Array();
    $.ajax({
        url: "/menu/findAllMenu",
        type: "POST",
        dataType: "json",
        success: function (json) {
            var str = ""
            for (var i = 0; i < json.data.length; i++) {
                str += "<tr class='success'>" +
                    " <td><input type='checkbox' id='"+json.data[i].menuId+"'value='"+json.data[i].menuId+"'></td>" +
                    " <td>"+json.data[i].menuUrl+"</td>" +
                    " <td>"+json.data[i].menuName+"</td></tr>";
                ids[i] = json.data[i].menuId;
                console.log(ids[i]);
            }
            $('#permission').html(str);
        },error:function () {
            alert("flase");
        }
    });
    var modal = $(this);
    $.ajax({
        url:"/role/findMenuAlready",
        type: "POST",
        dataType: "json",
        data:{id:recipient},
        success:function (json) {
            for (var i=0;i<json.data.length;i++){

                if (-1 !=ids.indexOf(json.data[i].menuId)){
                    console.log(json.data[i].menuId);
                    modal.find('#'+json.data[i].menuId).attr("checked","checked");
                }
            }
        }
    })

    modal.find('.modal-title').text('菜单选择')
    // modal.find('[type="checkbox"]').attr("checked","checked");
});

$(document).ready(function () {
    $.ajax({
        url: "/role/findAll",
        type: "POST",
        dataType: "json",
        success: function (json) {
            var data = json.data;
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += '<tr>' +
                    "<td >" + validate(data[i].roleId) + "</td>" +
                    "<td>" + validate(data[i].roleName) + "</td>" +
                    "<td>" + validate(data[i].description) + "</td>" +
                    "<th ><button type='button' class='btn btn-default' data-toggle='modal' data-target='#exampleModal'" +
                    "data-whatever='" + data[i].roleId + "'>授权</button>" +
                    "</th>" +
                    "<td>" + validate(data[i].mobile) + "</td>" +
                    "</tr>";
            }
            $('#roleList').html(str);
        }
    })
});
var validate = function (data) {
        if (data == null) {
            return "";
        }
        return data;
    }


