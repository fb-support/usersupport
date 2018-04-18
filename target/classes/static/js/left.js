/**
 * 前往系统设置--基本信息页面
 */
function runUserBaseInfo() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    $("#system-base-info-page").show();

    //显示tab
    if ( $("li[value='system-base-info-page']").length > 0 ) {
        switchPage( "system-base-info-page" );
    } else {
        addSmallCardForThisPage("system-base-info-page", "基本信息");
    }
}

/**
 * 前往系统设置--修改密码页面
 */
function runChangePassword() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    $("#system-change-password-page").show();

    //显示tab
    if ( $("li[value='system-change-password-page']").length > 0 ) {
        switchPage( "system-change-password-page" );
    } else {
        addSmallCardForThisPage("system-change-password-page", "修改密码");
    }
}

/**
 * 前往业务查询--还款查询页面
 */
function runRepaymentSearch() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();

    //获取当前债券还款查询卡的个数
    var count = 0;
    $(".module_card_span").each(function() {
        if($(this).text() == "债券还款查询") {
            count = count + 1;
        }
    });

    //显示债券还款查询页面。
    if(count == 0) {
        $("#service-repayment-page").show();
        addSmallCardForThisPage("service-repayment-page", "债券还款查询");
    } else {
        $("#always-change-area").append("<iframe class='center_show_area' id='service-repayment-page"+count+"' src='/service/search' frameborder='0'></iframe>");
        addSmallCardForThisPage("service-repayment-page"+count, "债券还款查询");
    }
}

/**
 * 前往业务查询--b页面
 */
function runRepaymentOrderSearch() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();

    //获取当前订单还款查询查询卡的个数
    var count = 0;
    $(".module_card_span").each(function() {
        if($(this).text() == "订单还款查询") {
            count = count + 1;
        }
    });

    //显示订单还款查询页面。
    if(count == 0) {
        $("#service-repaymentOrder-page").show();
        addSmallCardForThisPage("service-repaymentOrder-page", "订单还款查询");
    } else {
        $("#always-change-area").append("<iframe class='center_show_area' id='service-repaymentOrder-page"+count+"' src='/service/orderSearch' frameborder='0'></iframe>");
        addSmallCardForThisPage("service-repaymentOrder-page"+count, "订单还款查询");
    }
}
/**
 * 前往日志-资金记录页面
 */
function moneyRecord() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();

    //获取当前资金记录卡的个数
    var count = 0;
    $(".module_card_span").each(function() {
        if($(this).text() == "资金记录") {
            count = count + 1;
        }
    });

    //显示资金记录页面。
    if(count == 0) {
        $("#log-record").show();
        addSmallCardForThisPage("log-record", "资金记录");
    } else {
        $("#always-change-area").append("<iframe class='center_show_area' id='log-record"+count+"' src='/money/moneyRecord' frameborder='0'></iframe>");
        addSmallCardForThisPage("log-record"+count, "资金记录");
    }
}
/**
 * 前往日志-资金流水页面
 */
function generalJournal() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();

    //获取当前资金流水卡的个数
    var count = 0;
    $(".module_card_span").each(function() {
        if($(this).text() == "资金流水") {
            count = count + 1;
        }
    });

    //显示资金流水页面。
    if(count == 0) {
        $("#log-journal").show();
        addSmallCardForThisPage("log-journal", "资金流水");
    } else {
        $("#always-change-area").append("<iframe class='center_show_area' id='log-journal"+count+"' src='/log/generalJournal' frameborder='0'></iframe>");
        addSmallCardForThisPage("log-journal"+count, "资金流水");
    }
}
/**
 * 前往权限-用户角色管理页面
 */
function run_addUserManeger() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    $("#userPermission-manage").show();

    //显示tab
    if ( $("li[value='userPermission-manage']").length > 0 ) {
        switchPage( "userPermission-manage" );
    } else {
        addSmallCardForThisPage("userPermission-manage", "用户角色管理");
    }
}
/**
 * 前往权限-角色管理页面
 */
function roleManage() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示基本信息页面。
    $("#role-manage").show();
    if ( $("li[value='role-manage']").length > 0 ) {
        switchPage( "role-manage" );
    } else {
        addSmallCardForThisPage("role-manage", "角色管理");
    }
}
/**
 * 前往权限-菜单管理页面
 */
function menuManage() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示菜单管理页面。
    $("#menu-manage").show();
    //显示tab
    if ( $("li[value='menu-manage']").length > 0 ) {
        switchPage( "menu-manage" );
    } else {
        addSmallCardForThisPage("menu-manage", "菜单管理");
    }
}

moneyRecord()
/**
 * 前往用户管理--添加用户界面
 */
function run_addUser() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    $("#userManager-add-user-page").show();

    //显示tab
    if ( $("li[value='userManager-add-user-page']").length > 0 ) {
        switchPage( "userManager-add-user-page" );
    } else {
        addSmallCardForThisPage("userManager-add-user-page", "添加用户");
    }
}

/**
 * 前往用户管理--管理用户界面
 */
function runOperaUser() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示用户管理界面
    $("#userManager-opera-user-page").show();

    //显示tab
    if ( $("li[value='userManager-opera-user-page']").length > 0 ) {
        switchPage( "userManager-opera-user-page" );
    } else {
        addSmallCardForThisPage("userManager-opera-user-page", "用户管理");
    }
}

/**
 * 前往用户登录流水界面
 */
function runUserLog() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示用户管理界面
    $("#userManager-login-page").show();

    //显示tab
    if ( $("li[value='userManager-login-page']").length > 0 ) {
        switchPage( "userManager-login-page" );
    } else {
        addSmallCardForThisPage("userManager-login-page", "用户登录流水");
    }
}

/**
 * 前往考勤系统--加班管理页面
 */
function runOvertimeManage() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示加班管理页面
    $("#attendance-overtime-page").show();

    //显示tab
    if ( $("li[value='attendance-overtime-page']").length > 0 ) {
        switchPage( "attendance-overtime-page" );
    } else {
        addSmallCardForThisPage("attendance-overtime-page", "加班管理");
    }
}

/**
 * 前往考勤系统--调休管理页面
 */
function runRestManage() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示调休管理页面
    $("#attendance-rest-page").show();

    //显示tab
    if ( $("li[value='attendance-rest-page']").length > 0 ) {
        switchPage( "attendance-rest-page" );
    } else {
        addSmallCardForThisPage("attendance-rest-page", "调休管理");
    }
}

/**
 * 前往考勤系统--请假管理页面
 */
function runLeaveManage() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示请假管理页面
    $("#attendance-leave-page").show();

    //显示tab
    if ( $("li[value='attendance-leave-page']").length > 0 ) {
        switchPage( "attendance-leave-page" );
    } else {
        addSmallCardForThisPage("attendance-leave-page", "请假管理");
    }
}

/**
 * 前往考勤系统--审核管理页面
 */
function runDealManage() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示考勤管理页面
    $("#attendance-deal-page").show();

    //显示tab
    if ( $("li[value='attendance-deal-page']").length > 0 ) {
        switchPage( "attendance-deal-page" );
    } else {
        addSmallCardForThisPage("attendance-deal-page", "审核管理");
    }
}

/**
 * 前往考勤系统--考勤管理页面
 */
function runAttendanceManage() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示考勤管理页面
    $("#attendance-attendance-page").show();

    //显示tab
    if ( $("li[value='attendance-attendance-page']").length > 0 ) {
        switchPage( "attendance-attendance-page" );
    } else {
        addSmallCardForThisPage("attendance-attendance-page", "考勤管理");
    }
}

/**
 * 前往考勤系统--统计页面
 */
function runAttendanceAcount() {
    // 隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    // 展示统计页面
    $("#attendance-acount-page").show();

    //显示tab
    if ( $("li[value='attendance-acount-page']").length > 0 ) {
        switchPage( "attendance-acount-page" );
    } else {
        addSmallCardForThisPage("attendance-acount-page", "统计");
    }
}