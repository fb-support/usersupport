package com.facebank.usersupport.attendance.dto;

/**
 * @author NingKui
 * @date 2018/6/13 18:42
 * 用于传递考勤记录里面的work_number和emp_name字段
 **/
public class EmpUserDto {

    //员工号
    private Integer workNumber;

    //员工姓名
    private String empName;

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
}
