package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.mapper.DealRecordMapper;
import com.facebank.usersupport.attendance.model.DealRecordModel;
import com.facebank.usersupport.attendance.service.IDealRecordService;
import com.facebank.usersupport.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-03-28
 */
@Service
public class DealRecordServiceImpl implements IDealRecordService {

    @Autowired
    private DealRecordMapper dealRecordMapper;

    /**
     * 添加申请处理记录
     * @param dealRecordModel
     * @return
     */
    @Override
    public RestModel insertApplyDealRecord(DealRecordModel dealRecordModel) {
        int is_insert = dealRecordMapper.insertApplyDealRecord(dealRecordModel);
        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 更新申请处理记录
     * @param dealRecordModel
     * @return
     */
    @Override
    public RestModel updateApplyDealRecord(DealRecordModel dealRecordModel) {
        int is_update = dealRecordMapper.updateApplyDealRecord(dealRecordModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 根据id获取
     * @param dealRecordModel
     * @return
     */
    @Override
    public DealRecordModel findDealRecordById(DealRecordModel dealRecordModel) {
        return dealRecordMapper.findDealRecordById(dealRecordModel);
    }

    /**
     * 根据apply_id获取
     * @param applyId
     * @return
     */
    @Override
    public List<DealRecordModel> findDealRecordByApplyId(Long applyId) {
        return dealRecordMapper.findDealRecordByApplyId(applyId);
    }

    /**
     * 删除处理记录
     * @param applyId
     * @return
     */
    @Override
    public RestModel deleteApplyDealRecord(Long applyId) {
        int is_delete = dealRecordMapper.deleteDealRecord(applyId);
        if (is_delete == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

}
