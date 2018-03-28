/**
 * 前往系统设置--基本信息页面
 */
function runUserBaseInfo() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示基本信息页面。
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
    //显示还款查询页面
    $("#service-repayment-page").show();

    //显示tab
    if ( $("li[value='service-repayment-page']").length > 0 ) {
        switchPage( "service-repayment-page" );
    } else {
        addSmallCardForThisPage("service-repayment-page", "还款查询");
    }
}

/**
 * 前往业务查询--b页面
 */
function runB() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
}
/**
 * 前往日志-资金记录页面
 */
function moneyRecord() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示基本信息页面。
    $("#log-record").show();
    if ( $("li[value='log-record']").length > 0 ) {
        switchPage( "log-record" );
    } else {
        addSmallCardForThisPage("log-record", "资金记录");
    }
}
/**
 * 前往日志-资金流水页面
 */
function generalJournal() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示基本信息页面。
    $("#log-journal").show();
    //显示tab
    if ( $("li[value='log-journal']").length > 0 ) {
        switchPage( "log-journal" );
    } else {
        addSmallCardForThisPage("log-journal", "资金流水");
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
    //显示基本信息页面。
    $("#menu-manage").show();
    //显示tab
    if ( $("li[value='menu-manage']").length > 0 ) {
        switchPage( "menu-manage" );
    } else {
        addSmallCardForThisPage("menu-manage", "资源管理");
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
 * 前往项目管理页面
 */
function runTestProjectManager() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示项目管理界面
    $("#test-project-manager-page").show();

    //显示tab
    if ( $("li[value='test-project-manager-page']").length > 0 ) {
        switchPage( "test-project-manager-page" );
    } else {
        addSmallCardForThisPage("test-project-manager-page", "项目");
    }
}

/**
 * 前往提测工单管理页面
 */
function runTestFormManager() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示提测工单管理界面
    $("#test-form-manager-page").show();

    //显示tab
    if ( $("li[value='test-form-manager-page']").length > 0 ) {
        switchPage( "test-form-manager-page" );
    } else {
        addSmallCardForThisPage("test-form-manager-page", "提测工单管理");
    }
}

/**
 * 前往上线工单管理页面
 */
function runLaunchFormManager() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示上线工单管理界面
    $("#launch-form-manager-page").show();

    //显示tab
    if ( $("li[value='launch-form-manager-page']").length > 0 ) {
        switchPage( "launch-form-manager-page" );
    } else {
        addSmallCardForThisPage("launch-form-manager-page", "上线工单管理");
    }
}

/**
 * 前往项目详情页面
 */
function runTestProjectDetail() {
    //隐藏主页中部内容区的默认内容
    $(".center_show_area").hide();
    //显示项目详情界面
    $("#test_project_record").show();

    //显示tab
    if ( $("li[value='test_project_record']").length > 0 ) {
        switchPage( "test_project_record" );
    } else {
        addSmallCardForThisPage("test_project_record", "项目详情");
    }
}