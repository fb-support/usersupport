package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.UserModel;

/**
 * @author NingKui
 * @date 2018/3/9 10:58
 **/
public interface UserMapper extends BaseWriteMapper<UserModel, Long> {

    /**
     *
     *
     * 可以重用BaseWriteMapper中的方法，因为BaseWriteMapper继承了BaseReadMapper
     * 所以继承BaseWriteMapper就同时有了Read和write的基本方法
     */

}
