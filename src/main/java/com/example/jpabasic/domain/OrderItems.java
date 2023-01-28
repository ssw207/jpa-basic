package com.example.jpabasic.domain;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class OrderItems extends AbstractList<OrderItem> {

	@OneToMany(mappedBy = "orders") // OrderItem Entity의 연관된 필드명 지정
	private final List<OrderItem> orderItems = new ArrayList<>();

	@Override
	public OrderItem get(int index) {
		return orderItems.get(index);
	}

	@Override
	public int size() {
		return orderItems.size();
	}

	@Override
	public boolean add(OrderItem orderItem) {
		return orderItems.add(orderItem);
	}

	@Override
	public OrderItem remove(int index) {
		return orderItems.remove(index);
	}
}
