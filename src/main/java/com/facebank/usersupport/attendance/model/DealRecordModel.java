package com.facebank.usersupport.attendance.model;

import java.util.Date;

public class DealRecordModel {
    //审核记录编号
    private Long id;
    //申请记录编号
    private Long applyId;
    //审核人员工号
    private Integer workNumber;
    //审核顺序
    private Integer dealLevel;
    //审核时间
    private Date dealTime;
    //状态（0：通过，1：不通过）
    private Integer status;
    //未通过原因
    private String notpassCause;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getDealLevel() {
        return dealLevel;
    }

    public void setDealLevel(Integer dealLevel) {
        this.dealLevel = dealLevel;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotpassCause() {
        return notpassCause;
    }

    public void setNotpassCause(String notpassCause) {
        this.notpassCause = notpassCause == null ? null : notpassCause.trim();
    }
}