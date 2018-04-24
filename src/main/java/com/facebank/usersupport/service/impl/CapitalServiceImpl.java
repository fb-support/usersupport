package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.mapper.usersupport.p2p.MoneyRecordMapper;
import com.facebank.usersupport.model.MoneyRecord;
import com.facebank.usersupport.model.PageRestModel;
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
    public RestModel selectByMobile(String mobile, String type, Date starttime, Date endtime, String draw){
        List<MoneyRecord> moneyRecords= moneyRecordMapper.selectByMobile(mobile,type,starttime,endtime);

        PageRestModel pageRestModel = new PageRestModel(
                draw,
                new Long(moneyRecords.size() + ""),
                new Long(moneyRecords.size() + ""),
                moneyRecords
        );
        return new RestModel(pageRestModel);
    }

}
