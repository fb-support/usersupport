package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.GeneralJournalModel;

import java.util.Date;
import java.util.List;

/**
 * GeneralJournalService 接口类
 *
 * @author JJ
 * @create 2018-03-09 下午3:47
 **/
public interface GeneralJournalService {

    /**
     * 根据电话号码，业务类型，开始以及结束时间查询
     * @param
     * @return
     */
    List<GeneralJournalDto> getGeneralJournal(String modile, String type, Long startTime, Long endTime);
    /**
     * 根据电话号码，业务类型，分页查询
     * @param
     * @return
     */
    PageDto getGeneralJournalPage(String mobile, String type, Long starttime, Long endtime, Integer page, Integer counts);
}
