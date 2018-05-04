package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.online.process.mapper.TestProjectMapper;
import com.facebank.usersupport.online.process.mapper.TestProjectUserMapper;
import com.facebank.usersupport.online.process.model.TestProjectModel;
import com.facebank.usersupport.online.process.model.TestProjectUserModel;
import com.facebank.usersupport.online.process.service.ITestProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private TestProjectUserMapper testProjectUserMapper;

    /**
     * 根据页码、每页长度查询指定的项目数据
     * @param pageSize
     * @param pageNumber
     * @param testProjectModel
     * @return
     */
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, TestProjectModel testProjectModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<TestProjectModel> testProjectModelList = testProjectMapper.selectAllByCondition(testProjectModel);
        PageInfo<TestProjectModel> pageInfo =new PageInfo<>(testProjectModelList);
        return pageInfo;
    }

    /**
     * 新增一个新项目
     * @param model
     * @param developPeople
     * @param testPeople
     * @param operationsPeople
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertProject(TestProjectModel model,
                             String[] developPeople,
                             String[] testPeople,
                             String[] operationsPeople) {

        // 1.新增项目（插入项目表）
        model.setGmtCreate(System.currentTimeMillis());
        model.setGmtModify(System.currentTimeMillis());
        testProjectMapper.insert(model);
        //获取插入项目时的编号
        Long projectId = model.getProjectId();
        // 2.批量添加项目用户关联表信息记录
        List<TestProjectUserModel> projectUserRecordlist = new ArrayList<>();

        //批量创建角色为“开发”的项目用户关联pojo对象，并添加到集合中
        for (int i=0; i<developPeople.length; i++) {
            TestProjectUserModel relativeModel = new TestProjectUserModel();
            relativeModel.setProjectId(projectId);
            relativeModel.setUserId(Long.parseLong(developPeople[i]));
            relativeModel.setUserRole(1);
            relativeModel.setGmtCreate(System.currentTimeMillis());
            relativeModel.setGmtModify(System.currentTimeMillis());
            projectUserRecordlist.add(relativeModel);
        }

        //批量创建角色为“测试”的项目用户关联pojo对象，并添加到集合中
        for (int i=0; i<testPeople.length; i++) {
            TestProjectUserModel relativeModel = new TestProjectUserModel();
            relativeModel.setProjectId(projectId);
            relativeModel.setUserId(Long.parseLong(testPeople[i]));
            relativeModel.setUserRole(2);
            relativeModel.setGmtCreate(System.currentTimeMillis());
            relativeModel.setGmtModify(System.currentTimeMillis());
            projectUserRecordlist.add(relativeModel);
        }

        //批量创建角色为“运维”的项目用户关联pojo对象，并添加到集合中
        for (int i=0; i<operationsPeople.length; i++) {
            TestProjectUserModel relativeModel = new TestProjectUserModel();
            relativeModel.setProjectId(projectId);
            relativeModel.setUserId(Long.parseLong(operationsPeople[i]));
            relativeModel.setUserRole(3);
            relativeModel.setGmtCreate(System.currentTimeMillis());
            relativeModel.setGmtModify(System.currentTimeMillis());
            projectUserRecordlist.add(relativeModel);
        }
        testProjectUserMapper.batchInsert(projectUserRecordlist);
        return projectId;
    }
}
