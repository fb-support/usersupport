package com.facebank.usersupport.online.process.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.online.process.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NingKui
 * @date 2018/4/24 13:51
 **/
@RestController(value = "online-process-controller-user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 查询所有用户（指定技术部）集合
     * @return
     */
    @GetMapping("/online-process/getUserByQuery")
    public RestModel getUserListByQuery(String query){
        try {
            List<UserModel> list = userService.getUserForOnlineProcess(query);
            return this.success(list);
        }
        catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }
}
