$(function() {

    /**
     * 左边菜单打开二级菜单的方法
     */
    $(".module-1").click(function() {
        var display = $(this).find(".module-2").css("display");
        if(display == "block") {
            $(this).find(".module-2").slideUp();
        } else if (display == "none") {
            $(this).find(".module-2").slideDown();
        }
    });

    //时间格式化方法
    Date.prototype.format = function (fmt) { //
        var o = {
            "M+": this.getMonth() + 1, // Month
            "d+": this.getDate(), // Day
            "h+": this.getHours(), // Hour
            "m+": this.getMinutes(), // Minute
            "s+": this.getSeconds(), // Second
            "q+": Math.floor((this.getMonth() + 3) / 3), // Season
            "S": this.getMilliseconds() // millesecond
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1,(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

});

/**
 * 页面切换函数
 * @param page_id   页面编号
 */
function switchPage(page_id) {
    //清除其他选项卡选定效果
    $(".block-selected").removeClass("block-selected");
    //选择当前选项卡
    var target =  $("li[value='"+page_id+"']")[0];
    target.className = "block-selected";
    //隐藏其他页面
    $(".center_show_area").hide();
    //显示指定页面
    $("#"+page_id).show();

}

/**
 * 页面关闭并且自动切换到下一个页面函数
 * @param page_id
 * @returns
 */
function closePage_and_switchOtherPage( page_id ) {
    //隐藏指定页面
    $("#"+page_id).hide();
    //清除该id对应的选项卡
    var target = $("li[value='"+page_id+"']");
    //获取所有选项卡
    var allNode = target.siblings();
    target.remove();
    //显示上一个选项卡。若当前为唯一的一个，则显示默认页面
    if ( allNode.length > 0 ) {
        allNode[0].className = "block-selected";
        var nextPage_id  = allNode[0].getAttribute("value");
        //显示下一个页面
        $("#"+nextPage_id).show();
    } else {
        $(".shortcuts-service").show();
    }
}

/**
 * 展示页面头的标签功能（可随时切换和关闭）
 * @param page_id    页面id
 * @param page_name  页面名称
 * @returns
 */
function addSmallCardForThisPage( page_id, page_name ) {
    var ul = document.getElementById("ul-block_for_page");
    var li = document.createElement("li");
    //清除其他选项卡选定效果
    $(".block-selected").removeClass("block-selected");
    //设置当前添加页面为选定
    li.className = "block-selected";
    li.setAttribute( "value", page_id );
    //创建页面名称标签
    var span_name = document.createElement("span");
    span_name.innerHTML = page_name;
    //设置事件监听
    span_name.onclick = function() {
        switchPage(page_id);
    };
    //创建页面关闭标签
    var span_close = document.createElement("span");
    span_close.className = "t-close";
    span_close.innerHTML = "×";
    //设置事件监听
    span_close.onclick = function() {
        closePage_and_switchOtherPage(page_id);
    };
    //添加标签
    li.appendChild(span_name);
    li.appendChild(span_close);
    ul.appendChild(li);
}

//js获取项目根路径
function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

