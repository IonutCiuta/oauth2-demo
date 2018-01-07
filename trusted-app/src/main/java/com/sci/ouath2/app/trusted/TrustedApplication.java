package com.sci.ouath2.app.trusted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ionutciuta24@gmail.com on 05.01.2018.
 */
@SpringBootApplication
public class TrustedApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(TrustedApplication.class, args);
    }
}
