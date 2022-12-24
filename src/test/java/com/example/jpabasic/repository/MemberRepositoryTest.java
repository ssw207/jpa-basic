package com.example.jpabasic.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Role;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	@Test
	void save() {
		// 트렌젝션 시작
		Member member = Member.builder()
			.age(10)
			.name("이름")
			.role(Role.USER)
			.build();

		Long id = memberRepository.save(member); // 1) 영속화

		Member find = memberRepository.findById(id); // 2) 영속성 컨텍스트에서 캐시된 객체를 리턴. select 쿼리가 안나간다

		assertThat(member).isEqualTo(find);
		//트렌젝션 종료 - 쿼리 실행
	}

	@Test
	void save2() {
		Member member = Member.builder()
			.age(10)
			.name("이름")
			.role(Role.USER)
			.build();

		// 트렌젝션 1시작
		Long id = memberRepository.save(member);
		// 트렌젝션 1종료 -> 쿼리실행

		// 트렌젝션 2시작 - 신규 영속성 컨텍스트실행
		Member find = memberRepository.findById(id); // 영속화된 엔티티가 없으므로 DB에서 조회
		// 트렌젝션 2종료 -> 쿼리실행

		assertThat(member).isNotEqualTo(find); // 1,2는 서로다른 영속성 컨텍스트이므로 같지 않다.
	}
}