package com.example.jpabasic.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "DTYPE")
public class Item {

    @Id
    @GeneratedValue
    @Column(name ="ITEM_ID")
    private Long id;

    @Column(name = "DTYPE")
    private String dtype;
}
