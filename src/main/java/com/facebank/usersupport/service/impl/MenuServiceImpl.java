package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.MenuMapper;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<MenuModel> queryMenuByName(String username) {
        return menuMapper.queryMenuByName(username);
    }

    @Override
    public List<MenuModel> queryMenuByUserId(Long userId) {
        return menuMapper.queryMenuByUserId(userId);
    }
}
