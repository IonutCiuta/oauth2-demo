package com.sci.ouath2.app.trusted.api;

import com.sci.ouath2.app.trusted.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import values.Api;
import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@RestController
@RequestMapping(Api.ROOT)
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

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
}
