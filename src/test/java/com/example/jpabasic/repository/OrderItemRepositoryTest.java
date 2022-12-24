package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Book;
import com.example.jpabasic.domain.Item;
import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.OrderItem;
import com.example.jpabasic.domain.Orders;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private EntityManager em;

    @Test
    void save() {
        Member member = new Member("이름");
        em.persist(member);

        Orders orders = new Orders();
        orders.changeMember(member);
        em.persist(orders);

        Item item = new Book();
        em.persist(item);

        OrderItem orderItem = new OrderItem();
        orderItem.changeOrders(orders);
        orderItem.changeItem(item);
        orderItemRepository.save(orderItem);

        em.flush();
    }
}