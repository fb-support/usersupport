package com.facebank.usersupport.service;

import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;

/**
 * @author NingKui
 * @date 2018/3/8 13:42
 **/
public interface IUserService {

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return 用户对象
     */
    UserModel getUserByUserId(final Long userId);

    /**
     * ---登录----根据多条件获取用户信息
     * @param loginName  登录名（用户名、电话、邮箱）
     * @param password   密码
     * @return 用户对象
     */
    UserModel getUserByExample(final String loginName, final String password);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return 用户对象
     */
    UserModel getUserByUsername(final String username);

    /**
     * 根据多条件获取用户信息
     * @param phone
     * @return 用户对象
     */
    UserModel getUserByPhone(final String phone);

    /**
     * 根据email获取用户信息
     * @param email
     * @return 用户对象
     */
    UserModel getUserByEmail(final String email);

    /**
     * 插入一个用户
     * @param user  用户对象
     * @return  包装结果类
     */
    RestModel insertUser(UserModel user);


}
