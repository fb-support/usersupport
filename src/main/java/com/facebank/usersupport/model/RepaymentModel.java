package com.facebank.usersupport.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author HuBiao
 * @date 2018/3/9 0009 14:02
 **/
public class RepaymentModel implements Serializable {

    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 债权id
     */
    private Long creditId;
    /**
     * 计划时间
     */
    private Long planDate;
    /**
     * 债权计划本金
     */
    private BigDecimal credPlanPrincipal;
    /**
     * 债权计划利息
     */
    private BigDecimal credPlanInterest;
    /**
     * 债权实收本金
     */
    private BigDecimal credRealPrincipal;
    /**
     * 债权实收利息
     */
    private BigDecimal credRealInterest;
    /**
     * 还款状态，100：未还，200：已还
     */
    private Integer bizStatus;
    /**
     * 红包信息
     */
    private String redLocalInfo;
    /**
     * 应收红包金额
     */
    private BigDecimal redPlanAmount;
    /**
     * 实收红包金额
     */
    private BigDecimal redRealAmount;
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
    private BigDecimal vipRate;
    /**
     * 应收vip收益
     */
    private BigDecimal vipPlanAmount;
    /**
     * 实收vip收益
     */
    private BigDecimal vipRealAmount;
    /**
     * VIP期数
     */
    private Integer vipTermNum;
    /**
     * 应收加息金额
     */
    private BigDecimal pfPlanAmount;
    /**
     * 实收加息金额
     */
    private BigDecimal pfRealAmount;
    /**
     * 加息期数
     */
    private Integer pfTermNum;
    /**
     * 首购加息/限时加息/项目加息
     */
    private Integer pfType;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Long planDate) {
        this.planDate = planDate;
    }

    public BigDecimal getCredPlanPrincipal() {
        return credPlanPrincipal;
    }

    public void setCredPlanPrincipal(BigDecimal credPlanPrincipal) {
        this.credPlanPrincipal = credPlanPrincipal;
    }

    public BigDecimal getCredPlanInterest() {
        return credPlanInterest;
    }

    public void setCredPlanInterest(BigDecimal credPlanInterest) {
        this.credPlanInterest = credPlanInterest;
    }

    public BigDecimal getCredRealPrincipal() {
        return credRealPrincipal;
    }

    public void setCredRealPrincipal(BigDecimal credRealPrincipal) {
        this.credRealPrincipal = credRealPrincipal;
    }

    public BigDecimal getCredRealInterest() {
        return credRealInterest;
    }

    public void setCredRealInterest(BigDecimal credRealInterest) {
        this.credRealInterest = credRealInterest;
    }

    public Integer getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(Integer bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getRedLocalInfo() {
        return redLocalInfo;
    }

    public void setRedLocalInfo(String redLocalInfo) {
        this.redLocalInfo = redLocalInfo;
    }

    public BigDecimal getRedPlanAmount() {
        return redPlanAmount;
    }

    public void setRedPlanAmount(BigDecimal redPlanAmount) {
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

    public BigDecimal getVipRate() {
        return vipRate;
    }

    public void setVipRate(BigDecimal vipRate) {
        this.vipRate = vipRate;
    }

    public BigDecimal getVipPlanAmount() {
        return vipPlanAmount;
    }

    public void setVipPlanAmount(BigDecimal vipPlanAmount) {
        this.vipPlanAmount = vipPlanAmount;
    }

    public Integer getVipTermNum() {
        return vipTermNum;
    }

    public void setVipTermNum(Integer vipTermNum) {
        this.vipTermNum = vipTermNum;
    }

    public BigDecimal getPfPlanAmount() {
        return pfPlanAmount;
    }

    public void setPfPlanAmount(BigDecimal pfPlanAmount) {
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

    public BigDecimal getRedRealAmount() {
        return redRealAmount;
    }

    public void setRedRealAmount(BigDecimal redRealAmount) {
        this.redRealAmount = redRealAmount;
    }

    public BigDecimal getVipRealAmount() {
        return vipRealAmount;
    }

    public void setVipRealAmount(BigDecimal vipRealAmount) {
        this.vipRealAmount = vipRealAmount;
    }

    public BigDecimal getPfRealAmount() {
        return pfRealAmount;
    }

    public void setPfRealAmount(BigDecimal pfRealAmount) {
        this.pfRealAmount = pfRealAmount;
    }
}
