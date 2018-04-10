package com.facebank.usersupport.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author HuBiao
 * @date 2018/4/2 0002 17:28
 **/
public class UserCapitalInfoModel implements Serializable {

    private Long userId;

    private String mobile;

    private String realName;
    // 可用余额
    private BigDecimal cash;
    // 提现冻结金额
    private BigDecimal frozenWithDrawCash;
    // 在投金额
    private BigDecimal frozenFpOrderCash;
    // 债权计划本金总额
    private BigDecimal totalCredPlanPrincipal;
    // 债权实收本金总额
    private BigDecimal totalCredRealPrincipal;
    // 债权计划利息总额
    private BigDecimal totalCredPlanInterest;
    // 债权实收利息总额
    private BigDecimal totalCredRealInterest;
    // 应收红包总额
    private BigDecimal totalRedPlanAmount;
    // 实收红包总额
    private BigDecimal totalRedRealAmount;
    // 应收vip总收益
    private BigDecimal totalVipPlanAmount;
    // 实收vip总收益
    private BigDecimal totalVipRealAmount;
    // 应收加息总额
    private BigDecimal totalPfPlanAmount;
    // 实收加息总额
    private BigDecimal totalPfRealAmount;
    // 总资产
    private BigDecimal totalAssets;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getFrozenWithDrawCash() {
        return frozenWithDrawCash;
    }

    public void setFrozenWithDrawCash(BigDecimal frozenWithDrawCash) {
        this.frozenWithDrawCash = frozenWithDrawCash;
    }

    public BigDecimal getFrozenFpOrderCash() {
        return frozenFpOrderCash;
    }

    public void setFrozenFpOrderCash(BigDecimal frozenFpOrderCash) {
        this.frozenFpOrderCash = frozenFpOrderCash;
    }

    public BigDecimal getTotalCredPlanPrincipal() {
        return totalCredPlanPrincipal;
    }

    public void setTotalCredPlanPrincipal(BigDecimal totalCredPlanPrincipal) {
        this.totalCredPlanPrincipal = totalCredPlanPrincipal;
    }

    public BigDecimal getTotalCredRealPrincipal() {
        return totalCredRealPrincipal;
    }

    public void setTotalCredRealPrincipal(BigDecimal totalCredRealPrincipal) {
        this.totalCredRealPrincipal = totalCredRealPrincipal;
    }

    public BigDecimal getTotalCredPlanInterest() {
        return totalCredPlanInterest;
    }

    public void setTotalCredPlanInterest(BigDecimal totalCredPlanInterest) {
        this.totalCredPlanInterest = totalCredPlanInterest;
    }

    public BigDecimal getTotalCredRealInterest() {
        return totalCredRealInterest;
    }

    public void setTotalCredRealInterest(BigDecimal totalCredRealInterest) {
        this.totalCredRealInterest = totalCredRealInterest;
    }

    public BigDecimal getTotalRedPlanAmount() {
        return totalRedPlanAmount;
    }

    public void setTotalRedPlanAmount(BigDecimal totalRedPlanAmount) {
        this.totalRedPlanAmount = totalRedPlanAmount;
    }

    public BigDecimal getTotalRedRealAmount() {
        return totalRedRealAmount;
    }

    public void setTotalRedRealAmount(BigDecimal totalRedRealAmount) {
        this.totalRedRealAmount = totalRedRealAmount;
    }

    public BigDecimal getTotalVipPlanAmount() {
        return totalVipPlanAmount;
    }

    public void setTotalVipPlanAmount(BigDecimal totalVipPlanAmount) {
        this.totalVipPlanAmount = totalVipPlanAmount;
    }

    public BigDecimal getTotalVipRealAmount() {
        return totalVipRealAmount;
    }

    public void setTotalVipRealAmount(BigDecimal totalVipRealAmount) {
        this.totalVipRealAmount = totalVipRealAmount;
    }

    public BigDecimal getTotalPfPlanAmount() {
        return totalPfPlanAmount;
    }

    public void setTotalPfPlanAmount(BigDecimal totalPfPlanAmount) {
        this.totalPfPlanAmount = totalPfPlanAmount;
    }

    public BigDecimal getTotalPfRealAmount() {
        return totalPfRealAmount;
    }

    public void setTotalPfRealAmount(BigDecimal totalPfRealAmount) {
        this.totalPfRealAmount = totalPfRealAmount;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }
}
