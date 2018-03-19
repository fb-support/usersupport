package com.facebank.usersupport.controller;


import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.UserRoleModel;
import com.facebank.usersupport.service.IRoleService;
import com.facebank.usersupport.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理controller
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class RoleController extends BaseController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserRoleService userRoleService;
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
    // 显示角色已有权限
    @RequestMapping("/role/findMenuAlready")
    public RestModel findMenuAlready(Long id) {
        return iRoleService.findMenuAlready(id);
    }

    @PostMapping("/role/findAllUser")
    public RestModel findAllUser(UserRoleDO userRoleDO){
        List<UserRoleDO> lists= userRoleService.findAllUser(userRoleDO);
        return this.success(lists);
    }
    @RequestMapping("/role/findAllRole")
    public RestModel findAllRole(RoleModel roleModel){
        List<RoleModel> lists = userRoleService.findAllRole(roleModel);
        return this.success(lists);
    }
    @RequestMapping("/role/findRoleBegin")
    public RestModel findRoleBegin(Long userId){
        List<UserRoleModel> userRoleModel = userRoleService.findRoleBegin(userId);
        return this.success(userRoleModel);
    }

    @RequestMapping("/role/updateRole")
    public RestModel updateRole(@RequestParam(value ="ids[]") Long[] ids, Long id) {
        return userRoleService.updateRole(ids, id);
    }
    @RequestMapping("/role/updataMenu")
    public RestModel updataMenu(@RequestParam(value = "ids[]") Long[] ids, Long id) {
        for(Long i: ids){
            System.out.println(i);
        }
        System.out.println(id);
        return iRoleService.updataMenu(ids,id);
    }
}
