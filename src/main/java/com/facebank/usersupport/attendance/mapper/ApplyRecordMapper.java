package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRecordMapper {

    /**
     * 添加申请记录
     * @return
     */
    Integer addApplyRecord(ApplyRecordModel applyRecordModel);

    /**
     * 查询申请记录
     * @param queryVo
     * @return
     */
    List<ApplyRecordModel> getApplyRecordByCondition(QueryVo queryVo);

    /**
     * 根据id获取申请记录
     * @param id
     * @return
     */
    ApplyRecordModel getApplyRecordById(Long id);

    /**
     * 更新申请记录
     * @param applyRecordModel
     */
    Integer updateApplyRecord(ApplyRecordModel applyRecordModel);

    /**
     * 更新状态
     * @param applyRecordModel
     * @return
     */
    Integer updateApplyRecordStatus(ApplyRecordModel applyRecordModel);
}
