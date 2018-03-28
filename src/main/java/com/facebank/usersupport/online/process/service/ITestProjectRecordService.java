package com.facebank.usersupport.online.process.service;


import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import com.github.pagehelper.PageInfo;

/**
 * 项目流水表记录接口
 */
public interface ITestProjectRecordService {

    /**
     * 分页，多条件模糊查询
     * @param pageSize
     * @param pageNumber
     * @param testProjectRecordModel
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, TestProjectRecordModel testProjectRecordModel);

    /**
     * 插入
     * @param testProjectRecordModel
     * @return
     */
    RestModel insertRecord(TestProjectRecordModel testProjectRecordModel);
}
