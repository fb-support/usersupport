package com.facebank.usersupport.model;

import java.io.Serializable;

/**
 * @Author : ChinaLHR
 * @Date : Create in 13:49 2018/3/13
 * @Email : 13435500980@163.com
 *
 * 用户校验Model 为true表示重复
 */
public class UserCheckModel implements Serializable{

    boolean phoneRepeat;

    boolean emailRepeat;

    boolean workNumberRepeat;

    boolean usernameRepeat;

    public boolean isPhoneRepeat() {
        return phoneRepeat;
    }

    public void setPhoneRepeat(boolean phoneRepeat) {
        this.phoneRepeat = phoneRepeat;
    }

    public boolean isEmailRepeat() {
        return emailRepeat;
    }

    public void setEmailRepeat(boolean emailRepeat) {
        this.emailRepeat = emailRepeat;
    }

    public boolean isWorkNumberRepeat() {
        return workNumberRepeat;
    }

    public void setWorkNumberRepeat(boolean workNumberRepeat) {
        this.workNumberRepeat = workNumberRepeat;
    }

    public boolean isUsernameRepeat() {
        return usernameRepeat;
    }

    public void setUsernameRepeat(boolean usernameRepeat) {
        this.usernameRepeat = usernameRepeat;
    }
}
