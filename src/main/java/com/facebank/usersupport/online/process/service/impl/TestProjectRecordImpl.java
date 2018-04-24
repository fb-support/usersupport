package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.mapper.TestProjectRecordMapper;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import com.facebank.usersupport.online.process.service.ITestProjectRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestProjectRecordImpl implements ITestProjectRecordService {

    @Autowired
    TestProjectRecordMapper testProjectRecordMapper;
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, TestProjectRecordModel testProjectRecordModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<TestProjectRecordModel> recordList = testProjectRecordMapper.selectAllByCondition(testProjectRecordModel);
        PageInfo<TestProjectRecordModel> pageInfo =new PageInfo<>(recordList);
        return pageInfo;
    }

    @Override
    public RestModel insertRecord(TestProjectRecordModel testProjectRecordModel) {
        testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
        testProjectRecordModel.setGmtModify(System.currentTimeMillis());
        int is_insert = testProjectRecordMapper.insertRecord(testProjectRecordModel);
        if (is_insert == 1) {
            return new RestModel(RestModel.CODE_SUCCESS, RestModel.MESSAGE_SUCCESS);
        }
        return new RestModel();

    }
}
