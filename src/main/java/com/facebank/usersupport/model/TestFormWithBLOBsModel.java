package com.facebank.usersupport.model;

import java.io.Serializable;

public class TestFormWithBLOBsModel extends TestFormModel implements Serializable {
    private String sqlChange;

    private String configureChange;

    private String mqChange;

    public String getSqlChange() {
        return sqlChange;
    }

    public void setSqlChange(String sqlChange) {
        this.sqlChange = sqlChange == null ? null : sqlChange.trim();
    }

    public String getConfigureChange() {
        return configureChange;
    }

    public void setConfigureChange(String configureChange) {
        this.configureChange = configureChange == null ? null : configureChange.trim();
    }

    public String getMqChange() {
        return mqChange;
    }

    public void setMqChange(String mqChange) {
        this.mqChange = mqChange == null ? null : mqChange.trim();
    }
}