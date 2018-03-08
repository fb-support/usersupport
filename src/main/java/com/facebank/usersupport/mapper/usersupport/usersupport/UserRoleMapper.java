package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.UserRoleModel;
import com.facebank.usersupport.model.UserRoleExampleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    long countByExample(UserRoleExampleModel example);

    int deleteByExample(UserRoleExampleModel example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleModel record);

    int insertSelective(UserRoleModel record);

    List<UserRoleModel> selectByExample(UserRoleExampleModel example);

    UserRoleModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRoleModel record, @Param("example") UserRoleExampleModel example);

    int updateByExample(@Param("record") UserRoleModel record, @Param("example") UserRoleExampleModel example);

    int updateByPrimaryKeySelective(UserRoleModel record);

    int updateByPrimaryKey(UserRoleModel record);
}