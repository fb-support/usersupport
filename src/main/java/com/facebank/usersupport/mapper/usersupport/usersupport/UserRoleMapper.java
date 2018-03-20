package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.dto.UserRoleDto;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.UserRoleModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByUserId(Long userId);

    int insert(UserRoleModel record);

    int insertSelective(UserRoleModel record);

    UserRoleModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleModel record);

    int updateByPrimaryKey(UserRoleModel record);

    int updateRoleByUserId(UserRoleModel model);

    List<UserRoleDO> findAllUser(UserRoleDO userRoleDO);

    List<RoleModel> findAllRole(RoleModel roleModel);

    List<UserRoleDto> findRoleBegin(Long userId);

    List<UserRoleModel> updateRole(Long id);

    int removeRoleByUser(@Param("user_id") Long user_id, @Param("role_id") Long role_id);
}