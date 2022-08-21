package com.service.product.util;

import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;

public class PayloadHelper {

    public static ProductDetails mockProductDetails(){
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Product1");
        productDetails.setId("AB1234");
        return productDetails;
    }

    public static ProductReviewDTO mockProductReviewDTO(){
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setAverageReviewScore(5.0);
        productReviewDTO.setNumberOfReviews(10);
        return productReviewDTO;
    }
}
