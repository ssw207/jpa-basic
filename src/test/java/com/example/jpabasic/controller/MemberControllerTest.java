package com.example.jpabasic.controller;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;
import com.example.jpabasic.repository.MemberRepository;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MemberControllerTest {

	@Autowired
	private EntityManager em;
	@Autowired
	private MemberRepository memberRepository;


	@Test
	void 회원_저장() {
		Team team = Team.builder().name("team").build();
		Member member = Member.builder().name("name").team(team).build();

		ExtractableResponse<Response> response = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(member)
				.post("/members")
				.then().log().all()
				.extract();

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void 회원_조회() {
		Team team = Team.builder().name("team").build();
		Member member = Member.builder().name("name").team(team).build();

		ExtractableResponse<Response> saveResponse = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(member)
				.post("/members")
				.then().log().all()
				.extract();

		long id = saveResponse.jsonPath().getLong("id");

		// when
		ExtractableResponse<Response> response = given()
			.pathParam("id", id)
			.get("/members/{id}")
			.then().log().all()
			.extract();

		// then
		assertThat(response.statusCode()).isEqualTo(200);
		assertThat(response.asString()).isEqualTo(team.getName());
	}
}