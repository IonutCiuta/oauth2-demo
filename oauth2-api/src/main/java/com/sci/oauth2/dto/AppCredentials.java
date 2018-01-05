package com.sci.oauth2.dto;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
public class AppCredentials {
    private String appSecret;
    private String appId;

    public AppCredentials() {
    }

    public AppCredentials(String appSecret, String appId) {
        this.appSecret = appSecret;
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AppCredentials{" +
                "appSecret='" + appSecret + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
