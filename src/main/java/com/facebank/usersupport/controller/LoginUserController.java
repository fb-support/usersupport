package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.ILoginUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录流水Controller
 * @author hanrong
 * @date 2018/3/15
 */
@RestController
public class LoginUserController extends BaseController {

    @Autowired
    private ILoginUserService loginUserService;
	
    /**
     * 分页查询
     * @param length 单页查询数量
     * @param start 页数
     * @return
     */
    @GetMapping("/ul/getUserByPage")
    public RestModel getUserListByPage(@RequestParam(required = false, defaultValue = "1") int start,
                                       @RequestParam(required = false, defaultValue = "10") int length,
                                       String draw,
                                       LoginUserModel loginUserModel) {
        try{
            int pageNo = start / length + 1;
            PageInfo pageInfo =loginUserService.selectByPage(length, pageNo, loginUserModel);
            PageRestModel pageRestModel = new PageRestModel(draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
