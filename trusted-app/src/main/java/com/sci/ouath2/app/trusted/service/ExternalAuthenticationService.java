package com.sci.ouath2.app.trusted.service;

import authorization.AuthField;
import authorization.Grant;
import dto.OAuth2Token;
import dto.StringWrapper;
import http.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExternalAuthenticationService {
    @Value("${application.server.url}")
    private String appServerUrl;

    @Autowired
    private RestTemplate restTemplate;

    public StringWrapper getExternalClientName(String externalClientId) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("/resource/client/name")
                .queryParam("appId", externalClientId)
                .build().toUriString();

        ResponseEntity<StringWrapper> result = RequestBuilder
                .prepareCall(url)
                .withGetMethod()
                .withJsonHeaders()
                .makeCall(restTemplate, StringWrapper.class);

        return result.getBody();
    }

    public OAuth2Token authenticateExternalUser(String appId, String appSecret, String scope, String username) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("/oauth/authorize")
                .build().toUriString();

        ResponseEntity<OAuth2Token> result = RequestBuilder
                .prepareCall(url)
                .withPostMethod()
                .acceptJson()
                .sendUrlFormEncoded()
                .withBody(externalAuthorizationData(appId, appSecret, scope, username))
                .makeCall(restTemplate, OAuth2Token.class);

        return result.getBody();
    }


    private MultiValueMap<String, String> externalAuthorizationData(String appId, String appSecret, String scope, String username) {
        MultiValueMap<String, String> authorizationData = new LinkedMultiValueMap<String, String>();

        authorizationData.add(AuthField.GRANT_TYPE, Grant.TOKEN);
        authorizationData.add(AuthField.APP_ID, appId);
        authorizationData.add(AuthField.APP_SECRET, appSecret);
        authorizationData.add(AuthField.SCOPE, scope);
        authorizationData.add(AuthField.USERNAME, username);

        return authorizationData;
    }
}
