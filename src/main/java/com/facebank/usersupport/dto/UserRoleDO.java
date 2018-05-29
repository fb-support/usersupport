package com.facebank.usersupport.dto;

import com.facebank.usersupport.model.RoleModel;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库查询返回数据包装对象
 * 主要是权限管理时联合用户表和权限表查询的结果集
 * @author NingKui
 * @date 2018/3/13 11:31
 **/
public class UserRoleDO implements Serializable{
    private Long userId;

    private Integer workNumber;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String roleName;

    private Long gmtCreate;

    private Long gmtModify;

    private Short status;

    private List<RoleModel> roles;

    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) { this.roleName = roleName; }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}
