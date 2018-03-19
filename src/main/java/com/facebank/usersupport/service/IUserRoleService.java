package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.UserRoleModel;

import java.util.List;

/**
 * @author JJ
 * @create
 **/
public interface IUserRoleService {

    /**
     * 通过用户id更改角色
     * @return
     */
   int updateRoleByUserId(UserRoleModel model);
    /**
     * 查询所有用户以及角色
     * @return
     */
    List<UserRoleDO> findAllUser(UserRoleDO userRoleDO);
    /**
     * 查询所角色
     * @return
     */
    List<RoleModel> findAllRole(RoleModel roleModel);
    /**
     * 通过userID查询角色状态
     * @return
     */
    List<UserRoleModel> findRoleBegin(Long userId);
    /**
     * 通过id更改角色
     * @return
     */
    RestModel updateRole(Long[] userId, Long id);
}
