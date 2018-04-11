package com.facebank.usersupport.attendance.dto.reqDto;

import java.util.Date;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 17:29
 **/
public class GetAttendanceForm {

    private Integer deptNumber;

    private String deptName;

    private Integer workNumber;

    private Date attendanceDate;

    public Integer getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(Integer deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}
