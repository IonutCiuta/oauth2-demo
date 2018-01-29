package com.sci.ouath2.app.trusted.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import values.Api;

@Controller
@RequestMapping(value = Api.AUTH_ROOT)
public class ExternalAuthenticationController {

    @GetMapping("/external")
    public String externalAuth() {
        return "index.html";
    }
}
