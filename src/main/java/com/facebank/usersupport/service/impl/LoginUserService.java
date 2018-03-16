package com.facebank.usersupport.service.impl;

import com.facebank.usersupport.mapper.usersupport.usersupport.LoginUserMapper;
import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.service.ILoginUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录流水表Service实现类
 * @author NingKui
 * @date 2018/3/14 15:51
 **/
@Service
public class LoginUserService implements ILoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    /**
     * 退出时调用---给指定记录添加退出时间信息
     * @param model
     * @return
     */
    @Override
    public int updateLoginOutTime(LoginUserModel model) {
        int flag = loginUserMapper.updateLoginOutTime(model);
        return flag;
    }

    /**
     * 根据不定条件查询出登录流水记录集合--并且分页
     * @param pageSize
     * @param pageNumber
     * @param loginUserModel
     * @return
     */
    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, LoginUserModel loginUserModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<LoginUserModel> loginUserList = loginUserMapper.selectAllByCondition(loginUserModel);
        PageInfo<LoginUserModel> pageInfo =new PageInfo<>(loginUserList);
        return pageInfo;
    }
}
