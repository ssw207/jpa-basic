package com.example.jpabasic.util;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.repository.MemberRepository;

@Transactional
@SpringBootTest
class DataBaseCleanUpTest {

	@Autowired
	private DataBaseCleanUp dataBaseCleanUp;

	@Autowired
	private MemberRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void cleanUp() {
		// test cleanUp method it need saved data
		Long id = repository.save(new Member("hi"));

		entityManager.flush();

		dataBaseCleanUp.cleanUp();

		entityManager.flush();
		entityManager.clear();

		assertThat(repository.findById(id)).isNull();
	}
}