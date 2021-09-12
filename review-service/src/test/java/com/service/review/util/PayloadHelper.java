package com.service.review.util;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.persistence.entity.ProductReviewEntity;

public class PayloadHelper {

    public static ProductReviewDTO mockProductReviewDTO(){
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setProductId("AB12345");
        productReviewDTO.setNumberOfReviews(20);
        productReviewDTO.setAverageReviewScore(5.0);
        return productReviewDTO;
    }

    public static ProductReviewEntity mockProductReviewEntity(){
        ProductReviewEntity productReviewEntity = new ProductReviewEntity();
        productReviewEntity.setProductId("AB12345");
        productReviewEntity.setNumberOfReviews(20);
        productReviewEntity.setAverageReviewScore(5.0);
        return productReviewEntity;
    }
}
