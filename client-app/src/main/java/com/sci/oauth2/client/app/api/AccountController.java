package com.sci.oauth2.client.app.api;

import dto.Details;
import http.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import values.Api;

@RestController
@RequestMapping(value = Api.ROOT)
public class AccountController {
    @Value("${application.resource.server.url}")
    private String appServerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/account/details")
    public Details getAccountDetails(@RequestHeader(value="User-Token") String token) {
        String url = UriComponentsBuilder
                .fromHttpUrl(appServerUrl)
                .pathSegment("resource/account/details")
                .build().toUriString();

        ResponseEntity<Details> details = RequestBuilder
                .prepareCall(url)
                .authorizedWith(token)
                .acceptJson()
                .withGetMethod()
                .makeCall(restTemplate, Details.class);

        if(details.getStatusCode().is2xxSuccessful()) {
            Details result = details.getBody();
            return result;
        }

        return new Details("", "", "Unauthorized");
    }
}
