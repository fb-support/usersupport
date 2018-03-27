package com.facebank.usersupport.mapper.usersupport.generater;

import com.facebank.usersupport.pojo.TestProject;
import com.facebank.usersupport.pojo.TestProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestProjectMapper {
    long countByExample(TestProjectExample example);

    int deleteByExample(TestProjectExample example);

    int deleteByPrimaryKey(Long projectId);

    int insert(TestProject record);

    int insertSelective(TestProject record);

    List<TestProject> selectByExample(TestProjectExample example);

    TestProject selectByPrimaryKey(Long projectId);

    int updateByExampleSelective(@Param("record") TestProject record, @Param("example") TestProjectExample example);

    int updateByExample(@Param("record") TestProject record, @Param("example") TestProjectExample example);

    int updateByPrimaryKeySelective(TestProject record);

    int updateByPrimaryKey(TestProject record);
}