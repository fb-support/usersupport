package com.facebank.usersupport.common;

import java.io.Serializable;

/**
 * @author NingKui
 * @date 2018/3/8 13:50
 **/
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 200;

    public static final int FAIL = 500;

    public static final int NO_PERMISSION = 404;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL ;
    }
}