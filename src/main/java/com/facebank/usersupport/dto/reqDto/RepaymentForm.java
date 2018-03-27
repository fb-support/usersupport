package com.facebank.usersupport.dto.reqDto;

/**
 * serviceDto
 * serviceController 接受前端表单的数据。
 * @author HuBiao
 * @date 2018/3/9 000912:53
 **/
public class RepaymentForm {

    private Long userId;

    private Long orderId;

    private Long startTime;

    private Long endTime;

    private Integer page = 1;

    private Integer pageSize = 10;
	
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
