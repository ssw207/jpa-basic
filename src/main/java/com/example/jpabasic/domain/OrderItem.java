package com.example.jpabasic.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class OrderItem {

    // auto increment 처럼 관게를 설정하면 자동으로 생성될줄  알았으나 아님. 직점 세팅해줘야함
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

    @Builder
    public OrderItem(Orders orders, Item item) {
        this.orders = orders;
        this.item = item;
        this.id = new OrderItemId(orders.getId(), item.getId());
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
        updateId();
    }

    public void changeItem(Item item) {
        this.item = item;
        updateId();
    }

    private void updateId() {
        if (orders != null && item != null) {
            this.id = new OrderItemId(orders.getId(), item.getId());
        }
    }
}
