package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.github.pagehelper.PageInfo;

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

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    UserModel getByUserId(Long userId);

    /**
     * 更新用户密码
     * @param model
     * @return
     */
    int updatePasswordById(UserModel model);

    /**
     * 更新用户基本信息
     * @param model
     * @return
     */
    int updateBaseInfoMationById(UserModel model);

    /**
     * 分页，多条件模糊查询
     * @param pageSize
     * @param pageNumber
     * @param model
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, UserModel model);

    /**
     * 批量删除用户
     * @param ids 用户id array
     */
    void deleteByUserIds(Integer[] ids);

    /**
     * 批量禁止用户
     * @param ids 用户id array
     */
    void banByUserIds(Integer[] ids);

    /**
     * 批量启用用户
     * @param ids 用户id array
     */
    void enableUserByIds(Integer[] ids);

    /**
     * 获取当前用户id
     * @return
     */
    Long getActiveUserId();


}
