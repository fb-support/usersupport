package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserMainModel;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 权限Controller
 * @author NingKui
 * @date 2018/3/9 10:41
 **/
public class PermisionController extends BaseController {

    @GetMapping("/private/getUserMainByMobile")
    public RestModel getUserMainByMobile(){
        /**
         * 注意：controller层所有代码都要try-catch包裹着。这是为了用户友好
         */
        //try {
            //UserMainModel model = userMainService.getUserMainByMobile("18515570827",1);
            //return this.success(JSON.toJSONString(model));
        //} catch (Exception e) {
            //e.printStackTrace();
            //return this.excpRestModel(MessageKeyEnum.UNKNOW);
        //}
        /** 返回值规范：使用下一行格式。若是成功，则为this.success(data),失败的话，对应想要的
         * 错误类型传回，比如是系统或网路异常错误。那就返回：this.this.excpRestModel(MessageKeyEnum.UNKNOW);
         */
        //return this.success(JSON.toJSONString(model));
        //return this.excpRestModel(MessageKeyEnum.UNKNOW);
        return null;
    }
}
