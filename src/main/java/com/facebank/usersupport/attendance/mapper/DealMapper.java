package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.DealModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审核mapper
 * Created by wz on 2018/4/2.
 */
@Repository
public interface DealMapper {
    List<DealModel> getDealModels(QueryVo queryVo);
}
