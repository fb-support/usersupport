package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.attendance.dto.AcountDto;
import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        // 封装UserForm类型的字段到UserModel类型的变量中。
        BeanUtils.copyProperties(userForm,userModel);

        // 补全对象字段
        userModel.setGmtCreate(System.currentTimeMillis());
        userModel.setGmtModify(System.currentTimeMillis());
        userModel.setStatus(Short.valueOf("0"));

        int is_insert = userMapper.insert(userModel);
        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 通过不定条件查询出用户集合
     * @param userModel
     * @return
     */
    @Override
    public List<UserModel> selectByUserModel(UserModel userModel) {
        return userMapper.selectByUserModel(userModel);

    }

    /**
     * 通过Id获取指定用户
     * @param userId
     * @return
     */
    @Override
    public UserModel getByUserId(Long userId) {
        return  userMapper.getUserById(userId);
    }

    /**
     * 更新账号密码
     * @param model
     * @return
     */
    @Override
    public int updatePasswordById(UserModel model) {
        return userMapper.updatePasswordById(model);
    }

    /**
     * 更新用户基本信息
     * @param model
     * @return
     */
    @Override
    public int updateBaseInfoMationById(UserModel model) {
        return userMapper.updateBaseInfomationById(model);
    }

    @Override
    public PageInfo selectByPage(UserModel model,Integer pagenumber,Integer pageSize) {
        PageHelper.startPage(pagenumber,pageSize);
        List<UserModel> userModels = userMapper.selectAllByCondition(model);
        PageInfo<UserModel> pageInfo =new PageInfo<>(userModels);

        return pageInfo;
    }

    /**
     * 根据id删除指定用户
     * @param ids 用户id array
     */
    @Override
    public void deleteByUserIds(Integer[] ids) {
        userMapper.batchDeleteUsers(ids);
    }

    /**
     * 根据id禁止指定用户
     * @param ids 用户id array
     */
    @Override
    public void banByUserIds(Integer[] ids) {
        userMapper.updateStatusForBanUser(ids);
    }

    /**
     * 根据id启用指定用户
     * @param ids 用户id array
     */
    @Override
    public void enableUserByIds(Integer[] ids) {
        userMapper.updateStatusForEnableUser(ids);
    }

    /**
     * 获取当前正在登录用户的ID
     * @return
     */
    @Override
    public Long getActiveUserId() {
        // 获取当前登录用户的详细信息
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        // 根据用户名查询出指定用户（夹带角色信息）。
        UserRoleDO userRoleDO = userMapper.selectBySelectiveForPermission(userDetails.getUsername());
        return userRoleDO.getUserId();
    }


}
