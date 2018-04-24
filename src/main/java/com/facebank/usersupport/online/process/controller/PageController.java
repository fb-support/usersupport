package com.facebank.usersupport.online.process.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 提测模块页面跳转Controller
 * @author NingKui
 * @date 2018/3/26 17:57
 **/
@Controller(value = "online-process-controller-page")
public class PageController {

    /**
     * 跳转到项目页面
     * @param pageName
     * @return
     */
    @RequestMapping("/online-process/{pageName}")
    public String showPermissionPage(@PathVariable String pageName){
        return "/online-process/"+pageName;
    }

}
