package com.example.jpabasic.updatetest.code;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders_test")
public class Order {

	// 더티체킹시는 모든필드를 equals를 호출해 같은지 비교
	@Convert(converter = PayConverter.class)
	private final List<Pay> pays = new ArrayList<>(); //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 불변은 이슈없음

	public Order(Pay pay) {
		this.pays.add(pay);
	}
}