package com.example.jpabasic.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpabasic.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Map<Long, Member> findAllByName();
}
