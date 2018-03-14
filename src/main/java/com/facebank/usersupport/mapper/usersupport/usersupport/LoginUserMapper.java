package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.LoginUserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录Mapper
 * @author NingKui
 * @date 2018/3/14 10:58
 **/
@Repository
public interface LoginUserMapper extends BaseWriteMapper<LoginUserModel, Long> {

    /**
     * 插入登录记录
     * @param model
     * @return
     */
    @Override
    int insert(LoginUserModel model);

    /**
     * 登出，添加记录中的登出时间
     */
    int updateLoginOutTime(LoginUserModel model);
}
