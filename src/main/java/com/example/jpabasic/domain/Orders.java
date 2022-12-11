package com.example.jpabasic.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // ORDERS 테이블의 MEMBER FK 컬럼 이름
    private Member member;

    @OneToMany(mappedBy = "orders") // OrderItem Entity의 연관된 필드명 지정
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Orders() {
    }

    public Orders(Member member) {
        this.member = member;
    }

    public void addOrderItems(OrderItem orderItem) {
        if (orderItems.contains(orderItem)) {
            return;
        }

        orderItems.add(orderItem);
        orderItem.changeOrders(this);
    }

    public void removeOrderItems(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public void changeMember(Member member) {
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        member.getOrders().add(this);
        this.member = member;
    }
}
