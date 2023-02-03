package com.example.jpabasic;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jpabasic.domain.Item;
import com.example.jpabasic.repository.ItemRepository;

@DataJpaTest
public class SpringDataJpaTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void name() {
		// spring data jpa는 단일 조회결과가 없으면 결과를 null을 리턴한다
		Item item = itemRepository.findByName("name");
		assertThat(item).isNull();

		// Optional을 지원한다 (spring data jpa 2.0부터)
		Optional<Item> name = itemRepository.findOptionalByName("name");
		assertThat(name).isEqualTo(Optional.empty());

		// spring data jpa는 다중 조회결과가 없으면 빈 리스트를 리턴한다. 컬랙션을 jqpl 파라미터로 이용시 in으로 표시한다
		List<Item> items = itemRepository.findAllByNames(List.of("name1", "name2"));
		assertThat(items).isNotNull();
	}
}
