package com.example.jpabasic.repository;

import com.example.jpabasic.domain.OrderItem;
import com.example.jpabasic.domain.OrderItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
@RequiredArgsConstructor
public class OrderItemRepository {

    private final EntityManager em;

    public OrderItemId save(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem.getId();
    }

}
