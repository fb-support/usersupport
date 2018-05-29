package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.AcountDto;
import com.facebank.usersupport.attendance.dto.reqDto.AcountVo;
import com.github.pagehelper.PageInfo;

/**
 * 考勤统计
 * @author zhanguo.huang
 * @date 2018-04-11
 */
public interface IAcountService {

    /**
     * 分页获取考勤
     * @param pageSize
     * @param pageNumber
     * @param acountVo
     * @return
     */
    PageInfo<AcountDto> getAcountByPage(AcountVo acountVo);
}
