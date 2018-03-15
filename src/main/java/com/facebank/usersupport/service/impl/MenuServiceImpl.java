package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.MenuMapper;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<MenuModel> getMenu(String menuName, String menuUrl, Short status){
        System.out.println(menuName);
        return menuMapper.getMenu(menuName,menuUrl,status);
    }

    @Override
    public int updateMenu(MenuModel menuModel) {
        return menuMapper.updateByPrimaryKeySelective(menuModel);
    }

    @Override
    public void deleteByMenuIds(Long id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertMenu(MenuModel menuModel) {

        return menuMapper.insert(menuModel);
    }


    @Override
    public MenuModel selectById(Long menuId) {
        return menuMapper.selectById(menuId);
    }

    @Override
    public RestModel findAll() {
        return new RestModel(menuMapper.queryAllMenu());
    }

    @Override
    public List<MenuModel> queryMenuByName(String username) {
        return menuMapper.queryMenuByName(username);
    }

    @Override
    public List<MenuModel> queryMenuByUserId(Long userId) {
        return menuMapper.queryMenuByUserId(userId);
    }
}
