package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.model.UserMainModel;

/**
 * IUserMainService 接口类
 *
 * @author hailong.Yang
 * @create 2018-03-05 下午5:51
 **/
public interface IUserMainService {

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    UserMainModel getUserMainByUserId(final Long userId);

    /**
     * 根据手机号获取用户信息
     * @param mobile
     * @return
     */
    UserMainModel getUserMainByMobile(final String mobile, final Integer userType);
}