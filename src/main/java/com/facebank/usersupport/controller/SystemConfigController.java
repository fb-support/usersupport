package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统设置Controller
 * @author NingKui
 * @date 2018/3/9 10:42
 **/
@RestController
public class SystemConfigController extends BaseController {

    @Autowired
    IUserService userService;

    @GetMapping("/sc/getByUserId")
    public RestModel getByUserId(Long userId) {
        try{
            UserModel model = userService.getByUserId(userId);
            return this.success(JSONObject.parseObject(JSON.toJSONString(model)));
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }
    }

    /**
     * 根据id更新用户信息
     *
     * @param model
     * @return
     */
    @PostMapping("/sc/updateBaseInfoMationById")
    public RestModel updateBaseInfoMationById(UserModel model) {
        System.out.println(model.toString());
        try {
            int status = userService.updateBaseInfoMationById(model);
            if (status > 0) {
                return this.success(MessageKeyEnum.SUCCESS);
            } else {
                return this.excpRestModel(MessageKeyEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }
    }

    @PostMapping("/sc/updatePasswordById")
    public RestModel updatePasswordById(UserModel model) {
        System.out.println(model.toString());
        try {
            int status = userService.updatePasswordById(model);
            if (status > 0) {
                return this.success(MessageKeyEnum.SUCCESS);
            } else {
                return this.excpRestModel(MessageKeyEnum.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }
    }

}
