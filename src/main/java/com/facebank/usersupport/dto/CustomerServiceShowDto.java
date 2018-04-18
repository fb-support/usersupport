package com.facebank.usersupport.dto;

import java.math.BigDecimal;

public class CustomerServiceShowDto {
    private Long id;

    private Long serviceNo;

    private String phoneNumber;

    private String name;

    private Integer typeId;

    private Integer workerNumber;

    private Byte isSatisfied;

    private Integer userComment;

    private String commentContent;

    private Integer status;

    private String note;

    private Long gmtCreate;

    private Long gmtModified;

    private String phoneType;

    private String workName;

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public CustomerServiceShowDto setPhoneType(String phoneType) {
        this.phoneType = phoneType;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceNo() {
               Long serviceNoI=Long.valueOf(new BigDecimal(serviceNo.toString()).toString());
        return serviceNoI;
    }

    public void setServiceNo(Long serviceNo) {

        this.serviceNo = serviceNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(Integer workerNumber) {
        this.workerNumber = workerNumber;
    }

    public Byte getIsSatisfied() {
        return isSatisfied;
    }

    public void setIsSatisfied(Byte isSatisfied) {
        this.isSatisfied = isSatisfied;
    }

    public Integer getUserComment() {
        return userComment;
    }

    public void setUserComment(Integer userComment) {
        this.userComment = userComment;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "CustomerServiceShowDto{" +
                "id=" + id +
                ", serviceNo=" + serviceNo +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", workerNumber=" + workerNumber +
                ", isSatisfied=" + isSatisfied +
                ", userComment=" + userComment +
                ", commentContent='" + commentContent + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", phoneType='" + phoneType + '\'' +
                ", workName='" + workName + '\'' +
                '}';
    }
}
