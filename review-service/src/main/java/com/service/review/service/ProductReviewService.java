package com.service.review.service;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.persistence.entity.ProductReviewEntity;

import java.util.List;
import java.util.Optional;

public interface ProductReviewService {
    void createReview(ProductReviewDTO productReviewDTO);

    Optional<ProductReviewDTO> getReviewByProductId(String productId);

    List<ProductReviewDTO> getAllProductReviews();

    void deleteProductReview(String productId);

    void updateProductReview(ProductReviewDTO productReviewDTO,String productId);


    default ProductReviewDTO mapEntityToDto(ProductReviewEntity productReviewEntity) {
        ProductReviewDTO productReviewDTO = new ProductReviewDTO();
        productReviewDTO.setProductId(productReviewEntity.getProductId());
        productReviewDTO.setAverageReviewScore(productReviewEntity.getAverageReviewScore());
        productReviewDTO.setNumberOfReviews(productReviewEntity.getNumberOfReviews());
        return productReviewDTO;
    }

    default ProductReviewEntity mapDtoToEntity(ProductReviewDTO productReviewDTO) {
        ProductReviewEntity productReviewEntity = new ProductReviewEntity();
        productReviewEntity.setProductId(productReviewDTO.getProductId());
        productReviewEntity.setAverageReviewScore(productReviewDTO.getAverageReviewScore());
        productReviewEntity.setNumberOfReviews(productReviewDTO.getNumberOfReviews());
        return productReviewEntity;
    }
}
