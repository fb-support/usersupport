package com.facebank.usersupport.mapper.usersupport.generater;

import com.facebank.usersupport.pojo.TestProjectUser;
import com.facebank.usersupport.pojo.TestProjectUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestProjectUserMapper {
    long countByExample(TestProjectUserExample example);

    int deleteByExample(TestProjectUserExample example);

    int deleteByPrimaryKey(Long projectUser);

    int insert(TestProjectUser record);

    int insertSelective(TestProjectUser record);

    List<TestProjectUser> selectByExample(TestProjectUserExample example);

    TestProjectUser selectByPrimaryKey(Long projectUser);

    int updateByExampleSelective(@Param("record") TestProjectUser record, @Param("example") TestProjectUserExample example);

    int updateByExample(@Param("record") TestProjectUser record, @Param("example") TestProjectUserExample example);

    int updateByPrimaryKeySelective(TestProjectUser record);

    int updateByPrimaryKey(TestProjectUser record);
}