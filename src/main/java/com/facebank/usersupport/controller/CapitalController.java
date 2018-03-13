package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yaozun on 2018/3/9.
 */

@RestController
public class CapitalController extends BaseController {
    @Autowired
    private ICapitalService iCapitalService;
    @PostMapping("/log/getMoneyRecord")
    public RestModel getMoneyRecord(String mobile, String type, String starttime, String endtime)  {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = null;
        Date time2 =null;
        System.out.println(starttime);
        try {
            time1 = sdf.parse(starttime);
             time2 = sdf.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<CapitalDto> capitalDtos = iCapitalService.getMoneyRecord(mobile,type,time1,time2);
        System.out.println("sfdsdf");
        return this.success(capitalDtos);
    }
    @GetMapping("/capital/getGeneralJournal")
    public RestModel getGeneralJournal(String mobile, String type, Date startTime, Date endTime){
        Object model = null;
        return this.success(JSON.toJSONString(model));
    }
}
