package com.service.review.common;

import lombok.AccessLevel;

@lombok.NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String SERVICE_IDENTIFIER = "REVIEW";
    public static final String APPLICATION_SECRET = "x-application-secret";
    public static final String OPERATION = "Operation";
    public static final String API_SECURED_PATH_PREFIX = "/**";
    public static final String API_OPEN_PATH_PREFIX = "/open/**";

}
