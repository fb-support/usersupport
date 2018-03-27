package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICapitalService;
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
public class CapitalController extends BaseController {
    @Autowired
    private ICapitalService iCapitalService;
    /**
     * 功能描述: 获取资金记录查询分页
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/money/getMoneyRecordPage")
    public RestModel getMoneyRecord(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "5") int couts,
                                    String mobile, String type, String starttime, String endtime)  {
        System.out.println(mobile+"=="+type+"=="+starttime+"=="+endtime);
        if (starttime==""||endtime==""){return new RestModel("时间不能为空");}
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = null;
        if (starttime!=""&&starttime!=null){
            try {
                time1 = sdf.parse(starttime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date time2 =null;
        if (endtime!=""&&endtime!=null){
            try {
                time2 = sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(time2.getTime()-time1.getTime()>432000000){return new RestModel("日期有误，无法查询");}

        try{
            PageDto pageDto = iCapitalService.getMoneyRecordPage(mobile,type,time1,time2,page,couts);
            return this.success(pageDto);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel();
        }
    }

}
