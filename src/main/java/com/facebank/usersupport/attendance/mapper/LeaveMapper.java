package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
@Repository
public interface LeaveMapper {

    /**
     * 更新申请记录
     * @param applyRecordModel
     * @return
     */
    Integer updateApplyRecord(ApplyRecordModel applyRecordModel);

    /**
     * 添加请假申请
     * @param applyRecordModel
     * @return
     */
    Integer addLeaveApply(ApplyRecordModel applyRecordModel);

    ApplyRecordModel getApplyById(Long id);

    /**
     * 根据条件获取请假记录
     * @param
     * @return
     */
    List<ApplyRecordModel> getApplyByCondition(QueryVo queryVo);

    /**
     * 更新
     * @param applyRecordModel
     * @return
     */
    Integer updateApplyRecordStatus(ApplyRecordModel applyRecordModel);
}
