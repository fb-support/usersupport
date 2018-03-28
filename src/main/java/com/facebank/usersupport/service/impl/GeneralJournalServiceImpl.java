package com.facebank.usersupport.service.impl;


import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.mapper.usersupport.p2p.GeneralJournalMapper;
import com.facebank.usersupport.model.GeneralJournalModel;
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
    public List<GeneralJournalDto> getGeneralJournal(String modile, String type, Long startTime, Long endTime) {
        List lists = generalJournalMapper.getGeneralJournal(modile, type, startTime, endTime);
        return lists;
    }

    @Override
    public PageDto getGeneralJournalPage(String mobile, String type, Long starttime, Long endtime, Integer page, Integer counts) {
        Integer totalNumber = generalJournalMapper.getPageCount(mobile,type,starttime,endtime);
        int lastPage = PageUtil.getLastPage(counts,totalNumber);
        int startNumber = (page-1)*counts;
        List<CapitalDto> capitalDtos = generalJournalMapper.getGeneralJournalPage(mobile,type,starttime,endtime,startNumber,counts);
        PageDto pageDto = new PageDto();
        pageDto.setCurrentPage(page);
        pageDto.setTotalPages(lastPage);
        pageDto.setPageData(capitalDtos);
        return pageDto;
    }

    @Override
    public RestModel selectByMobile(String mobile, Integer type, Long startTime, Long endTime, Integer page, Integer counts) {
        PageHelper.startPage(page, counts);
        List<GeneralJournalModel> generalJournalModels= generalJournalMapper.selectByMobile(mobile,type,startTime,endTime);
        PageInfo<GeneralJournalModel> pageInfo = new PageInfo<>(generalJournalModels);
        return new RestModel(pageInfo);
    }
}
