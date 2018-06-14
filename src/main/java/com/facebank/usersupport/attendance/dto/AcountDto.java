package com.facebank.usersupport.attendance.dto;

/**
 * 考勤统计
 * @author zhanguo.huang
 * @date 2018-04-10
 */
public class AcountDto {
    /**
     * 员工号
     */
    private Integer workNumber;
    /**
     * 姓名
     */
    private String empName;
    /**
     * 部门
     */
    private String deptName;
    /**
     * 迟到
     */
    private Integer late;
    /**
     * 早退
     */
    private Integer early;
    /**
     * 迟到早退
     */
    private Integer lateAndEarly;
    /**
     * 旷工
     */
    private Integer absent;
    /**
     * 异常
     */
    private Integer exp;
    /**
     * 请假时长
     */
    private Float leaveDuration;
    /**
     * 调休时长
     */
    private Float restDuration;
    /**
     * 加班时长
     */
    private Float overtimeDuration;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public Integer getEarly() {
        return early;
    }

    public void setEarly(Integer early) {
        this.early = early;
    }

    public Integer getLateAndEarly() {
        return lateAndEarly;
    }

    public void setLateAndEarly(Integer lateAndEarly) {
        this.lateAndEarly = lateAndEarly;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Float getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(Float leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public Float getRestDuration() {
        return restDuration;
    }

    public void setRestDuration(Float restDuration) {
        this.restDuration = restDuration;
    }

    public Float getOvertimeDuration() {
        return overtimeDuration;
    }

    public void setOvertimeDuration(Float overtimeDuration) {
        this.overtimeDuration = overtimeDuration;
    }
}
