package com.facebank.usersupport.dto;

import java.io.Serializable;

/**
 * @Auther: yaozun
 * @Date: 2018/4/4 0004 10:10
 * @Description:
 */
public class CustomerTypeDto implements Serializable {

    private Long firId;

    private String firName;

    private Long scId;

    private String scName;

    private Long thId;

    private String thName;

    public Long getFirId() {
        return firId;
    }

    public void setFirId(Long firId) {
        this.firId = firId;
    }

    public String getFirName() {
        return firName;
    }

    public void setFirName(String firName) {
        this.firName = firName;
    }

    public Long getScId() {
        return scId;
    }

    public void setScId(Long scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public Long getThId() {
        return thId;
    }

    public void setThId(Long thId) {
        this.thId = thId;
    }

    public String getThName() {
        return thName;
    }

    public void setThName(String thName) {
        this.thName = thName;
    }
}
