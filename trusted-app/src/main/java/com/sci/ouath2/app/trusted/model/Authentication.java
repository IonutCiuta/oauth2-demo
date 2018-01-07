package com.sci.ouath2.app.trusted.model;

import org.springframework.data.annotation.Id;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
public class Authentication {
    @Id
    private String id;
    private String oauthToken;
    private String userToken;

    public Authentication() {
    }

    public Authentication(String oauthToken, String userToken) {
        this.oauthToken = oauthToken;
        this.userToken = userToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id='" + id + '\'' +
                ", oauthToken='" + oauthToken + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
