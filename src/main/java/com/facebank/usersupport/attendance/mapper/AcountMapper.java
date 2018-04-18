package com.facebank.usersupport.attendance.mapper;

import com.facebank.usersupport.attendance.dto.AcountDto;
import com.facebank.usersupport.attendance.dto.reqDto.AcountVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考勤统计
 * @author zhanguo.huang
 * @date 2018-04-10
 */
@Repository
public interface AcountMapper {

    /**
     * 获取所以得统计记录
     * @param acountVo
     * @return
     */
    List<AcountDto> acountAll(AcountVo acountVo);
}
