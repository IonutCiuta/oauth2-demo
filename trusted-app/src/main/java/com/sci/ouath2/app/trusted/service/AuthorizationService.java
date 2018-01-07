package com.sci.ouath2.app.trusted.service;

import authorization.AuthField;
import authorization.Grant;
import com.sci.ouath2.app.trusted.dao.AuthorizationRepo;
import com.sci.ouath2.app.trusted.model.Authorization;
import dto.OAuth2Token;
import dto.User;
import http.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@Service
public class AuthorizationService {
    @Value("${application.server.url}")
    private String appServerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthorizationRepo authorizationRepo;

    public OAuth2Token authenticateUser(User user) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("/oauth/authorize")
                .build().toUriString();

        ResponseEntity<OAuth2Token> result = RequestBuilder
                .prepareCall(url)
                .withPostMethod()
                .acceptJson()
                .sendUrlFormEncoded()
                .withBody(authorizationData(user))
                .makeCall(restTemplate, OAuth2Token.class);

        return result.getBody();
    }

    public Authorization loadAuthorization() {
        return authorizationRepo.findAll().get(0);
    }

    private MultiValueMap<String, String> authorizationData(User user) {
        Authorization authorization = loadAuthorization();
        MultiValueMap<String, String> authorizationData = new LinkedMultiValueMap<String, String>();

        authorizationData.add(AuthField.GRANT_TYPE, Grant.PASSWORD);
        authorizationData.add(AuthField.USERNAME, user.getUsername());
        authorizationData.add(AuthField.PASSWORD, user.getPassword());
        authorizationData.add(AuthField.APP_ID, authorization.getAppId());
        authorizationData.add(AuthField.APP_SECRET, authorization.getAppSecret());

        return authorizationData;
    }
}
