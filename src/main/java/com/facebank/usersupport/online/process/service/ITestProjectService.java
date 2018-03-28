package com.facebank.usersupport.online.process.service;

import com.facebank.usersupport.online.process.model.TestProjectModel;
import com.github.pagehelper.PageInfo;

/**
 * 项目Service
 */
public interface ITestProjectService {

    /**
     * 根据页码、每页长度查询指定的项目数据
     * @param pageSize
     * @param pageNumber
     * @param testProjectModel
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, TestProjectModel testProjectModel);

    /**
     * 新增一个新项目
     * @param model
     * @param developPeople
     * @param testPeople
     * @param operationsPeople
     * @return
     */
    int insertProject(TestProjectModel model,
                      String[] developPeople,
                      String[] testPeople,
                      String[] operationsPeople);
}
