package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TeamRepository extends JpaRepository<Team, Long> {

    Page<Team> findAll(Pageable pageable);
}
