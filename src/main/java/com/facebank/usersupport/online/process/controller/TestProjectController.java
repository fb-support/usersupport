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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * 创建新项目
     * @param model 封装表单中项目的基本信息
     * @param developPeople  开发人员
     * @param testPeople    测试人员
     * @param operationsPeople  运维人员
     * @param str_beginTime    起始时间
     * @param str_endTime  结束时间
     * @return
     */
    @GetMapping("/online-process/newTestProject")
    public RestModel newTestProject(TestProjectModel model,
                                    String developPeople,
                                    String testPeople,
                                    String operationsPeople,
                                    String str_beginTime,
                                    String str_endTime) {

        //规范数据
        String[] developPeopleIds = developPeople.split(",");
        String[] testPeopleIds = testPeople.split(",");
        String[] operationsPeopleIds = operationsPeople.split(",");

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(str_beginTime);
            // date类型转成long类型
            long beginTime = beginDate.getTime();
            model.setBeginTime(beginTime);

            Date endDate = formatter.parse(str_endTime);
            // date类型转成long类型
            long endTime = endDate.getTime();
            model.setEndTime(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i =  testProjectService.insertProject(model, developPeopleIds, testPeopleIds, operationsPeopleIds);
        return this.success(null);
    }
}
