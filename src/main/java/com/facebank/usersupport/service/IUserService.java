package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;

import java.util.List;

/**
 * 用户表service接口
 * @author Ningkui
 * @date 2018/03/08 上午9:54
 */
public interface IUserService {

    /**
     * 新增一个用户 -- （注册）
     * @param userForm  用户表单对象
     * @return  结果包装类
     */
    RestModel insertUser(UserForm userForm);

    /**
     * 查询用户，根据model中的存在条件
     * @param userModel
     * @return
     */
    List<UserModel> selectByUserModel(UserModel userModel);
}
