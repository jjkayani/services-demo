package com.service.demo.payload;

import java.io.Serializable;
import java.util.Map;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
public class ResponsePayload implements Serializable {
    private static final long serialVersionUID = -9101367281436093704L;

    private Map<String, Boolean> output;
}
