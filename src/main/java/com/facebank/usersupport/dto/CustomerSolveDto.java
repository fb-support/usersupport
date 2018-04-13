package com.facebank.usersupport.dto;

/**
 * @Auther: yaozun
 * @Date: 2018/4/2 0002 10:58
 * @Description:
 */
public class CustomerSolveDto {
    private Long id;

    private String description;

    private Long gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
