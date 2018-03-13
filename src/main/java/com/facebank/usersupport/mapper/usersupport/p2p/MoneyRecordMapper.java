package com.facebank.usersupport.mapper.usersupport.p2p;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.model.MoneyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MoneyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyRecord record);

    int insertSelective(MoneyRecord record);

    MoneyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoneyRecord record);

    int updateByPrimaryKey(MoneyRecord record);

    List<CapitalDto> getMoneyRecord(@Param("mobile") String mobile, @Param("type") String type, @Param("starttime") Date starttime, @Param("endtime") Date endtime);
}