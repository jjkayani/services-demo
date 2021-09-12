package com.service.review.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "security")
@lombok.Getter
@lombok.Setter
public class SecurityConfigReader {

    private String appSecret;
    private OAuthServiceConfig oauthServer;

    @lombok.Data
    public static class OAuthServiceConfig {

        private String basePath;
    }
}
