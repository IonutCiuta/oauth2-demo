package com.sci.oauth2.dto;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
public class AppDetails {
    private String appName;
    private String appDomain;
    private String redirectUrl;

    public AppDetails() {
    }

    public AppDetails(String appName, String appDomain, String redirectUrl) {
        this.appName = appName;
        this.appDomain = appDomain;
        this.redirectUrl = redirectUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDomain() {
        return appDomain;
    }

    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "AppDetails{" +
                "appName='" + appName + '\'' +
                ", appDomain='" + appDomain + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
