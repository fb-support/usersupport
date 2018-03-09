package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.facebank.usersupport.service.base.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户表操作service实现类
 * @author NingKui
 * @date 2018/3/9 9:55
 **/
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户
     * @param userForm  用户表单对象
     * @return
     */
    @Override
    public RestModel insertUser(UserForm userForm) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userForm,userModel);

        //补全对象字段
        userModel.setGmtCreate(System.currentTimeMillis());
        userModel.setGmtModify(System.currentTimeMillis());
        userModel.setStatus(Short.valueOf("0"));

        int is_insert = userMapper.insert(userModel);
        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    @Override
    public List<UserModel> selectByUserModel(UserModel userModel) {
        return null;
    }
}
