package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestProjectRecordMapper extends BaseWriteMapper<TestProjectRecordModel,Long>{

    /**
     * 分页，多条件模糊查询
     * @param testProjectRecordModel
     * @return
     */
    List<TestProjectRecordModel> selectAllByCondition(TestProjectRecordModel testProjectRecordModel);

    /**
     * 插入
     * @param testProjectRecordModel
     * @return
     */
    int insertRecord(TestProjectRecordModel testProjectRecordModel);

}