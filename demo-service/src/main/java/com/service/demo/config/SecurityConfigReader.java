package com.service.demo.config;

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
}
