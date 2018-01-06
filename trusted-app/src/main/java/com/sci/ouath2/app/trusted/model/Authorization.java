package com.sci.ouath2.app.trusted.model;

import org.springframework.data.annotation.Id;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
public class Authorization {
    @Id
    private String id;
    private String appId;
    private String appSecret;

    public Authorization() {
    }

    public Authorization(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
