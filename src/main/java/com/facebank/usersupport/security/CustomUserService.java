package com.facebank.usersupport.security;

import com.facebank.usersupport.mapper.usersupport.usersupport.LoginUserMapper;
import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.MenuModel;
import com.facebank.usersupport.model.LoginUserModel;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.dto.UserRoleDO;
import com.facebank.usersupport.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IMenuService menuService;

	@Autowired
    LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        // 根据username/phone/email查询数据库,返回包装对象
        UserRoleDO queryResultOfUserAndRole = userMapper.selectBySelectiveForPermission(username);

        if(queryResultOfUserAndRole == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(RoleModel role : queryResultOfUserAndRole.getRoles())
//        {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            System.out.println(role.getRoleName());
//        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<MenuModel> modelList = menuService.queryMenuByName(username);
        for(MenuModel menuModel : modelList){
            if (menuModel != null && menuModel.getMenuName()!=null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menuModel.getMenuName());
                authorityList.add(grantedAuthority);
            }
		}
        //创建登录用户对象,用于插入登录记录表
        LoginUserModel loginUserModel = new LoginUserModel();
        loginUserModel.setLoginWay(1);
        loginUserModel.setUsername(queryResultOfUserAndRole.getUsername());
        loginUserModel.setWorkNumber(queryResultOfUserAndRole.getWorkNumber());
        loginUserModel.setGmtCreate(System.currentTimeMillis());
        loginUserModel.setGmtModified(System.currentTimeMillis());
        loginUserModel.setLoginTime(System.currentTimeMillis());

        //添加记录到登录流水表，记录本次登录
        try {
            loginUserMapper.insert(loginUserModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(RoleModel role : queryResultOfUserAndRole.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(role.getRoleName());
        }
        return new org.springframework.security.core.userdetails.User(queryResultOfUserAndRole.getUsername(),
                queryResultOfUserAndRole.getPassword(), authorities);
    }


}
