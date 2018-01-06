package com.sci.oauth2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Controller
public class AppController {

    @GetMapping("/")
    public String index(Model model) {
        return "index.html";
    }
}
