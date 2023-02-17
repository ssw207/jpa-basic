package com.example.jpabasic.updatetest;

import com.example.jpabasic.updatetest.code.Order;
import com.example.jpabasic.updatetest.code.OrderRepository;
import com.example.jpabasic.updatetest.code.Pay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        orderRepository.save(new Order(new Pay("code", 10L)));
        em.flush();
        em.clear();
        System.out.println("================== 백데이터 추가 완료 ===================");
    }

    @Test
    void name() {

        orderRepository.findAll();
        em.flush();
        em.clear();
    }
}