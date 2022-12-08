package com.example.jpabasic.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.PipedReader;
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
}
