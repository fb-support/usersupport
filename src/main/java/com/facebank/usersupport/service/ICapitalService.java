package com.facebank.usersupport.service;

import com.facebank.usersupport.dto.CapitalDto;
import com.facebank.usersupport.dto.PageDto;
import com.facebank.usersupport.model.RestModel;

import java.util.Date;
import java.util.List;

/**
 * Created by yaozun on 2018/3/9.
 */
public interface ICapitalService {
    /**
     * 根据用户id获取用户信息
     * @param mobile
     * @return
     */
    List<CapitalDto> getMoneyRecord(String mobile, String type, Date starttime, Date endtime);

    PageDto getMoneyRecordPage(String mobile, String type, Date starttime, Date endtime, Integer page, Integer counts);

    public Integer getCounts(String mobile, String type, Date starttime, Date endtime);

    RestModel selectByMobile(String mobile, String type, Date starttime, Date endtime, Integer page, Integer counts, String draw);
}
