package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.mapper.usersupport.p2p.MoneyRecordMapper;
import com.facebank.usersupport.model.MoneyRecord;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICapitalService;
import com.facebank.usersupport.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yaozun on 2018/3/9.
 */
@Service
public class CapitalServiceImpl implements ICapitalService {
    @Autowired
    private MoneyRecordMapper moneyRecordMapper;
    @Override
    public List<CapitalDto> getMoneyRecord(String mobile, String type, Date starttime, Date endtime) {

        return moneyRecordMapper.getMoneyRecord(mobile,type,starttime,endtime);
    }

    @Override
    public PageDto getMoneyRecordPage(String mobile, String type, Date starttime, Date endtime, Integer page, Integer counts) {
               Integer totalNumber = moneyRecordMapper.getPageCount(mobile,type,starttime,endtime);
               int lastPage = PageUtil.getLastPage(counts,totalNumber);
               int startNumber = (page-1)*counts;
               List<CapitalDto> capitalDtos = moneyRecordMapper.getMoneyRecordPage(mobile,type,starttime,endtime,startNumber,counts);
               PageDto pageDto = new PageDto();
               pageDto.setCurrentPage(page);
               pageDto.setTotalPages(lastPage);
               pageDto.setPageData(capitalDtos);
        return pageDto;
    }
    @Override
    public Integer getCounts(String mobile, String type, Date starttime, Date endtime){
        return moneyRecordMapper.getPageCount(mobile,type,starttime,endtime);
    }
    @Override
    public RestModel selectByMobile(String mobile, String type, Date starttime, Date endtime, Integer page, Integer counts){
        PageHelper.startPage(page, counts);
        List<MoneyRecord> moneyRecords= moneyRecordMapper.selectByMobile(mobile,type,starttime,endtime);
        PageInfo<MoneyRecord> pageInfo = new PageInfo<>(moneyRecords);
        return new RestModel(pageInfo);
    }

}
