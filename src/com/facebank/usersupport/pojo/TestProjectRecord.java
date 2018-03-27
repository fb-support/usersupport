package com.facebank.usersupport.pojo;

public class TestProjectRecord {
    private Long id;

    private String operatingContent;

    private String operatingPeople;

    private Long projectId;

    private Long formId;

    private Integer formType;

    private Long gmtCreate;

    private Long gmtModify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatingContent() {
        return operatingContent;
    }

    public void setOperatingContent(String operatingContent) {
        this.operatingContent = operatingContent == null ? null : operatingContent.trim();
    }

    public String getOperatingPeople() {
        return operatingPeople;
    }

    public void setOperatingPeople(String operatingPeople) {
        this.operatingPeople = operatingPeople == null ? null : operatingPeople.trim();
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
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