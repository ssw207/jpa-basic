package com.example.jpabasic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "MEMBER")
public class Member extends AbstractEntity<Long> {

	@OneToMany(mappedBy = "member")
	private final List<Orders> orders = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;
	@JoinColumn(name = "TEAM_ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Team team;
	@Column(length = 10)
	private String name;
	@Column(nullable = false)
	private int age;

	@Convert(converter = Role.RoleConverter.class)
	private Role role;
	private LocalDateTime created;
	private LocalDateTime updated;

	protected Member() { // 기본 생성자가 없으면 에러 발생
	}

	public Member(String name) {
		this.name = name;
	}


	@Builder
	public Member(Long id, Team team, String name, int age, Role role, LocalDateTime created, LocalDateTime updated) {
		this.id = id;
		this.team = team;
		this.name = name;
		this.age = age;
		this.role = role;
		this.created = created;
		this.updated = updated;
	}

	@Builder
	public Member(String name, int age, Role role) {
		this.name = name;
		this.age = age;
		this.role = role;
	}

	public void add(Team team) {
		this.team = team;
		team.add(this);
	}
}
