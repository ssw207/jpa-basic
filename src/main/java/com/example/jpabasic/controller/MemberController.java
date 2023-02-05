package com.example.jpabasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
