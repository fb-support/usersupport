package com.facebank.usersupport.dto;

import java.math.BigDecimal;

public class GeneralJournalDto {
    private Long id;

    private Short journaltype;

    private String mobile;

    private Integer userId;

    private String nickname;

    private String serialnumber;

    private Long starttime;

    private Long endtime;

    private String relationid;

    private Byte inouttype;

    private BigDecimal amount;

    private BigDecimal balance;

    private String remarks;

    private Short status;

    private Long createtime;

    private Long modifytime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getJournaltype() {
        return journaltype;
    }

    public void setJournaltype(Short journaltype) {
        this.journaltype = journaltype;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public String getRelationid() {
        return relationid;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid == null ? null : relationid.trim();
    }

    public Byte getInouttype() {
        return inouttype;
    }

    public void setInouttype(Byte inouttype) {
        this.inouttype = inouttype;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    public String toString() {
        return "GeneralJournalDto{" +
                "id=" + id +
                ", journaltype=" + journaltype +
                ", mobile='" + mobile + '\'' +
                ", userid=" + userId +
                ", nickname='" + nickname + '\'' +
                ", serialnumber='" + serialnumber + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", relationid='" + relationid + '\'' +
                ", inouttype=" + inouttype +
                ", amount=" + amount +
                ", balance=" + balance +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }
}