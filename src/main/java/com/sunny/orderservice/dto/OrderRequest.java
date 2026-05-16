package com.sunny.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank
    private String productName;

    @NotNull
    private Double price;

    @NotNull
    private Long userId;
}