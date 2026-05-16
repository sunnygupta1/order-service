package com.sunny.orderservice.service;

import java.util.List;

import com.sunny.orderservice.dto.OrderRequest;
import com.sunny.orderservice.entity.Order;

public interface OrderService {
	
	Order createOrder(OrderRequest request);
	
	List<Order> getAllOrder();
}
