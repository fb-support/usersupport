package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author NingKui
 * @date 2018/3/7 10:35
 **/
@Controller
public class PageController extends BaseController {
    @RequestMapping("/{pageName}")
    public String showBasePage(@PathVariable String pageName){
        return pageName;
    }

    /**
     * 访问相对应模块的页面，permision为一级文件夹名称
     * @param pageName
     * @return
     */
    @RequestMapping("/pm/{pageName}")
    public String showPermissionPage(@PathVariable String pageName){
        return "permissionManagerment/"+pageName;
    }

    @RequestMapping("/money/{pageName}")
    public String showMoneyPage(@PathVariable String pageName){
        return "logManagement/"+pageName;
    }

    @RequestMapping("/log/{pageName}")
    public String showLogPage(@PathVariable String pageName){
        return "logManagement/"+pageName;
    }

    @RequestMapping("/role/{pageName}")
    public String showRoleMenuPage(@PathVariable String pageName){
        return "permissionManagerment/"+pageName;
    }

    @RequestMapping("/menu/{pageName}")
    public String showRoleMenuPage1(@PathVariable String pageName){
        return "permissionManagerment/"+pageName;
    }

    @RequestMapping("/um/{pageName}")
    public String showUserPage(@PathVariable String pageName){
        return "userManagerment/"+pageName;
    }

    @RequestMapping("/service/{pageName}")
    public String showServicePage(@PathVariable String pageName){
        return "serviceManagerment/"+pageName;
    }

    @RequestMapping("/sc/{pageName}")
    public String showSystemConfigPage(@PathVariable String pageName){
        return "systemConfig/"+pageName;
    }

    @RequestMapping("/ul/{pageName}")
    public String showUserLoginPage(@PathVariable String pageName){
        return "userLoginManagerment/"+pageName;
    }

    @RequestMapping("/customer/{pageName}")
    public String showCustomerPage(@PathVariable String pageName){
        return "customerService/"+pageName;
    }

}
