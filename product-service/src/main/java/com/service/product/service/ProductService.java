package com.service.product.service;

import com.service.product.payload.ProductDetails;

public interface ProductService {

    ProductDetails getProductByProductId(String productId);

}
