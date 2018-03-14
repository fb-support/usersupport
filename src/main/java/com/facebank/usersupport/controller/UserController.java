package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.reqDto.UserForm;
import com.facebank.usersupport.mapper.usersupport.usersupport.LoginUserMapper;
import com.facebank.usersupport.model.*;
import com.facebank.usersupport.service.ILoginUserService;
import com.facebank.usersupport.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户表Controller
 * 规范：继承base,
 * @author NingKui
 * @date 2018/3/9 10:40
 **/
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginUserService loginUserService;

    /**
     * 注册页面
     * @param userForm
     * @return
     */
    @PostMapping("/register")
    private RestModel register (UserForm userForm) {
        try {
            RestModel model = userService.insertUser(userForm);
            if (model.getCode().equals(RestModel.CODE_SUCCESS)) {
                return this.success(JSON.toJSONString(model));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
    }

    /**
     * 验证是否被注册
     * @param verityObj 验证数据:数据类型
     * objType 验证数据类型。1为工号，2为用户名。3为手机号码。4为邮箱
     * @return
     */
    @GetMapping("/sync/verity")
    private RestModel register(String verityObj, String objType) {
        try {
            UserModel userModel = new UserModel();
            // 根据验证数据类型设置对应的值
            switch (objType) {
                case "1":
                    userModel.setWorkNumber(Integer.parseInt(verityObj));
                    break;
                case "2":
                    userModel.setUsername(verityObj);
                    break;
                case "3":
                    userModel.setPhone(verityObj);
                    break;
                case "4":
                    userModel.setEmail(verityObj);
                    break;
            }
            List<UserModel> userModelList = userService.selectByUserModel(userModel);
            //若集合为空
            //若查询返回集合大小大于0，则代表用户已存在，
            return (userModelList != null && userModelList.size() > 0) ?
                this.excpRestModel(MessageKeyEnum.ERROR) :
                this.success(MessageKeyEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.excpRestModel(MessageKeyEnum.REST_SERVICE_ERROR);
    }

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

    /**
     * 进行用户字段校验
     * @param model
     * @return
     */
    @GetMapping("um/check")
    public RestModel CheckUserModel(UserModel model){
        try{
            UserCheckModel checkModel = new UserCheckModel();
            UserModel TempModel = new UserModel();
            //进行phone判断
            TempModel.setPhone(model.getPhone());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId())
                checkModel.setPhoneRepeat(true);
            //用户名判断
            TempModel.setPhone(null);
            TempModel.setUsername(model.getUsername());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId())
                checkModel.setUsernameRepeat(true);
            //邮箱判断
            TempModel.setUsername(null);
            TempModel.setEmail(model.getEmail());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId())
                checkModel.setEmailRepeat(true);
            //workNumber判断
            TempModel.setEmail(null);
            TempModel.setWorkNumber(model.getWorkNumber());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId())
                checkModel.setWorkNumberRepeat(true);

            return this.success(checkModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }


    /**
     * 安全退出方法
     */
    @PostMapping("/beforeLogoutForLog")
    public RestModel logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            //修改登录流水表，主要是添加登出时间
            LoginUserModel model = new LoginUserModel();
            model.setUsername(auth.getName());
            model.setLogoutTime(System.currentTimeMillis());
            loginUserService.updateLoginOutTime(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return this.success(MessageKeyEnum.SUCCESS);
    }
}
