package com.example.jpabasic.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpabasic.domain.Member;

import lombok.RequiredArgsConstructor;

@Transactional
@Repository
@RequiredArgsConstructor
public class MemberOldRepository {

	private final EntityManager em;

	public Member findById(Long id) {
		return em.find(Member.class, id);
	}

	public Long save(Member member) {
		em.persist(member); // member 객체 영속화됨
		return member.getId();
	}
}
