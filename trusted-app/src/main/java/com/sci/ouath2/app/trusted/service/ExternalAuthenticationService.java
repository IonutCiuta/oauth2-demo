package com.sci.ouath2.app.trusted.service;

import dto.OAuth2Token;
import dto.StringWrapper;
import http.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
}
