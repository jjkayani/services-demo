package com.service.review.config;

import com.google.common.collect.ImmutableMap;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Map;


//@Component
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "service-mesh")
//@lombok.Getter
//@lombok.Setter
//@Validated
public class ServiceMeshConfig {

    private static final String API_ACTUATOR_HEALTH = "actuator/health";


    @NotNull
    private HostConfig utilityService;

    @NotNull
    private ClientConfig oauthService;

    @NotNull
    private HostConfig accountsService;

    @NotNull
    private HostConfig kycService;

    @NotNull
    private HostConfig cacheDataService;

    @NotNull
    private HostConfig partyService;


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

    @lombok.Getter
    @lombok.Setter
    public static class ClientConfig extends HostConfig {
        @NotNull
        private String clientKey;
        @NotNull
        private String clientSecret;
        @NotNull
        private String clientSecretHeaderKey;
    }

    @PostConstruct
    public void collectHostConfigs() {
        hostConfigs = ImmutableMap.<String, HostConfig>builder()
                .put("Utility-Service", utilityService)
                .put("Oauth-Service", oauthService)
                .put("Kyc-Service", kycService)
                .put("Accounts-Service", accountsService)
                .put("Cache-Service", cacheDataService)
                .put("Party-Service", partyService)
                .build();
    }
}


