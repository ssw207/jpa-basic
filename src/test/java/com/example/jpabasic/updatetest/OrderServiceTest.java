package com.example.jpabasic.updatetest;

import java.time.Period;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpabasic.updatetest.code.Member;
import com.example.jpabasic.updatetest.code.Order;
import com.example.jpabasic.updatetest.code.OrderRepository;
import com.example.jpabasic.updatetest.code.OrderService;
import com.example.jpabasic.updatetest.code.Pay;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EntityManager em;

	@BeforeEach
	void setUp() {
		Order order = new Order(
			"orderNo",
			Period.ZERO,
			new Member("a", "b"),
			new Pay("code", 10L, new Pay.PayDetail("type", 1L)));

		orderRepository.save(order);
	}

	@Test
	void name() {
		orderService.showPayDetailAmount("orderNo");
	}
}