package com.facebank.usersupport.util;

public class StrUtil {

    //解决mybatis无法识别0问题，将空和null转成-1
    public static int parseStringToInt(Object parameter, int defualtValue) {
        try {
            if ((parameter == null) || (parameter.equals(""))) {
                return defualtValue;
            }
            return Integer.parseInt(parameter.toString());
        } catch (Exception ex) {
        }
        return 0;
    }
}
