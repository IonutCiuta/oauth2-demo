package com.sci.oauth2.model;

import dto.OAuth2Token;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
public class AccountToken {
    @Id
    private String accountId;
    private OAuth2Token token;
    private Date created;

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
}
