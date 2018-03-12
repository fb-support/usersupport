package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.mapper.usersupport.p2p.MoneyRecordMapper;
import com.facebank.usersupport.service.ICapitalService;
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
}
