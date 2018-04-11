/**
 * 提交表单
 */
function addOverTime() {
    var startTime=new Date($("#add_startTime").val());
    var endTime=new Date($("#add_endTime").val());
    var applyCause=$("#add_leaveCause").val();
    var duration=$("#add_duration").val();
    if(startTime!=""&&endTime!=""&&applyCause!=""&&duration!="") {
        if(confirm("确定要保存吗")) {
                $.ajax({
                    type: "POST",
                    url: "/attendance/addOverTime",
                    cache: false,
                    data: {startTime: startTime, endTime: endTime, applyCause: applyCause, duration: duration},
                    dataType: 'json', //返回值类型
                    success: function (data) {
                        console.log(data);
                        if (data.code == 1) {
                            alert("添加成功！");
                            $('#addApply').modal('hide');
                        } else {
                            alert("添加失败！");
                        }
                    }
                });
                return true;
        } else {
            return false;
        }
    }else{
        alert("以下任意字段都不能为空");
    }
}

