package com.sci.oauth2.model;

import dto.OAuth2Token;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
public class AccountToken {
    @Id
    private String accountId;
    private String appId;
    private OAuth2Token token;
    private Set<String> scope = new HashSet<>();
    private Date created;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public OAuth2Token getToken() {
        return token;
    }

    public void setToken(OAuth2Token token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }
}
