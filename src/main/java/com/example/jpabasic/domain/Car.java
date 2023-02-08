package com.example.jpabasic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;


@Setter
@Getter
@Entity
public class Car extends BaseEntity {

    @Id
    private Long id;

    private String name;

    @Version // 수정할떄마다 자동으로 증가한다.
    private int version;
}
