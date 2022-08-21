package com.service.product.service;

import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;
import com.service.product.service.impl.ProductServiceImpl;
import com.service.product.service.vendor.RestApiClient;
import com.service.product.util.PayloadHelper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ProductService {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private RestApiClient restApiClient;

    @Test
    public void testGetProductByProductId(){
        String productId = "AB123";
        ProductDetails mockProductDetails = PayloadHelper.mockProductDetails();
        ProductReviewDTO mockProductReviewDTO = PayloadHelper.mockProductReviewDTO();
        when(restApiClient.getProductDetails(productId)).thenReturn(mockProductDetails);
        when(restApiClient.getProductReviewByProductId(productId)).thenReturn(mockProductReviewDTO);
        ProductDetails productDetails = productService.getProductByProductId(productId);

        Assert.assertNotNull(productDetails);
        Assert.assertEquals(Double.valueOf(mockProductReviewDTO.getAverageReviewScore()),productDetails.getAverageReviewScore());
        Assert.assertEquals(Double.valueOf(mockProductReviewDTO.getNumberOfReviews()),productDetails.getNumberOfReviews());

    }
}
