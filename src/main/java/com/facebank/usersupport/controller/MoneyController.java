package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.model.GeneralJournalModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.GeneralJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class MoneyController extends BaseController {
    @Autowired
    GeneralJournalService generalJournalService;
    @GetMapping("/money/getMoneyRecord")
    public RestModel getMoneyRecord(String modile, String type, Data startTime, Date endTime){
        System.out.println("sdf");
       Object model = null;
        return this.success(JSON.toJSONString(model));
    }
    @RequestMapping("/money/getGeneralJournal")
    public RestModel getGeneralJournal(@RequestParam("mobile") String mobile, @RequestParam("type") String type, Date startTime, Date endTime){
        if(mobile!=null)
            System.out.println(mobile);
        if(type!=null)
            System.out.println(type);
        if(startTime!=null)
            System.out.println(startTime);
        if(endTime!=null)
            System.out.println(endTime);
        Object model = null;
        List<GeneralJournalDto> generalJournalModelList = generalJournalService.getGeneralJournal(mobile,type,null,null);

        return this.success(generalJournalModelList);
    }
}
