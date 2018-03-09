package com.facebank.usersupport.dto.reqDto;

import java.io.Serializable;

/**
 * userDto
 * Controller 接受前端表单的数据。
 * 因为有可能传的字段不会是UserModel的全部字段。所以需要有这么个Dto
 * @author hailong.Yang
 * @create 2018-03-09 上午10:19
 **/
public class UserForm implements Serializable {

    private Integer workNumber;

    private String username;

    private String password;

    private String email;

    private String phone;

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
