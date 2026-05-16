package com.sunny.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunny.orderservice.dto.OrderRequest;
import com.sunny.orderservice.entity.Order;
import com.sunny.orderservice.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class Controller {
	
	private final OrderService orderService;
	
	@PostMapping("/createorder")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
		
		Order order = orderService.createOrder(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllorder(){
		return ResponseEntity.ok(orderService.getAllOrder());
	}
	
	

}
