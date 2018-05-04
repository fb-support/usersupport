package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.online.process.mapper.TestFormMapper;
import com.facebank.usersupport.online.process.model.TestFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.facebank.usersupport.online.process.service.ITestFormService;
import com.facebank.usersupport.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : ChinaLHR
 * @Date : Create in 15:58 2018/3/27
 * @Email : 13435500980@163.com
 */
@Service
public class TestFormServiceImpl extends BaseService implements ITestFormService {

    @Autowired
    private TestFormMapper testFormMapper;

    @Override
    public Long insertForm(TestFormWithBLOBsModel testFormModel) {
        testFormModel.setGmtCreate(System.currentTimeMillis());
        testFormModel.setGmtModify(System.currentTimeMillis());

        Long formId = testFormMapper.insertGetKey(testFormModel);
        return formId;
    }

    @Override
    public TestFormWithBLOBsModel selectByFormId(Long formId) {
        return testFormMapper.selectByPrimaryKey(formId);
    }

    @Override
    public int updateTestForm(TestFormWithBLOBsModel testFormModel) {
        testFormModel.setGmtModify(System.currentTimeMillis());
        return testFormMapper.updateTestFormById(testFormModel);
    }

    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, TestFormModel testFormModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<TestFormWithBLOBsModel> models = testFormMapper.selectAllByCondition(testFormModel);
        PageInfo<TestFormWithBLOBsModel> pageInfo =new PageInfo<>(models);
        return pageInfo;
    }

    @Override
    public int updateTestFormStatus(TestFormModel testFormModel) {
        return testFormMapper.updateTestFormStatus(testFormModel);
    }

    @Override
    public List<TestFormWithBLOBsModel> selectSuccessTestForm(Long projectId) {
        return testFormMapper.selectSuccessTestForm(projectId);
    }


}
