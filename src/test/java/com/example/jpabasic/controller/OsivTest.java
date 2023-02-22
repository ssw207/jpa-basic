package com.example.jpabasic.controller;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Team;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OsivTest {

	@Test
	void Osiv사용시_잘못된_업데이트가_나가는_케이스() {
		Team team = Team.builder().name("team").build();
		Member member = Member.builder().name("name").team(team).build();

		ExtractableResponse<Response> saveResponse = given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(member)
			.post("/members")
			.then().log().all()
			.extract();

		long id = saveResponse.jsonPath().getLong("id");

		given()
			.pathParam("id", id).log().all()
			.get("/members/osiv-exception/{id}")
			.then().log().all()
			.statusCode(HttpStatus.OK.value());

		// when
		ExtractableResponse<Response> response = given()
			.pathParam("id", id).log().all()
			.get("/members/{id}")
			.then().log().all()
			.extract();

		// then
		assertThat(response.jsonPath().getString("name")).isEqualTo("XXX");
	}
}