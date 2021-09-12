package com.service.review.service;

import com.service.review.payload.ProductReviewDTO;
import com.service.review.persistence.entity.ProductReviewEntity;
import com.service.review.persistence.repository.ProductReviewsRepository;
import com.service.review.service.impl.ProductReviewServiceImpl;
import com.service.review.util.PayloadHelper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductReviewService {

    @InjectMocks
    private ProductReviewServiceImpl productReviewService;

    @Mock
    private ProductReviewsRepository productReviewsRepository;

    private final String PRODUCT_ID = "ABC123";

    @Test
    public void testCreateReview(){
        ProductReviewDTO productReviewDTO = PayloadHelper.mockProductReviewDTO();
        ArgumentCaptor<ProductReviewEntity> argumentCaptor = ArgumentCaptor.forClass(ProductReviewEntity.class);
        productReviewService.createReview(productReviewDTO);
        verify(productReviewsRepository,times(1)).save(argumentCaptor.capture());
        assertEquals(productReviewDTO.getProductId(),argumentCaptor.getValue().getProductId());
        assertEquals(productReviewDTO.getAverageReviewScore(),argumentCaptor.getValue().getAverageReviewScore());
        assertEquals(productReviewDTO.getNumberOfReviews(),argumentCaptor.getValue().getNumberOfReviews());

    }

    @Test
    public void testGetReviewByProductId(){
        ProductReviewEntity productReviewEntity = PayloadHelper.mockProductReviewEntity();
        when(productReviewsRepository.findByProductId(PRODUCT_ID)).thenReturn(Optional.of(productReviewEntity));
        ProductReviewDTO productReviewDTO = productReviewService.getReviewByProductId(PRODUCT_ID).get();
        assertEquals(productReviewEntity.getProductId(),productReviewDTO.getProductId());
        assertEquals(productReviewEntity.getNumberOfReviews(),productReviewDTO.getNumberOfReviews());
        assertEquals(productReviewEntity.getAverageReviewScore(),productReviewDTO.getAverageReviewScore());
    }

    @Test
    public void testGetAllReviews(){
        ProductReviewEntity productReviewEntity = PayloadHelper.mockProductReviewEntity();
        when(productReviewsRepository.findAll()).thenReturn(Arrays.asList(productReviewEntity));
        ProductReviewDTO productReviewDTO = productReviewService.getAllProductReviews().get(0);
        assertEquals(productReviewEntity.getProductId(),productReviewDTO.getProductId());
        assertEquals(productReviewEntity.getNumberOfReviews(),productReviewDTO.getNumberOfReviews());
        assertEquals(productReviewEntity.getAverageReviewScore(),productReviewDTO.getAverageReviewScore());
    }

    @Test
    public void testDeleteProductReview(){
        productReviewService.deleteProductReview(PRODUCT_ID);
        verify(productReviewsRepository,times(1)).delete(any());
    }

    @Test
    public void testUpdateProductReview(){
        ProductReviewDTO productReviewDTO = PayloadHelper.mockProductReviewDTO();
        ArgumentCaptor<ProductReviewEntity> argumentCaptor = ArgumentCaptor.forClass(ProductReviewEntity.class);
        productReviewService.updateProductReview(productReviewDTO, PRODUCT_ID);
        verify(productReviewsRepository,times(1)).save(argumentCaptor.capture());
        assertEquals(PRODUCT_ID,argumentCaptor.getValue().getProductId());
        assertEquals(productReviewDTO.getAverageReviewScore(),argumentCaptor.getValue().getAverageReviewScore());
        assertEquals(productReviewDTO.getNumberOfReviews(),argumentCaptor.getValue().getNumberOfReviews());
    }

}
