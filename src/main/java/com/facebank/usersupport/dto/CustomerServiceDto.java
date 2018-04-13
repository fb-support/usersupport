package com.facebank.usersupport.dto;

/**
 * @author JJ
 * @create
 **/
public class CustomerServiceDto {
    private Long id;

    private String phoneNumber;

    private String name;

    private Integer typeId;

    private Integer workerNumber;

    private Byte isSatisfied;

    private String phoneType;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getIsSatisfied() {
        return isSatisfied;
    }

    public void setIsSatisfied(Byte isSatisfied) {
        this.isSatisfied = isSatisfied;
    }
}
