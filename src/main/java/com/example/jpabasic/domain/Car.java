package com.example.jpabasic.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Car extends BaseEntity {

	@Id
	private Long id;

	private String name;

	@Version // 수정할떄마다 자동으로 증가한다.
	private int version;

	public Car(long id) {
		this.id = id;
	}
}
