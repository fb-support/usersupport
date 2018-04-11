package com.facebank.usersupport.attendance.service;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.model.RestModel;
import com.github.pagehelper.PageInfo;

/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
public interface ILeaveService {

    /**
     * 添加请假申请
     * @param applyRecordModel
     * @return
     */
    RestModel addLeaveApply(ApplyRecordModel applyRecordModel);

    /**
     * 分页，以及申请时间查询
     * @param pageSize
     * @param pageNumber
     * @param queryVo
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, QueryVo queryVo);

    /**
     * 更新请假记录
     * @param applyRecordModel
     * @return
     */
    RestModel updateLeaveApply(ApplyRecordModel applyRecordModel);

    /**
     * 更新状态
     * @param applyRecordModel
     * @return
     */
    RestModel updateApplyRecordStatus(ApplyRecordModel applyRecordModel);

    /**
     * 根据id获取apply
     * @param id
     * @return
     */
    ApplyRecordModel getApplyRecordById(Long id);
}
