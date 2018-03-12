package com.facebank.usersupport.exception;

/**
 * 平台异常，抛出各种错误信息
 */
public class PlateformException extends Exception {

    /**
     * 异常代码
     */
    private String code;
    /**
     * 异常文本说明
     */
    private String message;

    public PlateformException()
    {
        super();
    }

    public PlateformException(final Throwable e) {
        super(e);
    }

    public PlateformException(final String message) {
        super(message);
        this.message = message;
    }

    public PlateformException(final String message, final Throwable e) {
        super(e);
        this.message = message;
    }

    public PlateformException(final String code, final String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public PlateformException(final String code, final String message, final Throwable e) {
        super(e);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
