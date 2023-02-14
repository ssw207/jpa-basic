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

        List<Parent> list = em.createQuery("select p from Parent p", Parent.class)
                .getResultList();

        System.out.println("리스트 조회 완료");

        for (Parent parent : list) {
            System.out.println("parent = " + parent.getChildList());
        }
        System.out.println("종료.");
        em.flush();
        em.clear();
    }

    @Transactional
    @Test
    void 업데이트시_쿼리() {
        Parent parent = new Parent();
        em.persist(parent);

        System.out.println("영속화후 쿼리가 실행됐을까?");

        // jpql 실행전 쿼리가 나감
        List<Parent> selectPFromParentP = em.createQuery("select p from Parent p", Parent.class).getResultList();

        System.out.println("jpql 업데이트전 쿼리가 실행되나?");

        // 업데이트가 곧바로 나간다.
        int i = em.createQuery("update Parent p set name = 'kk'")
                .executeUpdate();

        System.out.println("i = " + i);
    }

    @Transactional
    @Test
    void 업데이트시_쿼리호출_테스트() {

        System.out.println("시작");
        Parent parent = new Parent();
        em.persist(parent); // 쓰기지연이 일어나므로 실행안됨
        Long id = parent.getId();

        System.out.println("id = " + id);

        em.flush(); // 쿼리실행
        em.clear();

        Parent parent1 = em.find(Parent.class, 1L); // select는 바로 실행
        parent1.setName("gg");

        System.out.println("jpql 실행전 이므로 flush가 호출되어 parent 업데이트가 나간다.");

        em.createQuery("update Parent p set p.name ='변경'") // jqpl는 바로실행
                .executeUpdate();

        System.out.println("jpql은 바로나간다");

        em.flush();
        em.clear();
    }
}