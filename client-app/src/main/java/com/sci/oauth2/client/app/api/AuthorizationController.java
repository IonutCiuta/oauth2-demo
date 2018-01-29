package com.sci.oauth2.client.app.api;

import com.sci.oauth2.client.app.dao.AuthorizationRepo;
import dto.Credentials;
import model.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import values.Api;

@RestController
@RequestMapping(Api.ROOT)
public class AuthorizationController {
    @Autowired
    private AuthorizationRepo authorizationRepo;

    @PostMapping("/authorization/credentials")
    public ResponseEntity<Void> storeAuthorization(@RequestBody Credentials credentials) {
        Authorization authorization = authorizationRepo.save(new Authorization(
                credentials.getAppId(),
                credentials.getAppSecret()
        ));

        return authorization.getId() != null ? ResponseEntity.ok(null) :
                ResponseEntity.status(500).body(null);
    }
}
