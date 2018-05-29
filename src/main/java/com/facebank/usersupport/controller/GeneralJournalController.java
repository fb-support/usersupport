package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.GeneralJournalModel;
import com.facebank.usersupport.model.PageBeanModel;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IGeneralJournalService;
import com.facebank.usersupport.util.StrUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class GeneralJournalController extends BaseController {
    @Autowired
    private IGeneralJournalService generalJournalService;

    /**
     * 功能描述: 获取资金流水查询分页
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/log/generalPage")
    public RestModel getMoneyRecord(String mobile, Integer type, Long starttime, Long endtime, Integer pageSize, Integer pageNumber)  {
        System.out.println("进入方法");
        PageBeanModel pageRestModel=new PageBeanModel();
        if (starttime==null||endtime==null){return new RestModel("203","时间不能为空");}
        if (mobile==""){return new RestModel("202","手机号不能为空");}
        //type为空转-1
        type = StrUtil.parseStringToInt(type,-1);
        try{
            PageInfo<GeneralJournalModel> pageinfos = generalJournalService.selectByMobile(mobile,type,starttime,endtime,pageSize,pageNumber);
            pageRestModel.setData(pageinfos.getList());
            pageRestModel.setPage(pageinfos.getPageNum());
            pageRestModel.setPageSize(pageinfos.getPageSize());
            pageRestModel.setTotalCount(pageinfos.getTotal());
            pageRestModel.setTotalPage(pageinfos.getPages());
            return success(pageRestModel);
        }catch (Exception e){
            return this.excpRestModel();
        }

    }
    /*@RequestMapping("/log/generalPage")
    public RestModel getMoneyRecord(String mobile, Integer type, Long starttime, Long endtime,String draw)  {
        if (starttime==null||endtime==null){return new RestModel("203","时间不能为空");}
        if (mobile==""){return new RestModel("202","手机号不能为空");}
        //type为空转-1
        type = StrUtil.parseStringToInt(type,-1);
        try{
            return generalJournalService.selectByMobile(mobile,type,starttime,endtime,draw);
        }catch (Exception e){
            return this.excpRestModel();
        }

    }*/

}
