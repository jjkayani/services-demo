package com.service.review.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_REVIEWS")
@lombok.Setter
@lombok.Getter
public class ProductReviewEntity implements Serializable {

    private static final long serialVersionUID = -1530325681864371965L;
    @Id
    private String productId;

    private double averageReviewScore;

    private long numberOfReviews;

}
