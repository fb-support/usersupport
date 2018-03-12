package com.facebank.usersupport.model;

import java.io.Serializable;

/**
 * @author HuBiao
 * @date 2018/3/9 0009 14:02
 **/
public class RepaymentModel implements Serializable {

    /**
     * 订单号
     */
    private Long credOrderId;
    /**
     * 用户id
     */
    private Long credUserId;
    /**
     * 债权计划本金
     */
    private Double credPlanPrincipal;
    /**
     * 计划利息
     */
    private Double credPlanInterest;
    /**
     * 计划时间
     */
    private Long credPlanDate;
    /**
     * 红包信息
     */
    private String redLocalInfo;
    /**
     * 红包金额
     */
    private Double redPlanAmount;
    /**
     * 加息红包/返现红包
     */
    private Integer redPackageType;
    /**
     * 红包期限
     */
    private Long redTermNum;
    /**
     * vip利率
     */
    private Double vipRate;
    /**
     * vip收益
     */
    private Double vipPlanAmount;
    /**
     * VIP期数
     */
    private Integer vipTermNum;
    /**
     * 加息金额
     */
    private Double pfPlanAmount;
    /**
     * 加息期数
     */
    private Integer pfTermNum;
    /**
     * 首购加息/限时加息/项目加息
     */
    private Integer pfType;

    public Long getCredOrderId() {
        return credOrderId;
    }

    public void setCredOrderId(Long credOrderId) {
        this.credOrderId = credOrderId;
    }

    public Long getCredUserId() {
        return credUserId;
    }

    public void setCredUserId(Long credUserId) {
        this.credUserId = credUserId;
    }

    public Double getCredPlanPrincipal() {
        return credPlanPrincipal;
    }

    public void setCredPlanPrincipal(Double credPlanPrincipal) {
        this.credPlanPrincipal = credPlanPrincipal;
    }

    public Double getCredPlanInterest() {
        return credPlanInterest;
    }

    public void setCredPlanInterest(Double credPlanInterest) {
        this.credPlanInterest = credPlanInterest;
    }

    public Long getCredPlanDate() {
        return credPlanDate;
    }

    public void setCredPlanDate(Long credPlanDate) {
        this.credPlanDate = credPlanDate;
    }

    public String getRedLocalInfo() {
        return redLocalInfo;
    }

    public void setRedLocalInfo(String redLocalInfo) {
        this.redLocalInfo = redLocalInfo;
    }

    public Double getRedPlanAmount() {
        return redPlanAmount;
    }

    public void setRedPlanAmount(Double redPlanAmount) {
        this.redPlanAmount = redPlanAmount;
    }

    public Integer getRedPackageType() {
        return redPackageType;
    }

    public void setRedPackageType(Integer redPackageType) {
        this.redPackageType = redPackageType;
    }

    public Long getRedTermNum() {
        return redTermNum;
    }

    public void setRedTermNum(Long redTermNum) {
        this.redTermNum = redTermNum;
    }

    public Double getVipRate() {
        return vipRate;
    }

    public void setVipRate(Double vipRate) {
        this.vipRate = vipRate;
    }

    public Double getVipPlanAmount() {
        return vipPlanAmount;
    }

    public void setVipPlanAmount(Double vipPlanAmount) {
        this.vipPlanAmount = vipPlanAmount;
    }

    public Integer getVipTermNum() {
        return vipTermNum;
    }

    public void setVipTermNum(Integer vipTermNum) {
        this.vipTermNum = vipTermNum;
    }

    public Double getPfPlanAmount() {
        return pfPlanAmount;
    }

    public void setPfPlanAmount(Double pfPlanAmount) {
        this.pfPlanAmount = pfPlanAmount;
    }

    public Integer getPfTermNum() {
        return pfTermNum;
    }

    public void setPfTermNum(Integer pfTermNum) {
        this.pfTermNum = pfTermNum;
    }

    public Integer getPfType() {
        return pfType;
    }

    public void setPfType(Integer pfType) {
        this.pfType = pfType;
    }
}
