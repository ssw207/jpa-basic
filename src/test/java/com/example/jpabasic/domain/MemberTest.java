package com.example.jpabasic.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void 수정() {
        Member member = new Member();

        Orders orders = new Orders();
        orders.changeMember(member);

        assertThat(member.getOrders()).contains(orders);
    }
}