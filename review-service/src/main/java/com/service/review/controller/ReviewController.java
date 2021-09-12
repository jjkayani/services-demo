package com.service.review.controller;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.payload.error.FieldError;
import com.service.review.payload.error.FormValidationException;
import com.service.review.service.ProductReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = {"ReviewController"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequestMapping("/review")
public class ReviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private Validator validator;

    @GetMapping("/{product-id}")
    @ApiOperation("This api is to fetch a particular product review")
    public ResponseEntity<ProductReviewDTO> getProductReview(@PathVariable("product-id") String productId) {
        Optional<ProductReviewDTO> productReviewDTO = productReviewService.getReviewByProductId(productId);
        return productReviewDTO.isPresent() ? ResponseEntity.ok().body(productReviewDTO.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @ApiOperation("This api is to list all the product reviews")
    public ResponseEntity<List<ProductReviewDTO>> getAllProductReviews() {
        List<ProductReviewDTO> productReviewDTOList = productReviewService.getAllProductReviews();
        return !productReviewDTOList.isEmpty() ? ResponseEntity.ok().body(productReviewDTOList) : ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("This api is to create a new product review")
    public void createProductReview(@RequestBody ProductReviewDTO productReviewDTO) {
        reject(validator.validate(productReviewDTO));
        productReviewService.createReview(productReviewDTO);
    }

    @DeleteMapping("/{product-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("This api is to delete a product review")
    public void deleteProductReview(@PathVariable("product-id") String productId) {
        productReviewService.deleteProductReview(productId);
    }

    @PutMapping("/{product-id}")
    @ApiOperation("This api is to update an existing product review")
    public void updateProductReview(@RequestBody ProductReviewDTO productReviewDTO, @PathVariable("product-id") String productId) {
        productReviewService.updateProductReview(productReviewDTO, productId);
    }

    private <T extends Serializable> void reject(Set<ConstraintViolation<T>> violations) {
        List<FieldError> errors = new ArrayList<>();
        if (!violations.isEmpty()) {
            errors.addAll(violations.stream()
                    .map(this::cast)
                    .collect(Collectors.toList()));
        }
        if (!errors.isEmpty()) {
            throw new FormValidationException("Business validation failure", errors);
        }
    }

    private FieldError cast(ConstraintViolation violation) {
        String constraint = violation.getConstraintDescriptor().getAnnotation().annotationType().getName();
        return new FieldError(violation.getPropertyPath().toString(), constraint.substring(constraint.lastIndexOf('.') + 1));
    }

}
