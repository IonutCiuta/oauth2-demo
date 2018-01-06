package com.sci.ouath2.app.trusted.api;

import com.sci.oauth2.values.Api;
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

    @PostMapping("/account/create")
    public ResponseEntity<Void> createAccount(@RequestBody User user) {
        log.info("{} {}", user.getUsername(), user.getPassword());
        return ResponseEntity.ok(null);
    }
}
