package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.model.UserModel;
import com.facebank.usersupport.attendance.service.base.BaseService;
import com.facebank.usersupport.online.process.dto.reqDto.UserForm;
import com.facebank.usersupport.online.process.usersupport.usersupport.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
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

    /**
     * 根据不定条件查询用户，并分页
     * @param pageSize
     * @param pageNumber
     * @param model
     * @return
     */
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, UserModel model) {
        PageHelper.startPage(pageNumber,pageSize);
        List<UserModel> userList = userMapper.selectAllByCondition(model);
        PageInfo<UserModel> pageInfo =new PageInfo<>(userList);
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
