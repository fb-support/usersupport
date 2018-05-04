package com.facebank.usersupport.online.process.service;

import com.facebank.usersupport.online.process.model.LaunchFormModel;
import com.github.pagehelper.PageInfo;

public interface ILaunchFormService {
    /**
     * 根据页码、每页长度查询指定的项目数据
     * @param pageSize
     * @param pageNumber
     * @param launchFormModel
     * @return
     */
    PageInfo selectByPage(int pageSize, int pageNumber, LaunchFormModel launchFormModel);

    LaunchFormModel selectByFormId(Long formId);

    Long insertLaunchForm(LaunchFormModel launchFormModel);

    int updateLaunchForm(LaunchFormModel launchFormModel);
}
