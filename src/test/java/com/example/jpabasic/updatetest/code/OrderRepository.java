package com.example.jpabasic.updatetest.code;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

	// List<Order> findAllByOrderNo(String orderNo);
}
