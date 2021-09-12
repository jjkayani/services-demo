package com.service.product.controller;

import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;
import com.service.product.service.impl.ProductServiceImpl;
import com.service.product.util.PayloadHelper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private static final String BASE_PATH = "/product";

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductServiceImpl productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetProductDetails() throws Exception {
        ProductDetails productDetails = PayloadHelper.mockProductDetails();
        when(productService.getProductByProductId(anyString())).thenReturn(productDetails);
        mockMvc.perform(get(BASE_PATH + anyString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        Matchers.is(productDetails.getName())))
                .andExpect(jsonPath("$.id",
                        Matchers.is(productDetails.getId())));

        Mockito.verify(productService, Mockito.times(1)).getProductByProductId(anyString());
    }

    @Test
    public void testGetProductDetailsWithReview() throws Exception {
        ProductDetails productDetails = PayloadHelper.mockProductDetails();
        ProductReviewDTO productReviewDTO = PayloadHelper.mockProductReviewDTO();
        productDetails.setNumberOfReviews(productReviewDTO.getNumberOfReviews());
        productDetails.setAverageReviewScore(productReviewDTO.getAverageReviewScore());
        when(productService.getProductByProductId(anyString())).thenReturn(productDetails);
        mockMvc.perform(get(BASE_PATH + anyString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        Matchers.is(productDetails.getName())))
                .andExpect(jsonPath("$.id",
                        Matchers.is(productDetails.getId())))
                .andExpect(jsonPath("$.averageReviewScore",
                        Matchers.is(productDetails.getAverageReviewScore())))
                .andExpect(jsonPath("$.numberOfReviews",
                        Matchers.is(productDetails.getNumberOfReviews())));

        Mockito.verify(productService, Mockito.times(1)).getProductByProductId(anyString());
    }
}
