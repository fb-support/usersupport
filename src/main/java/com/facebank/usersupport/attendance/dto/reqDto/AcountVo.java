package com.facebank.usersupport.attendance.dto.reqDto;

import java.util.Date;

/**
 * 统计请求参数
 * @author zhanguo.huang
 * @date 2018-04-10
 */
public class AcountVo {

    /**
     * 按年统计
     */
    private Date year;

    /**
     * 按月统计
     */
    private Date month;
    /**
     * 员工号
     */
    private Integer workNumber;

    /**
     * 部门号
     */
    private Integer deptNumber;

    private  Integer pageNumber;
    private Integer  pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public Integer getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(Integer deptNumber) {
        this.deptNumber = deptNumber;
    }
}
