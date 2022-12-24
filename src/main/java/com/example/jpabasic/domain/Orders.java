package com.example.jpabasic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;

@Getter
@Entity
public class Orders {

	@OneToMany(mappedBy = "orders") // OrderItem Entity의 연관된 필드명 지정
	private final List<OrderItem> orderItems = new ArrayList<>();
	@Id
	@GeneratedValue
	@Column(name = "ORDERS_ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEMBER_ID") // ORDERS 테이블의 MEMBER FK 컬럼 이름
	private Member member;

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
}
