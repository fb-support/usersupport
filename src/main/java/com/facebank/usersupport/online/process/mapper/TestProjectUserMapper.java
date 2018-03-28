package com.facebank.usersupport.online.process.mapper;
import com.facebank.usersupport.online.process.model.TestProjectUserModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestProjectUserMapper {

    /***
     * 批量插入数据
     * @return
     */
    int batchInsert(List<TestProjectUserModel> list);
}