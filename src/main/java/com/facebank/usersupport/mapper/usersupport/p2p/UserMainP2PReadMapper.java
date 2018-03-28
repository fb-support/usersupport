package com.facebank.usersupport.mapper.usersupport.p2p;

import com.facebank.usersupport.mapper.usersupport.base.BaseReadMapper;
import com.facebank.usersupport.model.UserMainModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMainP2PReadMapper extends BaseReadMapper<UserMainModel,Long> {

    UserMainModel selectByMobile(@Param("mobile") String mobile, @Param("userType") Integer userType);

    List<UserMainModel> selectAll(Map<String, Object> param);

    Long selectAllCount(Map<String, Object> param);

    List<UserMainModel> selectByUserIds(List<Long> userIds);

    List<UserMainModel> selectByMobiles(@Param("list") List<String> mobiles, @Param("userType") Integer userType);

    Long selectUserIdByMobile(String mobile);
}