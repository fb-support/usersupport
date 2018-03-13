package com.facebank.usersupport.Security;

import com.facebank.usersupport.mapper.usersupport.usersupport.UserMapper;
import com.facebank.usersupport.model.RoleModel;
import com.facebank.usersupport.dto.UserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return null;
//    }

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        // 根据username/phone/email查询数据库,返回包装对象
        UserRoleDO queryResultOfUserAndRole = userMapper.selectBySelectiveForPermission(username);

        if(queryResultOfUserAndRole == null){
            throw new UsernameNotFoundException("用户名不存在");
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
