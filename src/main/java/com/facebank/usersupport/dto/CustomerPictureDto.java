package com.facebank.usersupport.dto;

/**
 * @author JJ
 * @create
 **/
public class CustomerPictureDto {

    private Long id;

    private String picUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
