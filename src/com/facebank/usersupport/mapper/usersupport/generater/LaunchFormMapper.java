package com.facebank.usersupport.mapper.usersupport.generater;

import com.facebank.usersupport.pojo.LaunchForm;
import com.facebank.usersupport.pojo.LaunchFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LaunchFormMapper {
    long countByExample(LaunchFormExample example);

    int deleteByExample(LaunchFormExample example);

    int deleteByPrimaryKey(Long formId);

    int insert(LaunchForm record);

    int insertSelective(LaunchForm record);

    List<LaunchForm> selectByExampleWithBLOBs(LaunchFormExample example);

    List<LaunchForm> selectByExample(LaunchFormExample example);

    LaunchForm selectByPrimaryKey(Long formId);

    int updateByExampleSelective(@Param("record") LaunchForm record, @Param("example") LaunchFormExample example);

    int updateByExampleWithBLOBs(@Param("record") LaunchForm record, @Param("example") LaunchFormExample example);

    int updateByExample(@Param("record") LaunchForm record, @Param("example") LaunchFormExample example);

    int updateByPrimaryKeySelective(LaunchForm record);

    int updateByPrimaryKeyWithBLOBs(LaunchForm record);

    int updateByPrimaryKey(LaunchForm record);
}