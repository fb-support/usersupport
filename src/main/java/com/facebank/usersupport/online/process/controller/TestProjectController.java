package com.facebank.usersupport.online.process.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.controller.base.BaseController;
import com.facebank.usersupport.online.process.model.TestProjectModel;
import com.facebank.usersupport.online.process.service.ITestProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提测模块项目管理Controller
 * @author NingKui
 * @date 2018/3/27 15:35
 **/
@RestController
public class TestProjectController extends BaseController {


    @Autowired
    private ITestProjectService testProjectService;

    /**
     * 分页查询
     * @param length 单页查询数量
     * @param start 页数
     * @return
     */
    @GetMapping("/online-process/getProjectsByPage")
    public RestModel getUserListByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                       @RequestParam(required = false, defaultValue = "10") int length,
                                       String draw,
                                       TestProjectModel testProjectModel) {
        try{
            System.out.println("进入/online-process/getProjectsByPage");
            int pageNo = start / length + 1;
            PageInfo pageInfo =testProjectService.selectByPage(length, pageNo, testProjectModel);
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
}
