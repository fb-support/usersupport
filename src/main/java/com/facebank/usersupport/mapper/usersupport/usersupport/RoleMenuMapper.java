package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleMenuExampleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapper {
    long countByExample(RoleMenuExampleModel example);

    int deleteByExample(RoleMenuExampleModel example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleMenuModel record);

    int insertSelective(RoleMenuModel record);

    List<RoleMenuModel> selectByExample(RoleMenuExampleModel example);

    RoleMenuModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleMenuModel record, @Param("example") RoleMenuExampleModel example);

    int updateByExample(@Param("record") RoleMenuModel record, @Param("example") RoleMenuExampleModel example);

    int updateByPrimaryKeySelective(RoleMenuModel record);

    int updateByPrimaryKey(RoleMenuModel record);
}