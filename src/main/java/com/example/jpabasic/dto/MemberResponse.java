package com.example.jpabasic.dto;

import com.example.jpabasic.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

	private String name;

	public static MemberResponse from(Member member) {
		return new MemberResponse(member.getName());
	}
}
