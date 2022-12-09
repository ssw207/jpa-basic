package com.example.jpabasic.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class OrdersTest {

    @Autowired
    private EntityManager em;

    @Test
    void addOrderItem() {
        Orders orders = new Orders();
        em.persist(orders);

        System.out.println("========================= order 쿼리 =====================");

        OrderItem orderItem = new OrderItem();
        orderItem.changeOrders(orders);
        em.persist(orderItem);

        System.out.println("========================= order item 쿼리 =====================");

        em.flush();
        em.clear();

        Orders find = em.find(Orders.class, orders.getId());
        assertThat(find.getOrderItems().size()).isGreaterThan(0);
    }
}