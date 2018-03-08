package com.facebank.usersupport.model;

import java.io.Serializable;

public class UserMainModel implements Serializable {

    private static final long serialVersionUID = -839747599722388339L;

    /**
     * 用户类型 平台账户
     */
    public static final Integer USERTYPE_PLATEFORM = 100;
    /**
     * 用户类型 投资人
     */
    public static final Integer USERTYPE_INVESTOR = 200;
    /**
     * 用户类型 企业借款人
     */
    public static final Integer USERTYPE_BORROWER_CORP = 300;
    /**
     * 用户类型 个人借款人
     */
    public static final Integer USERTYPE_BORROWER_PERSONAL = 400;
    /**
     * 用户类型 企业担保人
     */
    public static final Integer USERTYPE_GUARANTOR_CORP = 500;
    /**
     * 用户类型 个人担保人
     */
    public static final Integer USERTYPE_GUARANTOR_PERSONAL = 600;


    /**
     * 状态 正常
     */
    public static final Integer USERSTATUS_NORMAL = 100;
    /**
     * 状态 冻结
     */
    public static final Integer USERSTATUS_FROZEN = 200;
    /**
     * 状态 停用
     */
    public static final Integer USERSTATUS_DISABLE = 300;

    /**
     * 手势密码 启用
     */
    public static final Integer GESTUREPWDSTATUS_ENABLE = 1;
    /**
     * 手势密码 停用
     */
    public static final Integer GESTUREPWDSTATUS_DISABLE = 0;

    /**
     * 是否设置过登录密码
     */
    public static final String LOGINPWD_SET = "set";
    /**
     * 无密码默认值
     */
    public static final String LOGINPWD_NOPASS = "nopass";


    private Long userId;

    private String mobile;

    private String loginPwd;

    private String gesturePwd;

    private Integer gesturePwdStatus;

    private String nickName;

    private String email;

    private String info;

    private Integer userType;

    private Integer userStatus;

    private String remark;

    private Long createTime;

    private Long modifyTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getGesturePwd() {
        return gesturePwd;
    }

    public void setGesturePwd(String gesturePwd) {
        this.gesturePwd = gesturePwd;
    }

    public Integer getGesturePwdStatus() {
        return gesturePwdStatus;
    }

    public void setGesturePwdStatus(Integer gesturePwdStatus) {
        this.gesturePwdStatus = gesturePwdStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}