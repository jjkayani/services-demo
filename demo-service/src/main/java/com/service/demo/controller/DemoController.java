package com.service.demo.controller;

import com.service.demo.payload.RequestPayload;
import com.service.demo.payload.ResponsePayload;
import com.service.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = {"DemoController"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @PostMapping(value = "/perfect-cycle")
    @ApiOperation("This api is to find the perfect cycle in the given list")
    public ResponsePayload findPerfectCycle(@RequestBody RequestPayload requestPayload) {
        return demoService.findPerfectCycle(requestPayload);
    }
}
