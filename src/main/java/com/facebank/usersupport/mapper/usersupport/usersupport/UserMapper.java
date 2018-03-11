package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NingKui
 * @date 2018/3/9 10:58
 **/
@Repository
public interface UserMapper extends BaseWriteMapper<UserModel, Long> {

    /**
     *
     *
     * 可以重用BaseWriteMapper中的方法，因为BaseWriteMapper继承了BaseReadMapper
     * 所以继承BaseWriteMapper就同时有了Read和write的基本方法
     */
    @Override
    int insert(UserModel model);


    List<UserModel> selectByUserModel(UserModel userModel);

    int updatePasswordById(UserModel model);

    int updateBaseInfomationById(UserModel model);

    /**
     * 分页，多条件模糊查询
     * @param userModel
     * @return
     */
    List<UserModel> selectAllByCondition(UserModel userModel);

    /**
     * 批量删除用户
     * @param ids
     */
    void batchDeleteUsers(Integer[] ids);
}
