package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.RoleMenuModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByRoleId(Long roleId);

    int removeMenuByRole(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int insert(RoleMenuModel record);

    int insertSelective(RoleMenuModel record);

    RoleMenuModel selectByPrimaryKey(Long id);

    List<RoleMenuModel> findMenuAlready(Long roleId);

    int updateByPrimaryKeySelective(RoleMenuModel record);

    int updateByPrimaryKey(RoleMenuModel record);
}