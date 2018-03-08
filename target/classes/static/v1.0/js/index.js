$(function() {

    $(".module-1").click(function() {
        console.log("调用此方法");
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

//商品管理页面--初始化商品页码信息
function clearPageOfItemsList() {
    var pageBody = document.getElementById("pageNumList");
    pageBody.innerHTML = "";
    //创建上一页标签
    var preLi = document.createElement("li");
    preLi.id = "pagePreLi";
    var preA = document.createElement("a");
    preA.setAttribute( "aria-label", "Previous" );
    preA.className = "link_previous";
    var prespan = document.createElement("span");
    prespan.setAttribute( "aria-hidden", "true" );
    prespan.innerHTML = "«";
    preA.appendChild(prespan);
    preLi.appendChild(preA);
    //创建下一页标签
    var nextLi = document.createElement("li");
    nextLi.id = "pageNextLi";
    var nextA = document.createElement("a");
    nextA.setAttribute( "aria-label", "Next" );
    nextA.className = "link_previous";
    var nextspan = document.createElement("span");
    nextspan.setAttribute( "aria-hidden", "true" );
    nextspan.innerHTML = "»";
    nextA.appendChild(nextspan);
    nextLi.appendChild(nextA);

    pageBody.appendChild(preLi);
    pageBody.appendChild(nextLi);
}

//商品管理页面--从异步获得的商品信息填充到页面中
function fillInPageOfItems(rows){
    var body = document.getElementById("body-item-list");//获得tbody标签
    body.innerHTML = ""; //tbody初始化
    for (var i = 0; i < rows.length; i++) {
        //创建table的外层tr。
        var tr = document.createElement("tr");
        //创建商品编号td，并赋值设置属性
        var iid = document.createElement("td");
        iid.scope = "row";
        iid.innerHTML = rows[i].iid;
        //创建商品名称td，并赋值设置属性
        var title = document.createElement("td");
        var titleDiv = document.createElement("div");
        title.className = "title";
        title.innerHTML = rows[i].title;
        //创建商品价格td，并赋值设置属性
        var price = document.createElement("td");
        price.className = "price";
        price.innerHTML = "￥" + rows[i].price/100;
        //创建商品ISBN码td，并赋值设置属性
        var isbn = document.createElement("td");
        isbn.innerHTML = rows[i].isbn;
        //创建商品状态td，并赋值设置属性
        var status = document.createElement("td");
        var st = rows[i].status;
        //创建商品更新时间td，并赋值设置属性
        var update_time = document.createElement("td");
        update_time.innerHTML = new Date(rows[i].updateTime).format("yyyy/MM/dd hh:mm:ss");
        /*设置商品操作td，内含多个操作a标签*/
        var operas = document.createElement("td"); //创建操作集合td
        operas.className = "operas";
        //设置查看按钮
        var link2look = document.createElement("a"); //创建a标签用于查看
        link2look.href = "javascript:lookSingleItem("+rows[i].iid+")";//设置a标签的href
        link2look.innerHTML = "查看";//设置a标签显示内容，innerHTML
        //设置删除按钮
        var link2delete = document.createElement("a"); //创建标签用于删除商品
        link2delete.href = "javascript:deleteSingleItem("+rows[i].iid+")";//设置a标签的href
        link2delete.innerHTML = "删除";//设置a标签显示内容，innerHTML
        //设置上下架按钮
        var link2opera = document.createElement("a"); //创建标签用于管理商品上下架
        if ( st == 1 ) {
            //设置出售中显示和操作下架的按钮
            status.className = "text-red item_status";
            status.innerHTML = "出售中";
            link2opera.innerHTML = "<span class='glyphicon glyphicon-arrow-down'></span>下架";//设置a标签显示内容，innerHTML
            link2opera.href = "javascript:operaSingleItemStatus("+rows[i].iid+",0)";//设置a标签的href
        } else if( st == 0 ) {
            //设置未上架显示和操作上架的按钮
            status.innerHTML = "未上架";
            status.className = "item_status";
            link2opera.innerHTML = "<span class='glyphicon glyphicon-open'></span>上架";//设置a标签显示内容，innerHTML
            link2opera.href = "javascript:operaSingleItemStatus("+rows[i].iid+",1)";//设置a标签的href
        }
        operas.appendChild(link2look); //将a标签添加至td中
        operas.appendChild(link2opera);//将a标签添加至td中
        operas.appendChild(link2delete);//将a标签添加至td中
        tr.appendChild(iid);
        tr.appendChild(title);
        tr.appendChild(price);
        tr.appendChild(isbn);
        tr.appendChild(status);
        tr.appendChild(update_time);
        tr.appendChild(operas);
        body.appendChild(tr);
    }


}

/**
 * 商品管理页面--填充页面信息（页码，记录总数，当前页码）
 * @param msg  数据
 */
function fillInPageInfo(msg) {
    //设置具体页码
    var ul = document.getElementById("pageNumList");
    var nextli = document.getElementById("pageNextLi"); //获取下一页标签
    var preli = document.getElementById("pagePreLi");  //获取上一页标签
    for ( var i = msg.currentPage-2; i <= msg.currentPage+2; i++ ) {
        if ( i >= 1 && i <= msg.lastPage ) {
            var li = document.createElement("li");
            li.className = "link_pageNum";
            var a = document.createElement("a");
            a.href = "javascript:runItemManager("+i+")";
            a.innerHTML = i;
            li.appendChild( a );
            ul.insertBefore( li,nextli );
        }
    }

    //设置上一页
    if ( msg.currentPage != 1 ) {
        preli.firstElementChild.href = "javascript:runItemManager("+(msg.currentPage-1)+")";
    }
    //设置下一页
    if ( msg.currentPage != msg.lastPage ) {
        nextli.firstElementChild.href = "javascript:runItemManager("+(msg.currentPage+1)+")";
    }

    //设置总记录数
    $("#total_item_record").text(msg.total);
    //设置当前页码
    $("#currentPage").val(msg.currentPage);
    //设置页数
    $("#pages").text(msg.pages);
}


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


//商品管理页面--异步加载商品列表
function runItemManager(page){
    //隐藏其他页面
    $(".center_show_area").hide();
    //显示商品管理页面
    document.getElementById("itemManager-page").style.display = "inline";
    //若有该商品管理选项卡，则切换过来。否则添加选项卡
    if ( $("li[value='itemManager-page']").length > 0 ) {
        switchPage( "itemManager-page" );
    } else {
        addSmallCardForThisPage( "itemManager-page", "商品管理" );//展示页面头的标签功能（可随时切换和关闭）
    }
    var url = getRootPath();
    //调用ajax查询数据。发回json数据
    /*$.ajax({
        type : "POST", //请求方式
        url : url+"/item-list.do", //请求路径
        cache : false,
        data : "page="+page,  //传参 页数
        dataType : 'json', //返回值类型
        success : function(msg) {
            fillInPageOfItems(msg.rows);//填充商品列表
            clearPageOfItemsList();//初始化商品列表
            fillInPageInfo(msg); //填充页面信息（页码，记录总数等。。）
        },
        error : function() {
            alert("发生了一个未知错误。请刷新重试。");
        }
    });*/
}

/**
 * 商品管理页面--指定页码进行跳转
 * @returns
 */
function input_runItemManager() {
    var input = document.getElementById("currentPage").value; //获得用户输入的页码
    runItemManager(input);
}

/**
 * 填充数据到店铺页面（店铺logo）
 * @param msg 后台的数据
 */
function fillInPageOfStore(msg) {
    //设置店铺编号
    $("#store_id").val(msg.sid);
    //设置店铺名
    $("input[name='storeName']").val(msg.storeName);
    //设置店铺简介
    $("textarea[name='description']").val(msg.description);
    //设置店铺详情
    $("store_editor_id[name='pageDetail']").val(msg.pageDetail);
    store_editor.html(msg.pageDetail);
    //设置店铺logo
    document.getElementById('store_logo_1').src=msg.storeLogo;
    document.getElementById('store_logo_2').src=msg.storeLogo;
    document.getElementById('store_logo_3').src=msg.storeLogo;
}

/**
 * 店铺基本设置页面--前往店铺基本设置页面函数
 * @returns
 */
function run_storeBaseConfig() {
    var url = getRootPath();//获取根路径
    //隐藏其他页面
    $(".center_show_area").hide();
    //获取店铺id
    var data = document.getElementById("user_storeId").value;
    //显示店铺基本设置页面
    document.getElementById("store_baseConfigure-page").style.display = "inline";
    //若有该店铺基本设置选项卡，则切换过来。否则添加选项卡
    if ( $("li[value='store_baseConfigure-page']").length > 0 ) {
        switchPage( "store_baseConfigure-page" );
    } else {
        addSmallCardForThisPage( "store_baseConfigure-page", "店铺基本设置" );//展示页面头的标签功能（可随时切换和关闭）
    }
    //调用ajax查询数据。发回json数据
    $.ajax({
        type : "POST", //请求方式
        url : url+"/store-config.do", //请求路径
        cache : false,
        data : "storeId="+data,  //传参 页数
        dataType : 'json', //返回值类型
        success : function(msg) {
            //填充店铺信息到页面
            if( msg.status == 200 ) {
                fillInPageOfStore(msg.data1);
            } else {
                alert(msg.msg);
            }
        },
        error : function() {
            alert("发生了一个未知错误。请刷新重试。");
        }
    });
}

//将查询的订单数据填充页面中
function fillInOrderList(list,stat) {
    //初始化订单区域
    var body = document.getElementById("t-order-items-2");
    body.innerHTML = "";
    if ( list.length == 0 ) {
        $(".t-order").remove();
    } else {
        for ( var i = 0; i < list.length; i++ ) {
            if( list[i].status == stat || stat == 0 ) {
                if( typeof($(".cb_order[value='"+list[i].oid+"']").val()) == "undefined" ) {
                    //格式化商品规格
                    var specificationName = (list[i].specificName==null)?"":list[i].specificName;

                    $("#t-order-items-2").append(
                        '<div class="t-order-single">'+
                        '<div class="order-time-and-code">'+
                        '<input type="checkBox" class="cb_order" name="cb_order" value="'+list[i].oid+'">&nbsp;'+
                        '<span class="order_time">'+(new Date(list[i].createTime).format("yyyy/MM/dd hh:mm:ss"))+'</span>'+
                        '<span class="text-gray">订单号：</span>'+
                        '<span class="order_code">'+list[i].oid+'</span>'+
                        '<span class="text-gray">状态：</span>'+
                        '<span class="order_status"></span>'+
                        '<span class="order_print" onclick="javascript:printSingleOrder(this)">打印</span>'+
                        '</div>'+
                        '<div class="order-content">'+
                        '<div class="order-content-1 order-item">'+
                        '<ul class="nav">'+
                        '<li class="order-item-pic">'+
                        '<img class="book_pic" src="'+
                        list[i].img.split(",")[0]+
                        '" alt="商品图片">'+
                        '</li>'+
                        '<li class="order-item-title">'+
                        '<a href="#" class="book_title">'+list[i].title+'</a>'+
                        '</li>'+
                        '<li class="order-item-specific">'+
                        '<span class="book_specific">'+
                        specificationName+
                        '</span>'+
                        '</li>'+
                        '<li class="order-item-num">'+
                        '<span>×</span>'+
                        '<span class="book_num">'+list[i].num+'</span>'+
                        '</li>'+
                        '</ul>'+
                        '</div>'+
                        '<div class="order-content-1 t-order_value">'+
                        '<span>￥</span>'+
                        '<span class="order_value">'+(list[i].price/100)+'</span>'+
                        '</div>'+
                        '</div>'+
                        '<div class="order-deliver-info">'+
                        '<div class="order-content-1 t-order_address">'+
                        '<span class="glyphicon glyphicon-user"></span>'+
                        '<span class="receiver_name">'+list[i].receiverName+'</span>'+
                        '<span class="receiver_address">'+
                        list[i].receiverProvince+
                        list[i].receiverCity+
                        list[i].receiverDistinct+
                        '&nbsp;&nbsp;'+
                        list[i].receiverAddress+
                        '</span>'+
                        '<span class="receiver_phone">'+list[i].receiverPhone+'</span>'+
                        '</div>'+

                        '<div class="order-content-1 t-order_remark">'+
                        '<span class="text-gray text-mySmall">备注：</span>'+
                        '<span class="order_remark text-gray text-mySmall">'+list[i].buyerMessage+'</span>'+
                        '</div>'+
                        '<div class="order-content-1 t-order_btn">'+
                        '<div class="t-btn-deliver-item t-btn-opera-order">'+
                        '<a href="javascript:DeliverOrder(oid,this)" class="btn-opera-order">发货</a>'+
                        '</div>'+
                        '</div>'+
                        '<div class="order-content-1 t-order_deliver">'+
                        '<div class="t-order_deliver-way">'+
                        '<span class="text-gray">快递方式：</span>'+
                        '<input type="text" class="deliver-way" onclick="openDeliverWayListDiv()" value="'+list[i].shippingName+'" />'+
                        '<div class="hidden-deliver-way-list">'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">圆通快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">顺丰速递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">韵达快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">中通快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">EMS</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">申通快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">天天快递</span><br>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">汇通快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">优速快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">快捷快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">全峰快递</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">宅急送</span>'+
                        '<span class="deliver-list-way" onclick="javascript:selectDeliverWay(this)">速尔快递</span>'+
                        '</div>'+
                        '</div>'+
                        '<div class="t-order_deliver-code">'+
                        '<span class="text-gray">快递单号：</span>'+
                        '<input type="text" class="deliver-code" value="'+list[i].shippingCode+'" />'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>');
                    //规范操作按钮
                    var oid = list[i].oid;
                    //格式化订单状态
                    var order_status;
                    switch(list[i].status) {
                        case 1:
                            $(".order_status:last").text( "未付款" );
                            $(".t-order_btn:last").html('<div class="t-btn-deliver-item t-btn-opera-order"><span>等待买家付款</span></div>');
                            $(".t-order_deliver:last").remove();
                            break;
                        case 3:
                            $(".order_status:last").text( "等待买家确认收货" );
                            $(".t-order_btn:last").html('<div class="t-btn-deliver-item t-btn-opera-order"><span>等待买家确认收货</span></div>');
                            $(".deliver-way:last").attr("readonly","readonly");
                            $(".deliver-code:last").attr("readonly","readonly");
                            $(".hidden-deliver-way-list:last").remove();
                            break;
                        case 4:
                            $(".order_status:last").text( "买家已收货，未评价" );
                            $(".t-order_btn:last").html('<div class="t-btn-deliver-item t-btn-opera-order"><span>等待买家评价</span></div>');
                            $(".deliver-way:last").attr("readonly","readonly");
                            $(".deliver-code:last").attr("readonly","readonly");
                            $(".hidden-deliver-way-list:last").remove();
                            break;
                        case 5:
                            $(".order_status:last").text( "交易结束" );
                            $(".t-order_btn:last").html('<div class="t-btn-deliver-item t-btn-opera-order"><span>交易结束</span></div>');
                            $(".deliver-way:last").attr("readonly","readonly");
                            $(".deliver-code:last").attr("readonly","readonly");
                            $(".hidden-deliver-way-list:last").remove();
                            break;
                    }
                    //设置订单方式和订单单号

                } else {
                    $(".cb_order[value='"+list[i].oid+"']").parent().after(
                        '<div class="order-content">'+
                        '<div class="order-content-1 order-item">'+
                        '<ul class="nav">'+
                        '<li class="order-item-pic">'+
                        '<img class="book_pic" src="'+
                        list[i].img.split(",")[0]+
                        '" alt="商品图片">'+
                        '</li>'+
                        '<li class="order-item-title">'+
                        '<a href="#" class="book_title">'+list[i].title+'</a>'+
                        '</li>'+
                        '<li class="order-item-specific">'+
                        '<span class="book_specific">'+
                        specificationName+
                        '</span>'+
                        '</li>'+
                        '<li class="order-item-num">'+
                        '<span>×</span>'+
                        '<span class="book_num">'+list[i].num+'</span>'+
                        '</li>'+
                        '</ul>'+
                        '</div>'+
                        '<div class="order-content-1 t-order_value">'+
                        '<span>￥</span>'+
                        '<span class="order_value">'+(list[i].price/100)+'</span>'+
                        '</div>'+
                        '</div>');
                }
            }
        }
    }
}

/**
 * 店铺页面--前往店铺订单管理页面函数
 * @returns
 */
function run_storeOrderManager() {
    var url = getRootPath();//获取根路径
    //隐藏其他页面
    $(".center_show_area").hide();
    //获取店铺id
    var data = document.getElementById("user_storeId").value;
    //显示店铺基本设置页面
    document.getElementById("store_orderManager-page").style.display = "inline";
    //若有该店铺基本设置选项卡，则切换过来。否则添加选项卡
    if ( $("li[value='store_orderManager-page']").length > 0 ) {
        switchPage( "store_orderManager-page" );
    } else {
        addSmallCardForThisPage( "store_orderManager-page", "店铺订单管理" );//展示页面头的标签功能（可随时切换和关闭）
    }
    //调用ajax查询数据。发回json数据
    $.ajax({
        type : "POST", //请求方式
        url : url+"/store/order-all-list.do", //请求路径
        cache : false,
        data : "storeId="+data,  //传参 页数
        dataType : 'json', //返回值类型
        success : function(msg) {
            //填充店铺信息到页面
            if( msg.status == 200 ) {
                //将查询的订单数据填充页面中
                fillInOrderList(msg.data1,0);
                // 订单--->子模块选择
                $(".order-item-head").click(function() {
                    $(".order-selected").attr("class", "order-item-head");
                    $(this).attr("class", "order-selected");
                    console.log("order_success");
                    switch(this.getAttribute("value")) {
                        case "0":
                            fillInOrderList(msg.data1,0);
                            break;
                        case "1":
                            fillInOrderList(msg.data1,1);
                            break;
                        case "2":
                            fillInOrderList(msg.data1,2);
                            break;
                        case "3":
                            fillInOrderList(msg.data1,3);
                            break;
                        case "4":
                            fillInOrderList(msg.data1,4);
                            break;
                        case "5":
                            fillInOrderList(msg.data1,5);
                            break;
                    }
                });
            } else {
                alert(msg.msg);
            }
        },
        error : function() {
            alert("发生了一个未知错误。请刷新重试。");
        }
    });
}


//将查询的售后数据填充页面中
function fillInAftersalesDetail(list,stat) {
    //初始化售后表区域
    var body = document.getElementById("t-aftersales-items-2");
    body.innerHTML = "";
    if ( list.length == 0 ) {
        $(".t-aftersales-items").remove();
    } else {
        for ( var i = 0; i < list.length; i++ ) {
            if( list[i].aftersalesStatus == stat || stat == 0 ) {
                if( typeof($(".cb_aftersales[value='"+list[i].asid+"']").val()) == "undefined" ) {
                    $("#t-aftersales-items-2").append(
                        '<div class="t-aftersales-single">'+
                        '<div class="aftersales-time-and-code">	'+
                        '<input type="checkBox" class="cb_aftersales" name="cb_aftersales" value="'+list[i].asid+'">&nbsp;'+
                        '<span class="aftersales_time">'+(new Date(list[i].createTime).format("yyyy/MM/dd hh:mm:ss"))+'</span>'+
                        '<span class="text-gray">订单编号：</span>'+
                        '<span class="aftersales_order_code">'+list[i].sid+'</span>'+
                        '<span class="text-gray">售后编号：</span>'+
                        '<span class="aftersales_aftersales_code">'+list[i].asid+'</span>'+
                        '<span class="aftersales_print" onclick="javascript:printSingleAftersales(this)">打印</span>'+
                        '</div>'+
                        '<div class="aftersales-content">'+
                        '<div class="aftersales-content-1 aftersales-aftersales">'+
                        '<ul class="nav">'+
                        '<li class="aftersales-order-items-detail">'+
                        '<span class="order_item_serialNum" value="'+list[i].asid+'">1.</span>'+
                        '<span class="text-gray">商品编号：</span>'+
                        '<span class="aftersales-item-code">'+list[i].oiid+'</span><br>'+
                        '<a href="javascript:void(0)" class="link_item">'+
                        '<span class="aftersales-item-title">'+list[i].title+'</span>'+
                        '<span class="aftersales-item-specification">&nbsp;'+list[i].specificName+'</span>'+
                        '<span class="aftersales-item-num">&nbsp;×'+list[i].num+'</span>'+
                        '</a>'+
                        '</li>'+
                        '<li class="t-aftersales-value">'+
                        '<span class="aftersales-value">￥'+(list[i].payment/100)+'</span>'+
                        '</li>'+
                        '<li class="t-aftersales-status">'+
                        '<span class="aftersales-status">正在协商中</span>'+
                        '</li>'+
                        '<li class="t-aftersales-btn-detail">'+
                        '<span class="aftersales-btn-detail" onclick="javascript:openAftersaleDialog('+list[i].asid+')" >查看</span>'+
                        '</li>'+
                        '</ul>'+
                        '</div>'+
                        '</div>'+
                        '</div>'
                    );// end if
                    //设置变化属性
                    switch(list[i].aftersalesStatus) {
                        case "1":
                            $(".aftersales-status:last").html("等待处理");
                            break;
                        case "3":
                            $(".aftersales-status:last").html("正在退货中");
                            break;
                        case "4":
                            $(".aftersales-status:last").html("售后结束");
                            break;
                    }
                } else {
                    var target = $(".order_item_serialNum[value='"+list[i].asid+"']").html();
                    $(".order_item_serialNum[value='"+list[i].asid+"']").parent().append(
                        '<span class="order_item_serialNum" value="'+list[i].asid+'">'+(target.split(".")[0]+1)+'.</span>'+
                        '<span class="text-gray">商品编号：</span>'+
                        '<span class="aftersales-item-code">'+list[i].oiid+'</span><br>'+
                        '<a href="javascript:void(0)" class="link_item">'+
                        '<span class="aftersales-item-title">'+list[i].title+'</span>'+
                        '<span class="aftersales-item-specification">&nbsp;'+list[i].specificName+'</span>'+
                        '<span class="aftersales-item-num">&nbsp;×'+list[i].num+'</span>'+
                        '</a>'
                    );
                }
            }//end if
        }//end for
    }//end else
}



/**
 * 店铺页面--前往店铺售后处理页面函数
 * @returns
 */
function run_storeAfterSalesManager() {
    var url = getRootPath();//获取根路径
    //隐藏其他页面
    $(".center_show_area").hide();
    //获取店铺id
    var data = document.getElementById("user_storeId").value;
    //显示店铺售后处理页面
    document.getElementById("store_afterSalesManager-page").style.display = "inline";
    //若有该店铺售后处理选项卡，则切换过来。否则添加选项卡
    if ( $("li[value='store_afterSalesManager-page']").length > 0 ) {
        switchPage( "store_afterSalesManager-page" );
    } else {
        addSmallCardForThisPage( "store_afterSalesManager-page", "店铺售后处理" );//展示页面头的标签功能（可随时切换和关闭）
    }
    //调用ajax查询数据。发回json数据
    $.ajax({
        type : "POST", //请求方式
        url : url+"/store/aftersales-all-list.do", //请求路径
        cache : false,
        data : "storeId="+data,  //传参 店铺编号
        dataType : 'json', //返回值类型
        success : function(msg) {
            //填充店铺信息到页面
            if( msg.status == 200 ) {
                console.log(msg.data1);
                //将售后数据填充到页面
                fillInAftersalesDetail(msg.data1,0);
                // 售后--->子模块选择
                $(".aftersales-item-head").click(function() {
                    $(".aftersales-selected").attr("class", "aftersales-item-head");
                    $(this).attr("class", "aftersales-selected");
                    switch(this.getAttribute("value")) {
                        case "0":
                            fillInAftersalesDetail(msg.data1,0);
                            break;
                        case "1":
                            fillInAftersalesDetail(msg.data1,1);
                            break;
                        case "2":
                            fillInAftersalesDetail(msg.data1,2);
                            break;
                        case "3":
                            fillInAftersalesDetail(msg.data1,3);
                            break;
                        case "4":
                            fillInAftersalesDetail(msg.data1,4);
                            break;
                    }
                });
            } else {
                alert(msg.msg);
            }
        },
        error : function() {
            alert("发生了一个未知错误。请刷新重试。");
        }
    });
}

//跳转到“销售额统计”页面方法
function run_sellNumCalculate() {
    $(".center_show_area").hide();
    document.getElementById('report_sellnum_form').contentWindow.location.reload();
    $("#report_sellnum_form").show();
    if ( $("li[value='report_sellnum_form']").length > 0 ) {
        switchPage( "report_sellnum_form" );
    } else {
        addSmallCardForThisPage( "report_sellnum_form", "销售额统计" );//展示页面头的标签功能（可随时切换和关闭）
    }
}

/**
 * 获取销售额统计所用的柱状图表参数对象
 * @param value
 * @returns
 */
function getOptionOfSellNum( legend, category, data1, data2, data3, value){
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
//            data:['销售额','同比增长','订单数']
            data:legend
        },
        xAxis: [
            {
                type: 'category',
                data:category,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '金额（￥）',
                min: 0,
                max: 5000,
                interval: 500,
                axisLabel: {
                    formatter: '￥ {value}'
                }
            },
            {
                type: 'value',
                name: '订单数（个）',
                min: 0,
                max: 50,
                interval: 5,
                axisLabel: {
                    formatter: '{value} 单'
                }
            }
        ],
        series: [
            {
                name:'销售额',
                type:'bar',
                data:data1
            },
            {
                name:'同比增长',
                type:'bar',
                data: data2
            },
            {
                name:'订单数',
                type:'line',
                yAxisIndex: 1,
                data: data3
            }
        ]
    };

    if(value != 1) {
        option.yAxis = [{
            type: 'value',
            name: '金额（￥）',
            min: 0,
            max: 10000,
            interval: 1000,
            axisLabel: {
                formatter: '￥ {value}'
            }
        },{
            type: 'value',
            name: '订单数（个）',
            min: 0,
            max: 100,
            interval: 10,
            axisLabel: {
                formatter: '{value} 单'
            }
        }];
    }

    if(value == 3) {
        option.yAxis = [{
            type: 'value',
            name: '金额（￥）',
            min: 0,
            max: 100000,
            interval: 10000,
            axisLabel: {
                formatter: '￥ {value}'
            }
        },{
            type: 'value',
            name: '订单数（个）',
            min: 0,
            max: 1000,
            interval: 100,
            axisLabel: {
                formatter: '{value} 单'
            }
        }];
    }

    return option;
}

/**
 * 跳转到商品统计排行页面方法
 * @returns
 */
function run_bookSalesOrder() {
    $(".center_show_area").hide();
    document.getElementById('booksales_order_form').contentWindow.location.reload();
    $("#booksales_order_form").show();
    if ( $("li[value='booksales_order_form']").length > 0 ) {
        switchPage( "booksales_order_form" );
    } else {
        addSmallCardForThisPage( "booksales_order_form", "商品销售排行" );//展示页面头的标签功能（可随时切换和关闭）
    }
}

/**
 * 获取商品排行统计所用的柱状图表参数对象
 * @returns
 */
function getOptionOfSalesOrder(category, data1 ) {
    var option = {
        title: {
            text: '商品销量排行',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['销售量']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
//    	        data: ['围城','印尼','美国','印度','中国','世界人口(万)']
            data: category
        },
        series: [
            {
                name: '销售量',
                type: 'bar',
//    	            data: [18203, 23489, 29034, 104970, 131744, 630230]
                data: data1
            }
        ]
    };

    return option;
}

/**
 * 跳转到发布公告页面
 * @returns
 */
function run_deliverAnnouncement() {
    $(".center_show_area").hide();
    $("#deliver_announcement-page").show();
    if ( $("li[value='deliver_announcement-page']").length > 0 ) {
        switchPage( "deliver_announcement-page" );
    } else {
        addSmallCardForThisPage( "deliver_announcement-page", "发布公告" );//展示页面头的标签功能（可随时切换和关闭）
    }
}

/**
 * 填充通告信息到列表中
 * @param data
 * @returns
 */
function fillInDataForNoticeList( data ) {
    var tr = $("#table_announcement_list tbody tr");
    for( var i=1; i<=data.length;i++ ) {
        tr[i].children[0].innerHTML = new Date(data[i-1].createTime).format("yyyy/MM/dd hh:mm:ss");//设置时间
        tr[i].children[1].innerHTML = data[i-1].noticeTitle;//设置标题
        tr[i].children[2].value = data[i-1].nid;//设置查看详情按钮
        tr[i].children[2].innerHTML = "查看详情";//设置查看详情按钮
        tr[i].children[3].innerHTML = data[i-1].deliverUsername;//设置经手人
    }
}

/**
 * 商品管理页面--填充页面信息（页码，记录总数，当前页码）
 * @param msg  数据
 */
function fillInPageInfoOfNotice(msg) {
    //设置具体页码
    var ul = document.getElementById("pageNumList_notice");
    var nextli = document.getElementById("pageNextLi_notice"); //获取下一页标签
    var preli = document.getElementById("pagePreLi_notice");  //获取上一页标签
    for ( var i = msg.currentPage-2; i <= msg.currentPage+2; i++ ) {
        if ( i >= 1 && i <= msg.lastPage ) {
            var li = document.createElement("li");
            li.className = "link_pageNum";
            var a = document.createElement("a");
            a.href = "javascript:run_lookAnnouncement("+i+")";
            a.innerHTML = i;
            li.appendChild( a );
            ul.insertBefore( li,nextli );
        }
    }

    //设置上一页
    if ( msg.currentPage != 1 ) {
        preli.firstElementChild.href = "javascript:run_lookAnnouncement("+(msg.currentPage-1)+")";
    }
    //设置下一页
    if ( msg.currentPage != msg.lastPage ) {
        nextli.firstElementChild.href = "javascript:run_lookAnnouncement("+(msg.currentPage+1)+")";
    }

    //设置总记录数
    $("#total_notice_record").text(msg.total);
    //设置当前页码
    $("#currentPage_notice").val(msg.currentPage);
    //设置页数
    $("#pages_notice").text(msg.pages);
}

/**
 * 初始化通告列表函数
 * @returns
 */
function initNoticeList() {
    var tr = $("#table_announcement_list tbody tr");
    for( var i=1; i<=10;i++ ) {
        tr[i].children[0].innerHTML = "";//初始化时间
        tr[i].children[1].innerHTML = "";//初始化标题
        tr[i].children[2].value = "";//初始化置查看详情按钮
        tr[i].children[2].innerHTML = "";//初始化查看详情按钮
        tr[i].children[3].innerHTML = "";//初始化经手人
    }
}

//公告管理页面--初始化公告管理列表的页码信息
function clearPageOfNoticeList() {
    var pageBody = document.getElementById("pageNumList_notice");
    pageBody.innerHTML = "";
    //创建上一页标签
    var preLi = document.createElement("li");
    preLi.id = "pagePreLi_notice";
    var preA = document.createElement("a");
    preA.setAttribute( "aria-label", "Previous" );
    preA.className = "link_previous";
    var prespan = document.createElement("span");
    prespan.setAttribute( "aria-hidden", "true" );
    prespan.innerHTML = "«";
    preA.appendChild(prespan);
    preLi.appendChild(preA);
    //创建下一页标签
    var nextLi = document.createElement("li");
    nextLi.id = "pageNextLi_notice";
    var nextA = document.createElement("a");
    nextA.setAttribute( "aria-label", "Next" );
    nextA.className = "link_previous";
    var nextspan = document.createElement("span");
    nextspan.setAttribute( "aria-hidden", "true" );
    nextspan.innerHTML = "»";
    nextA.appendChild(nextspan);
    nextLi.appendChild(nextA);

    pageBody.appendChild(preLi);
    pageBody.appendChild(nextLi);
}


/**
 * 跳转到查看公告页面
 * @returns
 */
function run_lookAnnouncement(page) {
    $(".center_show_area").hide();
    $("#look_announcement-page").show();
    if ( $("li[value='look_announcement-page']").length > 0 ) {
        switchPage( "look_announcement-page" );
    } else {
        addSmallCardForThisPage( "look_announcement-page", "查看往期" );//展示页面头的标签功能（可随时切换和关闭）
    }

    var url = getRootPath();//获取根路径
    //调用ajax查询数据。发回json数据
    $.ajax({
        type : "POST", //请求方式
        url : url+"/notice/looknotice/"+page+".do", //请求路径
        cache : false,
        data: $("#form_lookAnnouncement").serialize(),
        dataType : 'json', //返回值类型
        success : function(msg) {
            initNoticeList(); //初始化列表
            clearPageOfNoticeList(); //初始化页码信息
            fillInDataForNoticeList(msg.rows); //填充数据到list中
            fillInPageInfoOfNotice(msg); //填充页码信息
        }
    });
}


/**
 * 填充广告栏位信息到页面
 * @returns
 */
function fillAllInAdvertising(data) {
    console.log(data);
    //填充
    for( var i=0; i< data.length; i++ ) {
        var nodes;
        switch(data[i].adid) {
            //设置主页左侧广告位
            case 1:
                nodes = $("#td-1-left").children();
                break;
            //设置主页大广告位
            case 2:
                nodes = $("#td-1-center-1").children();
                break;
            //设置主页大广告位下方的长广告位
            case 3:
                nodes = $("#td-1-center-3").children();
                break;
            //设置主页大广告位右侧的方形广告位
            case 4:
                nodes = $("#td-1-center-2").children();
                break;
            //设置主页右侧广告位
            case 5:
                nodes = $("#td-1-right").children();
                break;
            //设置强推位
            case 6:
                nodes = $(".index-Recommend-king").children();
                break;
            //设置小推荐位A
            case 7:
                nodes = $(".td-srA").children();
                break;
            //设置小推荐位B
            case 8:
                nodes = $(".td-srB").children();
                break;
            //设置小推荐位C
            case 9:
                nodes = $(".td-srC").children();
                break;
            //设置小推荐位D
            case 10:
                nodes = $(".td-srD").children();
                break;
        }
        //设置图片地址
        nodes[0].src = data[i].adImg;
        nodes[6].value = data[i].adImg;
        if( data[i].adid == 2 ) {
            //若为大广告位
            var src = data[i].adImg.split(",")[0]
            nodes[0].src = src;
            nodes[6].value = data[i].adImg;
        }
        //设置广告的编号
        nodes[2].value = data[i].adid;
        //设置广告的标题
        nodes[3].value = data[i].title;
        //设置广告的副标题
        nodes[4].value = data[i].alt;
        //设置广告图片链接的地址
        nodes[5].value = data[i].url;
        if (  data[i].adid == 6  ) {
            /*若为强推位*/
            nodes[0].src = "../images/bs_recommend_ad_head.png";
            //设置图片地址
            nodes[1].src = data[i].adImg;
            nodes[7].value = data[i].adImg;
            //设置广告的编号
            nodes[3].value = data[i].adid;
            //设置广告的标题
            nodes[4].value = data[i].title;
            //设置广告的副标题
            nodes[5].value = data[i].alt;
            //设置广告图片链接的地址
            nodes[6].value = data[i].url;
        }
    }
}


/**
 * 跳转到展示主页广告一览图页面
 * @returns
 */
function run_Advertising() {
    $(".center_show_area").hide();
    $("#set_advertising-page").show();
    if ( $("li[value='set_advertising-page']").length > 0 ) {
        switchPage( "set_advertising-page" );
    } else {
        addSmallCardForThisPage( "set_advertising-page", "主页广告一览图" );//展示页面头的标签功能（可随时切换和关闭）
    }

    var url = getRootPath();//获取根路径
    //调用ajax查询数据。发回json数据
    $.ajax({
        type : "POST", //请求方式
        url : url+"/advertising/lookall.do", //请求路径
        cache : false,
        dataType : 'json', //返回值类型
        success : function(msg) {
            //填充数据到页面中
            fillAllInAdvertising(msg.data1);
        }
    });
}


