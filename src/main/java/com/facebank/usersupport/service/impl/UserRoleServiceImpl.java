package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserRoleMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.UserRoleModel;
import com.facebank.usersupport.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    public List <UserRoleDO> findAllUser(UserRoleDO userRoleDO) {
        return userRoleMapper.findAllUser(userRoleDO);
    }

    @Override
    public List <RoleModel> findAllRole(RoleModel roleModel) {
        return userRoleMapper.findAllRole(roleModel);
    }

    @Override
    public List <UserRoleModel> findRoleBegin(Long userId) {
        return userRoleMapper.findRoleBegin(userId);
    }

    @Override
    public RestModel updateRole(Long[] userId, Long id) {
        //如果已有菜单被取消则移除
        List <Long> ids = returnIds(id);
        for (Long item : ids) {
            if (!Arrays.asList(userId).contains(item)) {
                userRoleMapper.removeRoleByUser(id, item);
            }
        }
        //如果未有菜单被勾选则增加
        List <Long> allNebuId = new ArrayList <Long>();
        List <RoleModel> roles = roleMapper.queryAllRole();
        for (RoleModel role : roles) {
            allNebuId.add(role.getRoleId());
        }
               for (Long role : allNebuId) {
                   for (Long iditem : userId) {
                       if (role == iditem) {
                           userRoleMapper.removeRoleByUser(id, iditem);
                           addRoleMenu(id, iditem);
                   }
               }
           }
        return new RestModel();
    }

    public List<Long> returnIds(Long userId) {
        List<UserRoleModel> userRoleModels = userRoleMapper.findRoleBegin(userId);
        List<Long> ids = new ArrayList<Long>();
        for (UserRoleModel item:userRoleModels) {
            new RoleModel();
            RoleModel role = roleMapper.selectByPrimaryKey(item.getRoleId());
            ids.add(role.getRoleId());
        }
        return ids;
    }
    public void addRoleMenu(Long userId, Long roleId){
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserId(userId);
        userRoleModel.setRoleId(roleId);
        userRoleModel.setGmtCreate(new Date().getTime());
        userRoleModel.setGmtModify(new Date().getTime());
        userRoleMapper.insert(userRoleModel);
    }

}
