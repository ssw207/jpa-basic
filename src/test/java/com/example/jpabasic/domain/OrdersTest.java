package com.example.jpabasic.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrdersTest {

	@Test
	void addOrderItem() {
		Orders orders = new Orders();

		OrderItem orderItem = new OrderItem();
		orders.addOrderItems(orderItem);

		assertThat(orderItem.getOrders()).isEqualTo(orders);
		assertThat(orders.getOrderItems().size()).isEqualTo(1);
	}
}