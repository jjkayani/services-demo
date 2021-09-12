package com.service.review.payload;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@lombok.Getter
@lombok.Setter
@ApiModel(description = "Model representing the ReferralConfig Code response")
public class ProductReviewDTO implements Serializable {
    private static final long serialVersionUID = -9101367281436093704L;

    @NotEmpty
    private String productId;

    @Positive
    private double averageReviewScore;

    @PositiveOrZero
    private long numberOfReviews;
}
