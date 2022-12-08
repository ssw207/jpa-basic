package com.example.jpabasic.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "team") // Member Entity의 team 필드를 통해 접근
    private Member member;

    public void add(Member member) {
        this.member = member;
    }
}
