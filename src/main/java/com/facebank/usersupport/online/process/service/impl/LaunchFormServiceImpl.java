package com.facebank.usersupport.online.process.service.impl;

import com.facebank.usersupport.online.process.mapper.LaunchFormMapper;
import com.facebank.usersupport.online.process.model.LaunchFormModel;
import com.facebank.usersupport.online.process.service.ILaunchFormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/3/28
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 * Description:
 * @author hyh
 */
@Service
public class LaunchFormServiceImpl implements ILaunchFormService {

    @Autowired
    LaunchFormMapper launchFormMapper;

    @Override
    public PageInfo selectByPage(int pageSize, int pageNumber, LaunchFormModel launchFormModel) {
        PageHelper.startPage(pageNumber,pageSize);
        List<LaunchFormModel> LaunchFormModelList = launchFormMapper.selectAllLaunchForm(launchFormModel);
        PageInfo<LaunchFormModel> pageInfo =new PageInfo<>(LaunchFormModelList);
        return pageInfo;
    }

    @Override
    public LaunchFormModel selectByFormId(Long formId) {
        LaunchFormModel launchFormModel = launchFormMapper.selectByFormId(formId);
        return launchFormModel;
    }

    @Override
    public int insertLaunchForm(LaunchFormModel launchFormModel) {
        launchFormModel.setGmtCreate(System.currentTimeMillis());
        launchFormModel.setGmtModify(System.currentTimeMillis());
        launchFormModel.setFormStatus(0);
        launchFormMapper.insert(launchFormModel);
        return 0;
    }

    @Override
    public int updateLaunchForm(LaunchFormModel launchFormModel) {
        launchFormMapper.updateByFormId(launchFormModel);
        return 0;
    }
}