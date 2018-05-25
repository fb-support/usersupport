package com.facebank.usersupport.controller;


import com.facebank.usersupport.common.MessageKeyEnum;
import com.facebank.usersupport.controller.base.BaseController;
import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.model.PageBeanModel;
import com.facebank.usersupport.model.RestModel;
import com.facebank.usersupport.model.RoleMenuModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.service.IRoleService;
import com.facebank.usersupport.service.IUserRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理controller
 * Created by yaozun on 2018/3/9.
 */
@RestController
public class RoleController extends BaseController {
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserRoleService userRoleService;


    /**
     * 功能描述: 新增角色
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/addRole")
    public RestModel insertRole(RoleModel role) {
        //TODO 获取session当前用户id
        role.setCreator(123456L);
        return iRoleService.insertRole(role);
    }

    /**
     * 功能描述: 查看全部角色
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/findAll")
    public RestModel findAll(RoleModel role) {
        return iRoleService.findAllRole();
    }

    /**
     * 功能描述: 通过id查找用户
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/findRoleById")
    public RestModel findRoleById(Long id) {
        return iRoleService.findRoleById(id);
    }

    /**
     * 功能描述: 修改角色状态
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/updateStatus")
    public RestModel findRoleById(Short status, Long id) {
        return iRoleService.updateStatus(status, id);
    }
    /**
     * 功能描述:角色增加菜单
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/addMenuToRole")
    public RestModel addMenuToRole(RoleMenuModel roleMenuModel) {
        return iRoleService.insertMenuByRole(roleMenuModel);
    }
    /**
     * 功能描述:角色移除菜单
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/removeMenuToRole")
    public RestModel removeMenuToRole(Long id) {
        return iRoleService.deleteMenuByRole(id);
    }
    /**
     * 功能描述: 显示角色已有权限
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/findMenuAlready")
    public RestModel findMenuAlready(Long id) {
        return iRoleService.findMenuAlready(id);
    }
      /**
       * 功能描述:查找所有用户
       * @param:
       * @return:
       * @auther: yaozun
       * @date:
       */
    @PostMapping("/role/findAllUser")
    public RestModel findAllUser(UserRoleDO userRoleDO){
        return userRoleService.findAllUser(userRoleDO);
    }
    /**
     * 功能描述:查找所有用户分页
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @PostMapping("/role/findUserByPage")
    public RestModel findUserByPage(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                    @RequestParam(required = false, defaultValue = "10") int length, UserRoleDO model){
        try {
            System.out.println(pageNo);
            PageInfo pageInfo = userRoleService.selectByPage(length, pageNo, model);
            PageBeanModel pageBeanModel = new PageBeanModel();
            pageBeanModel.setData(pageInfo.getList());
            pageBeanModel.setPage(pageInfo.getPageNum());
            pageBeanModel.setPageSize(pageInfo.getPageSize());
            pageBeanModel.setTotalCount(pageInfo.getTotal());
            pageBeanModel.setTotalPage(pageInfo.getPages());
            return this.success(pageBeanModel);
        } catch (Exception e) {
            e.printStackTrace();
            return this.excpRestModel(MessageKeyEnum.ERROR);
        }

    }
    /**
     * 功能描述: 查找所有角色
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/findAllRole")
    public RestModel findAllRole(RoleModel roleModel){
        return userRoleService.findAllRole(roleModel);
    }
    /**
     * 功能描述: 查找已有角色
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @PostMapping("/role/findRoleBegin")
    public RestModel findRoleBegin(Long userId){
        return userRoleService.findRoleBegin(userId);
    }
     /**
      * 功能描述: 更新角色
      * @param:
      * @return:
      * @auther: yaozun
      * @date:
      */
    @RequestMapping("/role/updateRole")
    public RestModel updateRole(@RequestParam(value ="ids[]") Long[] ids, Long id) {
        return userRoleService.updateRole(ids, id);
    }
    /**
     * 功能描述:更新菜单
     * @param:
     * @return:
     * @auther: yaozun
     * @date:
     */
    @RequestMapping("/role/updataMenu")
    public RestModel updataMenu(@RequestParam(value = "ids[]") Long[] ids, Long id) {
        for(Long i: ids){
            System.out.println(i);
        }
        System.out.println(id);
        return iRoleService.updataMenu(ids,id);
    }
}
