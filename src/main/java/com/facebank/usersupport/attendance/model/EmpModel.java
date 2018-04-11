package com.facebank.usersupport.attendance.model;

public class EmpModel {
    //编号
    private Long id;
    //员工号
    private Integer workNumber;
    //员工姓名
    private String name;
    //手机号
    private String telephone;
    //邮箱
    private String email;
    //所属部门号
    private Integer deptNumber;
    //加班总时长（单位：小时）
    private Float overtimeDuration;
    //调休总时长（单位：小时）
    private Float restDuration;
    //请假总时长
    private Float leaveDuration;
    //上级员工号
    private Integer parentNumber;
    //创建时间
    private Long gmtCreate;
    //修改时间
    private Long gmtUpdate;
    //状态（0：正常，1：停用）
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(Integer deptNumber) {
        this.deptNumber = deptNumber;
    }

    public Float getOvertimeDuration() {
        return overtimeDuration;
    }

    public void setOvertimeDuration(Float overtimeDuration) {
        this.overtimeDuration = overtimeDuration;
    }

    public Float getRestDuration() {
        return restDuration;
    }

    public void setRestDuration(Float restDuration) {
        this.restDuration = restDuration;
    }

    public Float getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(Float leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public Integer getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(Integer parentNumber) {
        this.parentNumber = parentNumber;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}