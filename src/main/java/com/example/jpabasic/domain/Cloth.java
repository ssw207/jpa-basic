package com.example.jpabasic.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@DiscriminatorValue("C")
@Entity
public class Cloth extends Item {

    @Id
    @GeneratedValue
    @Column(name = "CLOTH_ID")
    private Long id;
}
