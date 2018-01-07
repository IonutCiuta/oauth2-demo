package com.sci.oauth2.api.auth;

import authorization.AuthField;
import authorization.Grant;
import com.sci.oauth2.model.Account;
import com.sci.oauth2.service.AccountService;
import com.sci.oauth2.service.SecurityService;
import com.sci.oauth2.service.TokenService;
import dto.OAuth2Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import values.Api;

import java.util.Map;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@RestController
@RequestMapping(Api.ROOT)
public class AuthorizationController {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/oauth/authorize",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OAuth2Token authorize(@RequestParam Map<String, String> data) {
        log.info("{}", data);

        switch (data.get(AuthField.GRANT_TYPE)) {
            case Grant.PASSWORD:
                return getTokenForPasswordGrant(data);

            default:
                throw new UnsupportedOperationException("Unknown grant type");
        }
    }

    private OAuth2Token getTokenForPasswordGrant(Map<String, String> data) {
        String username = data.get(AuthField.USERNAME);
        String password = data.get(AuthField.PASSWORD);

        if(!securityService.checkUsernamePasswordCombination(username, password)) {
            throw new IllegalStateException("User/pass don't match");
        }

        String appId = data.get(AuthField.APP_ID);
        String appSecret = data.get(AuthField.APP_SECRET);

        if(!securityService.checkAppIdAppSecretCombination(appId, appSecret)) {
            throw new IllegalStateException("App id/secret don't match");
        }

        Account account = accountService.loadAccount(username);
        OAuth2Token token = tokenService.generateToken();

        return tokenService.storeToken(account, token).getToken();
    }
}
