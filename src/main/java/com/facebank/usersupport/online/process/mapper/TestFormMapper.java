package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.online.process.model.TestFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestFormMapper extends BaseWriteMapper<TestFormWithBLOBsModel,Long> {

    /**
     * 插入
     * @param model
     * @return
     */
    Long insertGetKey(TestFormWithBLOBsModel model);

    /**
     * 根据工单id修改工单信息
     * @return
     */
    int updateTestFormById(TestFormModel model);

    /**
     * 分页，模糊查询
     * @param testFormModel 提测服务对象
     * @return
     */
    List<TestFormWithBLOBsModel> selectAllByCondition(TestFormModel testFormModel);

    /**
     * 修改当前工单状态
     * @param testFormModel
     * @return
     */
    int updateTestFormStatus(TestFormModel testFormModel);

    /**
     * 查询测试通过的测试工单
     * @param projectId
     * @return
     */
    List<TestFormWithBLOBsModel> selectSuccessTestForm(@Param("projectId") Long projectId);
}