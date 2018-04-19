package com.facebank.usersupport.dto.reqDto;

import org.springframework.web.multipart.MultipartFile;

/**
 * CustomerServiceController 接受前端表单的数据。
 * @Auther: yaozun
 * @Date: 2018/4/19 0019 09:22
 * @Description:前端一次性传回多张表的个别信息
 */
public class CustomerServiceForm {
    private Long serviceId;//服务id
    private Long problemId;//问题id
    private Long solveId;//问题处理id
    private Long questionId;//问题详情id
    private Long pictureId;//图片id

    private Long beginTime;//服务开始时间
    private Long endTime;//服务结束时间
    private String phoneNumber;//电话号码
    private String phoneType;//手机型号类型
    private String name;//客户名
    private Long typeId;//问题类型id
    private String title;//问题标题
    private String description;//问题描述
    private String solve;//问题处理
    private Integer status;//服务记录表状态
    private MultipartFile file[];//问题图片

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getSolveId() {
        return solveId;
    }

    public void setSolveId(Long solveId) {
        this.solveId = solveId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }
}
