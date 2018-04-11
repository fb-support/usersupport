package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/3/28 0028 16:10
 **/
@Repository
public interface AdjustRestMapper {

    void saveRestApply(ApplyRecordModel applyRecordModel);

    List<ApplyRecordModel> getRestApplyListByQueryVo(QueryVo queryVo);

    void updateRestApply(ApplyRecordModel applyRecordModel);

}
