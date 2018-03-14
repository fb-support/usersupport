package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.MenuModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(MenuModel record);

    int insertSelective(MenuModel record);

    MenuModel selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(MenuModel record);

    int updateByPrimaryKey(MenuModel record);

    List<MenuModel> queryMenuByUserId(@Param("userId") Long userId);
    List<MenuModel> queryMenuByName(@Param("username") String username);
}