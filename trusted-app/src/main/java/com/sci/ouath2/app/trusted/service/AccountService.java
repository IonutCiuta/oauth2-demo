package com.sci.ouath2.app.trusted.service;

import dto.Authentication;
import dto.Details;
import dto.OAuth2Token;
import dto.User;
import http.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Service
public class AccountService {
    @Value("${application.server.url}")
    private String appServerUrl;

    @Autowired
    private RestTemplate restTemplate;

    public boolean createAccount(User user) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("account/create")
                .build().toUriString();

        ResponseEntity<Void> result = RequestBuilder
            .prepareCall(url)
            .withPostMethod()
            .withBody(user)
            .makeCall(restTemplate, Void.class);

        return result.getStatusCode().is2xxSuccessful();
    }

    public ResponseEntity<Details> getAccountDetails(String oauthToken) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("resource/account/details")
                .build().toUriString();

        ResponseEntity<Details> result = RequestBuilder
                .prepareCall(url)
                .authorizedWith(oauthToken)
                .acceptJson()
                .withGetMethod()
                .makeCall(restTemplate, Details.class);

        return result;
    }
}
