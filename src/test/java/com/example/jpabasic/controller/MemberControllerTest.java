package com.example.jpabasic.controller;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;
import com.example.jpabasic.repository.MemberRepository;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MemberControllerTest {

	@Autowired
	EntityManager em;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void 회원_조회() {
		// given
		Team team = Team.builder().name("team").build();
		em.persist(team);
		Member name = memberRepository.save(Member.builder().name("name").team(team).build());

		// when
		ExtractableResponse<Response> response = given()
			.pathParam("id", name.getId())
			.get("/members/{id}")
			.then().log().all()
			.extract();

		// then
		assertThat(response.statusCode()).isEqualTo(200);
		assertThat(response.asString()).isEqualTo(team.getName());
	}
}