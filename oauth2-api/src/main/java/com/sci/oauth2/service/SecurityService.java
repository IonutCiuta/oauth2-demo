package com.sci.oauth2.service;

import com.sci.oauth2.dao.AccountRepo;
import com.sci.oauth2.dao.AppCredentialsRepo;
import com.sci.oauth2.dto.AppCredentials;
import com.sci.oauth2.model.Account;
import com.sci.oauth2.model.ApplicationCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@Service
public class SecurityService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AppCredentialsRepo appCredentialsRepo;

    public boolean checkUsernamePasswordCombination(String username, String password) {
        Account account = accountRepo.findByUsername(username);
        return account.getPassword().equals(password);
    }

    public boolean checkAppIdAppSecretCombination(String appId, String appSecret) {
        ApplicationCredentials credentials = appCredentialsRepo.findOne(appId);
        return credentials.getSecret().equals(appSecret);
    }
}
