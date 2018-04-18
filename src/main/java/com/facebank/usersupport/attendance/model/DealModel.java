package com.facebank.usersupport.attendance.model;

import java.util.Date;

/**
 * 审核记录表
 * Created by wz on 2018/4/2.
 */
public class DealModel {
    //审核记录编号
    private Long id;
    //申请记录编号
    private Long applyId;
    //审核人员工号
    private Integer parentWorkerNumber;
    //审核顺序
    private Integer dealLevel;
    //审核时间
    private Date  dealTime;
    //状态（0：通过，1：不通过）
    private Integer dealStatus;
    //未通过原因
    private String notpassCause;
    //员工号
    private Integer workNumber;
    //员工姓名
    private String empName;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //申请日期
    private Date applyDate;
    //共计时长（单位：小时）
    private Float duration;
    //申请原因
    private String applyCause;
    //申请类型（0：加班，1：请假，2：调休）
    private Integer applyType;
    //状态（0：待审核，1：通过，2：未通过）
    private Integer status;

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

    public Integer getParentWorkerNumber() {
        return parentWorkerNumber;
    }

    public void setParentWorkerNumber(Integer parentWorkerNumber) {
        this.parentWorkerNumber = parentWorkerNumber;
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

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getNotpassCause() {
        return notpassCause;
    }

    public void setNotpassCause(String notpassCause) {
        this.notpassCause = notpassCause;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getApplyCause() {
        return applyCause;
    }

    public void setApplyCause(String applyCause) {
        this.applyCause = applyCause;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
