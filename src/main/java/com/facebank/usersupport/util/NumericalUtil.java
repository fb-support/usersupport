package com.facebank.usersupport.util;

import java.math.BigDecimal;

/**
 * DECIMAL 处理类
 *
 * @author hailong.Yang
 * @create 2017-11-23 下午5:57
 **/
public class NumericalUtil {

    private NumericalUtil(){};


    public static String getRoundForString(String val) {
        if (null == val || "".equals(val.trim())) {
            return null;
        }
        return new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal getRoundForString(BigDecimal bigdecimal,int scale) {
        if (null == bigdecimal) {
            return null;
        }
        return bigdecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal add(BigDecimal data1,BigDecimal dat2,int scale){
        if (null == data1 || null == dat2) {
            return null;
        }
        return data1.add(dat2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal subtract(BigDecimal data1,BigDecimal dat2,int scale){
        if (null == data1 || null == dat2) {
            return null;
        }
        return data1.subtract(dat2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply (BigDecimal data1,BigDecimal dat2,int scale){
        if (null == data1 || null == dat2) {
            return null;
        }
        return data1.multiply(dat2).setScale(scale, BigDecimal.ROUND_HALF_UP) ;
    }


    public static String divide (String data1,String dat2,int scale){
        if (null == data1 || null == dat2) {
            return "";
        }
        return new BigDecimal(data1).divide(new BigDecimal(dat2), scale, BigDecimal.ROUND_HALF_UP).toString();
    }


    public static BigDecimal divide (BigDecimal data1,BigDecimal dat2,int scale){
        if (null == data1 || null == dat2 || dat2.doubleValue() == 0) {
            return null;
        }
        return data1.divide(dat2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static Double decimalToDouble(BigDecimal value){
        if(null == value){
            return null;
        }
        return value.doubleValue();
    }



    public static String integerToStr(Integer value){
        if(null == value){
            return null;
        }
        return value.toString();
    }

    public static String longToStr(Long value){
        if(null == value){
            return null;
        }
        return value.toString();
    }

    public static String decimalToStr(BigDecimal value){
        if(null == value){
            return null;
        }
        return value.toString();
    }
}
