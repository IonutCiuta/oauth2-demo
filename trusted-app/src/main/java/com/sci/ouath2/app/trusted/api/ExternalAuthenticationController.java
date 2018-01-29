package com.sci.ouath2.app.trusted.api;

import com.sci.ouath2.app.trusted.service.ExternalAuthenticationService;
import dto.OAuth2Token;
import dto.StringWrapper;
import dto.User;
import dto.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import values.Api;

@RestController
@RequestMapping(value = Api.ROOT)
public class ExternalAuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(ExternalAuthenticationController.class);

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;

    @GetMapping("/resource/client/name")
    public ResponseEntity<StringWrapper> getClientAppDetails(@RequestParam String appId) {
        return ResponseEntity.ok(externalAuthenticationService.getExternalClientName(appId));
    }

    @GetMapping("/account/authenticate/external")
    public UserToken authenicateUser(@RequestParam String appId,
                                     @RequestParam String appSecret,
                                     @RequestParam String scope) {
        OAuth2Token auth2Token = externalAuthenticationService.authenticateExternalUser(appId, appSecret, scope);
        log.info("External token: {}", auth2Token.getToken());
        return new UserToken(auth2Token.getToken());
    }
}
