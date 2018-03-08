package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.model.RoleExampleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    long countByExample(RoleExampleModel example);

    int deleteByExample(RoleExampleModel example);

    int deleteByPrimaryKey(Long roleId);

    int insert(RoleModel record);

    int insertSelective(RoleModel record);

    List<RoleModel> selectByExample(RoleExampleModel example);

    RoleModel selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") RoleModel record, @Param("example") RoleExampleModel example);

    int updateByExample(@Param("record") RoleModel record, @Param("example") RoleExampleModel example);

    int updateByPrimaryKeySelective(RoleModel record);

    int updateByPrimaryKey(RoleModel record);
}