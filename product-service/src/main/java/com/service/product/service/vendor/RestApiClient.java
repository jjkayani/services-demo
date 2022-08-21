package com.service.product.service.vendor;

import com.service.product.common.Constants;
import com.service.product.config.ServiceMeshConfig;
import com.service.product.payload.ProductDetails;
import com.service.product.payload.ProductReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceMeshConfig serviceMeshConfig;

    private ServiceMeshConfig.HostConfig reviewHostConfig = null;

    private ServiceMeshConfig.HostConfig productHostConfig = null;


    @PostConstruct
    public void initiateHostsConfig() {
        reviewHostConfig = serviceMeshConfig.getReviewService();
        productHostConfig = serviceMeshConfig.getProductService();
    }

    public ProductReviewDTO getProductReviewByProductId(String productId) {
        ProductReviewDTO productReviewDTO = null;
        try {

            final HttpHeaders httpAuthHeader = new HttpHeaders();
            httpAuthHeader.add(Constants.APPLICATION_SECRET, reviewHostConfig.getAppSecret());
            final Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("product-id", productId);
            String targetURI = reviewHostConfig.getBaseUri()
                    + UriComponentsBuilder.fromPath(reviewHostConfig.getEndpoints().get("get-review")).buildAndExpand(uriVariables).toUriString();
            LOGGER.info("Making Review-Service Call: {}", targetURI);
            ResponseEntity<ProductReviewDTO> response = restTemplate.exchange(targetURI, HttpMethod.GET, new HttpEntity<>(uriVariables, httpAuthHeader),
                    new ParameterizedTypeReference<ProductReviewDTO>() {
                    });
            productReviewDTO = response.getBody();
        } catch (Exception ex) {
            LOGGER.error("Error occurred while consuming review-service: {}", ex);
        }
        return productReviewDTO;
    }

    public ProductDetails getProductDetails(String productId) {
        final Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("product-id", productId);
        String targetURI = productHostConfig.getBaseUri()
                + UriComponentsBuilder.fromPath(productHostConfig.getEndpoints().get("get-product")).buildAndExpand(uriVariables).toUriString();
        LOGGER.info("Making call to adidas products api: {}", targetURI);
        ResponseEntity<ProductDetails> response = restTemplate.exchange(targetURI, HttpMethod.GET, new HttpEntity<>(uriVariables, setApiHeaders()),
                new ParameterizedTypeReference<ProductDetails>() {
                });
        return response.getBody();
    }

    private HttpHeaders setApiHeaders() {
        HttpHeaders headerParams = new HttpHeaders();
        headerParams.setContentType(MediaType.APPLICATION_JSON);
        headerParams.add("User-Agent","PostmanRuntime/7.28.2");
        return headerParams;
    }
}
