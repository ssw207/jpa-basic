package com.example.jpabasic.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.PipedReader;

@Getter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    protected OrderItem() {
    }

    @Builder
    public OrderItem(Orders orders, Item item) {
        this.orders = orders;
        this.item = item;
    }
}
