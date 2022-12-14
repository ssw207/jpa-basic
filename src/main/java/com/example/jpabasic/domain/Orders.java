package com.example.jpabasic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Orders {

	@Id
	@GeneratedValue
	@Column(name = "ORDERS_ID")
	private Long id;

	@OneToMany(mappedBy = "orders") // OrderItem Entity의 연관된 필드명 지정
	private final List<OrderItem> orderItems = new ArrayList<>();


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID") // ORDERS 테이블의 MEMBER FK 컬럼 이름
	private Member member;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "ORDERS_ID")
	private Delivery delivery;

	public Orders() {
	}

	public Orders(Member member) {
		this.member = member;
	}

	public void addOrderItems(OrderItem orderItem) {
		if (orderItems.contains(orderItem)) {
			return;
		}

		orderItems.add(orderItem);
	}

	public void removeOrderItems(OrderItem orderItem) {
		orderItems.remove(orderItem);
	}

	public void changeMember(Member member) {
		if (this.member != null) {
			this.member.getOrders().remove(this);
		}
		member.getOrders().add(this);
		this.member = member;
	}

	public void add(Delivery delivery) {
		this.delivery = delivery;
	}
}
