package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.model.DealRecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-03-29
 */
@Repository
public interface DealRecordMapper {

    /**
     * 添加申请处理记录
     * @param dealRecordModel
     * @return
     */
    Integer insertApplyDealRecord(DealRecordModel dealRecordModel);

    /**
     * 更新申请处理记录
     * @param dealRecordModel
     * @return
     */
    Integer updateApplyDealRecord(DealRecordModel dealRecordModel);

    /**
     * 根据id获取
     * @param dealRecordModel
     * @return
     */
    DealRecordModel findDealRecordById(DealRecordModel dealRecordModel);

    /**
     * 根据申请id获取
     * @param applyId
     * @return
     */
    List<DealRecordModel> findDealRecordByApplyId(Long applyId);

    /**
     * 删除处理记录
     * @param applyId
     * @return
     */
    Integer deleteDealRecord(Long applyId);
}
