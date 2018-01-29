package com.sci.oauth2.api.resource;

import authorization.Scope;
import com.sci.oauth2.dao.AccountDetailsRepo;
import com.sci.oauth2.dao.AccountTokenRepo;
import com.sci.oauth2.dao.AppDetailsRepo;
import com.sci.oauth2.dto.AppCredentials;
import com.sci.oauth2.dto.AppDetails;
import com.sci.oauth2.model.AccountDetails;
import com.sci.oauth2.model.AccountToken;
import dto.Details;
import dto.StringWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import values.Api;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
@RestController
@RequestMapping(Api.ROOT)
public class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private AccountDetailsRepo accountDetailsRepo;

    @Autowired
    private AccountTokenRepo accountTokenRepo;

    @Autowired
    private AppDetailsRepo appDetailsRepo;

    @GetMapping("/resource/account/details")
    public ResponseEntity<Details> getAccountDetails(@RequestHeader("Authorization") String token) {
        AccountToken accountToken = accountTokenRepo.findByTokenToken(token);

        if(accountToken != null) {
            if(hasDetailsRights(accountToken)) {
                AccountDetails accountDetails = accountDetailsRepo.findOne(accountToken.getAccountId());
                Details details = new Details(
                    accountDetails.getFirstName(),
                    accountDetails.getLastName(),
                    accountDetails.getPhone()
                );

                log.info("{}", accountDetails);
                return ResponseEntity.ok(details);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private boolean hasDetailsRights(AccountToken accountToken) {
        log.info("Token {}: {}", accountToken.getToken(), accountToken.getScope());

        return accountToken.getScope().contains(Scope.ALL) ||
                (accountToken.getScope().contains(Scope.FIRST_NAME) &&
                        accountToken.getScope().contains(Scope.LAST_NAME) &&
                        accountToken.getScope().contains(Scope.PHONE));
    }

    @GetMapping("/resource/client/name")
    public ResponseEntity<StringWrapper> getClientAppDetails(@RequestParam String appId) {
        return ResponseEntity.ok(new StringWrapper(appDetailsRepo.findOne(appId).getAppName()));
    }
}
