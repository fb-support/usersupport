package com.facebank.usersupport.attendance.service.impl;

import com.facebank.usersupport.attendance.dto.reqDto.QueryVo;
import com.facebank.usersupport.attendance.mapper.DealMapper;
import com.facebank.usersupport.attendance.mapper.OverTimeMapper;
import com.facebank.usersupport.attendance.model.ApplyRecordModel;
import com.facebank.usersupport.attendance.model.DealModel;
import com.facebank.usersupport.attendance.service.IOverTimeService;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wz on 2018/3/27.
 */
@Service
public class OverTimeServiceImpl extends BaseService implements IOverTimeService {
    @Autowired
    private OverTimeMapper overTimeMapper;
    @Autowired
    private DealMapper dealMapper;

    @Override
    public RestModel insert(ApplyRecordModel applyRecordModel) {
        int is_insert = overTimeMapper.insert(applyRecordModel);

        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    @Override
    public PageInfo<ApplyRecordModel> search(Integer pageNum, Integer pageSize, QueryVo queryVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<ApplyRecordModel> applyRecordModels = overTimeMapper.getApplyRecordModels(queryVo);
        PageInfo<ApplyRecordModel> pageInfo = new PageInfo<>(applyRecordModels);
        return pageInfo;
    }

    @Override
    public RestModel updateApplyRecord(ApplyRecordModel applyRecordModel) {
        int is_insert = overTimeMapper.updateApplyRecord(applyRecordModel);

        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    @Override
    public PageInfo<DealModel> getDealModels(Integer pageNum, Integer pageSize, QueryVo queryVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<DealModel> dealModels = dealMapper.getDealModels(queryVo);
        PageInfo<DealModel> pageInfo = new PageInfo<>(dealModels);
        return pageInfo;
    }

    @Override
    public RestModel updateApplyRecordByStatus(ApplyRecordModel applyRecordModel) {
        int is_insert = overTimeMapper.updateApplyRecordByStatus(applyRecordModel);

        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }

    @Override
    public RestModel updateApplyRecordByStatusSetOne(ApplyRecordModel applyRecordModel) {

        int is_insert = overTimeMapper.updateApplyRecordByStatusSetOne(applyRecordModel);

        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();
    }


}
