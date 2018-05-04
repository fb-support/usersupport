package com.facebank.usersupport.online.process.model;

import java.io.Serializable;

public class LaunchFormModel implements Serializable {
    private Long formId;

    private Long projectId;

    private Long testFormId;

    private String createUsername;

    private Long createUser;

    private Long acceptDevelopUser;

    private String acceptDevelopUsername;

    private Long acceptTestUser;

    private String acceptTestUsername;

    private Long operationsUser;

    private String operationsUsername;

    private Integer formStatus;

    private String note;

    private Long gmtCreate;

    private Long gmtModify;

    private String formContent;

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTestFormId() {
        return testFormId;
    }

    public void setTestFormId(Long testFormId) {
        this.testFormId = testFormId;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getAcceptDevelopUser() {
        return acceptDevelopUser;
    }

    public void setAcceptDevelopUser(Long acceptDevelopUser) {
        this.acceptDevelopUser = acceptDevelopUser;
    }

    public String getAcceptDevelopUsername() {
        return acceptDevelopUsername;
    }

    public void setAcceptDevelopUsername(String acceptDevelopUsername) {
        this.acceptDevelopUsername = acceptDevelopUsername;
    }

    public Long getAcceptTestUser() {
        return acceptTestUser;
    }

    public void setAcceptTestUser(Long acceptTestUser) {
        this.acceptTestUser = acceptTestUser;
    }

    public String getAcceptTestUsername() {
        return acceptTestUsername;
    }

    public void setAcceptTestUsername(String acceptTestUsername) {
        this.acceptTestUsername = acceptTestUsername;
    }

    public Integer getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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

    public String getFormContent() {
        return formContent;
    }

    public void setFormContent(String formContent) {
        this.formContent = formContent == null ? null : formContent.trim();
    }

    public Long getOperationsUser() {
        return operationsUser;
    }

    public void setOperationsUser(Long operationsUser) {
        this.operationsUser = operationsUser;
    }

    public String getOperationsUsername() {
        return operationsUsername;
    }

    public void setOperationsUsername(String operationsUsername) {
        this.operationsUsername = operationsUsername;
    }
}