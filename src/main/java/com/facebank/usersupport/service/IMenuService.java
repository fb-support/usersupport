package com.facebank.usersupport.service;

import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;

import java.util.List;

public interface IMenuService {
    /**
     * 查询菜单，根据model中的存在条件
     * @param menuName
     * @param menuUrl
     * @param status
     * @return
     */
    List<MenuModel> getMenu(String menuName, String menuUrl, Short status);
    /**
     * 更改菜单
     * @param menuModel
     * @return
     */
    int updateMenu(MenuModel menuModel);
    /**
     * 删除菜单
     * @param id
     * @return
     */
    void deleteByMenuIds(Long id);
    /**
     * 添加菜单
     * @param menuModel
     * @return
     */
    int insertMenu(MenuModel menuModel);

    MenuModel selectById(Long menuId);
    RestModel findAll();
    List<MenuModel> queryMenuByName(String username);
    List<MenuModel> queryMenuByUserId(Long userId);
}
