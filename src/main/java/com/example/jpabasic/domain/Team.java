package com.example.jpabasic.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	@Id
	@GeneratedValue
	private Long id;

	@OrderBy("name desc")
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	private String name;

	public Team(String name) {
		this.name = name;
	}

	public void add(Member member) {
		this.members.add(member);
	}
}
