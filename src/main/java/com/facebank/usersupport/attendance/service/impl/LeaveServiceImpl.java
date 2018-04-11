package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.mapper.LeaveMapper;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.service.ILeaveService;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-03-27
 */
@Service
public class LeaveServiceImpl extends BaseService implements ILeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    /**
     * 添加请假申请
     * @param applyRecordModel
     * @return
     */
    @Override
    public RestModel addLeaveApply(ApplyRecordModel applyRecordModel) {
        int is_insert = leaveMapper.addLeaveApply(applyRecordModel);
        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 根据条件查询，以及分页
     * @param pageSize
     * @param pageNumber
     * @param queryVo
     * @return
     */
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, QueryVo queryVo) {
        PageHelper.startPage(pageNumber,pageSize);
        List<ApplyRecordModel> applyRecordModelList = leaveMapper.getApplyByCondition(queryVo);
        PageInfo<ApplyRecordModel> pageInfo =new PageInfo<>(applyRecordModelList);
        return pageInfo;

    }

    /**
     * 更新请假申请
     * @param applyRecordModel
     * @return
     */
    @Override
    public RestModel updateLeaveApply(ApplyRecordModel applyRecordModel) {
        int is_update = leaveMapper.updateApplyRecord(applyRecordModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 更新状态
     * @param applyRecordModel
     * @return
     */
    @Override
    public RestModel updateApplyRecordStatus(ApplyRecordModel applyRecordModel) {
        int is_update = leaveMapper.updateApplyRecordStatus(applyRecordModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    /**
     * 根据id获取apply
     * @param id
     * @return
     */
    @Override
    public ApplyRecordModel getApplyRecordById(Long id) {
        return leaveMapper.getApplyById(id);
    }

}
