package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.github.pagehelper.PageInfo;

/**
 * @author zhanguo.huang
 * @date 2018-04-02
 */
public interface IDealService {

    /**
     * 分页，以及申请时间查询
     * @param pageSize
     * @param pageNumber
     * @param queryVo
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, QueryVo queryVo);
}
