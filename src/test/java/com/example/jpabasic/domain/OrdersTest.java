package com.example.jpabasic.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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