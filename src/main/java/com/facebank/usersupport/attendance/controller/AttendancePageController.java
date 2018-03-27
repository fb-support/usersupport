package com.facebank.usersupport.attendance.controller;

import com.facebank.usersupport.attendance.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HuBiao
 * @date 2018/3/27 0027 9:16
 **/
@Controller
public class AttendancePageController extends BaseController {

    @RequestMapping("/attendance/{pageName}")
    public String showPage(@PathVariable String pageName){
        return "/attendance/" + pageName;
    }

}
