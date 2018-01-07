package com.sci.ouath2.app.trusted.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import values.Api;
import com.sci.ouath2.app.trusted.dao.AuthorizationRepo;
import dto.Credentials;
import com.sci.ouath2.app.trusted.model.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
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
