package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.UserExampleModel;
import java.util.List;

import com.facebank.usersupport.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    long countByExample(UserExampleModel example);

    int deleteByExample(UserExampleModel example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    List<UserModel> selectByExample(UserExampleModel example);

    UserModel selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserModel record, @Param("example") UserExampleModel example);

    int updateByExample(@Param("record") UserModel record, @Param("example") UserExampleModel example);

    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
}