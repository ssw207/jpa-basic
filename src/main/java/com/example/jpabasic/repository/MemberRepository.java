package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Member;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@RequiredArgsConstructor
public class MemberRepository {

  private final EntityManager em;

  public Member findById(Long id) {
    return em.find(Member.class, id);
  }

  public Long save(Member member) {
    em.persist(member); // member 객체 영속화됨
    return member.getId();
  }
}
