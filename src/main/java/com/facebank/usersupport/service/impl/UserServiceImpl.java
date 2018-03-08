package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserExampleModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.UserService;
import com.facebank.usersupport.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NingKui
 * @date 2018/3/8 14:08
 **/
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserModel getUserByExample(String loginName, String password) {
        // 1.查询用户名
        //User userGetByUsername = getUserByUsername(loginName);
        //if (userGetByUsername != null) {
           // userGetByUsername.getPassword().equals();
        //}
        // 2. 查询电话
        //User userGetByPhone = getUserByPhone(loginName);
        // 3. 查询邮箱
        //User userGetByEmail = getUserByEmail(loginName);
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        //创建查询对象，传入电话查询对应数据
        UserExampleModel userExample = new UserExampleModel();
        UserExampleModel.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UserModel> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public UserModel getUserByPhone(String Phone) {
        //创建查询对象，传入电话查询对应数据
        UserExampleModel userExample = new UserExampleModel();
        UserExampleModel.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(Phone);
        List<UserModel> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        //创建查询对象，传入邮箱查询对应数据
        UserExampleModel userExample = new UserExampleModel();
        UserExampleModel.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<UserModel> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public RestModel insertUser(UserModel user) {
        return null;
    }
}
