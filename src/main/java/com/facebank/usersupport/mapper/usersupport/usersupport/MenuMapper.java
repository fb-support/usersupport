package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.MenuModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    List<MenuModel> queryAllMenu();

    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    MenuModel selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(MenuModel record);

    MenuModel updateByPrimaryKey(MenuModel record);

    List<MenuModel> getMenu(@Param("menuName") String menuName, @Param("menuUrl") String menuUurl, @Param("status") Short status);

    MenuModel selectById(Long menuId);

    List<MenuModel> queryMenuByUserId(@Param("userId") Long userId);
    List<MenuModel> queryMenuByName(@Param("username") String username);
}