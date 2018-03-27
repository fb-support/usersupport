package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IMenuService;
import com.facebank.usersupport.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MenuController extends BaseController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    IUserService userService;
    /**
     * 根据用户名找到对应菜单
     * @param username
     * @return
     */
    @RequestMapping("/test1")
    public RestModel get(@RequestParam("username") String username){
        try {
            return this.success(menuService.queryMenuByName(username));
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 根据用户Id 找到对应菜单
     * @param userId
     * @return
     */
    @RequestMapping("/test2")
    public RestModel gets(@RequestParam("userId") Long userId ){
        try {
            return this.success(menuService.queryMenuByUserId(userId));
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }

    }

    /**
     * 找到所有菜单
     * @return
     */
    @RequestMapping("/menu/findAllMenu")
    public RestModel findAllMenu() {
        try {
            return menuService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 根据字段查询
     * @param menuName
     * @param menuUrl
     * @param status
     * @return
     */
    @GetMapping("/menu/getMenu")
    public RestModel getMenu(String menuName, String menuUrl, Short status) {
        try {
            List<MenuModel> menuModel = menuService.getMenu(menuName,menuUrl,status);
            return this.success(menuModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 更新菜单信息
     * @param menuModel
     * @return
     */
    @RequestMapping("/menu/updateByPrimaryKey")
    public RestModel updateMenu(MenuModel menuModel) {
        try{
            Date date = new Date();
            Long modifytime = date.getTime();
            menuModel.setGmtModify(modifytime);
            System.out.println(menuModel);
            menuService.updateMenu(menuModel);
            return this.success(menuModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 增加新菜单
     * @param menuModel
     * @return
     */
    @RequestMapping("/menu/insertSelective")
    public RestModel insertMenu(MenuModel menuModel) {
        try {
            Date date = new Date();
            Long createtime = date.getTime();
            menuModel.setCreator(userService.getActiveUserId());
            menuModel.setGmtCreate(createtime);
            menuService.insertMenu(menuModel);
            return this.success(menuModel);
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @RequestMapping("/menu/deleteMenu")
    public RestModel deleteMenuById(Long menuId) {
        try {
            menuService.deleteByMenuIds(menuId);
            return this.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.UNKNOW);
        }
    }
    /**
     * 根据menuId 获取菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/menu/selectById")
    public RestModel selectById(Long menuId) {
        System.out.println(menuId);
        try {
            MenuModel model = menuService.selectById(menuId);
            return this.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }
    }
}
