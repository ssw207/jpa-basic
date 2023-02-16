package com.example.jpabasic.updatetest.code;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Embedded;
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

	@Convert(converter = PayConverter.class)
	private final List<Pay> pays = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderNo;
	@Embedded
	private Member member;
	@Convert(converter = PeriodStringConverter.class)
	private Period period;

	public Order(String orderNo, Period period, Member member, Pay pay) {
		this.orderNo = orderNo;
		this.period = period;
		this.member = member;
		this.pays.add(pay);
	}
}