package com.facebank.usersupport.service;

import com.facebank.usersupport.model.MoneyRecord;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;

import java.util.Date;

/**
 * Created by yaozun on 2018/3/9.
 */
public interface ICapitalService {
    /**
     * 根据用户id获取用户信息
     * @param mobile
     * @return
     */
    RestModel selectByMobile(String mobile, String type, Date starttime, Date endtime, String draw);
    PageInfo<MoneyRecord> getMoneyRecord(String mobile, String type, Date starttime, Date endtime, Integer pageSize, Integer pageNumber);
}
