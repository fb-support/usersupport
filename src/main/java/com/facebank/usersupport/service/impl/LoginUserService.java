package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.LoginUserMapper;
import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.service.ILoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NingKui
 * @date 2018/3/14 15:51
 **/
@Service
public class LoginUserService implements ILoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public int updateLoginOutTime(LoginUserModel model) {
        int flag = loginUserMapper.updateLoginOutTime(model);
        return flag;
    }
}
