package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IGeneralJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class GeneralJournalController extends BaseController {
    @Autowired
    IGeneralJournalService generalJournalService;

    /**
     * 功能描述: 获取资金流水查询分页
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/log/generalPage")
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
        try{
            PageDto pageDto = generalJournalService.getGeneralJournalPage(mobile,type,startTime,endTime,page,couts);
//            System.out.println(pageDto);
            return this.success(pageDto);
        }catch (Exception e){
            return this.excpRestModel();
        }

    }

}
