package com.example.jpabasic.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Orders orders;

    @MapsId("itemId")
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

    public void changeOrders(Orders orders) {

        // 기존에 이미 주문이 등록되어 있으면
        if (this.orders != null) {
            //기존 주문에서 주문상품을 삭제
            this.orders.removeOrderItems(this);
        }

        // 새로운 주문에 주문상품을 추가
        this.orders = orders;
        orders.addOrderItems(this);
    }
}
