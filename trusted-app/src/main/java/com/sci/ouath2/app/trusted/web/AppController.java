package com.sci.ouath2.app.trusted.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
