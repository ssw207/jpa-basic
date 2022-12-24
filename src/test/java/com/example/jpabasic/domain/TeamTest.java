package com.example.jpabasic.domain;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TeamTest {

	@Autowired
	private EntityManager em;

	@Transactional
	@Test
	void save() {
		Team team = new Team();
		em.persist(team);

		Member member = Member.builder()
			.name("abc")
			.build();

		member.add(team);
		em.persist(member);

		em.flush();
		em.clear();

		Member find = em.find(Member.class, member.getId());
		assertThat(find.getTeam()).isNotNull();

		em.flush();
	}
}