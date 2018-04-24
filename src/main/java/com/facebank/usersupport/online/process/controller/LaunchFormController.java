package com.facebank.usersupport.online.process.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.controller.base.BaseController;
import com.facebank.usersupport.online.process.model.LaunchFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.facebank.usersupport.online.process.service.ILaunchFormService;
import com.facebank.usersupport.online.process.service.ITestFormService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/3/28
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class LaunchFormController extends BaseController {

    @Autowired
    ILaunchFormService launchFormService;
    @Autowired
    ITestFormService testFormService;

    @GetMapping("/online-process/getLaunchFormByPage")
    public RestModel getLaunchFormByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length,
                                         String draw,
                                         LaunchFormModel launchFormModel){
        try{
            int pageNo = start / length + 1;
            PageInfo pageInfo =launchFormService.selectByPage(length, pageNo, launchFormModel);
            PageRestModel pageRestModel = new PageRestModel(draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);
        }catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/online-process/createLaunchForm")
    public RestModel createLaunchForm(LaunchFormModel launchFormModel) {
        try {
            launchFormModel.setCreateUser(1L);
            launchFormModel.setCreateUsername("admin");
            launchFormService.insertLaunchForm(launchFormModel);
            return this.success(MessageKeyEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @GetMapping("/online-process/findByFormId")
    public RestModel findByFormId(Long formId){
        try {
            LaunchFormModel launchFormModel = launchFormService.selectByFormId(formId);
            return this.success(JSONObject.parseObject(JSON.toJSONString(launchFormModel)));
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/online-process/updateLaunchForm")
    public RestModel updateLaunchForm(LaunchFormModel launchFormModel){
        try {
            launchFormService.updateLaunchForm(launchFormModel);
            return this.success(MessageKeyEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @GetMapping("/online-process/getTestForm")
    public RestModel getTestForm(){
        try {
            List<TestFormWithBLOBsModel> testFormList = testFormService.selectSuccessTestForm(null);
            return this.success(testFormList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }
}