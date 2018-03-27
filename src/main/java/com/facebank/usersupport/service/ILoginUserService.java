package com.facebank.usersupport.service;

import com.facebank.usersupport.model.LoginUserModel;
import com.github.pagehelper.PageInfo;

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

    /**
     * 分页，多条件模糊查询
     * @param pageSize
     * @param pageNumber
     * @param loginUserModel
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, LoginUserModel loginUserModel);
}
