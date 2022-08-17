package com.service.demo.service;

import com.service.demo.payload.RequestPayload;
import com.service.demo.payload.ResponsePayload;

public interface DemoService {

    ResponsePayload findPerfectCycle(RequestPayload requestPayload);
}
