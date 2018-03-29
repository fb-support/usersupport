package com.facebank.usersupport.online.process.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.model.TestFormWithBLOBsModel;
import com.facebank.usersupport.online.process.service.ITestFormService;
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

    /**
     * 新建测试工单
     *
     * @param model
     * @return
     */
    @PostMapping("tf/insertTestForm")
    public RestModel insertForm(TestFormWithBLOBsModel model) {
        try {
            //TODO ①获取当前操作人id，username保存到创建人员 ②插入流水表
            model.setCreateUser(2L);
            model.setCreateUsername("冯宝宝");
            model.setFormStatus(0);
            int i = testFormService.insertForm(model);
            if (i > 0)
                return this.success(MessageKeyEnum.SUCCESS);
            else
                return this.excpRestModel(MessageKeyEnum.ERROR);
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
        //TODO ②插入流水表
        try {
            int i = testFormService.updateTestForm(model);
            if (i > 0)
                return this.success(MessageKeyEnum.SUCCESS);
            else
                return this.excpRestModel(MessageKeyEnum.ERROR);
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
     * @param formService
     * @return
     */
    @GetMapping("/tf/getFormByPage")
    public RestModel getUserListByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                       @RequestParam(required = false, defaultValue = "10") int length,
                                       String draw,
                                       String formService,
                                       @RequestParam(required = true) Integer formStatus){
        try {
            int pageNo = start / length + 1;
            if (formStatus == -1) formStatus = null;
            PageInfo pageInfo = testFormService.selectByPage(length, pageNo, formService,formStatus);
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
     * @param formId
     * @param formStatus
     * @return
     */
    @PostMapping("tf/updateTestFormStatus")
    public RestModel updateFormStatus(Long formId, Integer formStatus) {
        //TODO ①判断当前用户是否是测试人员 ②获取当前用户id username 保存到接单人员 ②插入流水表
        try {
            int i = testFormService.updateTestFormStatus(formId,formStatus);
            if (i > 0)
                return this.success(MessageKeyEnum.SUCCESS);
            else
                return this.excpRestModel(MessageKeyEnum.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }

    }
}