package com.facebank.usersupport.service;

import com.facebank.usersupport.common.ResultBean;
import com.facebank.usersupport.model.UserMainModel;
import com.facebank.usersupport.pojo.User;
import com.facebank.usersupport.service.base.BaseService;

/**
 * @author NingKui
 * @date 2018/3/8 13:42
 **/
public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return 用户对象
     */
    User getUserByUserId(final Long userId);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return 用户对象
     */
    User getUserByPhone(final String phone);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return 用户对象
     */
    User getUserByUsername(final String username);

    /**
     * 根据邮箱获取用户信息
     * @param email
     * @return 用户对象
     */
    User getUserByEmail(final String email);


    /**
     * 插入一个用户
     * @param user  用户对象
     * @return  包装结果类
     */
    ResultBean insertUser(User user);


}
