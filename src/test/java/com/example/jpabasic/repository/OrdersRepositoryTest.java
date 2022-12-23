package com.example.jpabasic.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.OrderItem;
import com.example.jpabasic.domain.Orders;
import com.example.jpabasic.domain.Role;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class OrdersRepositoryTest {

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private EntityManager em;

  @Test
  void save() {
    Member member = Member.builder()
        .age(10)
        .name("이름")
        .role(Role.ADMIN)
        .build();
    memberRepository.save(member);

    Orders orders = new Orders(member);
    Long id = ordersRepository.save(orders);
    assertThat(id).isNotNull();

    Orders find = ordersRepository.findById(id);
    assertThat(find.getMember().getAge()).isEqualTo(10);
  }

  @Transactional
  @Test
  void findOrderItems() {
    Member member = Member.builder()
        .age(10)
        .name("이름")
        .role(Role.ADMIN)
        .build();
    memberRepository.save(member);

    Orders orders = new Orders(member);
    Long id = ordersRepository.save(orders);

    OrderItem orderItem = OrderItem.builder()
        .orders(orders)
        .build();

    em.persist(orderItem);

    // 이거 주석치면 왜 테스트를 실패할까?
    em.flush();
    em.clear();

    Orders findOrders = ordersRepository.findById(id);
    List<OrderItem> orderItems = findOrders.getOrderItems();

    assertThat(orderItems).hasSize(1);

    em.flush();
  }
}