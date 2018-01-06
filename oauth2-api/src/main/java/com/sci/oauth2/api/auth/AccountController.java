package com.sci.oauth2.api.auth;

import com.sci.oauth2.model.Account;
import com.sci.oauth2.model.AccountDetails;
import com.sci.oauth2.service.AccountService;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import values.Api;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
@RestController
@RequestMapping(Api.ROOT)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/create")
    public ResponseEntity<Void> createAccount(@RequestBody User user) {
        Account account = accountService.createAccount(user);
        AccountDetails accountDetails = accountService.storeAccountDetails(account, user);

        if(account.getId().equals(accountDetails.getId())) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
}
