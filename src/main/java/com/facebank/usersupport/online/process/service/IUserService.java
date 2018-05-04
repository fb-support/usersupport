package com.facebank.usersupport.online.process.service;

import com.facebank.usersupport.model.UserModel;

import java.util.List;

/**
 * 用户表接口
 */
public interface IUserService {

    /**
     * 查询指定用户列表
     * @param query
     * @return
     */
    List<UserModel> getUserForOnlineProcess(String query);

    /**
     * 获取当前登录的用户对象
     * @return
     */
    UserModel getActiveUser();
}
