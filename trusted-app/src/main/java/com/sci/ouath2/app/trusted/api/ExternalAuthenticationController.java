package com.sci.ouath2.app.trusted.api;

import com.sci.ouath2.app.trusted.service.ExternalAuthenticationService;
import dto.StringWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import values.Api;

@RestController
@RequestMapping(value = Api.ROOT)
public class ExternalAuthenticationController {

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;

    @GetMapping("/resource/client/name")
    public ResponseEntity<StringWrapper> getClientAppDetails(@RequestParam String appId) {
        return ResponseEntity.ok(externalAuthenticationService.getExternalClientName(appId));
    }
}
