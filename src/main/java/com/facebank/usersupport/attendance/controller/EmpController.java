package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.model.EmpModel;
import com.facebank.usersupport.attendance.service.IEmpService;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工管理Controller
 * @author HuBiao
 * @date 2018/4/4 0004 15:48
 **/
@RestController
public class EmpController extends BaseController {

    @Autowired
    private IEmpService empService;

    @GetMapping("/emp/getEmpListByDeptNumber")
    public RestModel getEmpListByDeptNumber(Integer deptNumber){
        try {
            List<EmpModel> list = empService.getEmpListByDeptNumber(deptNumber);
            return this.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
