package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.GeneralJournalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.GeneralJournalModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.GeneralJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @RequestMapping("/money/generalPage")
    public RestModel getMoneyRecord(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "10") int couts,
                                  String mobile, String type, String starttime, String endtime)  {
        System.out.println(page+"=="+couts+"=="+mobile+"=="+type+"=="+starttime+"=="+endtime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = null;
        long startTime=0,endTime=0;
        if (starttime!=""&&starttime!=null){
            try {
                time1 = sdf.parse(starttime);
                startTime = time1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date time2 =null;
        if (endtime!=""&&endtime!=null){
            try {
                time2 = sdf.parse(endtime);
                endTime = time2.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        PageDto pageDto = generalJournalService.getGeneralJournalPage(mobile,type,startTime,endTime,page,couts);
        System.out.println(pageDto);
        return this.success(pageDto);
    }

}
