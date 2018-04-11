package com.facebank.usersupport.attendance.dto.reqDto;

import java.util.Date;

/**
 * Created by wz on 2018/3/28.
 */
public class QueryVo {
    private Date dateStartTime;
    private Date dateEndTime;
    private Integer workNumber;

    public Date getDateStartTime() {
        return dateStartTime;
    }

    public void setDateStartTime(Date dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    public Date getDateEndTime() {
        return dateEndTime;
    }

    public void setDateEndTime(Date dateEndTime) {
        this.dateEndTime = dateEndTime;
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }
}
