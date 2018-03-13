package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.ICapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/log/getMoneyRecordPage")
    public RestModel getMoneyRecord(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "5") int couts,
                                    String mobile, String type, String starttime, String endtime)  {
        System.out.println(mobile+"=="+type+"=="+starttime+"=="+endtime);
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
        PageDto pageDto = iCapitalService.getMoneyRecordPage(mobile,type,time1,time2,page,couts);

        return this.success(pageDto);
    }
    //获取前十条数据
    @PostMapping("/log/getMoneyRecord")
    public RestModel getMoneyRecord(String mobile, String type, String starttime, String endtime)  {
        System.out.println(mobile+"=="+type+"=="+starttime+"=="+endtime);
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
        List<CapitalDto> capitalDtos = iCapitalService.getMoneyRecord(mobile,type,time1,time2);
        return this.success(capitalDtos);
    }

    @GetMapping("/log/getCounts")
    public Integer getMoneyRecordd(String mobile, String type, String starttime, String endtime)  {
        System.out.println(mobile+"=="+type+"=="+starttime+"=="+endtime);
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
        Integer ss = iCapitalService.getCounts(mobile,type,time1,time2);
        return ss;
    }
}
