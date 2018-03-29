package com.facebank.usersupport.online.process.service;

import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.github.pagehelper.PageInfo;


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
    int insertForm(TestFormWithBLOBsModel testFormModel);

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
     * @param formService
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, String formService, Integer formStatus);

    /**
     * 修改测试工单状态
     * @param formId
     * @param formStatus
     * @return
     */
    int updateTestFormStatus(Long formId, Integer formStatus);
}

