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
}
