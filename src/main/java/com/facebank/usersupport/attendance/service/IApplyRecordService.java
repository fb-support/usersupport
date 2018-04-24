package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;

/**
 * @author zhanguo.huang
 * @date 2018-04-12
 */
public interface IApplyRecordService {
    /**
     * 添加申请
     * @param applyRecordModel
     * @return
     */
    RestModel addApplyRecord(ApplyRecordModel applyRecordModel);

    /**
     * 分页，以及申请时间查询
     * @param pageSize
     * @param pageNumber
     * @param queryVo
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, QueryVo queryVo);

    /**
     * 根据id获取apply
     * @param id
     * @return
     */
    ApplyRecordModel getApplyRecordById(Long id);

    /**
     * 更新申请记录
     * @param applyRecordModel
     * @return
     */
    RestModel updateApplyRecord(ApplyRecordModel applyRecordModel);

    /**
     * 更新申请记录
     * @param applyRecordModel
     * @return
     */
    RestModel updateApplyRecordStatus(ApplyRecordModel applyRecordModel);
}
