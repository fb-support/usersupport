package com.facebank.usersupport.mapper.usersupport.p2p;


import com.facebank.usersupport.dto.CapitalDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MoneyRecordMapper {

    List<CapitalDto> getMoneyRecord(@Param("mobile") String mobile, @Param("type") String type, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    List<CapitalDto> getMoneyRecordPage(@Param("mobile") String mobile, @Param("type") String type,
                                        @Param("starttime") Date starttime, @Param("endtime") Date endtime,
                                        @Param("pagestart") Integer pagestart, @Param("pagecount") Integer pagecount);


    Integer getPageCount(@Param("mobile") String mobile, @Param("type") String type, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

}