package com.facebank.usersupport.online.process.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.online.process.model.TestFormModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import com.facebank.usersupport.online.process.service.ITestFormService;
import com.facebank.usersupport.online.process.service.ITestProjectRecordService;
import com.facebank.usersupport.online.process.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : ChinaLHR
 * @Date : Create in 16:17 2018/3/27
 * @Email : 13435500980@163.com
 * <p>
 * 测试工单Controller
 */
@RestController
public class TestFormController extends BaseController {

    @Autowired
    private ITestFormService testFormService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITestProjectRecordService testProjectRecordService;

    /**
     * 新建测试工单
     *
     * @param model
     * @return
     */
    @PostMapping("tf/insertTestForm")
    public RestModel insertForm(TestFormWithBLOBsModel model) {
        try {
            // 1. 获取当前登录用户
            UserModel userModel = userService.getActiveUser();
            // 将当前登录用户赋值到要插入的model中
            model.setCreateUser(userModel.getUserId());
            model.setCreateUsername(userModel.getUsername());
            model.setFormStatus(0);
            // 插入数据库
            Long formId = testFormService.insertForm(model);

            if (formId != null) {
                //记录当次操作的流水
                // 2.封装操作流水model
                TestProjectRecordModel testProjectRecordModel = new TestProjectRecordModel();
                testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
                testProjectRecordModel.setGmtModify(System.currentTimeMillis());
                testProjectRecordModel.setFormType(1);
                testProjectRecordModel.setFormId(formId);
                testProjectRecordModel.setOperatingPeople(userModel.getUsername());
                testProjectRecordModel.setOperatingPeopleId(userModel.getUserId());
                testProjectRecordModel.setProjectId(model.getProjectId());
                testProjectRecordModel.setOperatingContent("创建了新的测试工单。测试工单编号为：" + model.getFormId() +
                        ",测试工单服务名称："+model.getFormService());
                // 3.插入数据库
                testProjectRecordService.insertRecord(testProjectRecordModel);
                return this.success(MessageKeyEnum.SUCCESS);
            } else {
                return this.excpRestModel(MessageKeyEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }

    }

    /**
     * 修改测试工单
     * @param model
     * @return
     */
    @PostMapping("tf/updateTestForm")
    public RestModel updateForm(TestFormWithBLOBsModel model) {

        try {
            int i = testFormService.updateTestForm(model);
            if (i > 0) {

                // 1. 获取当前登录用户
                UserModel userModel = userService.getActiveUser();
                //记录当次操作的流水
                // 2.封装操作流水model
                TestProjectRecordModel testProjectRecordModel = new TestProjectRecordModel();
                testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
                testProjectRecordModel.setGmtModify(System.currentTimeMillis());
                testProjectRecordModel.setFormType(1);
                testProjectRecordModel.setFormId(model.getFormId());
                testProjectRecordModel.setOperatingPeople(userModel.getUsername());
                testProjectRecordModel.setOperatingPeopleId(userModel.getUserId());
                testProjectRecordModel.setProjectId(model.getProjectId());
                testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 修改了工单。");
                // 3.插入数据库
                testProjectRecordService.insertRecord(testProjectRecordModel);
                return this.success(MessageKeyEnum.SUCCESS);
            } else {
                return this.excpRestModel(MessageKeyEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }

    }

    /**
     * 通过Id查询工单信息
     * @return
     */
    @GetMapping("tf/getByFormId")
    public RestModel getByFormId(@RequestParam Long formId){
        try {
            TestFormWithBLOBsModel model = testFormService.selectByFormId(formId);
            return this.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param testFormModel
     * @return
     */
    @GetMapping("/tf/getFormByPage")
    public RestModel getUserListByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                       @RequestParam(required = false, defaultValue = "10") int length,
                                       String draw,
                                       TestFormModel testFormModel){
        try {
            int pageNo = start / length + 1;
            if (testFormModel.getFormStatus()!=null && testFormModel.getFormStatus() == -1) {
                testFormModel.setFormStatus(null);
            }
            PageInfo pageInfo = testFormService.selectByPage(length, pageNo, testFormModel);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );

            return this.success(pageRestModel);
        }
        catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 修改工单状态
     * @param testFormModel
     * @return
     */
    @PostMapping("tf/updateTestFormStatus")
    public RestModel updateFormStatus(TestFormModel testFormModel) {
        //TODO ①判断当前用户是否是测试人员
        try {
            // 1. 获取当前登录用户
            UserModel userModel = userService.getActiveUser();
            if(testFormModel.getFormStatus() == 1) {
                // 封装数据对象
                testFormModel.setAcceptUser(userModel.getUserId());
                testFormModel.setAcceptUsername(userModel.getUsername());
            }
            // 调用service
            int i = testFormService.updateTestFormStatus(testFormModel);
            if (i > 0) {
                //记录当次修改状态操作的流水
                // 2.封装操作流水model
                TestProjectRecordModel testProjectRecordModel = new TestProjectRecordModel();
                testProjectRecordModel.setGmtCreate(System.currentTimeMillis());
                testProjectRecordModel.setGmtModify(System.currentTimeMillis());
                testProjectRecordModel.setFormType(1);
                testProjectRecordModel.setFormId(testFormModel.getFormId());
                testProjectRecordModel.setOperatingPeople(userModel.getUsername());
                testProjectRecordModel.setOperatingPeopleId(userModel.getUserId());
                testProjectRecordModel.setProjectId(testFormModel.getProjectId());
                switch(testFormModel.getFormStatus()) {
                    case 1:
                        testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 接受了测试工单");
                        break;
                    case 2:
                        testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 标记该测试工单为未通过。");
                        break;
                    case 3:
                        testProjectRecordModel.setOperatingContent(userModel.getUsername()+" 标记该测试工单为已通过");
                        break;
                        default:
                            testProjectRecordModel.setOperatingContent(" 修改了测试工单状态。");
                            break;
                }

                // 3.插入数据库
                testProjectRecordService.insertRecord(testProjectRecordModel);

                return this.success(MessageKeyEnum.SUCCESS);
            } else {
                return this.excpRestModel(MessageKeyEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }

    }
}