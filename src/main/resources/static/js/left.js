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
        addSmallCardForThisPage("system-base-info-page", "用户管理");
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
        addSmallCardForThisPage("system-change-password-page", "用户管理");
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
        addSmallCardForThisPage("userManager-add-user-page", "用户管理");
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