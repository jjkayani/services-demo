package com.service.demo.payload.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.demo.common.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model representing the error response.")
public class ErrorResponseModel {

    @ApiModelProperty(notes = "Identifier for the base error cause.", required = true)
    private String code;

    @JsonIgnore
    private HttpStatus httpStatus;

    @ApiModelProperty(notes = "Gives insight of what went wrong to the request we made to an external service.")
    private String vendorStatus;

    @ApiModelProperty(notes = "Describes the error in greater details. It also holds the tracking information.")
    private String desc;

    @ApiModelProperty(notes = "Timestamp when the particular error occurred.")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponseModel(ServiceException ex) {
        this.code = ex.getScenario().getCode();
        this.httpStatus = ex.getScenario().getHttpStatus();
        this.desc = ex.getMessage();
        this.vendorStatus = ex.getVendorStatus();
    }

    public ErrorResponseModel(ErrorScenario code, String desc) {
        this.code = code.getCode();
        this.httpStatus = code.getHttpStatus();
        this.desc = desc;
    }

}
