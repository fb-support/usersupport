package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserRoleMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.UserRoleModel;
import com.facebank.usersupport.service.IUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JJ
 * @create
 **/
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public int updateRoleByUserId(UserRoleModel model) {
        return userRoleMapper.updateRoleByUserId(model);
    }

    @Override
    public RestModel findAllUser(UserRoleDO userRoleDO) {
        return new RestModel(userRoleMapper.findAllUser(userRoleDO));
    }

    @Override
    public RestModel findAllRole(RoleModel roleModel) {
        return new RestModel(userRoleMapper.findAllRole(roleModel));
    }

    @Override
    public RestModel findRoleBegin(Long userId) {
        return new RestModel(userRoleMapper.findRoleBegin(userId));
    }

    @Override
    public RestModel updateRole(Long[] userIds, Long id) {
        //删除用户角色
        userRoleMapper.deleteByUserId(id);
        //更新用户角色
        for (Long item : userIds) {
            UserRoleModel userRole = new UserRoleModel();
            userRole.setRoleId(item);
            userRole.setUserId(id);
            userRole.setGmtCreate(System.currentTimeMillis());
            userRoleMapper.insert(userRole);
        }
        return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
    }


    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, UserRoleDO userRoleDO) {
        PageHelper.startPage(pageNumber, pageSize);
        List<UserRoleDO> userRoleDOS = userRoleMapper.findAllUser(userRoleDO);
        PageInfo<UserRoleDO> pageInfo = new PageInfo<>(userRoleDOS);
        return pageInfo;
    }


}
