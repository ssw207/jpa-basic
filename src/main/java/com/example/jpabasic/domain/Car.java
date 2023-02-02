package com.example.jpabasic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Setter
@Getter
@Entity
public class Car extends BaseEntity {

    @Id
    private Long id;
}
