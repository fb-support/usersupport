package com.facebank.usersupport.service;

import com.facebank.usersupport.model.LoginUserModel;

/**
 * 登录流水表service接口
 */
public interface ILoginUserService {

    /**
     * 修改登出时间
     * @param model
     * @return
     */
    int updateLoginOutTime(LoginUserModel model);
}
