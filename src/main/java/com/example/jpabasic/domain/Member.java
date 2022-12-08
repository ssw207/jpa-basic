package com.example.jpabasic.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @JoinColumn(name = "TEAM_ID")
    @OneToOne
    private Team team;

    @Column(length = 10)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING) // 저장시 ENUM 이름을 사용한다
    private Role role;
    private LocalDateTime created;
    private LocalDateTime updated;

    protected Member() { // 기본 생성자가 없으면 에러 발생
    }

    public Member(String name) {
        this.name = name;
    }

    @Builder
    public Member(String name, int age, Role role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public void add(Team team) {
        this.team = team;
        team.add(this);
    }
}
