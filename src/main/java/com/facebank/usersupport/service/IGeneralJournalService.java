package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.GeneralJournalModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * IGeneralJournalService 接口类
 *
 * @author JJ
 * @create 2018-03-09 下午3:47
 **/
public interface IGeneralJournalService {

    /**
     * 根据电话号码，业务类型，分页查询
     * @param
     * @return
     */

    PageInfo<GeneralJournalModel> selectByMobile(String mobile, Integer type, Long startTime, Long endTime, Integer pageSize, Integer pageNumber);
}
