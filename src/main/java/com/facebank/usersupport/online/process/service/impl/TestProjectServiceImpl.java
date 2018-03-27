package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.online.process.mapper.TestProjectMapper;
import com.facebank.usersupport.online.process.model.TestProjectModel;
import com.facebank.usersupport.online.process.service.ITestProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目管理的Service实现类
 * @author NingKui
 * @date 2018/3/27 15:44
 **/
@Service
public class TestProjectServiceImpl implements ITestProjectService {

    @Autowired
    private TestProjectMapper testProjectMapper;

    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, TestProjectModel testProjectModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<TestProjectModel> testProjectModelList = testProjectMapper.selectAllByCondition(testProjectModel);
        PageInfo<TestProjectModel> pageInfo =new PageInfo<>(testProjectModelList);
        return pageInfo;
    }
}
