package com.sci.ouath2.app.trusted.api;

import com.sci.ouath2.app.trusted.service.AccountService;
import com.sci.ouath2.app.trusted.service.AuthorizationService;
import dto.Authentication;
import dto.OAuth2Token;
import dto.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import values.Api;
import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@RestController
@RequestMapping(Api.ROOT)
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/account/create")
    public ResponseEntity<Void> createAccount(@RequestBody User user) {
        log.info("{}", user);
        if(accountService.createAccount(user)) {
            log.info("Account successfully created");
            return ResponseEntity.ok(null);
        } else {
            log.info("Error while creating account");
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/account/authenticate")
    public UserToken authenicateUser(@RequestBody User user) {
        log.info("{}", user);
        OAuth2Token auth2Token = authorizationService.authenticateUser(user);
        return new UserToken(auth2Token.getToken());
    }
}
