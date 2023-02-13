package com.example.jpabasic.domain;

import com.example.jpabasic.repository.ParentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ParentTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ParentRepository parentRepository;

    @Test
    void N_플러스_1_문제() {
        for (int i = 0; i < 10; i++) {
            em.persist(new Child(new Parent()));
        }

//
//        List<Parent> list = em.createQuery("select p from Parent p", Parent.class)
//                .getResultList();
//
//        System.out.println("리스트 조회 완료");
//
//        for (Parent parent : list) {
//            System.out.println("parent = " + parent.getChildList());
//        }
        System.out.println("종료.");
        em.flush();
        em.clear();
    }
}