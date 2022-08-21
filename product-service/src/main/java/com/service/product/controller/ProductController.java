package com.service.product.controller;

import com.service.product.payload.ProductDetails;
import com.service.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = {"ProductController"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;

    @GetMapping(value = "/{product-id}")
    @ApiOperation("This api is to fetch a particular product details")
    public ResponseEntity<ProductDetails> getProductDetails(@PathVariable("product-id") String productId){
        ProductDetails productDetails = productService.getProductByProductId(productId);
        return Objects.nonNull(productDetails) ? ResponseEntity.ok().body(productDetails) : ResponseEntity.notFound().build();
    }
}
