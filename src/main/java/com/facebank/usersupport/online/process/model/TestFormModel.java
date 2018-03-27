package com.facebank.usersupport.online.process.model;

import java.io.Serializable;

public class TestFormModel implements Serializable {
    private Long formId;

    private String formService;

    private String formBranch;

    private Integer isTest;

    private String influenceScope;

    private Integer isReview;

    private String otherChange;

    private String createUsername;

    private Long createUser;

    private String acceptUsername;

    private Long acceptUser;

    private Long projectId;

    private Integer formStatus;

    private Long gmtCreate;

    private Long gmtModify;

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFormService() {
        return formService;
    }

    public void setFormService(String formService) {
        this.formService = formService == null ? null : formService.trim();
    }

    public String getFormBranch() {
        return formBranch;
    }

    public void setFormBranch(String formBranch) {
        this.formBranch = formBranch == null ? null : formBranch.trim();
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public String getInfluenceScope() {
        return influenceScope;
    }

    public void setInfluenceScope(String influenceScope) {
        this.influenceScope = influenceScope == null ? null : influenceScope.trim();
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getOtherChange() {
        return otherChange;
    }

    public void setOtherChange(String otherChange) {
        this.otherChange = otherChange == null ? null : otherChange.trim();
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername == null ? null : createUsername.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getAcceptUsername() {
        return acceptUsername;
    }

    public void setAcceptUsername(String acceptUsername) {
        this.acceptUsername = acceptUsername == null ? null : acceptUsername.trim();
    }

    public Long getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(Long acceptUser) {
        this.acceptUser = acceptUser;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Integer formStatus) {
        this.formStatus = formStatus;
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