package com.example.jpabasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.dto.MemberResponse;
import com.example.jpabasic.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;
	private final MemberService memberService;

	@GetMapping("/members/{id}")
	public MemberResponse findOne(@PathVariable(name = "id") Long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		return MemberResponse.from(member);
	}

	@PutMapping("/members/{id}")
	public void update(@PathVariable(name = "id") Long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		memberService.update(id, "test");
	}

	@PostMapping("/members")
	public Member save(@RequestBody Member member) {
		return memberRepository.save(member); // 테스트 편의를 위해 엔티티 그대로 반환
	}

	@GetMapping("/members/osiv-exception/{id}")
	public void osivException(@PathVariable("id") Long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		member.changeName("XXX"); // 마스킹처리

		memberService.biz(); // 비즈니스 로직 실행시 트렌젝션 종료시점에 업데이트 쿼리실행됨
	}
}
