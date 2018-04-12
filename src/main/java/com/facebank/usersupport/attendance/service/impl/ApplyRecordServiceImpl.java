package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.mapper.ApplyRecordMapper;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.service.IApplyRecordService;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanguo.huang
 * @date 2018-04-12
 */
@Service
public class ApplyRecordServiceImpl extends BaseService implements IApplyRecordService {

    @Autowired
    private ApplyRecordMapper applyRecordMapper;

    /**
     * 添加请假申请
     * @param applyRecordModel
     * @return
     */
    @Override
    public RestModel addApplyRecord(ApplyRecordModel applyRecordModel) {
        int is_insert = applyRecordMapper.addApplyRecord(applyRecordModel);
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
        List<ApplyRecordModel> applyRecordModelList = applyRecordMapper.getApplyRecordByCondition(queryVo);
        PageInfo<ApplyRecordModel> pageInfo =new PageInfo<>(applyRecordModelList);
        return pageInfo;
    }

    /**
     * 根据id获取apply
     * @param id
     * @return
     */
    @Override
    public ApplyRecordModel getApplyRecordById(Long id) {
        return applyRecordMapper.getApplyRecordById(id);
    }

    /**
     * 更新申请
     * @param applyRecordModel
     * @return
     */
    @Override
    public RestModel updateApplyRecord(ApplyRecordModel applyRecordModel) {
        int is_update = applyRecordMapper.updateApplyRecord(applyRecordModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    @Override
    public RestModel updateApplyRecordStatus(ApplyRecordModel applyRecordModel) {
        int is_update = applyRecordMapper.updateApplyRecordStatus(applyRecordModel);
        if (is_update == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }
}
