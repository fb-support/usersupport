package com.facebank.usersupport.service;

import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;


public interface IRoleService {
    // 增加角色
    RestModel insertRole(RoleModel role);
    // 查看角色
    RestModel findAllRole();

    RestModel findRoleById(Long id);
    // 修改角色状态
    RestModel updateStatus(Short status, Long id);

    RestModel updataRole(RoleModel role);

    // 角色增加菜单
    RestModel insertMenuByRole(RoleMenuModel roleMenuModel);

    //T角色移除菜单
    RestModel deleteMenuByRole(Long id);
    RestModel removeMenuByRole(Long roleId, Long menuId);

    //角色已有菜单
    RestModel findMenuAlready(Long roleId);

    //角色未有菜单
    RestModel findMenuNotNave(Long roleId);
}
