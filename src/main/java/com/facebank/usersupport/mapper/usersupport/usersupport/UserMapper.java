package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.UserModel;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NingKui
 * @date 2018/3/9 10:58
 **/
@Repository
public interface UserMapper extends BaseWriteMapper<UserModel, Long> {

    /**
     * 插入
     * @param model
     * @return
     */
    @Override
    int insert(UserModel model);


    /**
     * 根据不定条件查询出用户集合
     * @param userModel
     * @return
     */
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

    /**
     * 批量删除用户
     * @param ids
     */
    void batchDeleteUsers(Integer[] ids);
	
    /**
     * 多表联合查询（用户表、角色表和用户角色中间表）
     * @param loginName 用户名、手机号、email
     * @return
     */
    UserRoleDO selectBySelectiveForPermission(String loginName);

    /**
     * 禁用用户
     * @param ids
     * @return
     */
    int updateStatusForBanUser(Integer[] ids);

    /**
     * 启用用户
     * @param ids
     * @return
     */
    int updateStatusForEnableUser(Integer[] ids);
}
