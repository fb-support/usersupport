package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.common.ResultBean;
import com.facebank.usersupport.mapper.usersupport.generater.UserMapper;
import com.facebank.usersupport.pojo.User;
import com.facebank.usersupport.pojo.UserExample;
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
    public User getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByPhone(String phone) {
        //创建查询对象，传入电话号码
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public ResultBean insertUser(User user) {
        return null;
    }
}
