package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.github.pagehelper.PageInfo;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 16:05
 **/
public interface IAdjustRestService {

    void saveRestApply(ApplyRecordModel applyRecordModel);

    PageInfo<ApplyRecordModel> getRestApplyListByPage(QueryVo queryVo,Integer page,Integer pageSize);

    void updateRestApply(ApplyRecordModel applyRecordModel);

}
