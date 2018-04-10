package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IGeneralJournalService;
import com.facebank.usersupport.util.StrUtil;
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
    public RestModel getMoneyRecord(@RequestParam(required = false, defaultValue = "1") int start,
                                    @RequestParam(required = false, defaultValue = "10") int couts,
                                    String mobile, Integer type, Long starttime, Long endtime,String draw)  {
        if (starttime==null||endtime==null){return new RestModel("203","时间不能为空");}
        if (mobile==""){return new RestModel("202","手机号不能为空");}
        type = StrUtil.parseStringToInt(type,-1);
        int pageNo = start / couts + 1;
        try{
            return generalJournalService.selectByMobile(mobile,type,starttime,endtime,pageNo,couts, draw);
        }catch (Exception e){
            return this.excpRestModel();
        }

    }

}
