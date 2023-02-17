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
	
	@Convert(converter = PayConverter.class)
	private Pay pays; // DB에 저장시 컨버터로 Strign json 형태로 저장하고 영속화시 컨버터로 객체를 생성함
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 불변은 이슈없음

	public Order(Pay pays) {
		this.pays = pays;
	}
}