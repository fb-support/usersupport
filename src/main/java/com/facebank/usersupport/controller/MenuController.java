package com.facebank.usersupport.controller;

import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.service.IMenuService;
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
    @RequestMapping("/test1")
    public RestModel get(@RequestParam("username") String username){
        return this.success(menuService.queryMenuByName(username));
    }
    @RequestMapping("/test2")
    public RestModel gets(@RequestParam("userId") Long userId ){
        return this.success(menuService.queryMenuByUserId(userId));
    }

    @RequestMapping("menu/findAllMenu")
    public RestModel findAllMenu() { return menuService.findAll(); }
    @GetMapping("menu/getMenu")
    public RestModel getMenu(String menuName, String menuUrl, Short status) {
        List<MenuModel> menuModel = menuService.getMenu(menuName,menuUrl,status);
        return this.success(menuModel);
        //return this.success(menuModel);
    }
    @RequestMapping("menu/updateByPrimaryKey")
    public RestModel updateMenu(MenuModel menuModel) {
        Date date = new Date();
        Long modifytime = date.getTime();
        menuModel.setGmtModify(modifytime);
        /*Long id = 11L;
        menuModel.setMenuId(id);
        menuModel.setMenuName("test13333");*/
        menuService.updateMenu(menuModel);
        return this.success(menuModel);
    }
    @RequestMapping("menu/insertSelective")
    public RestModel insertMenu(MenuModel menuModel) {

        Date date = new Date();
        Long createtime = date.getTime();
        /*Short s = 1;
        Long id = 1L;
        menuModel.setMenuName("test123");
        menuModel.setMenuUrl("/menu");
        menuModel.setStatus(s);
        menuModel.setCreator(id);*/
        menuModel.setGmtCreate(createtime);

        menuService.insertMenu(menuModel);
        return this.success(menuModel);
    }
    @RequestMapping("menu/deleteMenu")
    public void deleteMenuById(Long id) {
        id = 10L;
        menuService.deleteByMenuIds(id);
    }
    @GetMapping("/menu/selectById")
    public RestModel selectById(Long menuId) {
        try {
            MenuModel model = menuService.selectById(menuId);
            return this.success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }

    }
}
