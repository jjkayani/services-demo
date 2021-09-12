package com.service.review.advice;


import com.google.common.base.Throwables;
import com.service.review.common.ServiceException;
import com.service.review.payload.error.ErrorResponseModel;
import com.service.review.payload.error.ErrorScenario;
import com.service.review.payload.error.FieldError;
import com.service.review.payload.error.FormValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/***
 * Exception handler is responsible for catching the uncaught exceptions,
 * logging them, and returning a proper exception wrapped in defined structure
 * to the invoker.
 * <p>
 * It's shared across all the service <tt>@Controller</tt> classes.
 *
 * @author Jamal
 *
 */
@ControllerAdvice
public class GenericExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ErrorResponseModel> handleServiceException(ServiceException ex) {
        LOGGER.error("Got Service Exception.", ex);

        ErrorResponseModel response = new ErrorResponseModel();
        response.setCode(ex.getScenario().getCode());
        response.setHttpStatus(ex.getScenario().getHttpStatus());
        response.setDesc(ex.getMessage());
        response.setVendorStatus(ex.getVendorStatus());

        return serviceErrorResponse(response);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseModel> handleUncaughtExceptions(Exception ex) {
        LOGGER.error("Something went bad", ex);
        return throwRequiredException(ex);
    }

    @ExceptionHandler(FormValidationException.class)
    public final ResponseEntity<ErrorResponseModel> handleValidationFailures(FormValidationException ex) {
        LOGGER.error("Caught FormValidationException", ex);
        return processValidationFailures(ex.getErrors());
    }

    private ResponseEntity<ErrorResponseModel> processValidationFailures(List<FieldError> fieldErrors) {
        LOGGER.error("Form validation exception. Field errors: {}", fieldErrors);

        ErrorResponseModel response = new ErrorResponseModel();
        response.setFieldErrors(fieldErrors);
        response.setCode(ErrorScenario.BAD_REQUEST.getCode());
        response.setHttpStatus(ErrorScenario.BAD_REQUEST.getHttpStatus());
        response.setDesc("Form validation failures");

        return serviceErrorResponse(response);
    }

    private static ResponseEntity<ErrorResponseModel> serviceErrorResponse(ErrorResponseModel errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    private ResponseEntity<ErrorResponseModel> throwRequiredException(Throwable ex) {
        Throwable cause = Throwables.getRootCause(ex);
        if (cause instanceof ServiceException) {
            return handleServiceException((ServiceException) cause);
        }
        return serviceErrorResponse(new ErrorResponseModel(ErrorScenario.UNKNOWN, ex.getMessage()));
    }
}
