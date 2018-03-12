package com.facebank.usersupport.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by sunjunchen on 2017/9/28.
 */
public class RestModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(RestModel.class);

    private static final long serialVersionUID = -4497382838074190558L;

    public static final String CODE_SUCCESS = "200";
    public static final String MESSAGE_SUCCESS = "成功";

    /**
     * 返回代码
     */
    private String code;
    /**
     * 返回的内容
     */
    private String message;
    /**
     * 返回附带的实体内容
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RestModel() {
    }

    public RestModel(Object data) {
        this.code = CODE_SUCCESS;
        this.message = MESSAGE_SUCCESS;
        this.data = data;
    }

    public RestModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestModel(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}