package com.facebank.usersupport.mapper.usersupport.generater;

import com.facebank.usersupport.pojo.TestProjectRecord;
import com.facebank.usersupport.pojo.TestProjectRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestProjectRecordMapper {
    long countByExample(TestProjectRecordExample example);

    int deleteByExample(TestProjectRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TestProjectRecord record);

    int insertSelective(TestProjectRecord record);

    List<TestProjectRecord> selectByExample(TestProjectRecordExample example);

    TestProjectRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TestProjectRecord record, @Param("example") TestProjectRecordExample example);

    int updateByExample(@Param("record") TestProjectRecord record, @Param("example") TestProjectRecordExample example);

    int updateByPrimaryKeySelective(TestProjectRecord record);

    int updateByPrimaryKey(TestProjectRecord record);
}