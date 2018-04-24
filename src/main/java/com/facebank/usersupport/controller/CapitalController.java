package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICapitalService;
import com.facebank.usersupport.util.StrUtil;
import com.facebank.usersupport.util.TimeUtil;
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
    public RestModel getMoneyRecord(String mobile, String type, Long starttime, Long endtime, String draw)  {
        if (mobile==""){return new RestModel("202","手机号不能为空");}
        if (starttime==null||endtime==null){return new RestModel("203","时间不能为空");}
        //        if(time2.getTime()-time1.getTime()>432000000){return new RestModel("204","日期区间最大为5天，无法查询");}

        //时间类型转化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = TimeUtil.LongToDate(starttime,format);
        Date time2 = TimeUtil.LongToDate(endtime,format);
        try{
            RestModel restModel = iCapitalService.selectByMobile(mobile,type,time1,time2,draw);
            return restModel;
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel();
        }
    }
}
