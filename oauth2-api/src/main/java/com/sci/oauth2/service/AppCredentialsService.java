package com.sci.oauth2.service;

import com.sci.oauth2.dto.AppCredentials;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * ionutciuta24@gmail.com on 04.01.2018.
 */
@Component
public class AppCredentialsService {
    public AppCredentials generate(String appName) {
        return new AppCredentials(generateSecret(appName),  generateId());
    }

    private String generateSecret(String appName) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < 4; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
            builder.append(chars.charAt(random.nextInt(appName.length())));
        }

        return builder.toString();
    }

    private String generateId() {
        int value = new SecureRandom().nextInt(99999);
        StringBuilder builder = new StringBuilder();

        builder.append(value);
        int length = builder.length();

        while (length < 5) {
            builder.append("0");
            length++;
        }

        return builder.toString();
    }
}
