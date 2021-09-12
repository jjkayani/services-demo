package com.service.review.common;

import com.service.review.payload.error.ErrorScenario;
import com.service.review.payload.error.FieldError;
import org.slf4j.MDC;

import java.util.List;

/**
 * Exception for all Review-Service business/known controllable error types.
 *
 */
@lombok.Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7263684210348341559L;

    private final String operation;
    private final ErrorScenario scenario;
    private final String vendorStatus;
    private final List<FieldError> fieldErrors;

    @lombok.Builder
    public ServiceException(String message, Throwable cause, String operation, ErrorScenario errorScenario,
                            String vendorStatus, List<FieldError> fieldErrors, Object... args) {

        super(args == null || args.length == 0 ? message : String.format(message, args), cause);
        this.operation = operation == null ? MDC.get(Constants.OPERATION) : operation;
        this.scenario = errorScenario;
        this.vendorStatus = vendorStatus;
        this.fieldErrors = fieldErrors;
    }

}
