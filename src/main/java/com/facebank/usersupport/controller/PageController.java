package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserMainModel;
import com.facebank.usersupport.service.IUserMainService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "/permissionManagerment/"+pageName;
    }

    @RequestMapping("/um/{pageName}")
    public String showUserPage(@PathVariable String pageName){
        return "/userManagerment/"+pageName;
    }

    @RequestMapping("/service/{pageName}")
    public String showServicePage(@PathVariable String pageName){
        return "/serviceManagerment/"+pageName;
    }

    @RequestMapping("/sc/{pageName}")
    public String showSystemConfigPage(@PathVariable String pageName){
        return "/systemConfig/"+pageName;
    }
}
