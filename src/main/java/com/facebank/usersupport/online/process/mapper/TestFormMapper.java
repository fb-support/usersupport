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
    @Override
    int insert(TestFormWithBLOBsModel model);

    /**
     * 根据工单id修改工单信息
     * @return
     */
    int updateTestFormById(TestFormModel model);

    /**
     * 分页，模糊查询
     * @return
     */
    List<TestFormWithBLOBsModel> selectAllByCondition(@Param("formService") String formService
            , @Param("formStatus") Integer formStatus);

    /**
     * 修改当前工单状态
     * @param formId
     * @param formStatus
     * @return
     */
    int updateTestFormStatus(@Param("formId") Long formId, @Param("formStatus") Integer formStatus);
}