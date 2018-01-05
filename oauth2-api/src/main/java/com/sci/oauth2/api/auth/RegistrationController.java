package com.sci.oauth2.api.auth;

import com.sci.oauth2.dto.AppCredentials;
import com.sci.oauth2.dto.AppDetails;
import com.sci.oauth2.service.AppCredentialsService;
import com.sci.oauth2.values.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
@RestController
@RequestMapping(value = Api.AUTH_ROOT)
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AppCredentialsService appCredentialsService;

    @GetMapping("/register")
    public AppCredentials registerApplication(@RequestParam String appName,
                                              @RequestParam String appDomain,
                                              @RequestParam String redirectUrl) {
        AppDetails appDetails = new AppDetails(appName, appDomain, redirectUrl);
        log.info("{}", appDetails);

        AppCredentials appCredentials = appCredentialsService.generate(appName);
        log.info("{}", appCredentials);

        return appCredentials;
    }
}
