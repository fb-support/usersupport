package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.RoleMenuMapper;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    public RestModel insertRole(RoleModel role) {
        role.setGmtCreate(new Date().getTime());
        role.setStatus((short) 1);
        int is_success = roleMapper.insert(role);
        if (is_success == 1){return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);}
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
    public RestModel updateStatus(Short status,Long id) {
        Long currentTime =  new Date().getTime();
        int  is_success =  roleMapper.updateStatus(status,id,currentTime);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);}
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
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }

    @Override
    public RestModel removeMenuByRole(Long roleId, Long menuId) {
        int is_success = roleMenuMapper.removeMenuByRole(roleId,menuId);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }
    @Override
    public RestModel deleteMenuByRole(Long id) {
        int is_success = roleMenuMapper.deleteByPrimaryKey(id);
        if (is_success==1){return new RestModel(RestModel.CODE_SUCCESS,RestModel.MESSAGE_SUCCESS);}
        return new RestModel();
    }
    @Override
    public RestModel findMenuAlready(Long roleId) {
        List<RoleMenuModel> menus = roleMenuMapper.findMenuAlready(roleId);
        for (RoleMenuModel item:menus) {
            item.getMenuId();//TODO 获取已有菜单
        }
        return new RestModel(menus);
    }

    @Override
    public RestModel findMenuNotNave(Long roleId) {
        List<RoleMenuModel> menus = roleMenuMapper.findMenuAlready(roleId);
        //TODO 获取所有菜单 获取已有菜单从而获取未有菜单
        return null;
    }

}
