package com.service.review.persistence.repository;

import com.service.review.persistence.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductReviewsRepository extends JpaRepository<ProductReviewEntity, Long> {

    Optional<ProductReviewEntity> findByProductId(String productId);
}
