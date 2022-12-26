package com.example.jpabasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Delivery {

    @Id
    @Column(name = "ORDERS_ID")
    private Long id;

    private String name;

    public Delivery(Long id) {
        this.id = id;
    }
}
