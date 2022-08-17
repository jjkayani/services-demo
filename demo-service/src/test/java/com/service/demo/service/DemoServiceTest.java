package com.service.demo.service;

import com.service.demo.payload.RequestPayload;
import com.service.demo.payload.ResponsePayload;
import com.service.demo.service.impl.DemoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DemoServiceTest {

    @InjectMocks
    private DemoServiceImpl demoService;


    @Test
    public void testFindPerfectCycle() {
        RequestPayload requestPayload = new RequestPayload();
        HashMap<String, List<Integer>> requestMap = new HashMap<>();
        requestMap.put("list1", Arrays.asList(1, 2, 3));
        requestMap.put("list2", Arrays.asList(0, 2, 5));
        requestMap.put("list3", Arrays.asList(3, 0, 1, 2));
        requestPayload.setInput(requestMap);
        HashMap<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("list1", false);
        responseMap.put("list2", false);
        responseMap.put("list3", true);
        ResponsePayload expectedResponse = new ResponsePayload(responseMap);
        ResponsePayload responsePayload = demoService.findPerfectCycle(requestPayload);
        Assert.assertTrue(expectedResponse.getOutput().equals(responsePayload.getOutput()));
    }
}
