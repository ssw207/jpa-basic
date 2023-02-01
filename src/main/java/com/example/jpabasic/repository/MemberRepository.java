package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
