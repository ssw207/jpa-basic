package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository repository;

    @Transactional
    @Test
    public void test() {

        repository.save(new Team("B"));
        repository.save(new Team("A"));
        repository.save(new Team("C"));

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name"));
        Page<Team> list = repository.findAll(pageRequest);
        Page<TeamResponse> list2 = list.map(TeamResponse::of);

        assertThat(list2).hasSize(3);
        assertThat(list2.getContent().get(0).getName()).isEqualTo("C");
    }
}