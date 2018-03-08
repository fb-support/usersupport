package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserMainModel;
import com.facebank.usersupport.service.IUserMainService;
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
    @RequestMapping("/v1.0/{pageName}")
    public String getUserMainByUserId(@PathVariable String pageName){
        return pageName;
    }
}
