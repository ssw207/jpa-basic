package com.example.jpabasic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

	@OneToMany(mappedBy = "member")
	private final List<Orders> orders = new ArrayList<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private Long id;
	@JoinColumn(name = "TEAM_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private Team team;
	@Column(length = 10)
	private String name;
	@Column(nullable = false)
	private int age;

	@Convert(converter = Role.RoleConverter.class)
	private Role role;

	private CodeYn active;

	public Member(String name) {
		this.name = name;
	}

	public void add(Team team) {
		this.team = team;
		team.add(this);
	}

	public void changeName(String name) {
		this.name = name;
	}
}
