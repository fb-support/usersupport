package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.online.process.model.TestProjectUserModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author NingKui
 * @date 2018/3/28 17:58
 **/
@Component
public interface TestProjectUserMapper {

    /***
     * 批量插入数据
     * @return
     */
    int batchInsert(List<TestProjectUserModel> list);

}
