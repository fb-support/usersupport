package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.online.process.model.TestProjectModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestProjectMapper {

    /**
     * 分页，多条件模糊查询
     * @param testProjectModel
     * @return
     */
    List<TestProjectModel> selectAllByCondition(TestProjectModel testProjectModel);
}