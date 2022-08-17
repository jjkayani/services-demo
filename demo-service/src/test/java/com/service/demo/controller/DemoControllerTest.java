package com.service.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.demo.payload.RequestPayload;
import com.service.demo.service.impl.DemoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {


    @InjectMocks
    private DemoController controller;

    @Mock
    private DemoServiceImpl demoService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindPerfectCycle() throws Exception {
        RequestPayload requestPayload = new RequestPayload();
        mockMvc.perform(post("/perfect-cycle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestPayload)))
                .andExpect(status().isOk());
        Mockito.verify(demoService, Mockito.times(1)).findPerfectCycle(any());
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
