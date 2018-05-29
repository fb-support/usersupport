package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.model.RestModel;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-03-29
 */
public interface IDealRecordService {

    /**
     * 添加申请处理记录
     * @param dealRecordModel
     * @return
     */
    RestModel insertApplyDealRecord(DealRecordModel dealRecordModel);

    /**
     * 更新申请处理记录
     * @param dealRecordModel
     * @return
     */
    RestModel updateApplyDealRecord(DealRecordModel dealRecordModel);

    /**
     * 根据id获取
     * @param dealRecordModel
     * @return
     */
    DealRecordModel findDealRecordById(DealRecordModel dealRecordModel);

    /**
     * 根据apply_id 获取
     * @param applyId
     * @return
     */
    List<DealRecordModel> findDealRecordByApplyId(Long applyId);

    /**
     * 删除申请处理记录
     * @param applyId
     * @return
     */
    RestModel deleteApplyDealRecord(Long applyId);

}
