package com.facebank.usersupport.service;

import com.facebank.usersupport.model.MenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMenuService {
    List<MenuModel> queryMenuByName(String username);
    List<MenuModel> queryMenuByUserId(Long userId);
}
