package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserMainModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 * 参考类
 * @author hailong.Yang
 * @create 2018-03-05 下午3:32
 **/

@RestController
public class UserSupportController extends BaseController {

    @Autowired
    private IUserMainService userMainService;


    // 注意：根据相关业务模块，url的第一个就写什么。格式为  /业务模块/需求
    @GetMapping("/user/getUserMainByUserId")
    public RestModel getUserMainByUserId(@RequestBody UserModel user){
        UserMainModel model = userMainService.getUserMainByUserId(123456L);
        /** 返回值规范：使用下一行格式。若是成功，则为this.success(data),失败的话，对应想要的
         * 错误类型传回，比如是系统或网路异常错误。那就返回：this.this.excpRestModel(MessageKeyEnum.UNKNOW);
         */
        return this.success(JSON.toJSONString(model));
    }


    @PostMapping("/user/postUserMainByUserId，")
    public RestModel getUserMainByUserId(@RequestBody UserForm userForm){
        /**
         * 注意：controller层所有代码都要try-catch包裹着。这是为了用户友好
         */
        try{
            UserMainModel model = userMainService.getUserMainByUserId(123456L);
            return this.success(JSON.toJSONString(model));
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }

    }


//
//    @GetMapping("/private/getUserMainByMobile")
//    public RestModel getUserMainByMobile(){
//        UserMainModel model = userMainService.getUserMainByMobile("18515570827",1);
//        return this.success(JSON.toJSONString(model));
//    }
//
//
//    @GetMapping("/log/usertest/getP2PUserMainByUserId")
//    public RestModel getP2PUserMainByUserId(){
//        UserMainModel model = userMainService.getUserMainByUserId(1L);
//        return this.success(JSON.toJSONString(model));
//    }

}
