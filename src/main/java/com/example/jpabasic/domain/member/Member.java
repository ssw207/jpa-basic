package com.example.jpabasic.domain.member;

import lombok.Getter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
	private String name;

	protected Member() { // 기본 생성자가 없으면 에러 발생
	}

	public Member(String name) {
		this.name = name;
	}
}
