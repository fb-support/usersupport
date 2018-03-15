package com.facebank.usersupport.controller;

import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MenuController extends BaseController {
    @Autowired
    private IMenuService menuService;
    @RequestMapping("/test1")
    public RestModel get(@RequestParam("username") String username){
        return this.success(menuService.queryMenuByName(username));
    }
    @RequestMapping("/test2")
    public RestModel gets(@RequestParam("userId") Long userId ){
        return this.success(menuService.queryMenuByUserId(userId));
    }
}
