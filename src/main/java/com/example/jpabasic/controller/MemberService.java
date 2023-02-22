package com.example.jpabasic.controller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpabasic.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void update(Long id, String name) {
		log.info("업데이트가 나가나?");
	}

	public void biz() {
		log.info("메서드가 종료되면 트렌젝션 commit -> 영속성 컨텍스트 플러싱");
	}
}
