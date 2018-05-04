package com.facebank.usersupport.online.process.service;

import com.facebank.usersupport.online.process.model.TestFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @Author : ChinaLHR
 * @Date : Create in 15:40 2018/3/27
 * @Email : 13435500980@163.com
 *
 * 测试工单表接口
 */

public interface ITestFormService {

    /**
     * 新建工单
     * @param testFormModel
     * @return
     */
    Long insertForm(TestFormWithBLOBsModel testFormModel);

    /**
     * 根据测试工单id获取测试工单信息
     * @param formId
     * @return
     */
    TestFormWithBLOBsModel selectByFormId(Long formId);

    /**
     * 修改测试工单
     * @param testFormModel
     * @return
     */
    int updateTestForm(TestFormWithBLOBsModel testFormModel);

    /**
     * 分页查询
     * @param pageSize
     * @param pageNumber
     * @param testFormModel
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, TestFormModel testFormModel);

    /**
     * 修改测试工单状态
     * @param testFormModel
     * @return
     */
    int updateTestFormStatus(TestFormModel testFormModel);

    /**
     * 根据projectId 查询测试通过的工单
     * @param projectId
     * @return
     */
    List<TestFormWithBLOBsModel> selectSuccessTestForm(Long projectId);
}

