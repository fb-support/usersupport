package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.RoleModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    RoleModel selectByPrimaryKey(Long roleId);

    List<RoleModel> findAll();

    int updateStatus(@Param("status") Short status, @Param("roleId") Long roleId, @Param("currentTime") Long currentTime);

    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);
}