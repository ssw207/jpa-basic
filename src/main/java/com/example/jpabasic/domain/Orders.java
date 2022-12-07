package com.example.jpabasic.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.PipedReader;

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

    protected Orders() {
    }

    public Orders(Member member) {
        this.member = member;
    }
}
