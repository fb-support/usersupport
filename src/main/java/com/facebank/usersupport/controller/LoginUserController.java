package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.*;
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
     * @return
     */
    @GetMapping("/ul/getUserByPage")
    public RestModel getUserListByPage(int pageSize,int pageNumber,
                                       LoginUserModel loginUserModel) {
        try{
            PageInfo pageInfo =loginUserService.selectByPage(pageSize, pageNumber, loginUserModel);
            PageBeanModel pageBeanModel = new PageBeanModel();
            pageBeanModel.setData(pageInfo.getList());
            pageBeanModel.setPage(pageInfo.getPageNum());
            pageBeanModel.setPageSize(pageInfo.getPageSize());
            pageBeanModel.setTotalCount(pageInfo.getTotal());
            pageBeanModel.setTotalPage(pageInfo.getPages());
            return this.success(pageBeanModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
