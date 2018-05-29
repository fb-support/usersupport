package com.facebank.usersupport.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by yaozun on 2018/3/8.
 */
public class MyPasswordEncoder  implements PasswordEncoder {
    /**
     * 对密码进行加密并返回
     */
    @Override
    public String encode(CharSequence charSequence) {
         
        return charSequence.toString();
    }
    /**
     * 验证密码是否正确
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {

        return s.equals(charSequence.toString());
    }
}
