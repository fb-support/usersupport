package com.facebank.usersupport.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yaozun on 2018/3/9.
 */
public class CapitalDto {
    private Integer id;

    private Integer touserid;

    private String tousernickname;

    private Integer fromuserid;

    private String fromusernickname;

    private BigDecimal amount;

    private BigDecimal accountbalance;

    private Short tradetype;

    private Date tradetime;

    private Short tradechannel;

    private String tradecomment;

    private Integer loanid;

    private String loantitle;

    private String loanportraitpath;

    private String outserialno;

    private String realname;

    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTouserid() {
        return touserid;
    }

    public void setTouserid(Integer touserid) {
        this.touserid = touserid;
    }

    public String getTousernickname() {
        return tousernickname;
    }

    public void setTousernickname(String tousernickname) {
        this.tousernickname = tousernickname;
    }

    public Integer getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(Integer fromuserid) {
        this.fromuserid = fromuserid;
    }

    public String getFromusernickname() {
        return fromusernickname;
    }

    public void setFromusernickname(String fromusernickname) {
        this.fromusernickname = fromusernickname;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAccountbalance() {
        return accountbalance;
    }

    public void setAccountbalance(BigDecimal accountbalance) {
        this.accountbalance = accountbalance;
    }

    public Short getTradetype() {
        return tradetype;
    }

    public void setTradetype(Short tradetype) {
        this.tradetype = tradetype;
    }

    public Date getTradetime() {
        return tradetime;
    }

    public void setTradetime(Date tradetime) {
        this.tradetime = tradetime;
    }

    public Short getTradechannel() {
        return tradechannel;
    }

    public void setTradechannel(Short tradechannel) {
        this.tradechannel = tradechannel;
    }

    public String getTradecomment() {
        return tradecomment;
    }

    public void setTradecomment(String tradecomment) {
        this.tradecomment = tradecomment;
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
    }

    public String getLoantitle() {
        return loantitle;
    }

    public void setLoantitle(String loantitle) {
        this.loantitle = loantitle;
    }

    public String getLoanportraitpath() {
        return loanportraitpath;
    }

    public void setLoanportraitpath(String loanportraitpath) {
        this.loanportraitpath = loanportraitpath;
    }

    public String getOutserialno() {
        return outserialno;
    }

    public void setOutserialno(String outserialno) {
        this.outserialno = outserialno;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
