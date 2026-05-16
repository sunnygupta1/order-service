package com.sunny.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import com.sunny.orderservice.repository.OrderRepository;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class OrderServiceApplicationTests {

	@MockBean
	private OrderRepository orderRepository;

	@Test
	void contextLoads() {
	}

}
