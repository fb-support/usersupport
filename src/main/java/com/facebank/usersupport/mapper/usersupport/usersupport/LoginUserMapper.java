package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.model.LoginUserExampleModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserMapper {
    long countByExample(LoginUserExampleModel example);

    int deleteByExample(LoginUserExampleModel example);

    int deleteByPrimaryKey(Long loginId);

    int insert(LoginUserModel record);

    int insertSelective(LoginUserModel record);

    List<LoginUserModel> selectByExample(LoginUserExampleModel example);

    LoginUserModel selectByPrimaryKey(Long loginId);

    int updateByExampleSelective(@Param("record") LoginUserModel record, @Param("example") LoginUserExampleModel example);

    int updateByExample(@Param("record") LoginUserModel record, @Param("example") LoginUserExampleModel example);

    int updateByPrimaryKeySelective(LoginUserModel record);

    int updateByPrimaryKey(LoginUserModel record);
}