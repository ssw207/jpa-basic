package com.example.jpabasic.domain;

import javax.persistence.*;

@DiscriminatorValue("B")
@Entity
public class Book extends Item{

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;
}
