package com.example.jpabasic.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
// 2개이상 필드를 유니크조건으로 걸어야할때 UniqueConstraint 사용한다
@Table(name = "MY_MEMBER", uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"})})
public class Member {

    /*
        auto 인경우 DB에 따라 자동으로 생성전략이 정해진다, MYSQL DB는 IDENTITY 속성 지정시 AUTO_INCREMENT 사용한다
        영석송 컨텍스트에 등록하려면 ID값이 필수인다. IDENTITY 속성을 사용하면 ID값을 DB에서 조회한뒤 영속화가 가능하기 때문에 persist() 호출시 바로 쿼리가 나간다.
        기본키는 변하면 안되기 때문에 대체키 사용을 권고 (Long타입 자동생성)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 10)
    private String name;

    @Column(nullable = false) // 기본적으로 nullable true이다. 원시타입은 null을 허용하지 않으므로 nullable flase처리를 명시적으로 해야함
    private int age;

    @Enumerated(EnumType.STRING) // 저장시 ENUM 이름을 사용한다
    private Role role;
    private LocalDateTime created; // LocalDateTime은 @Tempotal 어노테이션이 없어도 된다
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
}
