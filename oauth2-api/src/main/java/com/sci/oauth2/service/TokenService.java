package com.sci.oauth2.service;

import com.sci.oauth2.dao.AccountTokenRepo;
import com.sci.oauth2.model.Account;
import com.sci.oauth2.model.AccountToken;
import dto.OAuth2Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@Service
public class TokenService {
    public static final long LIFESPAN = 3600;

    @Autowired
    private AccountTokenRepo accountTokenRepo;

    public OAuth2Token generateToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return new OAuth2Token(token,null, LIFESPAN);
    }

    public AccountToken storeToken(Account account, OAuth2Token token, Set<String> scope) {
        AccountToken accountToken = new AccountToken();
        accountToken.setAccountId(account.getId());
        accountToken.setToken(token);
        accountToken.setCreated(new Date());
        accountToken.setScope(scope);
        return accountTokenRepo.save(accountToken);
    }

    public AccountToken storeToken(String appId, OAuth2Token token, Set<String> scope, String accountId) {
        AccountToken accountToken = new AccountToken();
        accountToken.setAppId(appId);
        accountToken.setToken(token);
        accountToken.setCreated(new Date());
        accountToken.setScope(scope);
        accountToken.setAccountId(accountId);
        return accountTokenRepo.save(accountToken);
    }
}
