package com.facebank.usersupport.common;

/**
 * 状态码枚举类
 *
 * @author hailong.Yang
 * @create 2017-12-02 下午1:33
 **/
public enum MessageKeyEnum {

    //app使用appCode
    //wap使用code

    //业务成功 无需提示用户
    SUCCESS("1","操作成功"),

    //业务失败 无需提示用户
    ERROR("0","操作失败"),


    //app 专用参数
    //业务成功  请使用toast方式提示用户
    SUCCESS_TOAST("1","操作成功"),
    //业务成功  请使用dialog方式提示用户
    SUCCESS_DIALOG("1","操作成功"),

    //业务失败 且使用toast方式提示用户
    FAIL_TOAST("0","操作失败"),
    //业务失败 且使用DIALOG方式提示用户
    FAIL_DIALOG("0","操作失败"),



    //以下定义固定参数错误
    //系统异常状态码
    UNKNOW("-801","系统或网路异常"),
    UNCHECK_REQUEST_ERROR("-801","非法请求参数"),
    SESSION_EXPIRED("-803","用户session已过期，请重新登陆"),
    TOKEN_ERROR("-804", "重复提交表单"),
    SESSION_REPEAT_KIT("-805","您的账号在异地登陆，如非本人操作请及时更换密码"),


    //用户状态码
    USER_EXIST("-850","用户已存在"),
    USER_NOT_EXIST("-851","用户不存在"),
    USER_LOGIN("-852","用户已登录状态"),
    USER_UN_LOGIN("-853","请登录后执行此操作"),
    USER_NOT_OPENACCOUNT("-854","未创建银行账户"),
    USER_NOT_BINDCARD("-855","未绑定银行卡"),
    USER_Login_LOCK("-856","用户被锁定"),
    USER_LOGIN_DISABLED("-857","用户已被停用"),
    USER_LOGIN_THIRD("-858","用户登录3次错误"), //启用验证码
    USER_LOGIN_THIRD_NO_IMAGE("-859","用户登录3次错误"),//不启用验证码

    //用户等级信息检验码
    USER_MOBILE_EMPYT("-860","请输入手机号"),
    USER_MOBILE_ILLEGAL("-861","请输入正确的手机号格式"),
    USER_PPWD_EMPTY("-862","请输入6-20位的密码"),
    USER_SMSCODE_EMPTY("-863","请输入6位短信验证码"),
    USER_SMSCODE_ERROR("-864","您输入的短信验证码不正确，请重新输入"),
    USER_SMSCODE_INVALID("-865","您输入的短信验证码已过期，请重新获取"),
    USER_IMGCODE_ERROR("-866","请输入正确的图形验证码"),
    USER_NEW_PWD_EMPTY("-867","请输入6-20位的新密码"),
    USER_TWO_PWD_ERROR("-868","您两次输入的密码不一致，请重新输入"),
    USER_TWO_PWD_SAME("-869","您输入原密码和新密码一致，请重新设置"),
    USER_GESTRUE_PWD_EMPTY("-870","请设置手势密码"),
    USER_GESTRUE_PWD_ERROR("-871","手势密码有误，请重新输入有效的手势密码！"),



    //参数状态码
    PARAM_EMPTY("-901","请求参数为空"),
    PARAM_ILLEGAL("-902","请求参数不正确"),
    PARAM_EXPIRED("-903","参数码值已过期"),
    PARAM_FORM_SUMIT("-904","表单重复提交"),


    //依赖服务异常定义
    REST_SERVICE_ERROR("-831","rest服务异常"),
    HESSIAN_SERVICE_ERROR("-832","hessian服务异常");


    MessageKeyEnum(String code, String message){
        this.appCode = appCode;
        this.code = code;
        this.message = message;
    }
    private String code;
    private String appCode;
    private String message;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
