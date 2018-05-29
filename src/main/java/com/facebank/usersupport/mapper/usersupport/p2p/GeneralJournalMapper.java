package com.facebank.usersupport.mapper.usersupport.p2p;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.model.GeneralJournalModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralJournalMapper {

    List<GeneralJournalModel> selectByMobile(@Param("mobile") String mobile, @Param("type") Integer type, @Param("startTime") Long starttime, @Param("endTime") Long endtime);

}