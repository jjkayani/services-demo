package com.service.product.payload.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorScenario {

    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, "UNKNOWN"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST");

    private HttpStatus httpStatus;
    private String code;

}
