package com.facebank.usersupport.online.process.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.online.process.controller.base.BaseController;
import com.facebank.usersupport.online.process.model.LaunchFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import com.facebank.usersupport.online.process.service.ILaunchFormService;
import com.facebank.usersupport.online.process.service.ITestFormService;
import com.facebank.usersupport.online.process.service.ITestProjectRecordService;
import com.facebank.usersupport.online.process.service.IUserService;
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
    @Autowired
    private IUserService userService;
    @Autowired
    private ITestProjectRecordService testProjectRecordService;

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

    /**
     * 增加上线工单
     * @param launchFormModel
     * @return
     */
    @PostMapping("/online-process/createLaunchForm")
    public RestModel createLaunchForm(LaunchFormModel launchFormModel) {
        try {
            // 1. 获取当前登录用户
            UserModel userModel = userService.getActiveUser();
            launchFormModel.setCreateUser(userModel.getUserId());
            launchFormModel.setCreateUsername(userModel.getUsername());
            // 插入上线工单到数据库
            Long formId = launchFormService.insertLaunchForm(launchFormModel);

            //记录当次操作的流水
            // 2.封装操作流水model
            TestProjectRecordModel testProjectRecordModel = new TestProjectRecordModel();
            testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
            testProjectRecordModel.setGmtModify(System.currentTimeMillis());
            testProjectRecordModel.setFormType(2);
            testProjectRecordModel.setFormId(formId);
            testProjectRecordModel.setOperatingPeople(userModel.getUsername());
            testProjectRecordModel.setOperatingPeopleId(userModel.getUserId());
            testProjectRecordModel.setProjectId(launchFormModel.getProjectId());
            testProjectRecordModel.setOperatingContent("创建了新的上线工单。上线内容为："+launchFormModel.getFormContent());
            // 3.插入数据库
            testProjectRecordService.insertRecord(testProjectRecordModel);

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
            // 1. 获取当前登录用户
            UserModel userModel = userService.getActiveUser();

            launchFormModel.setGmtModify(System.currentTimeMillis());

            //记录当次修改状态操作的流水
            // 2.封装操作流水model
            TestProjectRecordModel testProjectRecordModel = new TestProjectRecordModel();
            testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
            testProjectRecordModel.setGmtModify(System.currentTimeMillis());
            testProjectRecordModel.setFormType(2);
            testProjectRecordModel.setFormId(launchFormModel.getFormId());
            testProjectRecordModel.setOperatingPeople(userModel.getUsername());
            testProjectRecordModel.setOperatingPeopleId(userModel.getUserId());
            testProjectRecordModel.setProjectId(launchFormModel.getProjectId());
            switch(launchFormModel.getFormStatus()) {
                case 1:
                    // 修改为待重新上线
                    testProjectRecordModel.setOperatingContent("待重新上线...");
                    break;
                case 2:
                    // 修改为运维已接单，上线中状态
                    launchFormModel.setOperationsUser(userModel.getUserId());
                    launchFormModel.setOperationsUsername(userModel.getUsername());
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 运维已接单，上线中...");
                    break;
                case 3:
                    // 修改为上线完成，待验证状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 上线完成，待验证...");
                    break;
                case 4:
                    //修改为开发验证中状态
                    launchFormModel.setAcceptDevelopUser(userModel.getUserId());
                    launchFormModel.setAcceptDevelopUsername(userModel.getUsername());
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 开发验证中...");
                    break;
                case 5:
                    // 修改为开发验证未通过状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 开发验证未通过");
                    break;
                case 6:
                    // 修改为开发验证通过状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 开发验证通过");
                    break;
                case 7:
                    // 修改为测试验证中状态
                    launchFormModel.setAcceptTestUser(userModel.getUserId());
                    launchFormModel.setAcceptTestUsername(userModel.getUsername());
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 测试验证中...");
                    break;
                case 8:
                    // 修改为测试验证未通过状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 测试验证未通过");
                    break;
                case 9:
                    // 修改为测试验证通过状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 测试验证通过");
                    break;
                case 10:
                    // 修改为运维关闭工单状态，即完成状态
                    testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 已完成");
                    break;
                default:
                    testProjectRecordModel.setOperatingContent("修改了上线内容");
                    break;
            }
            // 调用service修改上线工单
            launchFormService.updateLaunchForm(launchFormModel);

            // 3.插入数据库
            testProjectRecordService.insertRecord(testProjectRecordModel);
            return this.success(MessageKeyEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    @PostMapping("/online-process/getTestForm")
    public RestModel getTestForm(Long projectId){
        try {
            List<TestFormWithBLOBsModel> testFormList = testFormService.selectSuccessTestForm(projectId);
            return this.success(testFormList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }
}