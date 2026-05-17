package com.sunny.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	
	private Long orderId;
	private String productName;
	private double price;
	private UserResponse user;
	

}
