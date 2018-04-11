package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;

/**
 * Created by wz on 2018/3/27.
 */
public interface IOverTimeService {
    RestModel insert(ApplyRecordModel applyRecordModel);
    PageInfo<ApplyRecordModel> search(Integer pageNum, Integer pageSize, QueryVo queryVo);
    RestModel updateApplyRecord(ApplyRecordModel applyRecordModel);
    PageInfo<DealModel> getDealModels(Integer pageNum, Integer pageSize, QueryVo queryVo);
    RestModel updateApplyRecordByStatus(ApplyRecordModel applyRecordModel);
    RestModel updateApplyRecordByStatusSetOne(ApplyRecordModel applyRecordModel);
}
