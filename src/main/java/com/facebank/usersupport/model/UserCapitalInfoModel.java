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
    /**
     * 可用余额
     */
    private BigDecimal cash;
    /**
     * 提现冻结金额
     */
    private BigDecimal frozenWithDrawCash;
    /**
     * 在投金额
     */
    private BigDecimal frozenFpOrderCash;
    /**
     * 总资产
     */
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

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }
}
