package com.example.jpabasic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpabasic.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	Item findByName(String gg);

	@Query("select i from Item i where i.name in :names")
	List<Item> findAllByNames(@Param("names") List<String> names);

	Optional<Item> findOptionalByName(String name);
}
