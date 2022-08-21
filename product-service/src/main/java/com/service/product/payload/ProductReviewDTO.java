package com.service.product.payload;

import java.io.Serializable;

@lombok.Getter
@lombok.Setter
public class ProductReviewDTO implements Serializable {
    private static final long serialVersionUID = -9101367281436093704L;

    private String productId;

    private double averageReviewScore;

    private long numberOfReviews;
}
