package com.example.jpabasic.repository;

import com.example.jpabasic.domain.Member;
import com.example.jpabasic.domain.Orders;
import com.example.jpabasic.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder()
                .age(10)
                .name("이름")
                .role(Role.ADMIN)
                .build();
        memberRepository.save(member);

        Orders orders = new Orders(member);
        Long id = ordersRepository.save(orders);
        assertThat(id).isNotNull();

        Orders find= ordersRepository.findById(id);
        assertThat(find.getMember().getAge()).isEqualTo(10);
    }
}