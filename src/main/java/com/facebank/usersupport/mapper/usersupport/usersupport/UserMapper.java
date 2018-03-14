package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.UserModel;
import org.apache.catalina.User;
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

    /**
     * 根据用户ID修改密码
     * @param model
     * @return
     */
    int updatePasswordById(UserModel model);

    /**
     * 根据用户Id修改基本信息
     * @param model
     * @return
     */
    int updateBaseInfomationById(UserModel model);

    /**
     * 根据用户Id获取信息
     * @param userId
     * @return
     */
    UserModel getUserById(Long userId);

    /**
     * 分页，多条件模糊查询
     * @param userModel
     * @return
     */
    List<UserModel> selectAllByCondition(UserModel userModel);

    List<UserModel> selectA(UserModel userModel);

    /**
     * 批量删除用户
     * @param ids
     */
    void batchDeleteUsers(Integer[] ids);

    UserModel selectInfoMation(String objValue);
	
    /**
     * 多表联合查询（用户表、角色表和用户角色中间表）
     * @param loginName 用户名、手机号、email
     * @return
     */
    UserRoleDO selectBySelectiveForPermission(String loginName);
}
