package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
@RequiredArgsConstructor
public class OrdersRepository {

    private final EntityManager em;

    public Long save(Orders orders) {
        em.persist(orders);
        return orders.getId();
    }

    public Orders findById(Long id) {
        return em.find(Orders.class, id);
    }
}
