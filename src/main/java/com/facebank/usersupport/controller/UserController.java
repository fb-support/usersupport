package com.facebank.usersupport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
     * 注册方法
     * @param userForm
     * @return
     */
    @PostMapping("/register")
    public RestModel register (UserForm userForm) {
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
     * 验证字段是否被注册
     * @param verityObj 验证数据:数据类型
     * objType 验证数据类型。1为工号，2为用户名。3为手机号码。4为邮箱
     * @return
     */
    @PostMapping("/sync/verity")
    public RestModel register(String verityObj, String objType) {
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
                    default:
            }
            List<UserModel> userModelList = userService.selectByUserModel(userModel);
            // 若集合为空
            // 若查询返回集合大小大于0，则代表用户已存在，
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
                                        String draw, UserModel model){
        try {
            int pageNo = start / length + 1;
            //判断状态并规范
            if(model.getStatus() == -1) {
                model.setStatus(null);
            }
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
            return this.success(MessageKeyEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 批量禁止用户
     * @param id id数组
     * @return
     */
    @PostMapping("um/banUserByIds")
    public RestModel banUserByIds(@RequestParam(value = "id[]") Integer[] id){
        try{
            userService.banByUserIds(id);
            return this.success(MessageKeyEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 批量启用用户
     * @param id id数组
     * @return
     */
    @PostMapping("um/enableUserByIds")
    public RestModel enableUserByIds(@RequestParam(value = "id[]") Integer[] id){
        try{
            userService.enableUserByIds(id);
            return this.success(MessageKeyEnum.SUCCESS);
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
            // 进行phone判断
            TempModel.setPhone(model.getPhone());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId()) {
                checkModel.setPhoneRepeat(true);
            }
            // 用户名判断
            TempModel.setPhone(null);
            TempModel.setUsername(model.getUsername());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId()) {
                checkModel.setUsernameRepeat(true);
            }
            // 邮箱判断
            TempModel.setUsername(null);
            TempModel.setEmail(model.getEmail());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId())
                checkModel.setEmailRepeat(true);
            // workNumber判断
            TempModel.setEmail(null);
            TempModel.setWorkNumber(model.getWorkNumber());
            if (!userService.selectByUserModel(TempModel).isEmpty()
                    &&userService.selectByUserModel(TempModel).get(0).getUserId()!=model.getUserId()) {
                checkModel.setWorkNumberRepeat(true);
            }
            return this.success(checkModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNCHECK_REQUEST_ERROR);
        }
    }

    /**
     * 根据用户ID获取信息
     * @param
     * @return
     */
    @GetMapping("/um/getByUserId")
    public RestModel getByUserId(@RequestParam Long userId) {
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
    @PostMapping("/um/updateBaseInfoMationById")
    public RestModel updateBaseInfoMationById(UserModel model) {
        try {
            model.setGmtModify(System.currentTimeMillis());
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

    /**
     * 安全退出时添加日志的方法
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/beforeLogoutForLog")
    public RestModel logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            // 修改登录流水表，主要是添加登出时间
            LoginUserModel loginUserModel = new LoginUserModel();
            loginUserModel.setUsername(auth.getName());
            loginUserModel.setLogoutTime(System.currentTimeMillis());
            loginUserService.updateLoginOutTime(loginUserModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return this.success(MessageKeyEnum.SUCCESS);
    }

    /**
     * 验证 ---登录前的验证码问题
     * @param verityCode
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/verity/beforeLoginForVerityCode")
    public RestModel login(String verityCode,HttpServletRequest request, HttpServletResponse response) {
        // 获得存在session的验证码
        String session_VerityCode = (String)request.getSession().getAttribute("strCode");

        if (session_VerityCode != null &&
                verityCode != null &&
                session_VerityCode.trim().equals(verityCode.trim())) {
            return this.success(MessageKeyEnum.SUCCESS);
        }
        return this.excpRestModel(MessageKeyEnum.ERROR);
    }
}
