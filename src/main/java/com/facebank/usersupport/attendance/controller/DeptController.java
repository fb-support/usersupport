package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.model.DeptModel;
import com.facebank.usersupport.attendance.service.IDeptService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HuBiao
 * @date 2018/4/4 0004 15:23
 **/
@RestController
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @RequestMapping("/dept/getAllDept")
    public RestModel getAllDept(){
        try {
            List<DeptModel> depts = deptService.getAllDept();
            return this.success(depts);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
