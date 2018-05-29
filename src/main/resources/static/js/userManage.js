$(function () {
    getDate(1,10);
});
var validate = function (data) {
    if (data == null) {
        return "";
    }
    return data;
}
var getDate = function(page,length){
    $.ajax({
        url: "/role/findUserByPage",
        type: "post",
        dateType: "json",
        data :{pageNo:page,length:length},
        success: function (json) {

            console.log(json.data);
            var str = "";
            /*console.log(json.data[1]userId);*/
            createPageNav({
                $container : $("#pageing"),
                pageCount :json.data.pages,
                currentNum : json.data.pageNum,
                afterFun : function(num){
                    getDate(num,10);
                },
                hasCommonPage:false
            });
            console.log(json.data.list);
            var dataList = json.data.list;
            for (var i = 0; i < dataList.length; i++) {
                var date = "";
                date = new Date(dataList[i].gmtModify);
                str += '<tr>'
                    + '<th>' + dataList[i].userId + '</th>'
                    + '<th>' + dataList[i].username + '</th>'
                    + '<th>' + date.toLocaleString() + '</th>'
                    + "<th ><button type='button' class='btn btn-default' data-toggle='modal' data-target='#exampleModal'" +
                    "data-whatever='" + dataList[i].userId + "'>编辑</button>" +
                    "</th>"
                    + '</tr>'
            }
            $('#roleList').html(str);
        }
        ,error : function () {
            console.log("立哥报错啦");
        }
    })
}

