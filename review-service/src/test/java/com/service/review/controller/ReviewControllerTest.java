package com.service.review.controller;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.service.impl.ProductReviewServiceImpl;
import com.service.review.util.PayloadHelper;
import org.hamcrest.Matchers;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {

    private static final String BASE_PATH = "/review";

    @InjectMocks
    private ReviewController controller;

    @Mock
    private ProductReviewServiceImpl productReviewService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @org.junit.jupiter.api.Test
    public void testGetProductReview() throws Exception {
        ProductReviewDTO productReviewDTO = PayloadHelper.mockProductReviewDTO();
        when(productReviewService.getReviewByProductId(anyString())).thenReturn(Optional.of(productReviewDTO));
        mockMvc.perform(get(BASE_PATH+ anyString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId",
                        Matchers.is(productReviewDTO.getProductId())));
        Mockito.verify(productReviewService, Mockito.times(1)).getReviewByProductId(anyString());
    }

    @Test
    public void testGetAllProductReviews() throws Exception {
        List<ProductReviewDTO> productReviewDTOList = Arrays.asList(PayloadHelper.mockProductReviewDTO());
        when(productReviewService.getAllProductReviews()).thenReturn(Arrays.asList());
        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId",
                        Matchers.is(productReviewDTOList.get(0).getProductId())));
        Mockito.verify(productReviewService, Mockito.times(1)).getAllProductReviews();
    }

    @Test
    public void testCreateProductReview() throws Exception {
        ProductReviewDTO productReviewDTO = PayloadHelper.mockProductReviewDTO();
        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Mockito.verify(productReviewService, Mockito.times(1)).createReview(any());
    }

    @Test
    public void testDeleteProductReview() throws Exception{
        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(productReviewService, Mockito.times(1)).deleteProductReview(any());
    }

    @Test
    public void testUpdateProductReview() throws Exception{
        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(productReviewService, Mockito.times(1)).updateProductReview(any(),anyString());
    }

}
