package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.online.process.model.TestProjectModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ningkui
 */
@Component
public interface TestProjectMapper extends BaseWriteMapper {

    /**
     * 分页，多条件模糊查询
     * @param testProjectModel
     * @return
     */
    List<TestProjectModel> selectAllByCondition(TestProjectModel testProjectModel);

    /**
     * 新增一条记录
     * @param testProjectModel
     * @return  新插入记录的主键
     */
    Long insert(TestProjectModel testProjectModel);
}