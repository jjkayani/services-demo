package com.service.product.service.vendor;

import com.service.product.config.ServiceMeshConfig;
import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class RestClientApi {

    @InjectMocks
    private RestApiClient restApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ServiceMeshConfig serviceMeshConfig;

    @Before
    public void setup() {
        ServiceMeshConfig.HostConfig reviewConfig = new ServiceMeshConfig.HostConfig();
        reviewConfig.setAppSecret("0000000");
        reviewConfig.setBaseUri("http://review-service:0000/review");
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("get-review", "/review");
        reviewConfig.setEndpoints(endpoints);

        ServiceMeshConfig.HostConfig productConfig = new ServiceMeshConfig.HostConfig();
        productConfig.setAppSecret("0000000");
        productConfig.setBaseUri("http://product-service:0000/products");
        Map<String, String> productEndpoints = new HashMap<>();
        endpoints.put("get-product", "/product");
        productConfig.setEndpoints(productEndpoints);

        ServiceMeshConfig serviceMeshConfig = new ServiceMeshConfig();
        serviceMeshConfig.setReviewService(reviewConfig);
        serviceMeshConfig.setProductService(productConfig);

        ReflectionTestUtils.setField(this.restApiClient, "serviceMeshConfig", serviceMeshConfig);
        restApiClient.initiateHostsConfig();
    }

    @Test
    public void testGetProductReviewByProductId(){
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class),
                eq(new ParameterizedTypeReference<Void>() {
                }))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ProductReviewDTO productReviewDTO = restApiClient.getProductReviewByProductId("ABC123");
        Assert.assertNotNull(productReviewDTO);
    }

    @Test
    public void testGetProductDetails(){
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class),
                eq(new ParameterizedTypeReference<Void>() {
                }))).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ProductDetails productDetails = restApiClient.getProductDetails("ABC123");
        Assert.assertNotNull(productDetails);
    }
}
