package com.facebank.usersupport.mapper.usersupport.p2p;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.model.MoneyRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MoneyRecordMapper {

    List<MoneyRecord> selectByMobile(@Param("mobile") String mobile, @Param("type") String type, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

}