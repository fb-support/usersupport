package com.facebank.usersupport.dto;

/**
 * @Auther: quanli
 * @Date: 2018/3/28 09:44
 * @Description:
 */
public class ServiceShowDto {

    private Long id;

    private Long problemId;

    private String phoneNumber;

    private String name;

    private String phoneType;

    private String title;

    private Long problemType;

    private String description;

    //TODO 处理表字段

    private String picUrl;

    private Long beginTime;

    private Long endTime;

    private Integer workerNumber;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public ServiceShowDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getProblemId() {
        return problemId;
    }

    public ServiceShowDto setProblemId(Long problemId) {
        this.problemId = problemId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ServiceShowDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ServiceShowDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceShowDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public ServiceShowDto setPhoneType(String phoneType) {
        this.phoneType = phoneType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceShowDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getProblemType() {
        return problemType;
    }

    public ServiceShowDto setProblemType(Long problemType) {
        this.problemType = problemType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ServiceShowDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ServiceShowDto setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public ServiceShowDto setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public ServiceShowDto setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getWorkerNumber() {
        return workerNumber;
    }

    public ServiceShowDto setWorkerNumber(Integer workerNumber) {
        this.workerNumber = workerNumber;
        return this;
    }
}
