package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.model.PageRestModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.UserModel;
import com.facebank.usersupport.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户表Controller.'
 * 规范：继承base,
 * @author NingKui
 * @date 2018/3/9 10:40
 **/
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;


    /**
     * 注册页面
     * @param userForm
     * @return
     */
    @PostMapping("/register")
    public RestModel register (UserForm userForm) {
        try {
            RestModel model = userService.insertUser(userForm);
            if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
                return this.success(JSON.toJSONString(model));
            } else {
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证是否被注册
     * @param verityObj 验证数据:数据类型
     * objType 验证数据类型。1为工号，2为用户名。3为手机号码。4为邮箱
     * @return
     */
    //@PostMapping("/sync/verity")
//    public RestModel verity(String verityObj, String objType) {
//        try {
//            UserModel userModel = new UserModel();
//            // 根据验证数据类型设置对应的值
//            switch (objType) {
//                case "1":
//                    System.out.println("验证工号唯一");
//                    userModel.setWorkNumber(Integer.parseInt(verityObj));
//                    break;
//                case "2":
//                    userModel.setUsername(verityObj);
//                    break;
//                case "3":
//                    userModel.setPhone(verityObj);
//                    break;
//                case "4":
//                    userModel.setEmail(verityObj);
//                    break;
//            }
//            List<UserModel> userModelList = userService.selectByUserModel(userModel);
//            //若集合为空
//            //若查询返回集合大小大于0，则代表用户已存在，
//            return (userModelList != null && userModelList.size() > 0) ?
//                this.excpRestModel(MessageKeyEnum.USER_EXIST) :
//                this.success(MessageKeyEnum.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return this.excpRestModel(MessageKeyEnum.REST_SERVICE_ERROR);
//    }

    /**
     * 分页查询
     * @param length 单页查询数量
     * @param start 页数
     * @return
     */
    @GetMapping("/um/getUserByPage")
    public RestModel getUserListByPage( @RequestParam(required = false, defaultValue = "1") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        String draw,UserModel model){
        try {
            int pageNo = start / length + 1;
            PageInfo pageInfo = userService.selectByPage(length, pageNo, model);
            PageRestModel pageRestModel = new PageRestModel(
                    draw,
                    pageInfo.getTotal(),
                    pageInfo.getTotal(),
                    pageInfo.getList()
            );
            return this.success(pageRestModel);
        }
        catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 批量删除用户
     * @param id id数组
     * @return
     */
    @PostMapping("um/deleteUserByIds")
    public RestModel deleteUserByIds(@RequestParam(value = "id[]") Integer[] id){
        try{
            userService.deleteByUserIds(id);
            return this.success(null);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

}
