package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.MenuMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMenuMapper;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public RestModel insertRole(RoleModel role) {
        role.setGmtCreate(new Date().getTime());
        role.setStatus((short) 1);
        int is_success = roleMapper.insert(role);
        if (is_success == 1){return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }

    @Override
    public RestModel findAllRole() {
        return new RestModel(roleMapper.findAll());
    }

    @Override
    public RestModel findRoleById(Long id) {
        return new RestModel(roleMapper.selectByPrimaryKey(id));
    }
    @Override
    public RestModel updateStatus(Short status, Long id) {
        Long currentTime =  new Date().getTime();
        int  is_success =  roleMapper.updateStatus(status,id,currentTime);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }

    @Override
    public RestModel updataRole(RoleModel role) {
        return null;
    }

    @Override
    public RestModel insertMenuByRole(RoleMenuModel roleMenuModel) {
        roleMenuModel.setGmtCreate(new Date().getTime());
        int is_success = roleMenuMapper.insert(roleMenuModel);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }

    @Override
    public RestModel removeMenuByRole(Long roleId, Long menuId) {
        int is_success = roleMenuMapper.removeMenuByRole(roleId,menuId);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }
    @Override
    public RestModel deleteMenuByRole(Long id) {
        int is_success = roleMenuMapper.deleteByPrimaryKey(id);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }
    @Override
    public RestModel findMenuAlready(Long roleId) {
        List<RoleMenuModel> roleMenus = roleMenuMapper.findMenuAlready(roleId);
        List<MenuModel> menus =new ArrayList<MenuModel>();
        for (RoleMenuModel item:roleMenus) {
             new MenuModel();
            MenuModel menu = menuMapper.selectByPrimaryKey(item.getMenuId());
            ;menus.add(menu);
        }
        return new RestModel(menus);
    }

    @Override
    public RestModel updataMenu(Long[] roleId, Long id) {
        //如果已有菜单被取消则移除
       List<Long> ids =returnIds(id);
        for (Long item:ids) {
            if (!Arrays.asList(roleId).contains(item)){
                roleMenuMapper.removeMenuByRole(id,item);
            }
        }
        //如果未有菜单被勾选则增加
        List<Long> allNebuId = new ArrayList<Long>();
         List<MenuModel> menus = menuMapper.queryAllMenu();
        for (MenuModel item:menus) {
            allNebuId.add(item.getMenuId());
        }
        for (Long item:allNebuId) {
            for (Long iditem:roleId) {
                if (item == iditem){
                    addRoleMenu(id,iditem);
                }
            }
        }
        return new RestModel();
    }
    public List<Long> returnIds(Long roleId) {
        List<RoleMenuModel> roleMenus = roleMenuMapper.findMenuAlready(roleId);
        List<Long> ids = new ArrayList<Long>();
        for (RoleMenuModel item:roleMenus) {
            new MenuModel();
            MenuModel menu = menuMapper.selectByPrimaryKey(item.getMenuId());
            ids.add(menu.getMenuId());
        }
        return ids;
    }

    public void addRoleMenu(Long roleId,Long menuId) {
        RoleMenuModel roleMenuModel = new RoleMenuModel();
        roleMenuModel.setRoleId(roleId);
        roleMenuModel.setMenuId(menuId);
        roleMenuModel.setGmtCreate(new Date().getTime());
        roleMenuMapper.insert(roleMenuModel);
    }

}
