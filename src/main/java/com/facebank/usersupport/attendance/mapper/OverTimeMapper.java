package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wz on 2018/3/27.
 */
@Repository
public interface OverTimeMapper extends BaseWriteMapper<ApplyRecordModel,Long> {
    int insert(ApplyRecordModel applyRecordModel);
    List<ApplyRecordModel> getApplyRecordModels(QueryVo queryVo);
    int updateApplyRecord(ApplyRecordModel applyRecordModel);
    int updateApplyRecordByStatus(ApplyRecordModel applyRecordModel);
    int updateApplyRecordByStatusSetOne(ApplyRecordModel applyRecordModel);
}
