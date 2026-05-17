package com.sunny.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunny.orderservice.dto.UserResponse;

@FeignClient(name = "USER-SERVICE") //mean call service registered in Eureka NOT: localhost:8080 like webclient or resttemplate
public interface UserFeignClient {

    @GetMapping("/users/getuserbyid")
    UserResponse getUserById(
            @RequestParam Long id);
}
