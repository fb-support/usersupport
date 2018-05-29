function updataDate(){
    var data = {
        menuId : recipient,
        menuName: document.getElementById("umenuName").value,
        status: $("#umenuStatus option:selected").val(),

        description: document.getElementById("umenuDescription").value
    }
    $.ajax({
        url:"/menu/updateByPrimaryKey",
        type:"POST",
        data:data,
        dataType: "json",
        success: function (json) {
            console.log("add   Data"+json);
            if(json.status = 1){
                alert("更新成功");
                findAllMenu();
                $("#exampleModal").modal('hide');
            }else{
                alert("更新失败")
                $("#exampleModal").modal('hide');
            }
        }
    })
}
function InsertDate () {
    var data = {
        menuName: document.getElementById("menuName").value,
        menuUrl: document.getElementById("menuUrl").value,
        status: $("#status option:selected").val(),
        description: document.getElementById("description").value
    }
    console.log(data);
    $.ajax({
        url:"/menu/insertSelective",
        type:"POST",
        data:data,
        dataType: "json",
        success: function (json) {
            console.log("add   Data"+json);
            if(json.status = 1){
                alert("添加成功");
                findAllMenu();
                $("#exampleModalInsert").modal('hide');
            }else{
                alert("添加失败");
                $("#exampleModalInsert").modal('hide');
            }
        }
    })
}

function del(item) {
    $.ajax({
        url: "/menu/deleteMenu?menuId="+item,
        type: "GET",
        dataType: "json",
        success: function (json) {
            console.log(json);
            alert("删除成功");
            findAllMenu();

        }, error: function () {
            alert("删除失败");
        }

    })
}
var findAllMenu = function () {
    $.ajax({
        url: "/menu/findAllMenu",
        type: "GET",
        dataType: "json",
        success: function (json) {
            var data = json.data;
            var str = "";
            for (var i = 0; i < data.length; i++) {
                var status = "禁用";
                if(validate(data[i].status)==1){
                    status="启用";
                }
                str += '<tr>' +
                    "<td >" + validate(data[i].menuId) + "</td>" +
                    "<td>" + validate(data[i].menuName) + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" + validate(data[i].description) + "</td>" +
                    "<th ><button type='button' class='btn btn-default' data-toggle='modal' data-target='#exampleModal'" +
                    "data-whatever='" + data[i].menuId + "'>编辑</button>" +
                    "<button type='button' onclick='del("+data[i].menuId+")' class='btn btn-default'  data-target='#exampleModaldelete'" +
                    "data-whatever='" + data[i].menuId + "'>删除</button>"+
                    "</th>" +
                    "</tr>";
            }
            $('#menu').html(str);
        }, error: function () {
            alert("flase");
        }
    })
}
$(document).ready(function () {
    findAllMenu();
})
var validate = function (data) {
        if (data == null) {
            return "";
        }
        return data;
    }