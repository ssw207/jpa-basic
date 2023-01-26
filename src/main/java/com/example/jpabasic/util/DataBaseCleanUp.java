package com.example.jpabasic.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataBaseCleanUp {

	private EntityManager entityManager;
	private List<String> tableNames;

	public void cleanUp() {
		// 쓰기 지연 저장소에 남은 SQL 마저 수행
		entityManager.flush();
		
		// 연관 관계 매핑 테이블의 참조 무결성을 해제
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
		entityManager.createNativeQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}

	@PostConstruct
	public void init() {
		tableNames = entityManager.getMetamodel()
			.getEntities()
			.stream()
			.filter(isEntity())
			.map(EntityType::getName)
			.peek(System.out::println)
			.collect(Collectors.toList());
	}

	private Predicate<EntityType<?>> isEntity() {
		return e -> e.getJavaType().getAnnotation(Entity.class) != null;
	}
}
