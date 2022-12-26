package com.example.jpabasic.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.jpabasic.domain.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
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

	@Test
	void saveDelivery() {
		Orders orders = new Orders();
		em.persist(orders);

		Delivery delivery = new Delivery((orders.getId()));
		delivery.setName("이름");
		orders.add(delivery);
		em.persist(orders);

		em.flush();
		em.clear();

		log.info("========================== 저장 =============================");

		Orders orders1 = em.find(Orders.class, orders.getId());
		log.info("orders조회 완료");
		log.info(orders1.getDelivery().getName());

		em.flush();
	}
}