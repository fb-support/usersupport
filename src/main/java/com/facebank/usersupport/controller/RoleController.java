package com.facebank.usersupport.controller;


import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理controller
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class RoleController extends BaseController {
    @Autowired
    private IRoleService iRoleService;

    //新增角色
    @RequestMapping("/role/addRole")
    public RestModel insertRole(RoleModel role) {
        //TODO 获取session当前用户id
        role.setCreator(123456L);
        return iRoleService.insertRole(role);
    }

    // 查看全部角色
    @RequestMapping("/role/findAll")
    public RestModel findAll(RoleModel role) {
        return iRoleService.findAllRole();
    }

    //通过id查找用户
    @RequestMapping("/role/findRoleById")
    public RestModel findRoleById(Long id) {
        return iRoleService.findRoleById(id);
    }

    // 修改角色状态
    @RequestMapping("/role/updateStatus")
    public RestModel findRoleById(Short status, Long id) {
        return iRoleService.updateStatus(status, id);
    }
    // 角色增加菜单
    @RequestMapping("/role/addMenuToRole")
    public RestModel addMenuToRole(RoleMenuModel roleMenuModel) {
        return iRoleService.insertMenuByRole(roleMenuModel);
    }
    // 角色移除菜单
    @RequestMapping("/role/removeMenuToRole")
    public RestModel removeMenuToRole(Long id) {
        return iRoleService.deleteMenuByRole(id);
    }
    //TODO 显示角色已有权限

    //TODO 显示角色未有权限
}
