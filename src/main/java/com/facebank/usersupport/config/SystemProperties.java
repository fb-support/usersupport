package com.facebank.usersupport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by pengyuming on 2017/9/8.
 */
@Component("systemProperties")
public class SystemProperties {

    @Value("${version}")
    private String version;

    @Value("${appname}")
    private String appname;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

}
