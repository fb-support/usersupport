package com.facebank.usersupport.attendance.model;

import java.util.Date;

public class EmpAttendanceModel {
    //员工考勤记录编号
    private Long id;
    //员工号
    private Integer workNumber;
    //员工姓名
    private String empName;
    //所属部门
    private String deptName;
    //考勤日期
    private Date attendanceDate;
    //上班打卡时间
    private Date startTime;
    //下班打卡时间
    private Date endTime;
    //状态（0：正常，1：迟到，2：早退，3：迟到早退，4：旷工，5：异常，6：请假，7：调休）
    private Integer status;
    //加班时长
    private Integer attendCount;
    //备注字段，
    private String note;


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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}