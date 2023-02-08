package com.example.jpabasic.domain;

import com.example.jpabasic.repository.CarRepository;
import com.example.jpabasic.service.CarService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

@SpringBootTest
class LockTest {

    @Autowired
    private CarService service;

    @Autowired
    private CarRepository repository;

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void name() {
        // Tx1 시작
        // Car 엔티티 저장
        service.save(0L, "최초");

        // 엔티티 조회 - 낙관적 락 사용
        Car find = repository.findLockById(0L);

        // Tx2 에서 id가 0L인 car 엔티티 업데이트 -> version 0 -> 1
        service.update(0L, "Tx1변경");
        
        find.setName("Tx2변경");

        // Tx1 flush 시점에 업데이트 쿼리실행. 조회시점의 버전은 0인데 업데이트 시점의 버전은 1이므로 에러발생
        Assertions.assertThatThrownBy(() -> em.flush()).isInstanceOf(OptimisticLockException.class);
    }
}