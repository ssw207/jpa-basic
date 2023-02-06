package com.example.jpabasic.controller;

import org.springframework.web.bind.annotation.*;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;

	@GetMapping("/members/{id}")
	public String findOne(@PathVariable(name = "id") Long id) {
		Member member = memberRepository.findById(id).orElseThrow();
		return member.getTeam().getName();
	}

	@PostMapping("/members")
	public Member save(@RequestBody Member member) {
		return memberRepository.save(member); // 테스트 편의를 위해 엔티티 그대로 반환
	}
}
