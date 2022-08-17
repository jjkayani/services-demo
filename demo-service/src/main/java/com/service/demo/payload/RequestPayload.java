package com.service.demo.payload;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@lombok.Getter
@lombok.Setter
public class RequestPayload implements Serializable {
    private static final long serialVersionUID = -9101367281436093704L;

    private Map<String, List<Integer>> input;
}
