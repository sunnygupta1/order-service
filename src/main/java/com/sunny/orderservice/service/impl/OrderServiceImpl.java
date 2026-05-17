package com.sunny.orderservice.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sunny.orderservice.dto.OrderRequest;
import com.sunny.orderservice.dto.OrderResponse;
import com.sunny.orderservice.dto.UserResponse;
import com.sunny.orderservice.entity.Order;
import com.sunny.orderservice.feign.UserFeignClient;
import com.sunny.orderservice.repository.OrderRepository;
import com.sunny.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final UserFeignClient userFeignClient;

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
	
	@Override
	@Retry(name = "userServiceRetry")
	@CircuitBreaker(name = "userServiceBreaker", fallbackMethod = "userFallback")
	public OrderResponse getOrderById(Long id) {
		
		System.out.println("Calling user-service...");

	    Order order = orderRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id " + id));

	    // call user-service using feign clients
	    UserResponse user;
	        user = userFeignClient.getUserById(order.getUserId());
	   

	    return OrderResponse.builder()
	            .orderId(order.getId())
	            .productName(order.getProductName())
	            .price(order.getPrice())
	            .user(user)
	            .build();
	}
	
	public OrderResponse userFallback(Long id, Throwable ex) {

	    return OrderResponse.builder()
	            .orderId(id)
	            .productName("Unavailable")
	            .price(0)

	            .user(null)

	            .build();
	}

}
