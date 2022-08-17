package com.codility.rest;

import com.codility.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return  repository.findAll().stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }


    // @PostMapping
    // public void createTask(ProductReviewDTO productReviewDTO) {
    //     reject(validator.validate(productReviewDTO));
    //     productReviewService.createReview(productReviewDTO);
    // }


    TaskDto mapEntityToDto(Task task) {
        TaskDto taskDto = new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getStatus());
        return taskDto;
    }

    //  ProductReviewEntity mapDtoToEntity(ProductReviewDTO productReviewDTO) {
    //     ProductReviewEntity productReviewEntity = new ProductReviewEntity();
    //     productReviewEntity.setProductId(productReviewDTO.getProductId());
    //     productReviewEntity.setAverageReviewScore(productReviewDTO.getAverageReviewScore());
    //     productReviewEntity.setNumberOfReviews(productReviewDTO.getNumberOfReviews());
    //     return productReviewEntity;
    // }

}
