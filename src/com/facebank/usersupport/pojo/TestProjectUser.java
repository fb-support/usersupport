package com.facebank.usersupport.pojo;

public class TestProjectUser {
    private Long projectUser;

    private Long projectId;

    private Long userId;

    private Integer status;

    private Long gmtCreate;

    private Long gmtModify;

    public Long getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(Long projectUser) {
        this.projectUser = projectUser;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }
}