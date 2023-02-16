package com.example.jpabasic.updatetest.code;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@Transactional
	public void showPayDetailAmount(String orderNo) {
		log.info(">>>>>> findAll");
		orderRepository.findAllByOrderNo(orderNo);
	}
}