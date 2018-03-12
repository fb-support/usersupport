package com.facebank.usersupport.service.impl;


import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.mapper.usersupport.p2p.GeneralJournalMapper;
import com.facebank.usersupport.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author JJ
 * @create
 **/
@Service
public class GeneralJournalServiceImpl extends BaseService implements com.facebank.usersupport.service.GeneralJournalService {

    @Autowired
    GeneralJournalMapper generalJournalMapper;

    @Override
    public List<GeneralJournalDto> getGeneralJournal(String modile, String type, Date startTime, Date endTime) {
        List lists = generalJournalMapper.getGeneralJournal(modile, type, startTime, endTime);
        return lists;
    }
}
