package com.facebank.usersupport.attendance.model;

import java.util.Date;

public class ApplyRecordModel {
    //申请记录编号
    private Long id;
    //员工号
    private Integer workNumber;
    //员工姓名
    private String empName;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //共计时长
    private Float applyDuration;
    //申请日期
    private Date applyDate;
    //申请原因
    private String applyCause;
    //申请类型（0：加班，1：请假，2：调休）
    private Integer applyType;
    //状态（0：待审核，1：通过，2：未通过）
    private Integer status;
    //未通过原因
    private String notpassCause;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.empName = empName == null ? null : empName.trim();
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

    public Float getApplyDuration() {
        return applyDuration;
    }

    public void setApplyDuration(Float applyDuration) {
        this.applyDuration = applyDuration;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    public String getNotpassCause() {
        return notpassCause;
    }

    public void setNotpassCause(String notpassCause) {
        this.notpassCause = notpassCause == null ? null : notpassCause.trim();
    }
}