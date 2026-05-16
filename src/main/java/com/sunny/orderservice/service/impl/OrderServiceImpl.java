package com.sunny.orderservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sunny.orderservice.dto.OrderRequest;
import com.sunny.orderservice.entity.Order;
import com.sunny.orderservice.repository.OrderRepository;
import com.sunny.orderservice.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;

	@Override
	public Order createOrder(OrderRequest request) {
	Order order = Order.builder()
			.productName(request.getProductName())
			.price(request.getPrice())
			.userId(request.getUserId())
			.build();
	
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

}
