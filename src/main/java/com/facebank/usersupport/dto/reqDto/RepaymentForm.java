package com.facebank.usersupport.dto.reqDto;

/**
 * serviceDto
 * serviceController 接受前端表单的数据。
 * @author HuBiao
 * @date 2018/3/9 000912:53
 **/
public class RepaymentForm {

    private Long userId;

    private String mobile;

    private Long orderId;

    private Long startTime;

    private Long endTime;

    private Integer start = 1;

    private Integer length = 10;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
