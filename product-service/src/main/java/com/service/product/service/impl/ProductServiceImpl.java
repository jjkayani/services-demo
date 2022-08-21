package com.service.product.service.impl;

import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;
import com.service.product.service.ProductService;
import com.service.product.service.vendor.RestApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private RestApiClient restApiClient;

    @Override
    public ProductDetails getProductByProductId(String productId){
        ProductDetails productDetails = restApiClient.getProductDetails(productId);
        ProductReviewDTO productReviewDTO = restApiClient.getProductReviewByProductId(productId);
        if(Objects.nonNull(productReviewDTO)){
            productDetails.setNumberOfReviews(productReviewDTO.getNumberOfReviews());
            productDetails.setAverageReviewScore(productReviewDTO.getAverageReviewScore());
        }
        return productDetails;
    }
}
