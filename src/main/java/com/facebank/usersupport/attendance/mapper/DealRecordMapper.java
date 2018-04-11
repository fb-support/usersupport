package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.model.DealRecordModel;
import org.springframework.stereotype.Repository;

/**
 * @author HuBiao
 * @date 2018/3/29 0029 9:18
 **/
@Repository
public interface DealRecordMapper {

    void saveDealRecord(DealRecordModel dealRecordModel);

}
