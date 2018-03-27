package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.p2p.UserMainP2PReadMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.USUserMainMapper;
import com.facebank.usersupport.model.UserMainModel;
import com.facebank.usersupport.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * impl 实现类
 *Ø
 * @author hailong.Yang
 * @create 2018-03-05 下午5:52
 **/


@Service
public class UserMainServiceImpl extends BaseService implements IUserMainService {


    @Autowired
    private USUserMainMapper usUserMainMapper;

    @Autowired
    private UserMainP2PReadMapper userMainP2PReadMapper;

    @Override
    public UserMainModel getUserMainByUserId(Long userId) {

        //BeanUtils.copyProperties(source,target);
        System.out.println("++++++++++++" + usUserMainMapper);
        return usUserMainMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserMainModel getUserMainByMobile(String mobile, Integer userType) {
        return userMainP2PReadMapper.selectByPrimaryKey(Long.valueOf(mobile));
    }
}
