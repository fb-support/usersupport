package com.facebank.usersupport.mapper.usersupport.generater;

import com.facebank.usersupport.pojo.TestForm;
import com.facebank.usersupport.pojo.TestFormExample;
import com.facebank.usersupport.pojo.TestFormWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestFormMapper {
    long countByExample(TestFormExample example);

    int deleteByExample(TestFormExample example);

    int deleteByPrimaryKey(Long formId);

    int insert(TestFormWithBLOBs record);

    int insertSelective(TestFormWithBLOBs record);

    List<TestFormWithBLOBs> selectByExampleWithBLOBs(TestFormExample example);

    List<TestForm> selectByExample(TestFormExample example);

    TestFormWithBLOBs selectByPrimaryKey(Long formId);

    int updateByExampleSelective(@Param("record") TestFormWithBLOBs record, @Param("example") TestFormExample example);

    int updateByExampleWithBLOBs(@Param("record") TestFormWithBLOBs record, @Param("example") TestFormExample example);

    int updateByExample(@Param("record") TestForm record, @Param("example") TestFormExample example);

    int updateByPrimaryKeySelective(TestFormWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TestFormWithBLOBs record);

    int updateByPrimaryKey(TestForm record);
}