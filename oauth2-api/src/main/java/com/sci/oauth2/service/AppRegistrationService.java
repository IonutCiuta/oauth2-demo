package com.sci.oauth2.service;

import com.sci.oauth2.dao.AppCredentialsRepo;
import com.sci.oauth2.dao.AppDetailsRepo;
import com.sci.oauth2.dto.AppCredentials;
import com.sci.oauth2.dto.AppDetails;
import com.sci.oauth2.model.ApplicationCredentials;
import com.sci.oauth2.model.ApplicationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Component
public class AppRegistrationService {
    @Autowired
    private AppCredentialsService appCredentialsService;

    @Autowired
    private AppCredentialsRepo appCredentialsRepo;

    @Autowired
    private AppDetailsRepo appDetailsRepo;

    public AppCredentials registerApp(AppDetails appDetails) {
        AppCredentials appCredentials = appCredentialsService.generate(appDetails.getAppName());
        ApplicationCredentials applicationCredentials = from(appCredentials);
        appCredentialsRepo.save(applicationCredentials);

        ApplicationDetails applicationDetails = from(appDetails);
        applicationDetails.setId(appCredentials.getAppId());
        appDetailsRepo.save(applicationDetails);

        return appCredentials;
    }

    private ApplicationDetails from(AppDetails appDetails) {
        ApplicationDetails applicationDetails = new ApplicationDetails();
        applicationDetails.setAppName(appDetails.getAppName());
        applicationDetails.setAppDomain(appDetails.getAppDomain());
        applicationDetails.setRedirectUrl(appDetails.getRedirectUrl());
        applicationDetails.setRegistrationDate(new Date().toString());
        return applicationDetails;
    }

    private ApplicationCredentials from(AppCredentials appCredentials) {
        ApplicationCredentials applicationCredentials = new ApplicationCredentials();
        applicationCredentials.setSecret(appCredentials.getAppSecret());
        applicationCredentials.setId(appCredentials.getAppId());
        return applicationCredentials;
    }
}
