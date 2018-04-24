package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.online.process.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author NingKui
 * @date 2018/4/24 13:55
 **/
@Service(value = "online-process-service-user")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户集合
     * @param query
     * @return
     */
    @Override
    public List<UserModel> getUserForOnlineProcess(String query) {
        UserModel model = new UserModel();

        if(query == null || "".equals(query)) {
            return userMapper.selectAllByCondition(model);
        }

        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean isNumber = pattern.matcher(query).matches();

        // 根据用户姓名查询
        model.setUsername(query);
        List<UserModel> userList_username = userMapper.selectAllByCondition(model);

        List<UserModel> userList_workNumber = new ArrayList<>();
        // 根据用户工号查
        if(isNumber) {
            model.setUsername(null);
            model.setWorkNumber(Integer.parseInt(query));
            userList_workNumber = userMapper.selectAllByCondition(model);
        }

        // 合并两个集合
        userList_username.addAll(userList_workNumber);

        // 返回
        return userList_username;
    }
}
