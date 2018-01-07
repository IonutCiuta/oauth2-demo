package com.sci.ouath2.app.trusted.service;

import com.sci.ouath2.app.trusted.dao.AuthenticationRepo;
import com.sci.ouath2.app.trusted.model.Authentication;
import dto.OAuth2Token;
import dto.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@Service
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationService.class);

    @Autowired
    private AuthenticationRepo authenticationRepo;

    public UserToken storeAuthentication(OAuth2Token oauthToken) {
        Authentication authentication = authenticationRepo.save(
                new Authentication(oauthToken.getToken(), generateUserToken())
        );

        log.info("{}", authentication);
        return new UserToken(authentication.getUserToken());
    }

    public Authentication getAuthentication(String userToken) {
        return authenticationRepo.findByUserToken(userToken);
    }

    private String generateUserToken() {
        return UUID.randomUUID().toString();
    }
}
