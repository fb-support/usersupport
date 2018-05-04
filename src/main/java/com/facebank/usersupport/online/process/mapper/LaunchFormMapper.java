package com.facebank.usersupport.online.process.mapper;

import com.facebank.usersupport.mapper.usersupport.base.BaseWriteMapper;
import com.facebank.usersupport.online.process.model.LaunchFormModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LaunchFormMapper extends BaseWriteMapper<LaunchFormModel, Long> {

    List<LaunchFormModel> selectAllLaunchForm(LaunchFormModel launchFormModel);

    Long insertGetKey(LaunchFormModel launchFormModel);

    LaunchFormModel selectByFormId(Long formId);

    int updateByFormId(LaunchFormModel launchFormModel);

}