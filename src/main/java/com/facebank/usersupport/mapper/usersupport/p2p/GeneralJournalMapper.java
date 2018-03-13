package com.facebank.usersupport.mapper.usersupport.p2p;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.model.GeneralJournalModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface GeneralJournalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GeneralJournalModel record);

    GeneralJournalModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GeneralJournalModel record);

    int updateByPrimaryKey(GeneralJournalModel record);

    List<GeneralJournalDto> getGeneralJournal(@Param("mobile") String modile, @Param("journalType") String type, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<CapitalDto> getGeneralJournalPage(@Param("mobile") String mobile, @Param("journalType") String type,
                                           @Param("startTime") Date starttime, @Param("endTime") Date endtime,
                                           @Param("pagestart") Integer pagestart, @Param("pagecount") Integer pagecount);


    Integer getPageCount(@Param("mobile") String mobile, @Param("journalType") String type, @Param("startTime") Date starttime, @Param("endTime") Date endtime);
}