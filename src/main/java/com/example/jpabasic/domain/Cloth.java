package com.example.jpabasic.domain;

import jdk.jfr.Name;

import javax.persistence.*;

@DiscriminatorValue("C")
@Entity
public class Cloth extends Item {

    @Id
    @GeneratedValue
    @Column(name = "CLOTH_ID")
    private Long id;
}
