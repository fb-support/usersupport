package com.facebank.usersupport.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 业务工具类
 *
 * @author hailong.Yang
 * @create 2017-11-22 下午6:55
 **/
public class EncryptBizUtil {

    private final static Logger logger = LoggerFactory.getLogger(EncryptBizUtil.class);

    private EncryptBizUtil(){};

    //给实例对象加密
    public static <T> void encryptModel(T obj,String key){
      if(null == obj){
          return;
      }

      try{
          Field[] fields = obj.getClass().getDeclaredFields();
          for(Field field : fields){
              field.setAccessible(true);

              if(null == field.get(obj)){
                  continue;
              }

              encryptFieldValue(field,obj,key);
          }
      }catch (Exception e){
          logger.error("返利投对象转换错误",e);
      }
    }


    //给实例对象解密
    public static<T> void decryptModel(T obj,String key){
        if(null == obj){
            return;
        }

        AESUtil aesUtil = new AESUtil(key);
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                if(null == field.get(obj)){
                    continue;
                }

                decryptFieldValue(field,obj,key);

            } catch (IllegalAccessException e) {
                logger.error("返利投对象转换错误",e);
            }
        }
    }


    //map对象转换
    public static void encryptMap(Map<String,String> map,String key){
        if(null == map){
            return ;
        }

        AESUtil aesUtil = new AESUtil(key);

        for(Map.Entry<String,String> entry : map.entrySet()){
            entry.setValue(aesUtil.decrypt(entry.getValue()));
        }
    }





    //列表加密
    private  static <T> void encryptListModel(List<T> list,String key){
        if(null == list){
            return;
        }

        try{
            for(T obj : list){
                encryptModel(obj,key);
            }
        }catch (Exception e){
            logger.error("返利投对象转换错误",e);
        }
    }


    //列表转换
    private static <T> void decryptListModel(List<T> list,String key){
        if(null == list){
            return;
        }
        try{
            for(Object obj : list){
                decryptModel(obj,key);
            }
        }catch (Exception e){
            logger.error("返利投对象转换错误",e);
        }
    }


    private static <T> void decryptFieldValue(Field field,T obj,String key) {
        AESUtil aesUtil = new AESUtil(key);
        try{
            if(field.getType() == String.class){
                String val = (String)field.get(obj);
                field.set(obj,aesUtil.decrypt((String)field.get(obj)));
                return ;
            }

            if(field.getType() == List.class){
                decryptListModel((List<T>)field.get(obj),key);
            }

        }catch (Exception e){
            logger.error("动态设置值",e);
            try {
                field.set(obj,null);
            } catch (IllegalAccessException e1) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    private static <T> void encryptFieldValue(Field field,T obj,String key) {
        AESUtil aesUtil = new AESUtil(key);
        try{

            System.out.println("++++++++++++++++++++" + field.getName() + " --" + field.getType());

            if(field.getType() == String.class){
                String val = (String)field.get(obj);
                field.set(obj,aesUtil.encrypt((String)field.get(obj)));
                return ;
            }

            if(field.getType() == List.class){
                encryptListModel((List<T>)field.get(obj),key);
            }

        }catch (Exception e){
            logger.error("动态设置值",e);
            try {
                field.set(obj,null);
            } catch (IllegalAccessException e1) {
                logger.error(e.getMessage(),e);
            }
        }
    }

}
