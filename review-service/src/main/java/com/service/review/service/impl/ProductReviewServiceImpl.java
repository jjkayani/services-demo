package com.service.review.service.impl;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.persistence.entity.ProductReviewEntity;
import com.service.review.persistence.repository.ProductReviewsRepository;
import com.service.review.service.ProductReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewServiceImpl.class);


    @Autowired
    private ProductReviewsRepository productReviewsRepository;

    @Override
    public void createReview(ProductReviewDTO productReviewDTO) {
        LOGGER.info("Saving Product Review for productId : {}",productReviewDTO.getProductId());
        productReviewsRepository.save(mapDtoToEntity(productReviewDTO));
    }

    @Override
    public Optional<ProductReviewDTO> getReviewByProductId(String productId) {
        LOGGER.info("Fetching Product Review of productId : {}",productId);
        Optional<ProductReviewEntity> productReviewEntity = productReviewsRepository.findByProductId(productId);
        return productReviewEntity.isPresent() ? Optional.of(mapEntityToDto(productReviewEntity.get())) : Optional.empty();
    }

    @Override
    public List<ProductReviewDTO> getAllProductReviews() {
        LOGGER.info("Fetching All Product Reviews");
        return productReviewsRepository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProductReview(String productId) {
        LOGGER.info("Deleting Product Review for productId : {}",productId);
        ProductReviewEntity productReviewEntity = new ProductReviewEntity();
        productReviewEntity.setProductId(productId);
        productReviewsRepository.delete(productReviewEntity);
    }

    @Override
    public void updateProductReview(ProductReviewDTO productReviewDTO,String productId) {
        LOGGER.info("Updating Product Review for productId : {}",productReviewDTO.getProductId());
        ProductReviewEntity productReviewEntity = mapDtoToEntity(productReviewDTO);
        productReviewEntity.setProductId(productId);
        productReviewsRepository.save(productReviewEntity);
    }
}
