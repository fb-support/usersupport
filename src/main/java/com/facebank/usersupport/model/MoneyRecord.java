package com.facebank.usersupport.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyRecord {
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
        this.tousernickname = tousernickname == null ? null : tousernickname.trim();
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
        this.fromusernickname = fromusernickname == null ? null : fromusernickname.trim();
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
        this.tradecomment = tradecomment == null ? null : tradecomment.trim();
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
        this.loantitle = loantitle == null ? null : loantitle.trim();
    }

    public String getLoanportraitpath() {
        return loanportraitpath;
    }

    public void setLoanportraitpath(String loanportraitpath) {
        this.loanportraitpath = loanportraitpath == null ? null : loanportraitpath.trim();
    }

    public String getOutserialno() {
        return outserialno;
    }

    public void setOutserialno(String outserialno) {
        this.outserialno = outserialno == null ? null : outserialno.trim();
    }
}