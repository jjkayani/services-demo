package com.service.product.config;

import com.google.common.collect.ImmutableMap;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Map;


@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service-mesh")
@lombok.Getter
@lombok.Setter
@Validated
public class ServiceMeshConfig {

    private static final String API_ACTUATOR_HEALTH = "actuator/health";


    @NotNull
    private HostConfig reviewService;

    @NotNull
    private HostConfig productService;

    @lombok.Getter
    private transient Map<String, HostConfig> hostConfigs;

    @lombok.Getter
    @lombok.Setter
    public static class HostConfig {
        @URL
        private String baseUri;
        private String appSecret;
        private String healthPath = API_ACTUATOR_HEALTH;
        private Map<String, String> endpoints;
    }


    @PostConstruct
    public void collectHostConfigs() {
        hostConfigs = ImmutableMap.<String, HostConfig>builder()
                .put("Review-Service", reviewService)
                .put("Product-Service", productService)
                .build();
    }
}


