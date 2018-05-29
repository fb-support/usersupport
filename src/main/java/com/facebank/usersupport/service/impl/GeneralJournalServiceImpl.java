package com.facebank.usersupport.service.impl;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.mapper.usersupport.p2p.GeneralJournalMapper;
import com.facebank.usersupport.model.GeneralJournalModel;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IGeneralJournalService;
import com.facebank.usersupport.service.base.BaseService;
import com.facebank.usersupport.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JJ
 * @create
 **/
@Service
public class GeneralJournalServiceImpl extends BaseService implements IGeneralJournalService {

    @Autowired
    GeneralJournalMapper generalJournalMapper;


    @Override
    public PageInfo<GeneralJournalModel> selectByMobile(String mobile, Integer type, Long startTime, Long endTime, Integer pageSize, Integer pageNumber) {
        List<GeneralJournalModel> generalJournalModels= generalJournalMapper.selectByMobile(mobile,type,startTime,endTime);

        PageInfo<GeneralJournalModel> pageInfo = new PageInfo(generalJournalModels);
        return pageInfo;
    }
}
