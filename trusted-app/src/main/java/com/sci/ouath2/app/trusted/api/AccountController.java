package com.sci.ouath2.app.trusted.api;

import com.sci.ouath2.app.trusted.service.AccountService;
import com.sci.ouath2.app.trusted.service.AuthenticationService;
import com.sci.ouath2.app.trusted.service.AuthorizationService;
import dto.OAuth2Token;
import dto.User;
import dto.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import values.Api;

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

    @Autowired
    private AuthenticationService authenticationService;

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

        return authenticationService.storeAuthentication(auth2Token);
    }
}
