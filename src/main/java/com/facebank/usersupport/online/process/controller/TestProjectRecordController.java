package com.facebank.usersupport.online.process.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.online.process.controller.base.BaseController;
import com.facebank.usersupport.online.process.model.TestProjectRecordModel;
import com.facebank.usersupport.online.process.service.ITestProjectRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestProjectRecordController extends BaseController {

    @Autowired
    ITestProjectRecordService testProjectRecordService;


    /**
     * 分页查询
     * @param length 单页查询数量
     * @param start 页数
     * @return
     */
    @GetMapping("/online-process/selectByPage")
    public RestModel getRecordListByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                         @RequestParam(required = false, defaultValue = "10") int length,
                                         String draw,
                                         TestProjectRecordModel testProjectRecordModel){
        try {
            int pageNo = start / length + 1;
            PageInfo pageInfo = testProjectRecordService.selectByPage(length, pageNo, testProjectRecordModel);
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

//    @GetMapping("/rd/insert")
//    public RestModel insertRecord (TestProjectRecordModel testProjectRecordModel){
//        try {
//            RestModel model = testProjectRecordService.insertRecord(testProjectRecordModel);
//            if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
//                return this.success(JSON.toJSONString(model));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
//
//    }

}
