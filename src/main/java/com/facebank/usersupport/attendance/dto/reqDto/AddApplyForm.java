package com.facebank.usersupport.attendance.dto.reqDto;

import java.util.Date;

/**
 * 用于接收添加申请的表单数据
 * @author HuBiao
 * @date 2018/3/28 0028 14:40
 **/
public class AddApplyForm {

    private Date startTime;

    private Date endTime;

    private Float applyDuration;

    private String applyCause;

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

    public String getApplyCause() {
        return applyCause;
    }

    public void setApplyCause(String applyCause) {
        this.applyCause = applyCause;
    }
}
