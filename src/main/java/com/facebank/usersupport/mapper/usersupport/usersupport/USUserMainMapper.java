package com.facebank.usersupport.mapper.usersupport.usersupport;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.model.UserMainModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface USUserMainMapper extends BaseWriteMapper<UserMainModel,Long> {

    Integer updateUserStatusByUserId(@Param("userId") Long userId, @Param("userStatusOld") int userStatusOld,
                                     @Param("userStatusNew") int userStatusNew, @Param("modifyTime") Long modifyTime);

    int updateMobileByPrimaryKey(UserMainModel record);
}